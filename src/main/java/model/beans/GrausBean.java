package model.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class GrausBean {

	
	@PostConstruct
	public void construcao() {
		System.out.println("Bean Constru�do");
	}
	
    public double converterParaFahrenheit(double c) {
      return c * 1.8 + 32;
    }
    
    @PreDestroy
	public void destruir() {
		System.out.println("Bean vai ser Destru�do");
	}
}