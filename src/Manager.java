import java.applet.Applet;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Matcher;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class Manager{
	public static MainWindow main;
	public static ArrayList<Post> posts = new ArrayList<Post>();
	public static HashSet<Post> deletedPosts = new HashSet<Post>();
	public static HashMap<Post, Post> corrections = new HashMap<Post, Post>();
	public static Post correctedPost = null;
	public static ArrayList<Vote> votes = new ArrayList<Vote>();
	public static ArrayList<User> listedUsers = new ArrayList<User>();
	public static HashSet<User> votedUsers = new HashSet<User>();
	public static HashSet<String> voted = new HashSet<String>();
	public static String undoStore;
	public static HashSet<Post> undoDeletes = new HashSet<Post>();
	public static HashMap<User, Votals> votalsMap = new HashMap<User, Votals>();
	public static String usernameOutput = "~~List of users and their aliases~~";
	public static String warning;
	public static String status;
	private static boolean loaded = true;
	private static boolean isEnabled = false;
	public static boolean oneDayLimit = false;
	public static boolean allowMultipleVotes = true;
	private static int undoCode = 0;
	private static Comparator<Vote> currentSort;
	public static Date tcTime = null;
	private static boolean existsFishy = false;
	private static boolean existsDouble = false;
	private static boolean existsDuplicate = false;
	private static boolean existsBadVotecount = false;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				main = new MainWindow();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				main.userTabbedPane.setSelectedIndex(0);
				loadVotes();
			}
		});
	}

	public static void updateUserList() {
		if (!isLoaded())
			return;
		listedUsers.clear();
		((DefaultComboBoxModel) main.getUserList().getModel())
				.removeAllElements();
		String userlist = main.getEditUserArea().getText();
		Scanner reader = new Scanner(userlist);
		while (reader.hasNext())
			listedUsers.add(getUser(reader.nextLine()));
		for (User u : listedUsers) {
			((DefaultComboBoxModel) main.getUserList().getModel())
					.addElement(u);
		}
		reader.close();
	}

	public static void updateVoteLists() {
		disableActions();
		((DefaultComboBoxModel) main.getNumList().getModel())
				.removeAllElements();
		((DefaultComboBoxModel) main.getPosterList().getModel())
				.removeAllElements();
		((DefaultComboBoxModel) main.getVoteList().getModel())
				.removeAllElements();
		for (Vote v : votes) {
			((DefaultComboBoxModel) main.getNumList().getModel()).addElement(v);
			((DefaultComboBoxModel) main.getPosterList().getModel())
					.addElement(v);
			((DefaultComboBoxModel) main.getVoteList().getModel())
					.addElement(v);
		}
		status = "Status: Lists loaded";
		if (existsDouble){
			warning = "Double vote detected";
		}
		else if (existsDuplicate){
			warning = "Duplicate value or user in vote detected";
		}
		else if (existsBadVotecount){
			warning = "Bad votecount in a post detected";
		}
		else if (existsFishy) {
			warning = "Something fishy detected";
		} else {
			status = "Status: Lists loaded, nothing fishy";
			warning = "";
		}
		enableActions();
	}

	public static void fetch() {
		if (!isLoaded())
			return;
		disableActions();
		status = "Status: Fetching votes from topic";
		if (!setURL(main.getURLfield().getText())) {
			warning = "Enter valid URL";
			loaded = true;
			updateStatus();
			return;
		}
		updateStatus();
		VotesFromTopic.runMain();
//		main.getFetchBtn().setEnabled(false);
		enableActions();
		loadVotes();
	}

	public static void loadVotes() {
		if (!isLoaded())
			return;
		disableActions();
		loadUserNames();
		loadDeleted();
		loadCorrections();
		posts.clear();
		votes.clear();
		voted.clear();
		existsFishy = false;
		existsDouble = false;
		existsDuplicate = false;
		existsBadVotecount = false;
		File voteFile = new File("votes.txt");
		Scanner reader = null;
		try {
			reader = new Scanner(new FileReader(voteFile));
		} catch (FileNotFoundException e) {
			status = "Status: No votes.txt found, fetch already!";
			loaded = true;
			updateStatus();
			return;
		}

		String line = reader.nextLine();
		SimpleDateFormat df = new SimpleDateFormat("M/DD/yyyy h:mm:ss a");
		Date hourLimitTime = null;
		try {
			tcTime = df.parse(line.split("at date ")[1].split("~~")[0]);
			hourLimitTime = (Date) tcTime.clone();
			hourLimitTime.setDate(hourLimitTime.getDate() + 1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		status = "Status: Problem with votes.txt, refetch";
		updateStatus();
		
		Post.setExpectedVoteCount(Integer.parseInt(reader.next()));		

		Post currentPost = null;
		try {
			while (reader.hasNext()) {
				try {
					line = reader.nextLine();
					if (line.contains("*")) {
						int num = Integer.parseInt(line.substring(1, 4));
						String poster = line.substring(5).split("\\*")[1];
						String flags = line.substring(5).split("\\*")[2];
						Date date = df.parse(line.substring(5).split("\\*")[0]);
						currentPost = new Post(num, poster, flags, date);
						System.out.println("Next line is: " + flags);
						if (!posts.contains(currentPost)
								&& !deletedPosts.contains(currentPost) && !date.equals(tcTime))
							if (!voted.contains(poster) || allowMultipleVotes) {
								voted.add(poster);
								if (!oneDayLimit || date.before(hourLimitTime))
									posts.add(currentPost);
								if (allowMultipleVotes) {
									for (Post p : posts)
										if (p.posterName.equals(currentPost.posterName)
												&& p.postNumber != currentPost.postNumber)
											currentPost.setDouble(true);
								}
							}
					}
					if (line.startsWith("+")) {
						int value = Integer.parseInt(line.split("\\+")[1]
								.split("[^\\d]+?")[0]);
						String vote = "";
						if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
							vote = line.split("(\\+|\\-)[\\d]+")[1]
									.substring(1);
						else
							vote = line.split("(\\+|\\-)[\\d]+")[1];
						User voteUser = getUser(vote);
						if (!vote.isEmpty())
							currentPost.add(new Vote(value, voteUser));
					}
					if (line.startsWith("-")) {
						int value = Integer.parseInt(line.split("\\-")[1]
								.split("[^\\d]+?")[0]);
						String vote = "";
						if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
							vote = line.split("(\\+|\\-)[\\d]+")[1]
									.substring(1);
						else
							vote = line.split("(\\+|\\-)[\\d]+")[1];
						User voteUser = getUser(vote);
						if (!vote.isEmpty())
							currentPost.add(new Vote(-value, voteUser));
					}
					System.out.println(line);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("ArrayIndex error at loadVotes - "+line);
				} catch (Exception e) {
					System.out.println("Unknown error at loadVotes");
				}
			}
			for (Post p : posts) {			
				if (corrections.keySet().contains(p)) {
					if (corrections.get(p).getDate().equals(p.getDate())) {
						corrections.get(p).setDouble(p.isDouble());
						p = corrections.get(p);
					}
				}
				if (p.getVotes().size() != Post.getExpectedVoteCount())
					p.setFishy(true);
				if (p.getVotes().size() == 0)
					p.setFishy(false);
				votes.addAll(p.getVotes());
			}
			for (int i = 0; i < posts.size() /*&& !existsFishy*/; i++) {
				Post postToCheck = posts.get(i);
				if (corrections.containsKey(postToCheck))
					postToCheck = corrections.get(postToCheck);
				existsFishy = existsFishy || postToCheck.isFishy();
				existsDouble = existsDouble || postToCheck.isDouble();
				existsDuplicate = existsDuplicate || postToCheck.isDuplicate();
				existsBadVotecount = existsBadVotecount || postToCheck.isBadVotecount();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointer at loadVotes");
			status = "Status: Problem with votes.txt, refetch";
		}
		status = "Status: Votes loaded";
		reader.close();
		enableActions();
		updateStatus();
		try {
			Collections.sort(votes, currentSort);
		} catch (ClassCastException e) {
		}
		updateVoteLists();
		createVotals();
	}

	private static void loadCorrections() {
		disableActions();
		File userFile = new File("corrections.txt");
		Scanner reader = null;
		corrections.clear();
		try {
			reader = new Scanner(new FileReader(userFile));

			Post p = null;
			String line = reader.nextLine();
			while (reader.hasNext()) {
				line = reader.nextLine();
				if (line.startsWith("+")) {
					int value = Integer.parseInt(line.split("\\+")[1]
							.split("[^\\d]+?")[0]);
					String vote = "";
					if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
						vote = line.split("(\\+|\\-)[\\d]+")[1].substring(1);
					else
						vote = line.split("(\\+|\\-)[\\d]+")[1];
					User voteUser = getUser(vote);
					if (!vote.isEmpty())
						p.add(new Vote(value, voteUser));
				} else if (line.startsWith("-")) {
					int value = Integer.parseInt(line.split("\\-")[1]
							.split("[^\\d]+?")[0]);
					String vote = "";
					if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
						vote = line.split("(\\+|\\-)[\\d]+")[1].substring(1);
					else
						vote = line.split("(\\+|\\-)[\\d]+")[1];
					User voteUser = getUser(vote);
					if (!vote.isEmpty())
						p.add(new Vote(-value, voteUser));
				} else {
					int num = Integer.parseInt(line);
					Date d = new Date(Long.parseLong(reader.nextLine()));
					String name = reader.nextLine();
					p = new Post(num, name, "N", d);
					corrections.put(p, p);
				}
			}
			reader.close();
			enableActions();
		} catch (FileNotFoundException e) {
			System.out.println("NO CORRECTIONS.txt");
			return;
		}
		enableActions();
	}

	private static void storeCorrections() {
		disableActions();
		String correctionsOutput = "~~List of corrected votes NOTE: Editing this file manually is not recommended, so GTFO~~";
		for (Post p : corrections.keySet()) {
			correctionsOutput += "\n" + p.getNumber() + "\n"
					+ p.getDate().getTime() + "\n" + p.posterName;
			Post q = corrections.get(p);
			for (Vote v : q.getVotes()) {
				correctionsOutput += "\n" + v;
			}
		}
		File f = new File("corrections.txt");
		try {
			PrintWriter output = new PrintWriter(new FileWriter(f));
			output.write(correctionsOutput);
			output.close();
		} catch (IOException e) {
			System.out.println("ERROR in storing corrections.txt");
			e.printStackTrace();
		}
		enableActions();
	}

	private static void loadDeleted() {
		disableActions();
		File userFile = new File("deleted.txt");
		Scanner reader = null;
		deletedPosts.clear();
		try {
			reader = new Scanner(new FileReader(userFile));

			String line = reader.nextLine();
			while (reader.hasNext()) {
				line = reader.nextLine();
				int num = Integer.parseInt(line.split("\\*")[1]);
				String rest = line.split("\\*")[2].trim();
				deletedPosts.add(new Post(num, rest, "N", null));
			}
			reader.close();
			enableActions();
		} catch (FileNotFoundException e) {
			System.out.println("NO DELETED.txt");
			return;
		} catch (Exception e) {
			warning = "Problem with deleted.txt, delete it";
			updateStatus();
			return;
		}
	}

	private static void storeDeleted() {
		disableActions();
		String deletedOutput = "~~List of posts which will be ignored, by number and username~~";
		for (Post p : deletedPosts) {
			int num = p.getNumber();
			String user = p.posterName;
			String zeroes = "00";
			if (num > 99)
				zeroes = "";
			else if (num > 9)
				zeroes = "0";

			deletedOutput += "\n*" + zeroes + num + "*" + user;
		}
		File f = new File("deleted.txt");
		try {
			PrintWriter output = new PrintWriter(new FileWriter(f));
			output.write(deletedOutput);
			output.close();
		} catch (IOException e) {
			System.out.println("ERROR in storing deleted.txt");
			e.printStackTrace();
		}
		enableActions();
	}

	private static void loadUserNames() {
		disableActions();
		File userFile = new File("usernames.txt");
		Scanner reader = null;
		votedUsers.clear();
		try {
			reader = new Scanner(new FileReader(userFile));

			String line = reader.nextLine();
			while (reader.hasNext()) {
				line = reader.nextLine();
				String name = line.split("\\(")[0].trim();
				String rest = line.split("\\(")[1].replace(")", "");
				for (int i = 0; i < rest.split(",").length; i++) {
					String alias = rest.split(",")[i].trim();
					getUser(name).addAlias(alias);
				}
			}
			reader.close();
			enableActions();
		} catch (FileNotFoundException e) {
			System.out.println("NO USERNAMES.txt");
			return;
		} catch (Exception e) {
			warning = "Problem with usernames.txt, delete it";
			updateStatus();
			return;
		}
	}

	private static void storeUserNames() {
		disableActions();

		File userFile = new File("usernames.txt");
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileWriter(userFile));
		} catch (IOException e) {
			System.out.println("Problem with storing usernames");
			e.printStackTrace();
		}
		String undo = undoStore;
		String out = usernameOutput;

		undo = out;
		usernameOutput = "~~List of users and their aliases~~";
		for (User u : votedUsers) {
			usernameOutput += "\n" + u.fileFormat();
		}
		out = usernameOutput;

		output.write(usernameOutput);
		output.close();
		enableActions();
	}

	public static User getUser(String userString) {
		userString = userString.split("\\(")[0].trim();
		User retval = new User(userString);
		if (!votedUsers.isEmpty()) {
			for (User u : votedUsers) {
				if (!u.getAliases().isEmpty()) {
					for (String s : u.getAliases()) {
						if (s.equalsIgnoreCase(userString))
							return u;
					}
				}
			}
			for (User u : votedUsers) {
				if (u.getName().equalsIgnoreCase(userString))
					return u;
			}
		}
		votedUsers.add(retval);
		return retval;
	}

	public static void addAlias(String user, String alias) {
		getUser(user).addAlias(alias);
	}

	public static void test() {
		main.getVotalsScrollPane()
				.getVerticalScrollBar()
				.setValue(
						main.getVotalsScrollPane().getVerticalScrollBar()
								.getMinimum());
		//
		//
		//
		//
		//
		//
		//
	}

	private static void disableActions() {
		isEnabled = false;
		loaded = false;
		updateStatus();
	}

	private static void enableActions() {
		isEnabled = true;
		loaded = true;
		// warning = "";
		updateStatus();
	}

	public static void sortByPostNum() {
		currentSort = new Comparator<Vote>() {
			public int compare(Vote o1, Vote o2) {
				if (o1.post.postNumber - o2.post.postNumber == 0)
					return o2.getValue() - o1.getValue();
				else
					return o1.post.postNumber - o2.post.postNumber;
			}
		};
		Collections.sort(votes, currentSort);
	}

	public static void sortByPoster() {
		currentSort = new Comparator<Vote>() {
			public int compare(Vote o1, Vote o2) {
				if (o1.post.posterName.compareToIgnoreCase(o2.post.posterName) == 0)
					return o2.getValue() - o1.getValue();
				else
					return o1.post.posterName
							.compareToIgnoreCase(o2.post.posterName);
			}
		};
		Collections.sort(votes, currentSort);
	}

	public static void sortByVoteName() {
		currentSort = new Comparator<Vote>() {
			public int compare(Vote o1, Vote o2) {
				if (o1.getUser().getName()
						.compareToIgnoreCase(o2.getUser().getName()) == 0)
					return o2.getValue() - o1.getValue();
				else
					return o1.getUser().getName()
							.compareToIgnoreCase(o2.getUser().getName());
			}
		};
		Collections.sort(votes, currentSort);
	}

	public static boolean isLoaded() {
		return loaded;
	}

	public static void updateStatus() {
		try {
			main.getStatusLabel().setText(status);
			main.getWarningLabel().setText(warning);
		} catch (NullPointerException e) {
			System.out.println("NULL STATUSES");
			return;
		}
	}

	public static boolean setURL(String url) {
		return VotesFromTopic.setURL(url);
	}

	public synchronized static void replace(Object[] votes, Object object) {
		disableActions();
		undoCode = 1;
		main.getUndoBtn().setEnabled(true);
		for (Object v : votes) {
			replace((Vote) v, getUser(((User) object).getName()));
		}
		HashSet<User> removeUsers = new HashSet<User>();
		for (User u : votedUsers) {
			if (u.getAliases().isEmpty())
				removeUsers.add(u);
		}
		votedUsers.removeAll(removeUsers);
		enableActions();
		String undo = undoStore;
		undoStore = usernameOutput;
		undo = undoStore;
		storeUserNames();
		loadVotes();
	}

	private synchronized static void replace(Vote vote, User withUser) {
		String votename = vote.getUser().getName();
		withUser.addAlias(votename);
		vote.setUser(withUser);
	}

	public synchronized static void delete(ArrayList<Vote> votelist) {
		disableActions();
		undoCode = 2;
		main.getUndoBtn().setEnabled(true);
		undoDeletes.clear();
		for (Object v : votelist) {
			deletedPosts.add(((Vote) v).post);
			undoDeletes.add(((Vote) v).post);
		}
		storeDeleted();
		enableActions();
		loadVotes();
	}

	public synchronized static void correctLoad(Vote vote) {
		disableActions();
		undoCode = 0;
		correctedPost = vote.post;
		main.getCorrectDialog().setLocationRelativeTo(main);
		main.getCorrectDialog().setVisible(true);
		String zeroes = "00";
		if (correctedPost.postNumber > 99)
			zeroes = "";
		else if (correctedPost.postNumber > 9)
			zeroes = "0";
		String areaOutput = "*" + zeroes + correctedPost.postNumber + "*"
				+ correctedPost.posterName;
		for (Vote v : correctedPost.getVotes()) {
			areaOutput += "\n" + v;
		}
		main.getCorrectArea().setText(areaOutput);
	}

	public synchronized static void correctSave(String input) {
		Scanner reader = new Scanner(input);
		String line = reader.nextLine();
		int num = Integer.parseInt(line.substring(1, 4));
		String poster = line.substring(5).trim();
		Post post = new Post(num, poster, correctedPost.flags, correctedPost.getDate());
		while (reader.hasNext()) {
			line = reader.nextLine();
			if (line.startsWith("+")) {
				int value = Integer.parseInt(line.split("\\+")[1]
						.split("[^\\d]+?")[0]);
				String vote = "";
				if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
					vote = line.split("(\\+|\\-)[\\d]+")[1].substring(1);
				else
					vote = line.split("(\\+|\\-)[\\d]+")[1];
				User voteUser = getUser(vote);
				if (!vote.isEmpty())
					post.add(new Vote(value, voteUser));
			}
			if (line.startsWith("-")) {
				int value = Integer.parseInt(line.split("\\-")[1]
						.split("[^\\d]+?")[0]);
				String vote = "";
				if (line.split("(\\+|\\-)[\\d]+")[1].startsWith(" "))
					vote = line.split("(\\+|\\-)[\\d]+")[1].substring(1);
				else
					vote = line.split("(\\+|\\-)[\\d]+")[1];
				User voteUser = getUser(vote);
				if (!vote.isEmpty())
					post.add(new Vote(-value, voteUser));
			}
		}
		corrections.put(correctedPost, post);
		storeCorrections();
		enableActions();
		loadVotes();
	}

	public synchronized static void undo() {
		disableActions();
		undo(undoCode);
		enableActions();
	}

	private synchronized static void undo(int code) {
		if (code == 0) {// no undo
			undoCode = 0;
			main.getUndoBtn().setEnabled(false);
			warning = "No undo available";
			updateStatus();
		} else if (code == 1) { // undo replace
			undoCode = 0;
			File userFile = new File("usernames.txt");
			PrintWriter output = null;
			try {
				output = new PrintWriter(new FileWriter(userFile));
			} catch (IOException e) {
				System.out.println("Problem with undoing replace writer");
				e.printStackTrace();
			}
			String temp = undoStore;
			output.write(temp);
			output.close();
			usernameOutput = undoStore;
			undoStore = null;
			status = "Status: Undid last replace";
			main.getUndoBtn().setEnabled(false);
			enableActions();
			loadVotes();
		} else if (code == 2) { // undo delete
			undoCode = 0;
			deletedPosts.removeAll(undoDeletes);
			status = "Status: Undid last delete";
			main.getUndoBtn().setEnabled(false);
			storeDeleted();
			enableActions();
			loadVotes();
		}
	}

	private static void createVotals() {
		System.out.println(votes);
		System.out.println(posts);
		Votals.setTime();
		votalsMap.clear();
		for (Vote v : votes) {
			v.setUser(getUser(v.getUser().getName()));
		}
		for (Vote v : votes) {
			votalsMap.put(v.getUser(), new Votals(v.getUser()));
		}
		for (Vote v : votes) {
			votalsMap.get(v.getUser()).addVote(v);
		}
		main.getCountLabel().setText(
				votalsMap.size() + " users in " + votes.size()
						/ Post.getExpectedVoteCount() + " votes");
	}

	public static void printVotals() {
		status = "Status: Printing votals...";
		disableActions();
		// createVotals();
		// for (Votals v: votalsMap.values())
		// System.out.println(v);

		Votals.setTime();
		Votals.setMaxMin();
		votalsMap.clear();
		for (Vote v : votes) {
			v.setUser(getUser(v.getUser().getName()));
		}
		for (Vote v : votes) {
			votalsMap.put(v.getUser(), new Votals(v.getUser()));
		}
		for (Vote v : votes) {
			votalsMap.get(v.getUser()).addVote(v);
		}
		main.getCountLabel().setText(
				votalsMap.size() + " users in " + votes.size()
						/ Post.getExpectedVoteCount() + " votes");

		System.out.println("fishy = " + existsFishy);
		for (Post p : posts) {
//			if (p.isFishy())
				System.out.println(p);
		}

		String votals = "<b><i>~~Votals for the current topic~~\nUsercount: "
				+ votalsMap.keySet().size() + "\nVotecount: " + votes.size()
				/ Post.getExpectedVoteCount();// + "</i></b>";

		votals += "\n <b>Totals</b>";

		Comparator<String> ptsort = new Comparator<String>() {

			public int compare(String s1, String s2) {
				s1 = s1.trim().split(" ")[0];
				s2 = s2.trim().split(" ")[0];
				if (s1.startsWith("+"))
					s1 = s1.substring(1);
				if (s2.startsWith("+"))
					s2 = s2.substring(1);
				double a = Double.parseDouble(s1);
				double b = Double.parseDouble(s2);
				return (int) ((1000) * (b - a));
			}

		};

		Comparator<String> percsort = new Comparator<String>() {

			public int compare(String o1, String o2) {
				double a = Double.parseDouble(o1.split("%")[0]);
				double b = Double.parseDouble(o2.split("%")[0]);
				return (int) ((1000) * (b - a));
			}

		};

		ArrayList<String> totals = new ArrayList<String>();
		for (User u : votalsMap.keySet()) {
			if (votalsMap.get(u).getVoteTotal() >= 0)
				totals.add("\n +" + votalsMap.get(u).getVoteTotal() + " " + u);
			else
				totals.add("\n " + votalsMap.get(u).getVoteTotal() + " " + u);
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		votals += "\n\n<b>Separate Time Period Totals</b>";

		totals.clear();
		votals += "\n\n<i>Power Hour</i>";
		for (User u : votalsMap.keySet()) {
			if (votalsMap.get(u).getPowerHourTotal() >= 0)
				totals.add("\n +" + votalsMap.get(u).getPowerHourTotal() + " "
						+ u);
			else
				totals.add("\n " + votalsMap.get(u).getPowerHourTotal() + " "
						+ u);
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Night Vote</i>";
		for (User u : votalsMap.keySet()) {
			if (votalsMap.get(u).getNightVoteTotal() >= 0)
				totals.add("\n +" + votalsMap.get(u).getNightVoteTotal() + " "
						+ u);
			else
				totals.add("\n " + votalsMap.get(u).getNightVoteTotal() + " "
						+ u);
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Morning Vote</i>";
		for (User u : votalsMap.keySet()) {
			if (votalsMap.get(u).getMorningVoteTotal() >= 0)
				totals.add("\n +" + votalsMap.get(u).getMorningVoteTotal()
						+ " " + u);
			else
				totals.add("\n " + votalsMap.get(u).getMorningVoteTotal() + " "
						+ u);
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Day Vote</i>";
		for (User u : votalsMap.keySet()) {
			if (votalsMap.get(u).getDayVoteTotal() >= 0)
				totals.add("\n +" + votalsMap.get(u).getDayVoteTotal() + " "
						+ u);
			else
				totals.add("\n " + votalsMap.get(u).getDayVoteTotal() + " " + u);
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		votals += "\n\n<b>Additional Stats</b>";

		totals.clear();
		votals += "\n\n<i>Absolute Points</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getAbsoluteTotal() + " - "
					+ u.getName());
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Total Voted</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getVotedPercentage() + "%"
					+ " - " + votalsMap.get(u).getVotes().size() + " - "
					+ u.getName());
		}
		Collections.sort(totals, percsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Positive Percentage</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getPositivePercentage() + "%"
					+ " - " + u.getName());
		}
		Collections.sort(totals, percsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>First Places</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getFirstPlaceVotes() + " - "
					+ u.getName());
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Last Places</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getLastPlaceVotes() + " - "
					+ u.getName());
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		totals.clear();
		votals += "\n\n<i>Pts Per Vote</i>";
		for (User u : votalsMap.keySet()) {
			totals.add("\n" + votalsMap.get(u).getPtsPerVote() + " - "
					+ u.getName());
		}
		Collections.sort(totals, ptsort);
		for (String s : totals)
			votals += s;

		// int i = 0;
		// for (Post p : posts) {
		// try {
		// System.out.println(votalsMap.get(getUser("Dante")).getVotes()
		// .get(i));
		// } catch (Exception e) {
		// }
		// i++;
		// }

		main.getVotalsArea().setText(votals);
		status = "Status: Printed votals";
		enableActions();
	}

	public static int getVoteCount() {
		return votes.size();
	}

	public static int getPostCount() {
		return posts.size();
	}
}
