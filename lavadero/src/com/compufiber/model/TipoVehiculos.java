package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
// import com.compufiber.validadores.*;

@Entity
@Table(name="LV_TIPOVEHICULOS",
uniqueConstraints={
        @UniqueConstraint(name="tv_no_repetir_codigo", columnNames={"codigo"}),
        @UniqueConstraint(name="tv_no_repetir_descripcion", columnNames={"descripcion"})
    })
public class TipoVehiculos extends SuperClaseFeliz {
	@Required
//	@PropertyValidator(value=ValidadorTipoIva.class,message="TipoIva duplicado",onlyOnCreate=true)
	@Column(length=20,nullable=false,name="CODIGO")
	private Long codigo ;

	@Required
//	@PropertyValidator(value=Validador2014v.class,message="Nombre duplicado")
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