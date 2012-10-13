import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Votals {
	private User user;
	private ArrayList<Vote> powerhour = new ArrayList<Vote>();
	private ArrayList<Vote> nightvote = new ArrayList<Vote>();
	private ArrayList<Vote> morningvote = new ArrayList<Vote>();
	private ArrayList<Vote> dayvote = new ArrayList<Vote>();
	private static Date tc = Manager.tcTime;
	private static Date powerTime = (Date) tc.clone();
	private static Date nightTime = (Date) tc.clone();
	private static Date mornTime = (Date) tc.clone();
	private static Date dayTime = (Date) tc.clone();
	private static int max = 3;
	private static int min = -3;

	public ArrayList<Vote> getPowerhour() {
		return powerhour;
	}

	public ArrayList<Vote> getNightvote() {
		return nightvote;
	}

	public ArrayList<Vote> getMorningvote() {
		return morningvote;
	}

	public ArrayList<Vote> getDayvote() {
		return dayvote;
	}

	public static void setTime() {
		tc = Manager.tcTime;
		powerTime.setHours(tc.getHours() + 1);
		if (nightTime.getHours() > 3)
			nightTime.setDate(nightTime.getDate() + 1);
		nightTime.setHours(3);
		nightTime.setMinutes(0);
		mornTime = (Date) nightTime.clone();
		mornTime.setHours(9);
		dayTime.setDate(tc.getDate() + 1);
	}

	public static void setMaxMin() {
		if (Post.getExpectedVoteCount() == 6){
			max = 3;
			min = -3;
		} else if (Post.getExpectedVoteCount() == 10){
			max = 10;
			min = 1;
		} else {
			max = Post.getExpectedVoteCount();
			min = 1;
		}
	}

	public static int getTotalOfVotes(Collection<Vote> votes) {
		int total = 0;
		for (Vote v : votes) {
			total += v.getValue();
		}
		return total;
	}

	public static double getAverageOfVotes(Collection<Vote> votes) {
		double total = getTotalOfVotes(votes);
		double count = votes.size();
		double avg = total / count;
		int x = (int) (avg * 10000);
		return ((double) x) / 10000.0;
	}

	public static double getPtsPerVote(Collection<Vote> votes) {
		double total = getTotalOfVotes(votes);
		double count = Manager.getPostCount();
		double avg = total / count;
		int x = (int) (avg * 100);
		return ((double) x) / 100.0;
	}

	public static double getPositivePercentage(Collection<Vote> votes) {
		double pos = 0;
		for (Vote v : votes) {
			if (v.getValue() > 0)
				pos++;
		}
		double perc = pos / (double) votes.size();
		int x = (int) (perc * 10000);
		return ((double) x) / 100.0;
	}

	public double getVotedPercentage() {
		double votes = (double) Manager.getVoteCount()/Post.getExpectedVoteCount();
		double voted = (double) this.getVotes().size();
		double perc = voted / votes;
		int x = (int) (perc * 10000);
		return ((double) x) / 100.0;
	}

	public Votals(User user) {
		this.user = user;
	}

	public void addVote(Vote vote) {
		Date tx = tc;
		Date tn = nightTime;
		Date tm = mornTime;
		Date date = vote.post.getDate();
		if (date.before(powerTime)) {
			powerhour.add(vote);
			return;
		} else if (date.before(nightTime)) {
			nightvote.add(vote);
			return;
		} else if (date.before(mornTime)) {
			morningvote.add(vote);
			return;
		} else {
			dayvote.add(vote);
			return;
		}
	}

	public int getAbsoluteTotal() {
		int total = 0;
		for (Vote v : getVotes()) {
			total += Math.abs(v.getValue());
		}
		return total;
	}

	public int getFirstPlaceVotes() {
		int count = 0;
		for (Vote v : getVotes()) {
			if (v.getValue() == max)
				count++;
		}
		return count;
	}

	public int getLastPlaceVotes() {
		int count = 0;
		for (Vote v : getVotes()) {
			if (v.getValue() == min)
				count++;
		}
		return count;
	}

	public double getPositivePercentage() {
		return getPositivePercentage(getVotes());
	}

	public double getPtsPerVote() {
		return getPtsPerVote(getVotes());
	}

	public ArrayList<Vote> getVotes() {
		ArrayList<Vote> ret = new ArrayList<Vote>();
		ret.addAll(powerhour);
		ret.addAll(nightvote);
		ret.addAll(morningvote);
		ret.addAll(dayvote);
		return ret;
	}

	public String toString() {
		return user + " " + getVotes().size();
	}

	public int getVoteTotal() {
		return getTotalOfVotes(this.getVotes());
	}

	public int getPowerHourTotal() {
		return getTotalOfVotes(this.powerhour);
	}

	public int getNightVoteTotal() {
		return getTotalOfVotes(this.nightvote);
	}

	public int getMorningVoteTotal() {
		return getTotalOfVotes(this.morningvote);
	}

	public int getDayVoteTotal() {
		return getTotalOfVotes(this.dayvote);
	}
}
