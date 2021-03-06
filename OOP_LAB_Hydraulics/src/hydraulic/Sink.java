package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends ElementExt {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		type = "Sink";
		outputFlow = Double.NaN;
	}
	
	/**
	 * connect method for sink, this is done to prevent connect() to do anything when invoked on sink
	 */
	@Override
	public void connect(Element elem) {
	}
	
	
	/**
	 * Update the values of input and output flow according to the element connections
	 * @param observer notify the input and output stream together with name and type
	 */
	public void update(SimulationObserver observer, boolean enableMaxFlowCheck) {
	    if(enableMaxFlowCheck) {
		    if(inputFlow > maxFlow)
			((SimulationObserverExt) observer).notifyFlowError(type, name, inputFlow, maxFlow);
		}
	    	observer.notifyFlow(type, name, inputFlow, outputFlow);
		if(outputElement != null) {
			outputElement.setInputFlow(outputFlow);
			outputElement.update(observer, enableMaxFlowCheck);
		}	
	}
}
