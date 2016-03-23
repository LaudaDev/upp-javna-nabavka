package activiti.spring.javnaNabavka.util;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

@Service("GetAllApplicants")
public class GetAllApplicants implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8380549189587787377L;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution e = delegateTask.getExecution();
		IdentityService iService = e.getEngineServices().getIdentityService();
		List<User> offerApplicants = iService.createUserQuery().memberOfGroup("ponudjac").list();
		e.setVariable("offerApplicants", offerApplicants);
	}
}
