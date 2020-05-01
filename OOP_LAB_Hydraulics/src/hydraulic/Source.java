package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {

	public Source(String name) {
		super(name);
		type = "Source";
		inputFlow = Double.NaN;
	}

	
	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		outputFlow = flow;
	}
	
	/**
	 * This method have to do nothing when invoked on src
	 */
	public void setMaxFlow(double maxFlow) {
	}
	
	
	/**
	 * Update the values of inputflow of the connected elements according to the element connections
	 * @param observer notify the input and output stream together with name and type
	 */
	@Override
	public void update(SimulationObserver observer, boolean enableMaxFlowCheck) {
		observer.notifyFlow(type, name, inputFlow, outputFlow);
		if(outputElement != null) {
			outputElement.setInputFlow(outputFlow);
			outputElement.update(observer, enableMaxFlowCheck);
		}	
	}
	
	
	
	public String toString() {
	    return null;
	}
}
