package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

@Table(name="LV_TIEMPOESTIMADO",
uniqueConstraints={
	@UniqueConstraint(name="no_repetir_estimados", columnNames={"tipovehiculos_id", "servicios_id","empleados_id"})		
	})
public class TiempoEstimado extends SuperClaseFeliz {

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TIPOVEHICULOS_ID")	
	private TipoVehiculos tipovehiculo;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="SERVICIOS_ID")	
	private Servicios servicio;
	
	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados comisionista ;		
	
	@Required	
	@Column(length=19,nullable=false,name="ESTIMADOHORAS")	
	private Double estimadoHoras ;
		
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

	public Empleados getComisionista() {
		return comisionista;
	}

	public void setComisionista(Empleados comisionista) {
		this.comisionista = comisionista;
	}

	public Double getEstimadoHoras() {
		return estimadoHoras;
	}

	public void setEstimadoHoras(Double estimadoHoras) {
		this.estimadoHoras = estimadoHoras;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}		

}
