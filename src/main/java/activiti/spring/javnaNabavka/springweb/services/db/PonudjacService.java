package activiti.spring.javnaNabavka.springweb.services.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Ponudjac;

@Service("PonudjacService")
public class PonudjacService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Ponudjac save(String id, String name, String lastName, String email, String pass, Boolean sentEntry) {
		Ponudjac p = new Ponudjac();
		p.setId(id);
		p.setName(name);
		p.setLastName(lastName);
		p.setEmail(email);
		p.setPass(pass);
		p.setSentEntry(sentEntry);
		entityManager.persist(p);
		
		return p;
	}
	
	public List<String> aktuelnePonude() {
		List<String> pList = new ArrayList<String>();
		List<String> tmpList = new ArrayList<String>();
		
		List<?> result = (List<?>) entityManager.createQuery("SELECT id FROM Ponudjac WHERE sentEntry = 0").getResultList();
		for (Object object : result) {
		    if (object instanceof String) {
		        tmpList.add((String) object);
		    }
		}
		pList = tmpList; 
		
		return pList;
	}
	
	public Ponudjac changeFlag() {
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ponudjac p = new Ponudjac();
		p = (Ponudjac) entityManager.createQuery("SELECT p FROM Ponudjac p WHERE p.id = '" + u.getUsername() + "'").getSingleResult();
		p.setSentEntry(true);
		entityManager.merge(p);
		
		return p;
	}
}
