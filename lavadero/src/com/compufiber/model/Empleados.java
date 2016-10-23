package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;
import org.openxava.annotations.*;
import org.openxava.util.*;
// import com.compufiber.validadores.*;

@Entity
@Table(name="LV_EMPLEADOS")
public class Empleados extends SuperClaseFeliz {
	@Required
//	@PropertyValidator(value=ValidadorTipoIva.class,message="TipoIva duplicado",onlyOnCreate=true)
	@Column(length=7,nullable=false,name="RUC",unique=true)
	private String ruc ;

	@Required
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=false,name="NOMBRE")	
	private String nombre ;

//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=true,name="TELEFONO1")	
	private String telefono1 ;
	
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=true,name="TELEFONO2")	
	private String telefono2 ;

	@Email
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=true,name="CORREO")	
	private String correo ;
	
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=true,name="DIRECCION")	
	private String direccion ;
	
	
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
		
	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1.toUpperCase().trim();
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2.toUpperCase().trim();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
		
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.toUpperCase().trim();
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
