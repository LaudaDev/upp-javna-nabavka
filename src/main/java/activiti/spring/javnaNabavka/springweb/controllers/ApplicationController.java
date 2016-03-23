package activiti.spring.javnaNabavka.springweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/application")
public class ApplicationController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private FormService formService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private IdentityService iService;

	@RequestMapping(value="/tasksList", method = RequestMethod.GET)
	public String showUsersTasks(ModelMap model) {

		User user;
		try{
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception ex){
			return "redirect:/login";
		}
		
		String id = user.getUsername();

		List<Task> myTasks = taskService.createTaskQuery().taskAssignee(id).list();
		model.addAttribute("myTasks", myTasks);

		List<Task> candidateTasks = taskService.createTaskQuery().taskCandidateUser(id).list();
		model.addAttribute("candidateTasks", candidateTasks);
		model.addAttribute("username", id);

		return "application/tasksList";

	}


	@RequestMapping(value="/claim/{taskId}", method = RequestMethod.GET)
	public String claimTask(@PathVariable String taskId, ModelMap model) {
	
		User user;
		try{
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception ex){
			return "redirect:/login";
		}
		
		String message;
		String userId = user.getUsername();
		if (!canClaim(taskId, userId))
			message = "Ne možete prihvatiti zadatak";
		else{
			taskService.claim(taskId, userId);
			message = "Zadatak je uspešno prihvaćen";
		}

		model.addAttribute("message", message);
		model.addAttribute("username", userId);

		return showUsersTasks(model);

	}

	@RequestMapping(value="/showTask/{taskId}", method = RequestMethod.GET)
	public String showTask(@PathVariable String taskId, ModelMap model){
		User user;
		
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "redirect:/login";
		}
		
		User initiator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String tmpMsg = (initiator == null) ? "\nInicijator je NULL!" : "\nInicijator je " + initiator.getUsername();
		System.out.println(tmpMsg);
		String msg;
		String uId = user.getUsername();
		model.addAttribute("username", user.getUsername());
		
		if (!canExecute(taskId, uId)) {
			msg = "Ne možete izvršiti zadatak!";
			model.addAttribute("message", msg);
			System.out.println("USAO U PROVERU KOMISIJE!");
			return showUsersTasks(model);
		} 
		else {
			// Get form data...
			System.out.println("USAO U PROVERU KOMISIJE!");
			TaskFormData taskFormData = formService.getTaskFormData(taskId);
			List<FormProperty> formProperties = taskFormData.getFormProperties();
			
			if (checkKomisiju(taskId)) {
				System.out.println("USAO U PROVERU KOMISIJE!");
				List<org.activiti.engine.identity.User> pravnikList = iService.createUserQuery().memberOfGroup("strucnoLiceZaKomisiju").list();
				List<org.activiti.engine.identity.User> stranaLicaList = iService.createUserQuery().memberOfGroup("stranoLice").list();
				List<org.activiti.engine.identity.User> narucilacList = iService.createUserQuery().memberOfGroup("narucilac").list();
				
				model.addAttribute("formProperties", formProperties);
				model.addAttribute("pravnikList", pravnikList);
				model.addAttribute("stranaLicaList", stranaLicaList);
				model.addAttribute("narucilacList", narucilacList);
				
				model.addAttribute("formProperties", formProperties);
				Task ts = taskService.createTaskQuery().taskId(taskId).singleResult();
				model.addAttribute("task", ts);
				
				return "application/komisija";
			}
			else {
				if (formProperties.size() == 0) {
					taskService.complete(taskId);
					msg = "Zadatak uspešno izvršen!";
					model.addAttribute("message", msg);
					
					return showUsersTasks(model);
				}
				else {
					model.addAttribute("formProperties", formProperties);
					Task ts = taskService.createTaskQuery().taskId(taskId).singleResult();
					model.addAttribute("task", ts);
					
					String form = formService.getTaskFormData(taskId).getFormKey();
					
					return "application/" + form;
				}
			}
		}
	}

	@RequestMapping(value="/execute/{taskId}", method = RequestMethod.POST)
	public String execcuteTask(@PathVariable String taskId, @RequestParam Map<String, String> params, ModelMap model){

		User user;
		try{
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception ex){
			return "redirect:/login";
		}
		
		String userId = user.getUsername();
		String message;
		if (canExecute(taskId, userId)){
			//pre ovog koraka bi se trebala sprovesti validacija
			//da li su uneti svi potrebni parametri (required), da li ima neslaganja tipova
			//ako se unosi email adresa, da li je validna i sl.
			formService.submitTaskFormData(taskId, params);
			message = "Zadatak uspešno izvršen";
		}
		else
			message = "Ne možete izvršiti zadatak";

		model.addAttribute("message", message);
		model.addAttribute("username", userId);
		return showUsersTasks(model);

	}
	
	@RequestMapping(value="/newInstance", method = RequestMethod.GET)
	public String newInstance(ModelMap model){
		
		 if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null)
			return "redirect:/login";
		
		
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("JavnaNabavkaProces").latestVersion().singleResult();
		StartFormData formData = formService.getStartFormData(procDef.getId());
		List<FormProperty> formProperties = formData.getFormProperties();
		
		
		if (formProperties.size() == 0){
			iService.setAuthenticatedUserId("initiator");
			runtimeService.startProcessInstanceByKey("JavnaNabavkaProces");
			String message = "Nova instanca je uspešno pokrenuta";
			model.addAttribute("message", message);
			return printWelcome(model);
		}
		else{
			model.addAttribute("formProperties", formProperties);
			String form = formService.getStartFormData(procDef.getId()).getFormKey();
			return "application/"+form;
		}
			
	}
	
	
	@RequestMapping(value="/startNewInstance", method = RequestMethod.POST)
	public String startNewInstance(ModelMap model, @RequestParam Map<String, String> params){
		
		 if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null)
			return "redirect:/login";
		
		
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("JavnaNabavkaProces").latestVersion().singleResult();
		//takodje bi sada ovde trebala biti uradjena validacija
		iService.setAuthenticatedUserId("initiator");
		formService.submitStartFormData(procDef.getId(),params);
		String message = "Nova instanca je uspešno pokrenuta";
		model.addAttribute("message", message);
		System.out.println("Nova instanca uspesno pokrenuta!");
		return printWelcome(model);
	
	}
	
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		boolean canInitiate = false;

		User user;
		try{
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception ex){
			return "redirect:/login";
		}
		
		List<org.activiti.engine.identity.User> usr = (ArrayList<org.activiti.engine.identity.User>)iService.createUserQuery().memberOfGroup("narucilac").list();
		for (org.activiti.engine.identity.User u: usr) {
			if (u.getId().equals(user.getUsername())) {
				canInitiate = true;
				break;
			}
			
		}
			
		String name = user.getUsername();
		model.addAttribute("username", name);
		model.addAttribute("canInitiate", canInitiate);
		return "application/welcome";
 
	}
	
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public String printStats(ModelMap model) {
		User user;
		try{
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception ex){
			return "redirect:/login";
		}

		List<String> finishedInstances = new ArrayList<String>();
		List<String> unfinishedInstances = new ArrayList<String>();
		List<String> taskOnWait = new ArrayList<String>();
		
		for (HistoricActivityInstance hai: historyService.createHistoricActivityInstanceQuery().finished().list()) 
				finishedInstances.add(hai.getId() + " - " + hai.getActivityName() + " - " + hai.getEndTime().toString());
		
		for (HistoricActivityInstance hai: historyService.createHistoricActivityInstanceQuery().unfinished().list()) 
				unfinishedInstances.add(hai.getId() + " - " + hai.getActivityName() + " - " + hai.getStartTime().toString());
		
		for (HistoricTaskInstance hti: historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().list()) 
			taskOnWait.add(hti.getId() + " - " + hti.getName() + " - " + hti.getAssignee());
				
		model.addAttribute("finishedInstances", finishedInstances);
		model.addAttribute("unfinishedInstances", unfinishedInstances);
		model.addAttribute("taskOnWait", taskOnWait);
		model.addAttribute("username", user.getUsername());
		
		return "application/table";
	}


	private boolean checkKomisiju(String tId) {
		for (Task t: taskService.createTaskQuery().taskName("Formiranje komisije").list())
			if (t.getId().equals(tId))
				return true;
		
		return false;
	}
	
	private boolean canClaim(String taskId, String userId){
		for (Task t : taskService.createTaskQuery().taskCandidateUser(userId).list())
			if (t.getId().equals(taskId))
				return true;
		return false;
	}

	private boolean canExecute(String taskId, String userId){
		for (Task t : taskService.createTaskQuery().taskAssignee(userId).list())
			if (t.getId().equals(taskId))
				return true;
		return false;
	}
}
