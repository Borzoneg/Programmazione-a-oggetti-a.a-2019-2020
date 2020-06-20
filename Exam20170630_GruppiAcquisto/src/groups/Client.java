package groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Client {
    
    private String name;
    private Set<Group> groups = new TreeSet<>((g1, g2) -> g1.getName().compareTo(g2.getName()));
    
    public Client(String name) {
	this.name = name;
    }
    
    public void addGroup(Group group) {
	groups.add(group);
    }

    public List<String> getGroups() {
	List<String> result = new ArrayList<>();
	groups.stream().forEach(g -> result.add(g.getName()));
	return result;
    }

    public String getName() {
	return name;
    }

}
