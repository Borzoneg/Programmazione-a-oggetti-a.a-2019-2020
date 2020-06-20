package managingProperties;

public class Request {

    enum State {pending, assigned, completed;}

    private Professional professional;
    private String apartement;
    private Owner owner;
    private State state;
    private String profession;
    private int id;
    private Building building;

    public Request(Owner owner, String apartment, String profession, int id, Building building) {
	this.owner = owner;
	this.apartement  = apartement;
	this.profession = profession;
	state = State.pending;
	this.id = id;
	this.building = building;
    }



    public void setProfessional(Professional professional) {
	this.professional = professional;
	state = State.assigned;
    }



    public String getProfession() {
	return profession;
    }


    public int getId() {
	return id;
    }

    public String getState() {
	return state.toString();
    }



    public void complete(int amount) {
    	state= State.completed;
    	owner.addCharge(amount);
    	building.addCharge(amount, professional.getName());
    	professional.addCharge(amount);
    }

}
