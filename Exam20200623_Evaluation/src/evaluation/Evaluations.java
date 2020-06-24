package evaluation;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Facade class for the research evaluation system
 *
 */
public class Evaluations {

	private List<Integer> points = new ArrayList<>();
	private SortedMap<String, Journal> journals = new TreeMap<>();
	private List<String> disciplines = new ArrayList<>();
	private SortedMap<String, Group> groups = new TreeMap<>();
	private Map<String, Member> members = new HashMap<>();

	//R1
	/**
	 * Define number of levels and relative points.
	 * 
	 * Levels are numbered from 1 on, and points must be strictly decreasing
	 *  
	 * @param points points for the levels
	 * @throws EvaluationsException thrown if points are not decreasing
	 */
	public void addPointsForLevels (int... points) throws EvaluationsException {
		for(Integer i : points) {
			if(this.points.size() > 0 && i >= this.points.get(this.points.size()-1))
				throw new EvaluationsException();
			this.points.add(i);
		}
	}

	/**
	 * Retrieves the points for the given level.
	 * 
	 * @param level level for which points are required
	 * @return points for the level
	 */
	public int getPointsOfLevel (int level) {
		return points.get(level-1);
	}

	/**
	 * Add a new journal for a given disciplines and provides the corresponding level.
	 * 
	 * The level determines the points for the article published in the journal.
	 * 
	 * @param name name of the new journal
	 * @param discipline reference discipline for the journal
	 * @param level level for the journal.
	 * @throws EvaluationsException thrown if the specified level does not exist
	 */
	public void addJournal (String name, String discipline, int level) throws EvaluationsException {
		if(level<0 || level > points.size())
			throw new EvaluationsException();
		if(!disciplines.contains(discipline))
			disciplines.add(discipline);
		Journal j = new Journal(name, discipline, points.get(level-1));
		journals.put(name, j);
	}

	/**
	 * Retrieves number of journals.
	 * 
	 * @return journals count
	 */
	public int countJournals() {
		return journals.size();
	}

	/**
	 * Retrieves all the journals for a given discipline.
	 * 
	 * @param discipline the required discipline
	 * @return list of journals (sorted alphabetically)
	 */
	public List<String> getJournalNamesOfAGivenDiscipline(String discipline) {
		List<String> result = new ArrayList<>();
		for(Journal j : journals.values()) {
			if(j.getDiscipline().equals(discipline))
				result.add(j.getName());
		}
		return result;
	}

	//R2
	/**
	 * Add a research group and the relative disciplines.
	 * 
	 * @param name name of the research group
	 * @param disciplines list of disciplines
	 * @throws EvaluationsException thrown in case of duplicate name
	 */
	public void addGroup (String name, String... disciplines) throws EvaluationsException {
		if(groups.containsKey(name))
			throw new EvaluationsException();
		Group g = new Group(name);
		for(String d : disciplines)
			g.addDiscipline(d);
		groups.put(name, g);
	}

	/**
	 * Define the members for a previously defined research group.
	 * 
	 * @param groupName name of the group
	 * @param memberNames list of group members
	 * @throws EvaluationsException thrown if name not previously defined.
	 */
	public void setMembers (String groupName, String... memberNames) throws EvaluationsException {
		if(! groups.containsKey(groupName))
			throw new EvaluationsException();
		for(String mN : memberNames) {
			Member m = new Member(mN);
			members.put(mN, m);
			groups.get(groupName).addMember(members.get(mN));
		}	
	}

	/**
	 * Return list of members of a group.
	 * The list is sorted alphabetically.
	 * 
	 * @param groupName name of the group
	 * @return list of members
	 */
	public List<String >getMembers(String groupName){
		return groups.get(groupName).getMembersAsString();
	}

	/**
	 * Retrieves the group names working on a given discipline
	 * 
	 * @param discipline the discipline of interest
	 * @return list of group names sorted alphabetically
	 */
	public List<String> getGroupNamesOfAGivenDiscipline(String discipline) {
		List<String> result = new ArrayList<>();
		for(Group g : groups.values()) {
			if(g.getDisciplines().contains(discipline))
				result.add(g.getName());
		}
		return result;
	}

	//R3
	/**
	 * Add a new journal articles, with a given title and the list of authors.
	 * 
	 * The journal must have been previously defined.
	 * 
	 * The authors (at least one) are members of research groups.
	 * 
	 * @param title title of the article
	 * @param journalName name of the journal
	 * @param authorNames list of authors
	 * @throws EvaluationsException thrown if journal not defined or no author provided
	 */
	public void addPaper (String title, String journalName, String... authorNames) throws EvaluationsException {
		if(! journals.containsKey(journalName))
			throw new EvaluationsException();
		if(authorNames.length <= 0)
			throw new EvaluationsException();
		Article a = new Article(title, journals.get(journalName));
		for(String aN : authorNames) {
			members.get(aN).addArticle(a);
			a.addAuthor(members.get(aN));    		
		}
		journals.get(journalName).addArticle(a);
	}



	/**
	 * Retrieves the titles of the articles authored by a member of a research group
	 * 
	 * @param memberName name of the group member
	 * @return list of titles sorted alphabetically
	 */
	public List<String> getTitlesOfAGivenAuthor (String memberName) {
		return members.get(memberName).getArticles().stream().map(a -> a.getTitle()).collect(Collectors.toList());
	}


	//R4
	/**
	 * Returns the points for a given group member.
	 * 
	 * Points are collected for each article the member authored.
	 * The points are those corresponding to the level of the
	 * journal where the article is published, divided by
	 * the total number of authors.
	 * 
	 * The total points are eventually rounded to the closest integer.
	 * 
	 * @param memberName name of the group member
	 * @return total points
	 */
	public int getPointsOfAGivenAuthor (String memberName) {
		return members.get(memberName).getPoints();
	}

	/**
	 * Computes the total points collected by all members of all groups
	 *  
	 * @return the total points
	 */
	public int evaluate() {
		int result = 0;
		for(Journal j: journals.values()) {
			for(Article a : j.getArticles()) {
				result += a.getJournal().getPoints();
			}
		}
		return result;
	}


	//R5 Statistiche
	/**
	 * For each group return the total points collected
	 * by all the members of each research group.
	 * 
	 * Group names are sorted alphabetically.
	 * 
	 * @return the map associating group name to points
	 */
	public SortedMap<String, Integer> pointsForGroup() {
		SortedMap<String, Integer> result = new TreeMap<>();
		for(Group g : groups.values()) {
			result.put(g.getName(), g.totalPoints());
		}
		return result;
	}

	/**
	 * For each amount of points returns a list of
	 * the authors (group members) that achieved such score.
	 * 
	 * Points are sorted in decreasing order.
	 * 
	 * @return the map linking the number of point to the list of authors
	 */
	public SortedMap<Integer, List<String>> getAuthorNamesPerPoints () {
		SortedMap<Integer, List<String>> result = new TreeMap<>((i1, i2) -> i2.compareTo(i1));
		List<String> tmp;
		for(Member m : members.values()) {
			if(getPointsOfAGivenAuthor(m.getName())> 0) {
				if(!result.containsKey(getPointsOfAGivenAuthor(m.getName())))
					tmp = new ArrayList<>();
				else
					tmp = result.get(getPointsOfAGivenAuthor(m.getName()));
				tmp.add(m.getName());
				tmp.sort((s1, s2) -> s1.compareTo(s2));
				result.put(getPointsOfAGivenAuthor(m.getName()), tmp);
			}
		}
		return result;
	}


}