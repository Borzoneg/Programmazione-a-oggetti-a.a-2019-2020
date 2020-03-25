package phonebook;

/**
 * Represents a phonebook that contains
 * contact information entries.
 * 
 * Each entry includes first name, last name, 
 * and phone number.
 * 
 * Entries can be accessed by position using {@link #get(int)}
 * or searched by string match using {@link #find(String)}.
 *
 */
public class PhoneBook {
	/*
	 * Rappresenta il numero massimo di contatti inseribili in rubrica
	 */
	private static final int NUMERO_MAX_CONTATTI = 100;
	private final String name;
	private Contatto[] contatti = new Contatto[NUMERO_MAX_CONTATTI];
	
  /*
   * Create a new phonebook with given name
   */
  public PhoneBook(String name) {  
	  this.name = name;
  }

  /*
   * Return the phonebook name
   */
  public String getName() {
	  //TODO: to be implemented 
    return name;
  }

  /**
   * Insert a new contact at the end
   * 
   * @param first first name of the new contact
   * @param last  last name of the new contact
   * @param number phone number of the contact
   */  
  public void add(String first, String last, String number){
	  Contatto c = new Contatto(first, last, number);
	  for(int i=0; i<contatti.length; i++) {
		  if(contatti[i] == null) {
			  contatti[i] = c;
			  break; // potrebbe anche essere un return visto che poi non si fa nulla, meglio però break, per aggiunte future
		  }
	  }
  }

  /**
   * Return the first contact
   */  
  public String first() {
	  return contatti[0].toString();
  }

  /**
   * Return the i-th contact (assume the first index is 1)
   * 
   * @param index index of the contact, starting at 1 
   */
  public String get(int index) {
	  return contatti[index-1].toString();
  }

  /**
   * Return a string containing the list of textual 
   * representation of all contacts, separated by  {@code ", "}.
   * 
   * List starts with {@code "("} and ends with  {@code ")"} 
   */
  public String toString() {
	  StringBuffer str = new StringBuffer("(");
	  
	  for(int i=0; i<contatti.length; i++){
		  if(contatti[i] == null)
			  break;
		  if(i>0)
			  str.append(", ");
		  str.append(contatti[i].toString());
	  }
	  str.append(")");
	  return str.toString();
  }

  /**
   * Return the textual representation of first
   * contact containing "needle"
   */
  public String find(String needle) {
	  for(Contatto c : contatti){
		  if(c==null) break;
		  if(c.contiene(needle))
			  return c.toString();
	  }
	  return "non trovato";
  }
  
}
