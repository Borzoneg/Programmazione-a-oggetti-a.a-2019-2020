package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import sun.font.CreatedFontTracker;

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
    private ArrayList<Integer> mins = new ArrayList<>();
    private ArrayList<Integer> maxs = new ArrayList<>();
    private ArrayList<Municipality> municipalities = new ArrayList<>();
    private ArrayList<MountainHut> mountainHuts = new ArrayList<>();


    /**
     * Create a region with the given name.
     * 
     * @param name
     *            the name of the region
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
	    mins.add(Integer.valueOf(s.split("-")[0]));
	    maxs.add(Integer.valueOf(s.split("-")[1]));	
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
	for(i=0; i<mins.size(); i++) {
	    if(altitude > mins.get(i) && altitude < maxs.get(i))
		return String.format("%d-%d", mins.get(i), maxs.get(i));	
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
	return new Municipality(name, province, altitude);
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
	return new MountainHut(name, category, bedsNumber, municipality);
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
	return new MountainHut(name, category, bedsNumber, municipality, altitude);
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
    @SuppressWarnings("unused")
    private static List<String> readData(String file) {
	try (BufferedReader in = new BufferedReader(new FileReader(file))) {
	    return in.lines().collect(toList());
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	    return null;
	}
    }

    /**
     * Count the number of municipalities with at least a mountain hut per each
     * province.
     * 
     * @return a map with the province as key and the number of municipalities as
     *         value
     */
    public Map<String, Long> countMunicipalitiesPerProvince() {
	return null;
    }

    /**
     * Count the number of mountain huts per each municipality within each province.
     * 
     * @return a map with the province as key and, as value, a map with the
     *         municipality as key and the number of mountain huts as value
     */
    public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
	return null;
    }

    /**
     * Count the number of mountain huts per altitude range. If the altitude of the
     * mountain hut is not available, use the altitude of its municipality.
     * 
     * @return a map with the altitude range as key and the number of mountain huts
     *         as value
     */
    public Map<String, Long> countMountainHutsPerAltitudeRange() {
	return null;
    }

    /**
     * Compute the total number of beds available in the mountain huts per each
     * province.
     * 
     * @return a map with the province as key and the total number of beds as value
     */
    public Map<String, Integer> totalBedsNumberPerProvince() {
	return null;
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
	return null;
    }

    /**
     * Compute the municipality names per number of mountain huts in a municipality.
     * The lists of municipality names must be in alphabetical order.
     * 
     * @return a map with the number of mountain huts in a municipality as key and a
     *         list of municipality names as value
     */
    public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
	return null;
    }

}
