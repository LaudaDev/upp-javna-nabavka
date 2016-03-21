package activiti.spring.javnaNabavka.springweb.services;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SharedService")
public class SharedService {
	
	@Autowired
	private RuntimeService rs;

	public boolean checkPlan() {
		return true;
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
	
}
