package evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Group {
	
	private String name;
	private List<Member> members = new ArrayList<>();
	private List<String> disciplines =  new ArrayList<>();
	
	public Group(String name) {
		this.name = name;
	}
	
	public void addMember(Member member) {
		members.add(member);
	}
	
	public void addDiscipline(String discipline) {
		disciplines.add(discipline);
	}
	
	public List<String> getMembersAsString(){
		members.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
		return members.stream().map(m -> m.getName()).collect(Collectors.toList());
	}
	
	public List<Member> getMembers(){
		return members;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getDisciplines(){
		return disciplines;
	}

	public Integer totalPoints() {
		int result = 0;
		for(Member m : members) {
			result += m.getPoints();
		}
		return result;
	}
}
