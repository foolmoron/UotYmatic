import java.util.HashSet;

public class User {
	private String name;
	private HashSet<String> aliases = new HashSet<String>();
//	private Votals votals;

	public User(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public String listAliases() {
		String list = "(";
		for (String a : aliases)
			list += a + ",";
		if (list.endsWith(","))
			list = list.substring(0, list.length() - 1) + ")";
		else
			list = list.substring(0, list.length()) + ")";
		return list;
	}

	public HashSet<String> getAliases() {
		return aliases;

	}

	public String getName() {
		return name;
	}

	public void addAlias(String a) {
		if (!a.isEmpty() && !a.equals(name))
			aliases.add(a);
	}

	public String fileFormat() {
		return toString() + " " + listAliases();
	}

	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object o){
		if (o instanceof User)
			return (this.name.equals(((User) o).name));
		return false;
	}
	
//	public void setVotals(Votals votals){
//		this.votals = votals;
//	}
//	
//	public Votals getVotals(){
//		return votals;
//	}
}
