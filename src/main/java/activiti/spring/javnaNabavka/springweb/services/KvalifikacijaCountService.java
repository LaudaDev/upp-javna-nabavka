package activiti.spring.javnaNabavka.springweb.services;

import java.util.ArrayList;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import activiti.spring.javnaNabavka.enitity.Prijava;

@Service("KvalifikacijaCountService")
public class KvalifikacijaCountService implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -392517433493370014L;

	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution exe = delegateTask.getExecution();
		ArrayList<Prijava> kvalifikovanePrijave = (ArrayList<Prijava>)exe.getVariable("kvalifikovanePrijave");
		ArrayList<Prijava> odbijenePrijave = (ArrayList<Prijava>)exe.getVariable("odbijenePrijave");
		ArrayList<String> odobreniZaSlanjePonude = (ArrayList<String>)exe.getVariable("odobreniZaSlanjePonude");
		String potvrdjenaKvalifikacija = (String)exe.getVariable("prihvatanjeKvalifikacije");
		Integer brojPotvrdjenihKvalifikacija = (Integer)exe.getVariable("brojPotvrdjenihKvalifikacija");
		
		if (brojPotvrdjenihKvalifikacija == null && kvalifikovanePrijave == null && odobreniZaSlanjePonude == null) {
			brojPotvrdjenihKvalifikacija = 0;
			kvalifikovanePrijave = new ArrayList<Prijava>();
			odobreniZaSlanjePonude = new ArrayList<String>();
		}
		
		if (potvrdjenaKvalifikacija != null && potvrdjenaKvalifikacija.equals("da")) {
			brojPotvrdjenihKvalifikacija++;
			Prijava p = (Prijava) exe.getVariable("prijava");
			kvalifikovanePrijave.add(p);
			odobreniZaSlanjePonude.add(p.getUser());
		}
		else {
			if (odbijenePrijave == null)
				odbijenePrijave = new ArrayList<Prijava>();
			
			Prijava p = (Prijava) exe.getVariable("prijava");
			odbijenePrijave.add(p);
		}
		
		exe.setVariable("brojPotvrdjenihKvalifikacija", brojPotvrdjenihKvalifikacija);
		exe.setVariable("odobreniZaSlanjePonude", odobreniZaSlanjePonude);
		exe.setVariable("kvalifikovanePrijave", kvalifikovanePrijave);
		exe.setVariable("odbijenePrijave", odbijenePrijave);
		
		if (odbijenePrijave != null)
			System.out.println("Odbijenih prijava ima: " + odbijenePrijave.size());
		
		System.out.println("SETOVAO SVE VAR U KvalifikacijaCountService");
	}
}
