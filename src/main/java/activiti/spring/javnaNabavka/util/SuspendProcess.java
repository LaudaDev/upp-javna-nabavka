package activiti.spring.javnaNabavka.util;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("SuspendProcess")
public class SuspendProcess implements JavaDelegate {

	@Override
	public void execute(DelegateExecution e) throws Exception {
		RepositoryService repositoryService = e.getEngineServices().getRepositoryService();
		repositoryService.suspendProcessDefinitionById((String) e.getVariable("processId"));
		
		System.out.println("Proces " + (String) e.getVariable("processId") + " uspesno suspendovan!");
	}
}
