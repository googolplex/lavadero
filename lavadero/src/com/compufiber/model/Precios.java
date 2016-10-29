package com.compufiber.model;


import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
@Tab(properties="servicio.descripcion,tipovehiculo.descripcion,precio,montocomision")
@Entity
@Table(name="LV_PRECIOS",
	uniqueConstraints={
		@UniqueConstraint(name="pr_no_repetir_precios", columnNames={"tipovehiculos_id", "servicios_id"}), 
		@UniqueConstraint(name="pr_no_repetir_codigo", columnNames={"codigo"})		
		})

public class Precios extends SuperClaseFeliz {
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TIPOVEHICULOS_ID")	
	private TipoVehiculos tipovehiculo;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="SERVICIOS_ID")	
	private Servicios servicio;

	@Required
	@Column(length=20,nullable=false,name="CODIGO")
	@Digits(integer=19, fraction = 0)	
	private Long codigo ;
	
	@Stereotype("MONEY")
	@Column(length=19,nullable=false,name="PRECIO")
	private Double precio ;	
	
	@Stereotype("MONEY")
	@Column(length=10,nullable=false,name="LV_MCOMISION")
	private Double montocomision = 0.0D;
	
	@Stereotype("TIME")
	@Required
	@Column(name="duracionhoras",nullable=true)
	private String duracionHoras;	
	
	@Hidden
	@Column(name="rotulo")
	private String rotulo;
	
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

	public Double getMontocomision() {
		return montocomision;
	}

	public void setMontocomision(Double montocomision) {
		this.montocomision = montocomision;
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

	
	
	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	
	
	public String getDuracionHoras() {
		return duracionHoras;
	}

	public void setDuracionHoras(String duracionHoras) {
		this.duracionHoras = duracionHoras;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
			this.rotulo = this.getServicio().getDescripcion() +"-"+ this.getTipovehiculo().getDescripcion() ;
	}
	@PrePersist
	private void antesDeGrabar() {
		this.rotulo = this.getServicio().getDescripcion() +"-"+ this.getTipovehiculo().getDescripcion() ;
	}
}
	
	