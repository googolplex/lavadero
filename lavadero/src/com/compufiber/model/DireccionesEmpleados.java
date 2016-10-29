package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

@Entity
@Table(name="LV_DIRECCIONESEMPLEADOS",
uniqueConstraints={
        @UniqueConstraint(name="de_no_repetir_direccion", columnNames={"direccion","ciudad","barrio"})        
    })
public class DireccionesEmpleados extends SuperClaseFeliz {

	@ManyToOne
	@JoinColumn(name="EMPLEADO_ID")
	private Empleados empleado ;	
	
	@Column(length=100,nullable=true,name="DIRECCION")	
	private String direccion ;
	
	@Required
	@Column(length=100,nullable=false,name="CIUDAD")
	private String ciudad ;
	@Required
	@Column(length=100,nullable=false,name="BARRIO")	
	private String barrio ;
	
	@Stereotype("PHONE")
	@Column(length=40,nullable=true,name="CELULAR2")	
	private String celular2 ;
	
	@Stereotype("PHONE")
	@Column(length=40,nullable=true,name="LINEABAJA")	
	private String lineabaja ;	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.toUpperCase().trim();
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad.toUpperCase().trim();
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio.toUpperCase().trim();
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}			
}