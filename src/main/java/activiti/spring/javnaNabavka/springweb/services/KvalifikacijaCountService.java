package activiti.spring.javnaNabavka.springweb.services;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Prijava;

@Service("KvalifikacijaCountService")
public class KvalifikacijaCountService implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -392517433493370014L;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution exe = delegateTask.getExecution();
		List<Prijava> kPrijave = new ArrayList<Prijava>();
		List<String> kPonude = new ArrayList<String>();

		List<?> result = (List<?>) exe.getVariable("kvalifikovanePrijave");
		for (Object object : result) {
			if (object instanceof Prijava) {
				kPrijave.add((Prijava) object);
			}
		}
		
		List<?> result1 = (List<?>) exe.getVariable("kvalifikovaniZaPonude");
		for (Object object : result1) {
			if (object instanceof Prijava) {
				kPonude.add((String) object);
			}
		}

		String potvrdjenaKvalifikacija = (String)exe.getVariable("prihvatanjeKvalifikacije");
		Integer brojPotvrdjenihKvalifikacija = ((Integer) exe.getVariable("potvrdjenihKvalifikacija") == null) ? 0 : (Integer) exe.getVariable("potvrdjenihKvalifikacija");


		if (potvrdjenaKvalifikacija != null && potvrdjenaKvalifikacija.equals("true")) {
			brojPotvrdjenihKvalifikacija++;
			Prijava p = (Prijava) exe.getVariable("prijava");
			kPrijave.add(p);
			kPonude.add(p.getUser());
		}

		exe.setVariable("brojPotvrdjenihKvalifikacija", brojPotvrdjenihKvalifikacija);
		exe.setVariable("kvalifikovanePrijave", kPrijave);
		exe.setVariable("kvalifikovaniZaPonude", kPonude);
	}
}
