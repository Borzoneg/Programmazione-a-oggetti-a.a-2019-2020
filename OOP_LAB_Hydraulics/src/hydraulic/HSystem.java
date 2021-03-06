package hydraulic;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	protected List<Element> listElements = new ArrayList<Element>();
	
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		listElements.add(elem);
	}
	
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
		if(listElements.size() > 0) {
			Element [] elements = new Element[listElements.size()];
			int i = 0;
			for(Element e: listElements) {
				elements[i] = e;
				i++;
			}
			return elements;
		}
		return new Element[0];
	}
	
		
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		for(Element e : listElements) {
			if(e instanceof Source) {
				e.update(observer, false);
			}
		}
	}

}
