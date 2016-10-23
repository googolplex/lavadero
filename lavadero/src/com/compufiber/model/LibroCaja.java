package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

@Entity
@Table(name="LV_LIBROCAJA")
public class LibroCaja extends SuperClaseFeliz {

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados empleado ;

	@DescriptionsList(descriptionProperties="cliente.nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TRABAJOSCAB_ID")
	private TrabajosCab trabajo ;

	
	private Double montocobrado = 0.0D;

	
	
	public Empleados getEmpleado() {
		return empleado;
	}



	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}



	public TrabajosCab getTrabajo() {
		return trabajo;
	}



	public void setTrabajo(TrabajosCab trabajo) {
		this.trabajo = trabajo;
	}



	public Double getMontocobrado() {
		return montocobrado;
	}



	public void setMontocobrado(Double montocobrado) {
		this.montocobrado = montocobrado;
	}



	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
