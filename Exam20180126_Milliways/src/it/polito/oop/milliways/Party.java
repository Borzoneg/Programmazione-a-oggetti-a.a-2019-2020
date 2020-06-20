package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Party {

    private Map<Race, Integer> components = new HashMap<>();

    public void addCompanions(Race race, int num) {
	components.put(race, components.containsKey(race) ? components.get(race) + num : num);
    }

    public int getNum() {
	int result = 0;
	for(Integer i : components.values())
	    result += i;
	return result;
    }

    public int getNum(Race race) {
	return components.get(race);
    }

    public List<String> getRequirements() {
	List<String> result = new ArrayList<>();
	components.keySet().stream().forEach((r -> r.getRequirements().stream().forEach(s -> {if(! result.contains(s))result.add(s);})));
	result.sort((s1, s2) -> s1.compareTo(s2));
	return result;
    }

    public Map<String,Integer> getDescription(){
	Map<String,Integer> result = new TreeMap<>();
	components.keySet().stream().forEach(r -> result.put(r.getName(), components.get(r)));
	return result;
    }

    public void updateRaces() {
	components.keySet().stream().forEach(r -> r.addN(components.get(r)));
    }

}
