package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
// import com.compufiber.validadores.*;

@Entity
@Table(name="LV_EMPLEADOS",
uniqueConstraints={
        @UniqueConstraint(name="em_no_repetir_nombre", columnNames={"nombre"}),
        @UniqueConstraint(name="em_no_repetir_ruc", columnNames={"ruc"})
    })
public class Empleados extends SuperClaseFeliz {
	@Required
	@Column(length=20,nullable=false,name="RUC")
	private String ruc ;

	@Required
	@Column(length=100,nullable=false,name="NOMBRE")	
	private String nombre ;

	@Stereotype("PHONE")
	@Column(length=40,nullable=true,name="CELULAR")	
	private String celular ;
	

	@Stereotype("EMAIL")
	@Column(length=100,nullable=true,name="CORREO")	
	private String correo ;
	
	@DescriptionsList(descriptionProperties="direccion, ciudad, barrio")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="DIRECCION_ID")
	private DireccionesEmpleados direccion ;		
	
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc.toUpperCase().trim();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase().trim();
	}

	
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public DireccionesEmpleados getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionesEmpleados direccion) {
		this.direccion = direccion;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
