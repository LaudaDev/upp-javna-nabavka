package activiti.spring.javnaNabavka.springweb.services.db;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Kvalifikacija;

@Service("KvalifikacijaService")
public class KvalifikacijaService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Kvalifikacija save(String nazivNarucioca, String adresaNarucioca, String webNarucioca, String vrstaNarucioca,
			String vrstaPostupka, String opisPredmetaNabavke, String nazivNabavke, String oznakaNabavke,
			String obimRadova, String mestoIzvrsenjaRadova, Long brojPartija, String posebnaPonuda,
			String kriterijumZaDodeluUgovora, String webKonkursneDokumentacije, String webNadleznogOrgana,
			Date rokZaPodnosenjePonuda, String nacinPodnosenjaPonuda, Date vremeOtvaranjaPonuda,
			String mestoOtvaranjaPonuda, String dodatniUslovi, Date rokZaDonosenjeOdluke, String kontaktLice) {
		Kvalifikacija k = new Kvalifikacija();
		k.setNazivNarucioca(nazivNarucioca);
		k.setAdresaNarucioca(adresaNarucioca);
		k.setWebNarucioca(webNarucioca);
		k.setVrstaNarucioca(vrstaNarucioca);
		k.setVrstaPostupka(vrstaPostupka);
		k.setOpisPredmetaNabavke(opisPredmetaNabavke);
		k.setNazivNabavke(nazivNabavke);
		k.setOznakaNabavke(oznakaNabavke);
		k.setObimRadova(obimRadova);
		k.setMestoIzvrsenjaRadova(mestoIzvrsenjaRadova);
		k.setBrojPartija(brojPartija);
		k.setPosebnaPonuda(posebnaPonuda);
		k.setKriterijumZaDodeluUgovora(kriterijumZaDodeluUgovora);
		k.setWebKonkursneDokumentacije(webKonkursneDokumentacije);
		k.setWebNadleznogOrgana(webNadleznogOrgana);
		k.setRokZaPodnosenjePonuda(rokZaPodnosenjePonuda);
		k.setNacinPodnosenjaPonuda(nacinPodnosenjaPonuda);
		k.setVremeOtvaranjaPonuda(vremeOtvaranjaPonuda);
		k.setMestoOtvaranjaPonuda(mestoOtvaranjaPonuda);
		k.setDodatniUslovi(dodatniUslovi);
		k.setRokZaDonosenjeOdluke(rokZaDonosenjeOdluke);
		k.setKontaktLice(kontaktLice);
		
		entityManager.persist(k);
		
		return k;
	}

}
