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
		List<Prijava> prijave = new ArrayList<Prijava>();
		List<Prijava> tmpList = new ArrayList<Prijava>();

		List<?> result = (List<?>) exe.getVariable("kvalifikovanePrijave");
		for (Object object : result) {
			if (object instanceof Prijava) {
				tmpList.add((Prijava) object);
			}
		}
		prijave = tmpList; 
		tmpList.clear();

		String potvrdjenaKvalifikacija = (String)exe.getVariable("prihvatanjeKvalifikacije");
		Integer brojPotvrdjenihKvalifikacija = ((Integer) exe.getVariable("potvrdjenihKvalifikacija") == null) ? 0 : (Integer) exe.getVariable("potvrdjenihKvalifikacija");


		if (potvrdjenaKvalifikacija.equals("true")) {
			brojPotvrdjenihKvalifikacija++;
			prijave.add((Prijava)exe.getVariable("prijava"));
		}

		exe.setVariable("brojPotvrdjenihKvalifikacija", brojPotvrdjenihKvalifikacija);
		exe.setVariable("kvalifikovanePrijave", prijave);
	}
}
