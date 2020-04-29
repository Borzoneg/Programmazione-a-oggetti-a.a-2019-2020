package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

    
    	private Element[] outputs;
    	private double[] proportions;
    	private double[] outputFlows;
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name); //you can edit also this line
		type = "Multisplit";
		outputs = new Element[numOutput];
		outputFlows = new double[numOutput];
	}
    
	
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
        return outputs;
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		outputs[noutput] = elem;
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
	    	if(proportions.length != outputs.length) {
	    	    System.out.println("Too few proportions for the multisplit");
	    	    return;
	    	}
		this.proportions = proportions;
	}
	
	
	/**
	 * update method for split element, it sets the input stream of the two elements connected to half the input of the split or to full
	 */
	@Override
	public void update(SimulationObserver observer) {
	    int i;
	    for(i=0; i<proportions.length; i++) {
		outputFlows[i] = proportions[i]*inputFlow; 
	    }
		
	    observer.notifyFlow(type, name, inputFlow, outputFlows);
	    for(i=0; i<outputs.length; i++) {
		outputs[i].setInputFlow(outputFlows[i]);
		outputs[i].update(observer);
	    }
	}
	
	
	/**
	 * Create the layout of the single element and attach the layouts of its outputs to it
	 */
	@Override
	public String cascadeLayout(int length) {
	    String str = "";
	    int i, partialLength;
	    str += String.format("[%s]%s", name, type);
	    length += str.length();
	    
	    str += " +-> ";
	    partialLength = str.length();
	    str += outputs[0].cascadeLayout(length + partialLength);
	    for(Element o : outputs) {
		if(o != null) {
		    	str += "\n";
    			for(i=0; i<length; i++) str += " ";
    			str += " +-> ";
    			str += o.cascadeLayout(length + partialLength);
		}
	    }
	    return str;	
	}
}
