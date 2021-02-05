package model.managed;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.beans.ContadorAcessosBean;
import model.beans.GrausBean;

@Named ("grausMB")
@SessionScoped
public class GrausMB implements Serializable {

	@EJB
	GrausBean grausBean;
	
	@EJB
    ContadorAcessosBean contadorBean;

	double c;
	double resultadoF;

	public void converterParaFahrenheit() {
		this.resultadoF = this.grausBean.converterParaFahrenheit(c);
		this.contadorBean.incrementar();
		System.out.println("Esse é o acesso: " + this.contadorBean.getContador());
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getResultadoF() {
		return resultadoF;
	}

	public void setResultadoF(double resultadoF) {
		this.resultadoF = resultadoF;
	}
}