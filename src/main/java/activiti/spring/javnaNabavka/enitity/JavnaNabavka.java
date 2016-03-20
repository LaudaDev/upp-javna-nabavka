package activiti.spring.javnaNabavka.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JavnaNabavka {

	@Id
	@GeneratedValue
	private Long id;
	private String nazivNarucioca;
	private String adresaNarucioca;
	private Long rbNabavke;
	private String predmetNabavke;
	private String nazivNabavke;
	private String oznakaNabavke;
	private String vrstaPostupka;
	private Double procenjenaVrednost;
	
	public JavnaNabavka() {
		super();
	}
	
	public JavnaNabavka(String nazivNarucioca, String adresaNarucioca, Long rbNabavke, String predmetNabavke, String nazivNabavke, String oznakaNabavke, String vrstaPostupka, Double procenjenaVrednost) {
		super();
		this.nazivNabavke = nazivNabavke;
		this.adresaNarucioca = adresaNarucioca;
		this.rbNabavke = rbNabavke;
		this.predmetNabavke = predmetNabavke;
		this.nazivNabavke = nazivNabavke;
		this.oznakaNabavke = oznakaNabavke;
		this.vrstaPostupka = vrstaPostupka;
		this.procenjenaVrednost = procenjenaVrednost;
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

	public Long getRbNabavke() {
		return rbNabavke;
	}

	public void setRbNabavke(Long rbNabavke) {
		this.rbNabavke = rbNabavke;
	}

	public String getPredmetNabavke() {
		return predmetNabavke;
	}

	public void setPredmetNabavke(String predmetNabavke) {
		this.predmetNabavke = predmetNabavke;
	}

	public String getNazivNabavke() {
		return nazivNabavke;
	}

	public void setNazivNabavke(String nazivNabavke) {
		this.nazivNabavke = nazivNabavke;
	}

	public String getOznakaNabavke() {
		return oznakaNabavke;
	}

	public void setOznakaNabavke(String oznakaNabavke) {
		this.oznakaNabavke = oznakaNabavke;
	}

	public String getVrstaPostupka() {
		return vrstaPostupka;
	}

	public void setVrstaPostupka(String vrstaPostupka) {
		this.vrstaPostupka = vrstaPostupka;
	}

	public Double getProcenjenaVrednost() {
		return procenjenaVrednost;
	}

	public void setProcenjenaVrednost(Double procenjenaVrednost) {
		this.procenjenaVrednost = procenjenaVrednost;
	}
}
