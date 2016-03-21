package activiti.spring.javnaNabavka.springweb.services.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Prijava;

@Service("PrijavaService")
public class PrijavaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Prijava save(String nazivPodnosiocaPrijave, String adresaPodnosiocaPrijave, String emailPodnosiocaPrijave) {
		Prijava p = new Prijava();
		
		p.setNazivPodnosiocaPrijave(nazivPodnosiocaPrijave);
		p.setAdresaPodnosiocaPrijave(adresaPodnosiocaPrijave);
		p.setEmailPodnosiocaPrijave(emailPodnosiocaPrijave);
		entityManager.persist(p);
		
		return p;
	}
	
	public List<Prijava> listaPrijavljenih() {
		System.out.println("USAO U PrijavaService.listaPrijavljenih()");
		List<Prijava> pList = new ArrayList<Prijava>();
		List<Prijava> tmpList = new ArrayList<Prijava>();
		
		// Safe cast :)
		List<?> result = (List<?>) entityManager.createQuery("SELECT p FROM Prijava p").getResultList();
		for (Object object : result) {
		    if (object instanceof Prijava) {
		        tmpList.add((Prijava) object);
		    }
		}
		pList = tmpList; 
		
		System.out.println("Lista prijava size = " + pList.size());
		
		if (pList.size() == 0) {
			System.out.println("Timer Expired, noone applied!");
		}
		
		return pList;
	}
}
