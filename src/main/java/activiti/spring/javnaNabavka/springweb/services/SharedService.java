package activiti.spring.javnaNabavka.springweb.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.util.Consts;

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
	
	public void timerExpired() {
		System.out.println("Timer expired!");
	}
	
	public String fixTimer() {
	/*	Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.MINUTE, Calendar.MINUTE + Consts.TIMER_DELAY);
		now = calendar.getTime();


	//	now.setMinutes(now.getMinutes() + Consts.TIMER_DELAY);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		df.setTimeZone(tz);
*/
		
		Date now = new Date();
		now.setMinutes(now.getMinutes() + Consts.TIMER_DELAY);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		df.setTimeZone(tz);
		
		return df.format(now);
	}
}
