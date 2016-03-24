package activiti.spring.javnaNabavka.springweb.services;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

@Service("ResenjeZaKomisijuService")
public class ResenjeZaKomisijuService implements JavaDelegate {

	private IdentityService iService;
//	private User pravnik;
//	private List<User> stranaLica = new ArrayList<User>();
//	private List<User> sluzbenici = new ArrayList<User>();
//	private List<String> komisija = new ArrayList<String>();
//	private List<User> ponudjac = new ArrayList<User>();
	
	@Override
	public void execute(DelegateExecution e) throws Exception {
		iService = e.getEngineServices().getIdentityService();
		User pravnik = iService.createUserQuery().userId((String)e.getVariable("pravnik")).singleResult();
		List<User> stranaLica = iService.createUserQuery().userId((String)e.getVariable("stranaLica")).list();
		List<User> sluzbenici = iService.createUserQuery().userId((String)e.getVariable("sluzbenici")).list();
		List<String> komisija = new ArrayList<String>();
		List<User> ponudjac = iService.createUserQuery().memberOfGroup("ponudjac").list();
		
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
				
				e.setVariable("komisijaLista", komisija);
				e.setVariable("ponudjaci", ponudjac);
				e.setVariable("brojPotvrdaClanstva", 0);
				e.setVariable("processId", e.getProcessDefinitionId());
			}
		}
	}
}
