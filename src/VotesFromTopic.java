import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VotesFromTopic {
	private static String topicURL = null;
	public static String topicTitle = "";
	public static String tcTime = null;
	private static ArrayList<String> votes = new ArrayList<String>();
	private static String voteOutput = "";
	private static int maxPageNumber;
	private static String currentURL = null;
	private static String currentSource = null;
	private static final String filename = "votes.txt";
	private static final Pattern correctVotePat = Pattern.compile(">[ \\t]*[\\+|\\-]((1[0-5])|[1-9])[~#\\*\\/\\!:%&;=\\+<>\\*\\.\\?\\[\\]\\(\\)\\'\\,\\-\\_ a-zA-Z0-9]{2,}?(<br/|</div|</p)");
	private static final Pattern alternateVotePat = Pattern.compile(">[ \\t]*[~#\\*\\/\\!%:&;=\\+<>\\*\\.\\?\\[\\]\\(\\)\\'\\,\\-\\_ a-zA-Z0-9]{2,}?[\\+|\\-]((1[0-5])|[1-9])+\\s*(<br/|</div|</p)");
	private static final Pattern noPlusMinusVotePat = Pattern.compile(">[ \\t]*((1[0-5])|[1-9])[~#\\*\\/\\!:%&;=\\+<>\\*\\.\\?\\[\\]\\(\\)\\'\\,\\-\\_ a-zA-Z0-9]{2,}?(<br/|</div|</p)");
	private static final Pattern userPat = Pattern.compile("class=\"name\">[\\-_ a-zA-Z0-9]{1,}?<.*?" + "(class=\"author\"|div id=\"sponsored_links\"|div class=\"pages\")>");

	// TODO:
	// HANDLE NUMBER AFTER USER VOTES
	// HANDLE EDITED POSTS
	// HANDLE QUOTED VOTES
	// DIFFERENT FISHYS

	public static synchronized void runMain() {
		setPageSource(topicURL);
		maxPageNumber = getMaxPageNumber(topicURL);
		int num = 0;
		String zeroes = "00";

		Pattern titlePat = Pattern.compile("<h2 class=\"title\">.*?</h2>");
		Matcher titleMat = titlePat.matcher(currentSource);

		if (titleMat.find())
			topicTitle = titleMat.group().split("\">")[1].split("</h2>")[0];
		tcTime = currentSource.split("Posted ")[1].split("<br/>")[0];
		tcTime = tcTime.replace("&nbsp;", " ");
		voteOutput = "~~Votes collected from " + topicTitle + " at date " + tcTime + "~~" + "\n" + Post.getExpectedVoteCount() + " votes per post";

		for (int i = 0; i < maxPageNumber; i++) {
			if (i > 0)
				setPageSource(topicURL + i);

			Matcher userMatch = userPat.matcher(currentSource);
			while (userMatch.find()) {
				++num;
				if (num == 1)
					continue;
				if (num > 99)
					zeroes = "";
				else if (num > 9)
					zeroes = "0";
				String s = userMatch.group();
				System.out.println(s);
				String user = s.split("<")[0].split(">")[1];
				System.out.println(user);
				String t = s.split("Posted ")[1].split("<br/>")[0];
				t = t.replace("&nbsp;", " ");
				System.out.println("USER=" + user);
				String flags = "N";
				if (s.contains("<br/>(edited)</div>"))
					flags += "E";
				s = s.replaceAll("<i><p><strong>From:.*?</p></i>", "");
				if (!flags.contains("E"))
					if (findVotes(s)) {
						voteOutput += "\n*" + zeroes + num + "*" + t + "*" + user + "*" + flags;
						for (String v : votes) {
							voteOutput += "\n" + v;
						}
						votes.clear();
					}
			}

		}
		votesToFile();
	}

	private static void votesToFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
		} catch (IOException e) {
			System.out.println("Error creating file writer.");
			e.printStackTrace();
		}

		output.write(voteOutput);
		output.close();
	}

	private static boolean findVotes(String postText) {
		boolean foundVotes = false;
		Matcher voteMatch = correctVotePat.matcher(postText);
		String user = "";
		while (voteMatch.find()) {
			String s = voteMatch.group();
			System.out.println(s);
			if (s.contains("("))
				s = s.split("\\(")[0].trim();
			if (s.contains(","))
				s = s.split(",")[0].trim();
			s = s.split("<")[0].split(">")[1].trim();
			s = s.replace(":", "");
			if (s.split("[\\+|\\-]((1[0-5])|[1-9])").length > 0)
				user = s.split("[\\+|\\-]((1[0-5])|[1-9])")[1].trim();		
			if (!s.isEmpty() && !user.equals("")) {
				System.out.println("REGVOTE:" + s);
				votes.add(s);
				foundVotes = true;
			}
		}
		voteMatch = alternateVotePat.matcher(postText);
		while (voteMatch.find()) {
			try {
				String s = voteMatch.group();
				System.out.println(s);
				s = s.split(".*>")[1].split("<")[0];
				user = s.split("[\\+|\\-]")[0].trim();
				int value = Integer.parseInt(s.replace(user, "").replace("+", "").trim());
				s = (value > 0 ? "+" + value : value) + " " + user;
				if (!s.isEmpty() && !user.equals("")) {
					System.out.println("ALTVOTE:" + s);
					votes.add(s);
					foundVotes = true;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		voteMatch = noPlusMinusVotePat.matcher(postText);
		while (voteMatch.find()) {
			try {
				String s = voteMatch.group();
				System.out.println(s);
				s = s.split(".*>")[1].split("<")[0];
				if (s.split("((1[0-5])|[1-9])").length <= 1)
					continue;
				user = s.split("((1[0-5])|[1-9])")[1].trim();
				System.out.println("user="+user);
				int value = Integer.parseInt(s.split(user)[0].replace("+", "").trim());
				s = (value > 0 ? "+" + value : value) + " " + user;
				if (!s.isEmpty() && !user.equals("")) {
					System.out.println("PLUSMINUS:" + s);
					votes.add(s);
					foundVotes = true;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return foundVotes;
	}

	private static void checkfile(String fileName) {
		File f = new File(fileName);
		if (!f.exists())
			return;
		if (!f.canWrite())
			throw new IllegalArgumentException("Delete: write protected: " + fileName);
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
		}

		boolean success = f.delete();
		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");
	}

	private static int getMaxPageNumber(String url) {
		String source = currentSource;
		Pattern pattern = Pattern.compile("Page [\\d]* of [\\d]+");
		Matcher matcher = pattern.matcher(source);

		String s = null;
		while (matcher.find())
			s = matcher.group();

		return s == null ? 1 : Integer.parseInt(s.split("of ")[1]);
	}

	private static synchronized void setPageSource(String url) {
		if (url.equals(currentURL))
			return;

		currentURL = url;
		Scanner reader = null;
		try {
			URL link = new URL(url);
			reader = new Scanner(new InputStreamReader(link.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("url error");
		} catch (IOException e) {
			System.out.println("url error");
		}

		String pageSource = "";
		while (reader.hasNext())
			pageSource += reader.nextLine();
		// System.out.println(pageSource);

		reader.close();

		currentSource = pageSource;
	}

	public static boolean setURL(String url) {
		if (url.contains("http://www.gamefaqs.com/boards/")) {
			String s = url;
			if (url.contains("#"))
				s = s.split("#")[0];
			if (s.contains("?page="))
				topicURL = s.split("\\?page=")[0] + "?page=";
			else
				topicURL = s + "?page=";
			s = topicURL;
			return true;
		} else
			return false;
	}
}
