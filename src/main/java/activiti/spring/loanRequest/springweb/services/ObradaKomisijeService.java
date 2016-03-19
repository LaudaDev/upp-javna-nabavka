package activiti.spring.loanRequest.springweb.services;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

@Service("ObradaKomisijeService")
public class ObradaKomisijeService implements JavaDelegate, TaskListener {

	private IdentityService iService;
	private RuntimeService rService;
	private User pravnik;
	private List<User> stranaLica = new ArrayList<User>();
	private List<User> sluzbenici = new ArrayList<User>();
	private List<String> komisija = new ArrayList<String>();
	
	@Override
	public void execute(DelegateExecution e) throws Exception {
		iService = e.getEngineServices().getIdentityService();
		rService = e.getEngineServices().getRuntimeService();
		
		pravnik = iService.createUserQuery().userId((String)e.getVariable("pravnik")).singleResult();
		stranaLica = iService.createUserQuery().userId((String)e.getVariable("stranaLica")).list();
		sluzbenici = iService.createUserQuery().userId((String)e.getVariable("sluzbenici")).list();
		komisija = new ArrayList<String>();
		
		komisija.add(pravnik.getId());
		e.setVariable("clan_1", pravnik.getFirstName() + " " + pravnik.getLastName());
		
		if (stranaLica != null && sluzbenici != null) {
			if ((stranaLica.size() + sluzbenici.size()) > 2) {
				// well meh
			} else {
				for (User u: stranaLica) {
					komisija.add(u.getId());
					e.setVariable("clan_2", u.getFirstName() + " " + u.getLastName());
				}
				
				for (User u: sluzbenici) {
					komisija.add(u.getId());
					e.setVariable("clan_3", u.getFirstName() + " " + u.getLastName());
				}
				
				for (String id: komisija) {
					if (iService.createGroupQuery().groupName("Komisija").groupMember(id).singleResult() == null) {
						iService.createMembership(id, "komisija");
					}
				}
				
				e.setVariable("komisijaLista", komisija);
			}
		}
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution e = delegateTask.getExecution();
		
		String prihvacenoClanstvo = (String) e.getVariable("prihvatiClanstvoKomisije");
		Integer brojPrihvacenihClanova = (Integer)e.getVariable("brojPrihvacenihClanova");
		
		// nullcheck
		if (brojPrihvacenihClanova == null) 
			brojPrihvacenihClanova = 0;
		
		if (prihvacenoClanstvo.equals("true")) {
			brojPrihvacenihClanova++;
			
		}
		
		e.setVariable("brojPrihvacenihClanova", brojPrihvacenihClanova);
	}
}
