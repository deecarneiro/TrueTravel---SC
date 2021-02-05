package model.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.beans.VisitasBean;

@Named ("visitasMB")
@SessionScoped
public class VisitasMB implements Serializable{

      @EJB
      VisitasBean visitasBean;

      private String visitante;

      public void entrouVisita() {
            visitasBean.entrouVisita(visitante);
      }

      public List<String> getVisitas(){
             return new ArrayList<String>(visitasBean.getListaVisitantes());
      }

      public String getVisitante() {
            return visitante;
      }

      public void setVisitante(String visitante) {
            this.visitante = visitante;
      }
}