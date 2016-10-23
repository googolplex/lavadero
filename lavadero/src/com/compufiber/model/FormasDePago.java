package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
// import com.compufiber.validadores.*;

@Entity
@Table(name="LV_FORMASDEPAGO")
public class FormasDePago extends SuperClaseFeliz {
	@Required
//	@PropertyValidator(value=ValidadorTipoIva.class,message="TipoIva duplicado",onlyOnCreate=true)
	@Column(length=7,nullable=false,name="CODIGO",unique=true)
	private Long codigo ;

	@Required
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
	@Column(length=40,nullable=false,name="DESCRIPCION",unique=true)	
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
