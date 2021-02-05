package model.persistencia;

import java.util.HashSet;
import java.util.Set;

public class VisitasPersistencia {

	 private Set<String> listaVisitantes = new HashSet<String>();
	
	
	 public Set<String> getListaVisitantes() {
	        return listaVisitantes;
	  }

	  public void adicionarVista(String visitante) {
	        listaVisitantes.add(visitante);
	  }
	 
}
