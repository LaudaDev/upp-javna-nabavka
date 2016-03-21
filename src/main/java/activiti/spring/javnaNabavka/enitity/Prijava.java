package activiti.spring.javnaNabavka.enitity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Prijava implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6592634497279028635L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String user;
	private String nazivPodnosiocaPrijave;
	private String adresaPodnosiocaPrijave;
	private String emailPodnosiocaPrijave;
	
	public Prijava() {
		super();
	}
	
	public Prijava(String nazivPodnosiocaPrijave, String user, String adresaPodnosiocaPrijave, String emailPodnosiocaPrijave) {
		super();
		this.nazivPodnosiocaPrijave = nazivPodnosiocaPrijave;
		this.user = user;
		this.adresaPodnosiocaPrijave = adresaPodnosiocaPrijave;
		this.emailPodnosiocaPrijave = emailPodnosiocaPrijave;
	}
	
	public Prijava(String nazivPodnosiocaPrijave, String adresaPodnosiocaPrijave, String emailPodnosiocaPrijave) {
		super();
		this.nazivPodnosiocaPrijave = nazivPodnosiocaPrijave;
		this.adresaPodnosiocaPrijave = adresaPodnosiocaPrijave;
		this.emailPodnosiocaPrijave = emailPodnosiocaPrijave;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNazivPodnosiocaPrijave() {
		return nazivPodnosiocaPrijave;
	}

	public void setNazivPodnosiocaPrijave(String nazivPodnosiocaPrijave) {
		this.nazivPodnosiocaPrijave = nazivPodnosiocaPrijave;
	}

	public String getAdresaPodnosiocaPrijave() {
		return adresaPodnosiocaPrijave;
	}

	public void setAdresaPodnosiocaPrijave(String adresaPodnosiocaPrijave) {
		this.adresaPodnosiocaPrijave = adresaPodnosiocaPrijave;
	}

	public String getEmailPodnosiocaPrijave() {
		return emailPodnosiocaPrijave;
	}

	public void setEmailPodnosiocaPrijave(String emailPodnosiocaPrijave) {
		this.emailPodnosiocaPrijave = emailPodnosiocaPrijave;
	}
}
