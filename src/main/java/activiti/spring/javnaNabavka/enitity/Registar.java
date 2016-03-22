package activiti.spring.javnaNabavka.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Registar {
	
	@Id
	@GeneratedValue
	private Long id;
	private String clanoviRegistra;
	
	public Registar() {
		super();
	}

	public Registar(String clanoviRegistra) {
		super();
		this.clanoviRegistra = clanoviRegistra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClanoviRegistra() {
		return clanoviRegistra;
	}

	public void setClanoviRegistra(String clanoviRegistra) {
		this.clanoviRegistra = clanoviRegistra;
	}
}
