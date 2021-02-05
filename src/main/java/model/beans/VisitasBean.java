package model.beans;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import model.persistencia.VisitasPersistencia;

@Stateful
public class VisitasBean {

	private VisitasPersistencia visitasPersistencia;
	
	@PostConstruct
	public void configurar() {
		this.visitasPersistencia = new VisitasPersistencia();		
	}	
	
	public void entrouVisita(String visitante) {
		this.visitasPersistencia.adicionarVista(visitante);
	}

	public Set<String> getListaVisitantes() {
		return this.visitasPersistencia.getListaVisitantes();
	}
}
