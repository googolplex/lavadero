package com.compufiber.model;


import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.util.*;


@Entity
@Table(name="LV_SERVICIOS")
public class Servicios extends SuperClaseFeliz {
	
	@Required
	//@PropertyValidator(value=Validador2013e.class,message="numero duplicado",onlyOnCreate=true)	
	@Column(length=4,nullable=false,name="LVS_CODIGO",unique=true)
	@Digits(integer=19, fraction = 0)	
	private Long codigo ;

	@Required
	//@PropertyValidator(value=Validador2013d.class,message="Nombre duplicado")
	@Column(length=50,nullable=false,name="LVS_DESCRIPCION",unique=true)	
	private String descripcion ;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase().trim();
	}
	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}
	
}