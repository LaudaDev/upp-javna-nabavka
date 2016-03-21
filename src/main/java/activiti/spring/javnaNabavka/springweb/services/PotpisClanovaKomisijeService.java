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
	private Boolean potvrdaClanstva;
	private Integer brojPotvrdaClanstva;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution exe = delegateTask.getExecution();
		String tmp = (String) exe.getVariable("potvrdaClanstvaKomisije");
		System.out.println("KOMISIJA TMP = " + tmp);
		potvrdaClanstva = (tmp.equals("da")) ? true : false;
		
		//potvrdaClanstva = (Boolean) exe.getVariable("potvrdaClanstvaKomisije");
		
		System.out.println("exe.getVariable(\"brojPotvrdaClanstva\") = " + exe.getVariable("brojPotvrdaClanstva"));
		brojPotvrdaClanstva = ((Integer) exe.getVariable("brojPotvrdaClanstva") == null) ? 0 : (Integer) exe.getVariable("brojPotvrdaClanstva");
		
		if (potvrdaClanstva == true) {
			System.out.println("UVECAVAM BROJ POTPISA ZA JEDAN!");
			brojPotvrdaClanstva++;
		}
		
		System.out.println("BROJ POTVDJENIH CLANOVA KOMISIJE: " + brojPotvrdaClanstva);
		exe.setVariable("brojPotvrdaClanstva", brojPotvrdaClanstva);
	}
}
