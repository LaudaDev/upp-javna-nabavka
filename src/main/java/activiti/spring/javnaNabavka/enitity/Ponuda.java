package activiti.spring.javnaNabavka.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ponuda {
	@Id
	@GeneratedValue
	private Long id;
	private String user;
	private String nazivNarucioca;
	private String adresaNarucioca;
	private Double procenjenaVrednost;
	private Double predlozenaCena;
	
	public Ponuda() {
		super();
	}

	public Ponuda(String user, String nazivNarucioca, String adresaNarucioca, Double procenjenaVrednost, Double predlozenaCena) {
		super();
		this.user = user;
		this.nazivNarucioca = nazivNarucioca;
		this.adresaNarucioca = adresaNarucioca;
		this.procenjenaVrednost = procenjenaVrednost;
		this.predlozenaCena = predlozenaCena;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNazivNarucioca() {
		return nazivNarucioca;
	}

	public void setNazivNarucioca(String nazivNarucioca) {
		this.nazivNarucioca = nazivNarucioca;
	}

	public String getAdresaNarucioca() {
		return adresaNarucioca;
	}

	public void setAdresaNarucioca(String adresaNarucioca) {
		this.adresaNarucioca = adresaNarucioca;
	}

	public Double getProcenjenaVrednost() {
		return procenjenaVrednost;
	}

	public void setProcenjenaVrednost(Double procenjenaVrednost) {
		this.procenjenaVrednost = procenjenaVrednost;
	}

	public Double getPredlozenaCena() {
		return predlozenaCena;
	}

	public void setPredlozenaCena(Double predlozenaCena) {
		this.predlozenaCena = predlozenaCena;
	}
}
