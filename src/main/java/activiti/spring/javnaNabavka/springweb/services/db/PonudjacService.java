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
	
	public Ponuda saveOffer(String nazivNarucioca, String adresaNarucioca, Double procenjenaVrednost, Double predlozenaCena, Double koeficijent) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ponuda p = new Ponuda(u.getUsername(), nazivNarucioca, adresaNarucioca, procenjenaVrednost, predlozenaCena, koeficijent);
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

		List<?> result = (List<?>) entityManager.createQuery("SELECT p.id FROM Ponudjac p WHERE p.canSendOffer = 1 AND p.sentOffer = 0").getResultList();
		for (Object object : result) {
			if (object instanceof String) {
				pList.add((String) object);
			}
		}

		System.out.println("Vratio kvalifikovane ponudjace! Ukupno ih ima: " + pList.size());
		
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
	
	public List<Ponuda> getAllOffers() {
		List<Ponuda> offers = new ArrayList<Ponuda>();
		
		List<?> result = (List<?>) entityManager.createQuery("SELECT p FROM Ponuda p").getResultList();
		for (Object object : result) {
			if (object instanceof Ponuda) {
				offers.add((Ponuda) object);
			}
		}

		System.out.println("Poslate ponude uspesno prikupljene! Ukupno ih ima: " + offers.size());
		
		return offers;
	}
	
	public List<String> getOfferEmail() {
		List<String> emails = new ArrayList<String>();
		
		List<?> result = (List<?>) entityManager.createQuery("SELECT pn.email FROM Ponudjac pn WHERE pn.id IN (SELECT p.user FROM Ponuda p)").getResultList();
		for (Object object : result) {
			if (object instanceof String) {
				emails.add((String)object);
			}
		}
		
		System.out.println("FETCHING EMAILS FOR SENT OFFERS! Total size: " + emails.size());
		
		return emails;
		
	}
	
	public void rejectOffer() {
		double lowPrice = 150000, allowedLimit = .0;
		List<Ponuda> offers = new ArrayList<Ponuda>();
		offers = this.getAllOffers();
		
		for (Ponuda p: offers) {
			// 10% over the price is the max!
			allowedLimit = p.getProcenjenaVrednost() + (p.getProcenjenaVrednost() / 10);
			
			if (p.getKoeficijent() == 0)
				// If offered price is lower that lowPrice limit OR if allowed price limit is smaller than offered price - reject
				if ((p.getPredlozenaCena() <= lowPrice) || (p.getPredlozenaCena() > allowedLimit)) {
					// entityManager.remove(p); CRASH
					entityManager.createQuery("DELETE FROM Ponuda WHERE id = '" + p.getId() + "'").executeUpdate();
					System.out.println("Ponuda od korisnika " + p.getUser() + " odbijena zbog cene = " + p.getPredlozenaCena());
				}
		}
	}
	
	public Ponuda updateKoeficijent(Double koef, String user) {
		Ponuda p = (Ponuda)entityManager.createQuery("SELECT p FROM Ponuda p WHERE p.user = '" + user + "'").getSingleResult();
		p.setKoeficijent(koef);
		entityManager.merge(p);
		
		return p;
	}
	
	public String rankedOffers() {
	/*	List<String> ponudjaci = new ArrayList<String>();
		
		List<?> result = (List<?>) entityManager.createQuery("SELECT p.user FROM Ponuda p ORDER BY p.ponudjenaCena ASC").getResultList();
		for (Object object : result) {
			if (object instanceof String) {
				ponudjaci.add((String) object);
			}
		}
		*/
		
		String ponudjac = (String)entityManager.createQuery("SELECT p.user FROM Ponuda p ORDER BY p.predlozenaCena ASC").getSingleResult();
		
		System.out.println("Ponuda koja je najbolje rangirana je od " + ponudjac + " ponudjaca!");
		
		return ponudjac;
	}
}
