package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.util.*;

@Tab(properties="rotulo,fecha,precio.rotulo,comisionista.nombre,horaInicio,horaFin,montoComision")
@Entity
@Table(name="LV_COLAS"
, uniqueConstraints={
        @UniqueConstraint(name="cl_no_repetir_trabajo", columnNames={"trabajo_id","precios_id","empleados_id"})        
    }
)
public class Colas extends SuperClaseFeliz {
	
	@DescriptionsList(descriptionProperties="trabajoNro, cliente.nombre,vehiculo.chapa")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TRABAJO_ID")
	private TrabajosCab trabajo ;		
	
	@DescriptionsList(descriptionProperties="rotulo")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="PRECIOS_ID")	
	private Precios precio;
	
	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados comisionista ;	

	@DefaultValueCalculator(CurrentDateCalculator.class)
	@Stereotype("DATE")
	@Column(name="FECHA",nullable=true)
	private Date fecha; 	
	
	@Stereotype("TIME")
	@Required
	@Column(name="HORAINICIO",nullable=true)
	private String horaInicio;
	
	@Stereotype("TIME")
	@Required
	@Column(name="HORAFIN",nullable=true)
	private String horaFin;	
		
	@Required
	@Stereotype("MONEY")
	@Column(name="MONTOCOMISION",nullable=true,length=20)
	private double montoComision ;
	
	@Hidden
	@Column(name="rotulo")
	private String rotulo;	
	
	public TrabajosCab getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(TrabajosCab trabajo) {
		this.trabajo = trabajo;
	}


	public Precios getPrecio() {
		return precio;
	}

	public void setPrecio(Precios precio) {
		this.precio = precio;
	}

	public Empleados getComisionista() {
		return comisionista;
	}



	public void setComisionista(Empleados comisionista) {
		this.comisionista = comisionista;
	}
	


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public double getMontoComision() {
		return montoComision;
	}

	public void setMontoComision(double montoComision) {
		this.montoComision = montoComision;
	}
		

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
			this.setRotulo(this.getTrabajo().getRotulo());			
	}	
	
	@PrePersist
	private void antesDeGrabar() {
		this.setRotulo(this.getTrabajo().getRotulo());
	}	
}
