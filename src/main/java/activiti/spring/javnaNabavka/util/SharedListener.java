package activiti.spring.javnaNabavka.util;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("SharedListener")
public class SharedListener implements JavaDelegate {

	private RuntimeService rs;

	@Override
	public void execute(DelegateExecution e) throws Exception {
		rs = e.getEngineServices().getRuntimeService();
		
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("nazivNarucioca", (String)e.getVariable("nazivNarucioca"));
		vars.put("adresaNarucioca", (String)e.getVariable("adresaNarucioca"));
		vars.put("procenjenaVrednost", (Double)e.getVariable("procenjenaVrednost"));
		vars.put("vrstaPostupka", (String)e.getVariable("vrstaPostupka"));
		vars.put("predmetNabavke", (String)e.getVariable("predmetNabavke"));
		System.out.println("SETOVAO VARIJABLE ZA PONUDA PROCES");
		
		for (Map.Entry<String, Object> k: vars.entrySet()) {
			System.out.println(k.getKey() + " = " + k.getValue());
		}
		
	//	rs.startProcessInstanceById("slanjePonudeProces", vars);
	//	rs.signalEventReceived("SignalThrowEvent", vars);
	//	rs.startProcessInstanceByKey("slanjePonudeProces", vars);
		rs.startProcessInstanceByMessage("msgPonuda", vars);
		
	}
}
