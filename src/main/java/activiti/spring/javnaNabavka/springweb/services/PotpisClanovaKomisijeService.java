package activiti.spring.javnaNabavka.springweb.services;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service("PotpisClanovaKomisijeService")
public class PotpisClanovaKomisijeService implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5181069457907427866L;
	private String potvrdaClanstva;
	private Integer brojPotvrdaClanstva;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution exe = delegateTask.getExecution();
		potvrdaClanstva = (String) exe.getVariable("potvrdaClanstvaKomisije");
		brojPotvrdaClanstva = ((Integer) exe.getVariable("brojPotvrdjenihClanova") == null) ? 0 : (Integer) exe.getVariable("brojPotvrdjenihClanova");
		
		if (potvrdaClanstva.equals("true")) {
			brojPotvrdaClanstva++;
		}
		
		exe.setVariable("brojPotvrdaClanstva", brojPotvrdaClanstva);
	}
}
