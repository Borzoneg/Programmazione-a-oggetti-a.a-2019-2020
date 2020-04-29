package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
	    String str = new String();
	    Element tmp;
	    for(Element e : listElements) {
		if(e instanceof Source) {
		    str += e.cascadeLayout(0);
		}
	    }
		return str;
	}
	
	
	/**
	 * Get element from the list of elements base by name
	 * @param name
	 * @return
	 */
	public Element getElement(String name) {
	    for(Element e : listElements) {
		 if(e.getName().equals(name))
		     return e;
	    }
	    return null;
	}
	
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public void deleteElement(String name) {
	    Element toDelete  = getElement(name);
	    if(toDelete == null) {
		System.out.println("Elemento non trovato");
		return;
		}
	    
	    if(toDelete instanceof Split || toDelete instanceof Multisplit) { // delete split or multisplit cases
		Element[] outputs = ((Split) toDelete).getOutputs();
		Element tmp, tmp2; 
		for(Element o : outputs) { // for each output of the split/multisplit
		    for(tmp=o.getOutput(); o != null; tmp = tmp2) { // I have to delete all the output connected from the split to the end of the "chain"
			tmp2 = tmp.getOutput();
			listElements.remove(tmp);
		    }
		    listElements.remove(o); // remove the ouput
		}

	    }
	    
	    else // cases the element that we have to delete are not a split/multisplit
		toDelete.getInput().setOutput(toDelete.getOutput()); // setting output of the input of the element as the output of the element 
	    
	
	    listElements.remove(toDelete); // remove the element
	}

	
	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
	}
	
}
