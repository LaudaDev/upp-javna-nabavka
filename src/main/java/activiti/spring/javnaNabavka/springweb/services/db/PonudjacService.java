package activiti.spring.javnaNabavka.springweb.services.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Ponuda;
import activiti.spring.javnaNabavka.enitity.Ponudjac;
import activiti.spring.javnaNabavka.enitity.Registar;

@Service("PonudjacService")
public class PonudjacService {

	@PersistenceContext
	private EntityManager entityManager;

	public Ponudjac save(String id, String name, String lastName, String email, String pass, boolean sentEntry, boolean canSendOffer, boolean sentOffer) {
		Ponudjac p = new Ponudjac();
		p.setId(id);
		p.setName(name);
		p.setLastName(lastName);
		p.setEmail(email);
		p.setPass(pass);
		p.setSentEntry(sentEntry);
		p.setCanSendOffer(canSendOffer);
		p.setSentOffer(sentOffer);
		boolean exists = false;

		try {
		exists = (entityManager.createQuery("SELECT id FROM Ponudjac WHERE id = '" + id + "'").getSingleResult().equals(id)) ? true : false;
		} catch (NoResultException nre){
			System.out.println("NO RESULTS IN PonudjacService");
		}
		
		if (!exists)
			entityManager.persist(p);


		return p;
	}

	public List<String> getValidCandidates() {
		List<String> pList = new ArrayList<String>();
		List<String> tmpList = new ArrayList<String>();

		List<?> result = (List<?>) entityManager.createQuery("SELECT id FROM Ponudjac WHERE sentEntry = 0").getResultList();
		for (Object object : result) {
			if (object instanceof String) {
				System.out.println("Validan kandidat: " + object.toString());
				tmpList.add((String) object);
			}
		}
		pList = tmpList; 

		return pList;
	}

	public Ponudjac setApplicationFlag() {
		System.out.println("USAO U setApplicationFlag");
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ponudjac p = new Ponudjac();
		p = (Ponudjac) entityManager.createQuery("SELECT p FROM Ponudjac p WHERE p.id = '" + u.getUsername() + "'").getSingleResult();
		p.setSentEntry(true);
		entityManager.merge(p);

		return p;
	}

	public Ponudjac setOfferFlag(String uId) {
		Ponudjac p = new Ponudjac();
		p = (Ponudjac) entityManager.createQuery("SELECT p FROM Ponudjac p WHERE p.id = '" + uId + "'").getSingleResult();
		p.setCanSendOffer(true);
		entityManager.merge(p);

		return p;
	}

	public List<String> getQualified() {
		List<String> pList = new ArrayList<String>();
		List<String> tmpList = new ArrayList<String>();

		List<?> result = (List<?>) entityManager.createQuery("SELECT id FROM Ponudjac WHERE canSendOffer = 1 AND sentOffer = 0").getResultList();
		for (Object object : result) {
			if (object instanceof String) {
				tmpList.add((String) object);
			}
		}
		pList = tmpList; 
		tmpList.clear();

		return pList;
	}

	public Ponudjac setOfferSentFlag() {
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ponudjac p = (Ponudjac) entityManager.createQuery("SELECT p FROM Ponudjac p WHERE p.id = '" + u.getUsername() + "'").getSingleResult();
		p.setSentOffer(true);
		entityManager.merge(p);

		return p;
	}
	
	public Ponudjac setFlagDocumentation() {
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ponudjac p = (Ponudjac) entityManager.createQuery("SELECT p FROM Ponudjac p WHERE id = '" + u.getUsername() + "'").getSingleResult();
		p.setSentOffer(true);
		entityManager.merge(p);
		
		return p;
	}
	
	public Ponudjac setFlagRegistar() {
		boolean exists = false;
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Registar> registarList = new ArrayList<Registar>();
		
		List<?> result = (List<?>) entityManager.createQuery("SELECT r FROM Registar r").getResultList();
		for (Object object : result) {
			if (object instanceof Registar) {
				registarList.add((Registar) object);
			}
		}
		
		for (Registar r: registarList) {
			if (u.getUsername().equals(r.getClanoviRegistra())) {
				exists = true;
				break;
			}
		}
		
		if (!exists) {
			Ponudjac p = (Ponudjac)entityManager.createQuery("SELECT p FROM Ponudjac p WHERE id = '" + u.getUsername() + "'").getSingleResult();
			p.setCanSendOffer(false); // Lieeed
			entityManager.merge(p);
			
			// Remove offer since applican lied...
			Ponuda tmp = (Ponuda)entityManager.createQuery("SELECT p FROM Ponuda p WHERE user = '" + u.getUsername() + "'").getSingleResult();
			entityManager.remove(tmp);
			
			return p;
		}
		else {
			Ponudjac p = (Ponudjac)entityManager.createQuery("SELECT p FROM Ponudjac p WHERE id = '" + u.getUsername() + "'").getSingleResult();
			p.setSentOffer(true);
			entityManager.merge(p);
			
			return p;
		}
	}
}
