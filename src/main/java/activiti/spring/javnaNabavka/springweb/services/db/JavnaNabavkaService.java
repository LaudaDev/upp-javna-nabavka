package activiti.spring.javnaNabavka.springweb.services.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.JavnaNabavka;

@Service("JavnaNabavkaService")
public class JavnaNabavkaService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public JavnaNabavka save(String nazivNarucioca, String adresaNarucioca, Long rbNabavke, String predmetNabavke, String nazivNabavke, String oznakaNabavke, String vrstaPostupka, Double procenjenaVrednost) {
		JavnaNabavka jn = new JavnaNabavka();
		jn.setNazivNarucioca(nazivNarucioca);
		jn.setAdresaNarucioca(adresaNarucioca);
		jn.setRbNabavke(rbNabavke);
		jn.setPredmetNabavke(predmetNabavke);
		jn.setNazivNabavke(nazivNabavke);
		jn.setOznakaNabavke(oznakaNabavke);
		jn.setVrstaPostupka(vrstaPostupka);
		jn.setProcenjenaVrednost(procenjenaVrednost);
		entityManager.persist(jn);
		
		return jn;
	}
}
