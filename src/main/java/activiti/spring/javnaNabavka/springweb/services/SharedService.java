package activiti.spring.javnaNabavka.springweb.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.util.Consts;

@Service("SharedService")
public class SharedService {
	
	@Autowired
	private RuntimeService rs;
	
	@PersistenceContext
	private EntityManager em;

	public boolean checkPlan() {
		Random random = new Random();
		boolean tmp = random.nextBoolean();
		
		System.out.println("Provera plana vratila " + tmp);
		
		if (tmp)
			System.out.println("Nabavka se nastavlja!");
		else
			System.out.println("Nabavka se zatvara!");
		
		return tmp;
		
	}
	
	public void throwSignal() {
		System.out.println("Signal pushed!");
	}
	
	public void receiveEntry() {
		System.out.println("Entry received!");
		
		List<Execution> e = rs.createExecutionQuery().list();
		for (Execution ex: e) 
			System.out.println("Ukupno runtime: " + ex.getId());
	}
	
	public void timerExpired() {
		System.out.println("Timer expired!");
	}
	
	@SuppressWarnings("deprecation")
	public String fixTimer() {
	/*	Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.MINUTE, Calendar.MINUTE + Consts.TIMER_DELAY);
		now = calendar.getTime();


	//	now.setMinutes(now.getMinutes() + Consts.TIMER_DELAY);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		df.setTimeZone(tz);
*/
		
		Date now = new Date();
		now.setMinutes(now.getMinutes() + Consts.TIMER_DELAY);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		df.setTimeZone(tz);
		
		return df.format(now);
	}
	
	public List<String> getEmail(String type) {
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = (String) em.createQuery("SELECT p.email FROM Ponudjac p WHERE p.id = '" + u.getUsername() + "'").getSingleResult();
		List<String> tmp = new ArrayList<String>();
		tmp.add(email);
		
		if (type.equals("ponuda")) {
			System.out.println("Ponuda uspesno primljena. Saljem e-mail ponudjacu o prijemu ponude!");
			
			// Reset, boring to test
			em.createQuery("UPDATE Ponudjac p SET p.canSendOffer = 1, p.sentOffer = 0 WHERE p.id = '" + u.getUsername() + "'");
		}
		else 
			System.out.println("Prijava uspesno primljena. Saljem e-mail o prijemu prijave!");
		
		
		
		return tmp;
	}
	
	public boolean getRandom() {
		Random random = new Random();
		boolean tmp = random.nextBoolean();
		System.out.println("Random je vratio: " + tmp);
		
		if (!tmp)
			System.out.println("Glavni proces se nastavlja!");
		else
			System.out.println("Zastita prava validna, glavni proces se gasi!");
		
		return tmp;
	}
}
