package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {

    private String name;
    private ArrayList<Integer> minsAltitudes = new ArrayList<>();
    private ArrayList<Integer> maxsAltitudes = new ArrayList<>();
    private ArrayList<String> altitudes = new ArrayList<>();
    private ArrayList<Municipality> municipalities = new ArrayList<>();
    private ArrayList<MountainHut> mountainHuts = new ArrayList<>();
    //private ArrayList<Province> provinces = new ArrayList<>();   

    /**
     * Create a region with the given name.
     * 
     * @param name the name of the region
     */
    public Region(String name) {
	this.name = name;
    }

    /**
     * Return the name of the region.
     * 
     * @return the name of the region
     */
    public String getName() {
	return name;
    }

    /**
     * Create the ranges given their textual representation in the format
     * "[minValue]-[maxValue]".
     * 
     * @param ranges
     *            an array of textual ranges
     */
    public void setAltitudeRanges(String... ranges) {
	for (String s : ranges) {
	    altitudes.add(s);
	    minsAltitudes.add(Integer.valueOf(s.split("-")[0]));
	    maxsAltitudes.add(Integer.valueOf(s.split("-")[1]));	
	}
    }

    /**
     * Return the textual representation in the format "[minValue]-[maxValue]" of
     * the range including the given altitude or return the default range "0-INF".
     * 
     * @param altitude
     *            the geographical altitude
     * @return a string representing the range
     */
    public String getAltitudeRange(Integer altitude) {
	int i;
	for(i=0; i<minsAltitudes.size(); i++) {
	    if(altitude >= minsAltitudes.get(i) && altitude <= maxsAltitudes.get(i))
		return String.format("%d-%d", minsAltitudes.get(i), maxsAltitudes.get(i));	
	}
	return "0-INF";
    }

    /**
     * Create a new municipality if it is not already available or find it.
     * Duplicates must be detected by comparing the municipality names.
     * 
     * @param name
     *            the municipality name
     * @param province
     *            the municipality province
     * @param altitude
     *            the municipality altitude
     * @return the municipality
     */
    public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
	for(Municipality m : municipalities)
	    if(m.getName().equals(name))
		return m;	
	Municipality municipality = new Municipality(name, province, altitude);
	municipalities.add(municipality);
	return municipality;
    }

    /**
     * Return all the municipalities available.
     * 
     * @return a collection of municipalities
     */
    public Collection<Municipality> getMunicipalities() {
	return municipalities;
    }

    /**
     * Create a new mountain hut if it is not already available or find it.
     * Duplicates must be detected by comparing the mountain hut names.
     *
     * @param name
     *            the mountain hut name
     * @param category
     *            the mountain hut category
     * @param bedsNumber
     *            the number of beds in the mountain hut
     * @param municipality
     *            the municipality in which the mountain hut is located
     * @return the mountain hut
     */
    public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber, Municipality municipality) {
	for(MountainHut mh : mountainHuts)
	    if(mh.getName().equals(name))
		return mh;
	MountainHut mountainHut = new MountainHut(name, category, bedsNumber, municipality); 
	mountainHuts.add(mountainHut);
	return mountainHut;
    }

    /**
     * Create a new mountain hut if it is not already available or find it.
     * Duplicates must be detected by comparing the mountain hut names.
     * 
     * @param name
     *            the mountain hut name
     * @param altitude
     *            the mountain hut altitude
     * @param category
     *            the mountain hut category
     * @param bedsNumber
     *            the number of beds in the mountain hut
     * @param municipality
     *            the municipality in which the mountain hut is located
     * @return a mountain hut
     */
    public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
	for(MountainHut mh : mountainHuts)
	    if(mh.getName().equals(name))
		return mh;

	MountainHut mountainHut = new MountainHut(name, category, bedsNumber, municipality, altitude); 
	mountainHuts.add(mountainHut);
	return mountainHut;
    }

    /**
     * Return all the mountain huts available.
     * 
     * @return a collection of mountain huts
     */
    public Collection<MountainHut> getMountainHuts() {
	return mountainHuts;
    }

    /**
     * Factory methods that creates a new region by loadomg its data from a file.
     * 
     * The file must be a CSV file and it must contain the following fields:
     * <ul>
     * <li>{@code "Province"},
     * <li>{@code "Municipality"},
     * <li>{@code "MunicipalityAltitude"},
     * <li>{@code "Name"},
     * <li>{@code "Altitude"},
     * <li>{@code "Category"},
     * <li>{@code "BedsNumber"}
     * </ul>
     * 
     * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
     * may be empty.
     * 
     * @param name
     *            the name of the region
     * @param file
     *            the path of the file
     */
    public static Region fromFile(String name, String filename) {
	Region region = new Region(name);
	List<String> file = readData(filename);
	file.stream().
	skip(1). // skip intestation line
	forEach(line -> {
	    String [] splitted = line.split(";"); // split the line to get the fields
	    Municipality municipality = region.createOrGetMunicipality(splitted[1], splitted[0], Integer.valueOf(splitted[2])); // get or create the municipality with the fields
	    MountainHut mountainHut = splitted[4].isEmpty() ? region.createOrGetMountainHut(splitted[3], splitted[5], Integer.valueOf(splitted[6]), municipality) : // get or create the mh with the fields, we're not using th mh object just assign it to use the conditional operator
		region.createOrGetMountainHut(splitted[3], Integer.valueOf(splitted[4]), splitted[5], Integer.valueOf(splitted[6]), municipality);
	});

	return region;
    }

    /**
     * Internal class that can be used to read the lines of
     * a text file into a list of strings.
     * 
     * When reading a CSV file remember that the first line
     * contains the headers, while the real data is contained
     * in the following lines.
     * 
     * @param file the file name
     * @return a list containing the lines of the file
     */
    private static List<String> readData(String file) {
	try (BufferedReader in = new BufferedReader(new FileReader(file))) {
	    return in.lines().collect(toList());
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	    return null;
	}
    }

    /**
     * Check if the altitude is into the range
     * @param range
     * @param altitude
     * @return true if the altitude is inside the range, false else
     */
    public boolean altitudeIntoRange(String range, int altitude) {
	int min, max;
	min = Integer.valueOf(range.split("-")[0]);
	max = Integer.valueOf(range.split("-")[1]);
	if(altitude >= min && altitude <= max)
	    return true;
	return false;
    }

    /**
     * Count the number of municipalities with at least a mountain hut per each
     * province.
     * 
     * @return a map with the province as key and the number of municipalities as
     *         value
     */
    public Map<String, Long> countMunicipalitiesPerProvince() {
	Map<String, Long> mhPerProv = new HashMap<>();
	municipalities.
	stream().
	forEach(mun -> mhPerProv.put(mun.getProvince(), 
		(!mhPerProv.containsKey(mun.getProvince())) ? 1 : mhPerProv.get(mun.getProvince()) + 1));
	return mhPerProv;
    }

    /**
     * Count the number of mountain huts per each municipality within each province.
     * 
     * @return a map with the province as key and, as value, a map with the
     *         municipality as key and the number of mountain huts as value
     */
    public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
	Map<String, Map<String, Long>> mhPerMunPerProv = new HashMap<>();
	mountainHuts.
	stream().
	forEach(mh -> 	
	{
	    if(!mhPerMunPerProv.containsKey(mh.getMunicipality().getProvince())) {
		Map<String, Long> tmpMap = new HashMap<>();
		tmpMap.put(mh.getMunicipality().getName(), (long) 1);
		mhPerMunPerProv.put(mh.getMunicipality().getProvince(), tmpMap);
	    }
	    else {
		Map<String, Long> tmpMap = mhPerMunPerProv.get(mh.getMunicipality().getProvince());
		if(!tmpMap.containsKey(mh.getMunicipality().getName().toUpperCase()))
		    tmpMap.put(mh.getMunicipality().getName(), (long) 1);
		else 
		    tmpMap.put(mh.getMunicipality().getName(), tmpMap.get(mh.getMunicipality().getName()) + 1);
		  
	    }	
	});
	return mhPerMunPerProv;
    }

    /**
     * Count the number of mountain huts per altitude range. If the altitude of the
     * mountain hut is not available, use the altitude of its municipality.
     * 
     * @return a map with the altitude range as key and the number of mountain huts
     *         as value
     */ 	
    public Map<String, Long> countMountainHutsPerAltitudeRange() {
	Map<String, Long> mhPerAltitude = new HashMap<>();

	for(String s : altitudes)
	    mhPerAltitude.put(s, (long) 0);
	mhPerAltitude.put("0-INF", (long) 0);

	mountainHuts.
	stream().
	forEach(mh -> {
	    boolean inserito = false;
	    for(String range : altitudes) {
		int i =  mh.getAltitude().isPresent() ? mh.getAltitude().get() : mh.getMunicipality().getAltitude();
		if(altitudeIntoRange(range, i)) {
		    mhPerAltitude.put(range, mhPerAltitude.get(range) + 1);
		    inserito = true;
		}
		
	    }
	    if(!inserito)
		mhPerAltitude.put("0-INF", mhPerAltitude.get("0-INF") + 1);
	});
	return mhPerAltitude;
    }

    /**
     * Compute the total number of beds available in the mountain huts per each
     * province.
     * 
     * @return a map with the province as key and the total number of beds as value
     */
    public Map<String, Integer> totalBedsNumberPerProvince() {
	Map<String, Integer> bedPerProv = new HashMap<>();

	mountainHuts.
	stream().
	forEach(mh -> bedPerProv.put(mh.getMunicipality().getProvince(), (bedPerProv.containsKey(mh.getMunicipality().getProvince())) ? 
		bedPerProv.get(mh.getMunicipality().getProvince())+mh.getBedsNumber(): mh.getBedsNumber()));
	return bedPerProv;
    }

    /**
     * Compute the maximum number of beds available in a single mountain hut per
     * altitude range. If the altitude of the mountain hut is not available, use the
     * altitude of its municipality.
     * 
     * @return a map with the altitude range as key and the maximum number of beds
     *         as value
     */
    public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
	Map<String, Optional<Integer>> bedsPerAltitude = new HashMap<>();

	for(String s : altitudes)
	    bedsPerAltitude.put(s, Optional.of(0));
	bedsPerAltitude.put("0-INF", Optional.of(0));
	
	mountainHuts.
	stream().
	forEach(mh -> {
	    for(String range : altitudes) {
		bedsPerAltitude.put("0-INF", Optional.of(Integer.max(bedsPerAltitude.get("0-INF").get(), mh.getBedsNumber())));
		int i =  mh.getAltitude().isPresent() ? mh.getAltitude().get() : mh.getMunicipality().getAltitude();
		if(altitudeIntoRange(range, i))
		    bedsPerAltitude.put(range, Optional.of(Integer.max(bedsPerAltitude.get(range).get(), mh.getBedsNumber())));
		    
	    }
	});
	return bedsPerAltitude;
    }

    /**
     * Compute the municipality names per number of mountain huts in a municipality.
     * The lists of municipality names must be in alphabetical order.
     * 
     * @return a map with the number of mountain huts in a municipality as key and a
     *         list of municipality names as value
     */
    public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
	Map<Long, List<String>> munPerCountOfMh = new HashMap<>();
	Map<String, Long> tmpMap = new HashMap<>();
//	munPerCountOfMh.put((long)0, (List)municipalities);
	mountainHuts.
	stream().
	forEach(mh -> {
	    tmpMap.put(mh.getMunicipality().getName(), tmpMap.containsKey(mh.getMunicipality().getName()) ? tmpMap.get(mh.getMunicipality().getName()) + 1 : 1);
	});
	tmpMap.keySet().
	stream().
	forEach(mun -> {
	    if(munPerCountOfMh.get(tmpMap.get(mun)) == null) {
		List<String> tmpList = new ArrayList<>();
		tmpList.add(mun);
		munPerCountOfMh.put(tmpMap.get(mun), tmpList);
	    }
	    else {
		List<String> tmpList = munPerCountOfMh.get(tmpMap.get(mun));
		tmpList.add(mun);
		tmpList.sort((str1, str2) -> str1.compareTo(str2));
		munPerCountOfMh.put(tmpMap.get(mun), tmpList);
	    }
	});
	return munPerCountOfMh;
    }

}
