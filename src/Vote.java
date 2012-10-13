public class Vote {
	public Post post;
	private int voteValue;
	private User vote;

	public Vote(int voteValue, User voteName) {
		this.voteValue = voteValue;
		this.vote = voteName;
	}

	public String toString(int code) {
		switch (code) {
		case 1:
			String zero = "00";
			if (post.postNumber > 99)
				zero = "";
			else if (post.postNumber > 9)
				zero = "0";
			return zero + post.postNumber;
		case 2:
			return post.posterName;
		case 3:
			String plus = "";
			if (voteValue >= 0)
				return "+" + voteValue + " " + getUser();
			else return "- " + -voteValue + " " + getUser();
		case 4:
			return getUser().toString();
		}
		return "WRONG CODE ERROR";
	}

	public boolean equals(Object o) {
		if (o instanceof Vote)
			try {
				return (this.post.equals(((Vote) o).post) && this.vote.getName()
						.equals(((Vote) o).vote.getName()));
			} catch (Exception e) {
				return false;
			}
		return false;
	}

	public String toString() {
		if (voteValue > 0)
			return "+" + voteValue + " " + getUser();
		else
			return voteValue + " " + getUser();
	}

	public User getUser() {
		return vote;
	}
	
	public void setUser(User user){
		this.vote = user;
	}

	public int getValue() {
		return voteValue;
	}
}
