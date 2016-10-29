package com.compufiber.model;


import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.util.*;


@Entity
@Table(name="LV_SERVICIOS",
uniqueConstraints={
        @UniqueConstraint(name="lv_no_repetir_codigo", columnNames={"codigo"}),
        @UniqueConstraint(name="lv_no_repetir_descripcion", columnNames={"descripcion"})        
    })
public class Servicios extends SuperClaseFeliz {
	
	@Required
	@Column(length=20,nullable=false,name="CODIGO")
	@Digits(integer=19, fraction = 0)	
	private Long codigo ;

	@Required
	@Column(length=100,nullable=false,name="DESCRIPCION")	
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