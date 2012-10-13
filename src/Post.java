import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Post {
	public int postNumber;
	public String posterName;
	public String flags;
	private Date date;
	private ArrayList<Vote> votes = new ArrayList<Vote>();
	private boolean fishy = false;
	private boolean edited = false;
	private boolean duplicate = false;
	private boolean doublevote = false;
	private static int expectedVoteCount = 6;

	public Post(int postNumber, String posterName, String flags, Date date) {
		this.postNumber = postNumber;
		this.posterName = posterName;
		this.flags = flags;
		this.date = date;
		if (flags.contains("E"))
			edited = true;
	}

	public void add(Vote vote) {
		for (Vote v : votes) {
			if (v.getUser().getName().equals(vote.getUser().getName()))
				duplicate = true;
			if (v.getValue() == vote.getValue())
				duplicate = true;
		}
		if (!votes.contains(vote)) {
			vote.post = this;
			votes.add(vote);
		}
	}

	public void add(Collection<Vote> votez) {
		for (Vote v : votez) {
			if (!votes.contains(v)) {
				v.post = this;
				votes.add(v);
			}
		}
	}

	public void clear() {
		votes.clear();
	}

	public ArrayList<Vote> getVotes() {
		return votes;
	}

	public int getNumber() {
		return postNumber;
	}

	public String toString() {
		return "#" + postNumber + ", " + posterName + votes;
	}

	public boolean equals(Object o) {
		if (o instanceof Post)
			return (postNumber == ((Post) o).getNumber() && posterName
					.equals(((Post) o).posterName));
		return false;
	}

	public int hashCode() {
		return posterName.hashCode() + postNumber;
	}

	public Date getDate() {
		return date;
	}

	public boolean isFishy() {
		return fishy;
	}
	
	public boolean isEdited() {
		return edited;
	}
	
	public boolean isDuplicate() {
		return duplicate;
	}
	
	public boolean isBadVotecount(){
		return !(votes.size() == expectedVoteCount);
	}

	public void setFishy(boolean fishy) {
		this.fishy = fishy;
	}
	
	public boolean isDouble() {
		return doublevote;
	}
	
	public void setDouble(boolean doub){
		this.doublevote = doub;
	}

	public static int getExpectedVoteCount() {
		return expectedVoteCount;
	}

	public static void setExpectedVoteCount(int expectedCount) {
		expectedVoteCount = expectedCount;
	}
}
