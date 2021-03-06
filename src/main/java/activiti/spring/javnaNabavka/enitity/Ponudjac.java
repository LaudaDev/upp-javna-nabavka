package activiti.spring.javnaNabavka.enitity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ponudjac {
	
	@Id
	private String id;
	private String name;
	private String lastName;
	private String email;
	private String pass;
	private boolean sentEntry;
	private boolean canSendOffer;
	private boolean sentOffer;
	
	public Ponudjac() {
		super();
	}
	
	public Ponudjac(String id, String name, String lastName, String email, String pass, boolean sentEntry, boolean canSendOffer, boolean sentOffer) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.sentEntry = sentEntry;
		this.canSendOffer = canSendOffer;
		this.sentOffer = sentOffer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isSentEntry() {
		return sentEntry;
	}

	public void setSentEntry(boolean sentEntry) {
		this.sentEntry = sentEntry;
	}

	public boolean isCanSendOffer() {
		return canSendOffer;
	}

	public void setCanSendOffer(boolean canSendOffer) {
		this.canSendOffer = canSendOffer;
	}

	public boolean isSentOffer() {
		return sentOffer;
	}

	public void setSentOffer(boolean sentOffer) {
		this.sentOffer = sentOffer;
	}
	
	
}
