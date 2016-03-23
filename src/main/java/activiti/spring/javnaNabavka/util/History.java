package activiti.spring.javnaNabavka.util;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricDetailQuery;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.history.NativeHistoricActivityInstanceQuery;
import org.activiti.engine.history.NativeHistoricDetailQuery;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.history.NativeHistoricTaskInstanceQuery;
import org.activiti.engine.history.NativeHistoricVariableInstanceQuery;
import org.activiti.engine.history.ProcessInstanceHistoryLogQuery;

public class History implements HistoryService {

	@Override
	public HistoricProcessInstanceQuery createHistoricProcessInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricActivityInstanceQuery createHistoricActivityInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricDetailQuery createHistoricDetailQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NativeHistoricDetailQuery createNativeHistoricDetailQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricVariableInstanceQuery createHistoricVariableInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NativeHistoricVariableInstanceQuery createNativeHistoricVariableInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHistoricTaskInstance(String taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHistoricProcessInstance(String processInstanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NativeHistoricProcessInstanceQuery createNativeHistoricProcessInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NativeHistoricTaskInstanceQuery createNativeHistoricTaskInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NativeHistoricActivityInstanceQuery createNativeHistoricActivityInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoricIdentityLink> getHistoricIdentityLinksForTask(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoricIdentityLink> getHistoricIdentityLinksForProcessInstance(String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessInstanceHistoryLogQuery createProcessInstanceHistoryLogQuery(String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
