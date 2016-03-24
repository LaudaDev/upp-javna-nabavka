package activiti.spring.javnaNabavka.deploy;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;


/**
 * Klasa zaduzena za deployment
 * @author xyz
 *
 */
public class ProcessDeployer {
	public static void main (String[] args) {
		List<String> models = new ArrayList<String>();
		models.add("diagrams/UPP_JavnaNabavka.bpmn");
		models.add("diagrams/partials/Prijava.bpmn");
		models.add("diagrams/partials/Ponuda.bpmn");
		models.add("diagrams/partials/InitZastita.bpmn");
		models.add("diagrams/partials/ZastitaPrava.bpmn");
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();

		for (Deployment d : repositoryService.createDeploymentQuery().list())
			repositoryService.deleteDeployment(d.getId(), true);

			for (String s : models) {
				repositoryService.createDeployment().addClasspathResource(s).deploy();
				System.out.println("Deploy modela " + s + " uspesno zavrsen!");
			}

			System.out.println("Ukupan broj deployment-a: " + repositoryService.createDeploymentQuery().count());
	}
}
