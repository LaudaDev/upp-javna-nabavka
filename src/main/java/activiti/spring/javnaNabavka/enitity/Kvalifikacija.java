package activiti.spring.javnaNabavka.enitity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Kvalifikacija {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nazivNarucioca;
	private String adresaNarucioca;
	private String webNarucioca;
	private String vrstaNarucioca;
	private String vrstaPostupka;
	private String opisPredmetaNabavke;
	private String nazivNabavke;
	private String oznakaNabavke;
	private String obimRadova;
	private String mestoIzvrsenjaRadova;
	private Long brojPartija;
	private String posebnaNapomena;
	private String kriterijumZaDodeluUgovora;
	private String webKonkursneDokumentacije;
	private String webNadleznogOrgana;
	private Date rokZaPodnosenjePonuda;
	private String nacinPodnosenjaPonuda;
	private Date vremeOtvaranjaPonuda;
	private String mestoOtvaranjaPonuda;
	private String dodatniUslovi;
	private Date rokZaDonosenjeOdluke;
	private String kontaktLice;
	
	public Kvalifikacija() {
		super();
	}

	public Kvalifikacija(String nazivNarucioca, String adresaNarucioca, String webNarucioca, String vrstaNarucioca,
			String vrstaPostupka, String opisPredmetaNabavke, String nazivNabavke, String oznakaNabavke,
			String obimRadova, String mestoIzvrsenjaRadova, Long brojPartija, String posebnaNapomena,
			String kriterijumZaDodeluUgovora, String webKonkursneDokumentacije, String webNadleznogOrgana,
			Date rokZaPodnosenjePonuda, String nacinPodnosenjaPonuda, Date vremeOtvaranjaPonuda,
			String mestoOtvaranjaPonuda, String dodatniUslovi, Date rokZaDonosenjeOdluke, String kontaktLice) {
		super();
		this.nazivNarucioca = nazivNarucioca;
		this.adresaNarucioca = adresaNarucioca;
		this.webNarucioca = webNarucioca;
		this.vrstaNarucioca = vrstaNarucioca;
		this.vrstaPostupka = vrstaPostupka;
		this.opisPredmetaNabavke = opisPredmetaNabavke;
		this.nazivNabavke = nazivNabavke;
		this.oznakaNabavke = oznakaNabavke;
		this.obimRadova = obimRadova;
		this.mestoIzvrsenjaRadova = mestoIzvrsenjaRadova;
		this.brojPartija = brojPartija;
		this.posebnaNapomena = posebnaNapomena;
		this.kriterijumZaDodeluUgovora = kriterijumZaDodeluUgovora;
		this.webKonkursneDokumentacije = webKonkursneDokumentacije;
		this.webNadleznogOrgana = webNadleznogOrgana;
		this.rokZaPodnosenjePonuda = rokZaPodnosenjePonuda;
		this.nacinPodnosenjaPonuda = nacinPodnosenjaPonuda;
		this.vremeOtvaranjaPonuda = vremeOtvaranjaPonuda;
		this.mestoOtvaranjaPonuda = mestoOtvaranjaPonuda;
		this.dodatniUslovi = dodatniUslovi;
		this.rokZaDonosenjeOdluke = rokZaDonosenjeOdluke;
		this.kontaktLice = kontaktLice;
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

	public String getWebNarucioca() {
		return webNarucioca;
	}

	public void setWebNarucioca(String webNarucioca) {
		this.webNarucioca = webNarucioca;
	}

	public String getVrstaNarucioca() {
		return vrstaNarucioca;
	}

	public void setVrstaNarucioca(String vrstaNarucioca) {
		this.vrstaNarucioca = vrstaNarucioca;
	}

	public String getVrstaPostupka() {
		return vrstaPostupka;
	}

	public void setVrstaPostupka(String vrstaPostupka) {
		this.vrstaPostupka = vrstaPostupka;
	}

	public String getOpisPredmetaNabavke() {
		return opisPredmetaNabavke;
	}

	public void setOpisPredmetaNabavke(String opisPredmetaNabavke) {
		this.opisPredmetaNabavke = opisPredmetaNabavke;
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

	public String getObimRadova() {
		return obimRadova;
	}

	public void setObimRadova(String obimRadova) {
		this.obimRadova = obimRadova;
	}

	public String getMestoIzvrsenjaRadova() {
		return mestoIzvrsenjaRadova;
	}

	public void setMestoIzvrsenjaRadova(String mestoIzvrsenjaRadova) {
		this.mestoIzvrsenjaRadova = mestoIzvrsenjaRadova;
	}

	public Long getBrojPartija() {
		return brojPartija;
	}

	public void setBrojPartija(Long brojPartija) {
		this.brojPartija = brojPartija;
	}

	public String getPosebnaNapomena() {
		return posebnaNapomena;
	}

	public void setPosebnaNapomena(String posebnaNapomena) {
		this.posebnaNapomena = posebnaNapomena;
	}

	public String getKriterijumZaDodeluUgovora() {
		return kriterijumZaDodeluUgovora;
	}

	public void setKriterijumZaDodeluUgovora(String kriterijumZaDodeluUgovora) {
		this.kriterijumZaDodeluUgovora = kriterijumZaDodeluUgovora;
	}

	public String getWebKonkursneDokumentacije() {
		return webKonkursneDokumentacije;
	}

	public void setWebKonkursneDokumentacije(String webKonkursneDokumentacije) {
		this.webKonkursneDokumentacije = webKonkursneDokumentacije;
	}

	public String getWebNadleznogOrgana() {
		return webNadleznogOrgana;
	}

	public void setWebNadleznogOrgana(String webNadleznogOrgana) {
		this.webNadleznogOrgana = webNadleznogOrgana;
	}

	public Date getRokZaPodnosenjePonuda() {
		return rokZaPodnosenjePonuda;
	}

	public void setRokZaPodnosenjePonuda(Date rokZaPodnosenjePonuda) {
		this.rokZaPodnosenjePonuda = rokZaPodnosenjePonuda;
	}

	public String getNacinPodnosenjaPonuda() {
		return nacinPodnosenjaPonuda;
	}

	public void setNacinPodnosenjaPonuda(String nacinPodnosenjaPonuda) {
		this.nacinPodnosenjaPonuda = nacinPodnosenjaPonuda;
	}

	public Date getVremeOtvaranjaPonuda() {
		return vremeOtvaranjaPonuda;
	}

	public void setVremeOtvaranjaPonuda(Date vremeOtvaranjaPonuda) {
		this.vremeOtvaranjaPonuda = vremeOtvaranjaPonuda;
	}

	public String getMestoOtvaranjaPonuda() {
		return mestoOtvaranjaPonuda;
	}

	public void setMestoOtvaranjaPonuda(String mestoOtvaranjaPonuda) {
		this.mestoOtvaranjaPonuda = mestoOtvaranjaPonuda;
	}

	public String getDodatniUslovi() {
		return dodatniUslovi;
	}

	public void setDodatniUslovi(String dodatniUslovi) {
		this.dodatniUslovi = dodatniUslovi;
	}

	public Date getRokZaDonosenjeOdluke() {
		return rokZaDonosenjeOdluke;
	}

	public void setRokZaDonosenjeOdluke(Date rokZaDonosenjeOdluke) {
		this.rokZaDonosenjeOdluke = rokZaDonosenjeOdluke;
	}

	public String getKontaktLice() {
		return kontaktLice;
	}

	public void setKontaktLice(String kontaktLice) {
		this.kontaktLice = kontaktLice;
	}
}
