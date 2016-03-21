package activiti.spring.javnaNabavka.deploy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	private static Scanner scanner;

	public static void main (String[] args) {
		List<String> models = new ArrayList<String>();
		models.add("diagrams/UPP_JavnaNabavka.bpmn");
		models.add("diagrams/partials/Prijava.bpmn");
		models.add("diagrams/partials/Ponuda.bpmn");
		
		scanner = new Scanner(System.in);
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		System.out.println("Deploy-ovati definiciju? [d/n]");
		Character reply = scanner.next().charAt(0);
		RepositoryService repositoryService = processEngine.getRepositoryService();

		if (reply == 'd') {
			System.out.println("Izbrisati prethodne deploymente? [d/n]");
			if (scanner.next().equalsIgnoreCase("d"))
				for (Deployment d : repositoryService.createDeploymentQuery().list())
					repositoryService.deleteDeployment(d.getId(), true);

			for (String s : models) {
				repositoryService.createDeployment().addClasspathResource(s).deploy();
				System.out.println("Deploy modela " + s + " uspesno zavrsen!");
			}

			System.out.println("Ukupan broj deployment-a: " + repositoryService.createDeploymentQuery().count());
		}
	}
}
