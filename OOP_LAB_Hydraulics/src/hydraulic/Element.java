package hydraulic;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public class Element {
	
	protected String name;
	protected String type;
	private Element inputElement;
	protected Element outputElement;
	protected double inputFlow;
	protected double outputFlow;
	
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		this.name = name;
	}

	/**
	 * @return a string representation of the element	
	 */
	public String toString() {
		String str = name + "\n";
		if(inputElement != null)
			str += "Input: " + inputElement.getName() + "\n";
		if(outputElement != null)
			str += "Output: " + outputElement.getName() + "\n";
		str += "Inputflow: " + inputFlow + "\n";
		str += "Outputflow: " + outputFlow + "\n";
		return str;
	}
	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		outputElement = elem;	
		elem.setInputFlow(outputFlow);
		elem.setInput(this);
	}
	
	
	/**
	 * Set the input flow of the element 
	 * @param inputFlow the input flow of the element
	 */
	protected void setInputFlow(double inputFlow) {
		this.inputFlow = inputFlow;
	}


	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		return outputElement;
	}

	
	/**
	 * Update the values of input and output flow according to the element connections
	 * @param observer notify the input and output stream together with name and type
	 */
	public void update(SimulationObserver observer) {
		outputFlow = inputFlow;
		observer.notifyFlow(type, name, inputFlow, outputFlow);
		if(outputElement != null) {
			outputElement.setInputFlow(outputFlow);
			outputElement.update(observer);
		}	
	}
	
	
	/**
	 * Set the input element of the element this method is called from
	 * @param elem the element that will be placed upstream
	 */
	public void setInput(Element elem) {
		inputElement = elem;
	}
	
}
