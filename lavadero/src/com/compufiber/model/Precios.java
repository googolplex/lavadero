package com.compufiber.model;


import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.util.*;


@Entity
@Table(name="LV_PRECIOS"
//,
//uniqueConstraints={
//		@UniqueConstraint(name="no_repetir_precios", columnNames={"lvt_tipovehiculo", "lvs_servicio"})		
//		}
		)

public class Precios extends SuperClaseFeliz {
	
	@Required
	//@PropertyValidator(value=Validador2013e.class,message="numero duplicado",onlyOnCreate=true)	
	@Column(length=4,nullable=false,name="CODIGO",unique=true)
	@Digits(integer=19, fraction = 0)	
	private Long codigo ;

	@Required
//	@PropertyValidator(value=ValidadorTipoIva.class,message="TipoIva duplicado",onlyOnCreate=true)
	@Column(length=7,nullable=false,name="PRECIO",unique=true)
	private Double precio ;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TIPOVEHICULOS_ID")	
	private TipoVehiculos tipovehiculo;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="SERVICIOS_ID")	
	private Servicios servicio;

	@Column(length=10,nullable=false,name="LV_MCOMISION")
	private Double montocomision = 0.0D;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public TipoVehiculos getTipovehiculo() {
		return tipovehiculo;
	}

	public void setTipovehiculo(TipoVehiculos tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}

	public Servicios getServicio() {
		return servicio;
	}

	public void setServicio(Servicios servicio) {
		this.servicio = servicio;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
	
	