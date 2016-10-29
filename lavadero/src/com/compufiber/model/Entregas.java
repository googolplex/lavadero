package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.util.*;

@Tab(properties="rotulo,fecha,hora,comentario,montoCobrado,pagadocon.descripcion")
@Entity
@Table(name="LV_ENTREGAS"
,
uniqueConstraints={
        @UniqueConstraint(name="en_entrega_repetida", columnNames={"trabajo_id"})
    }
		)
public class Entregas extends SuperClaseFeliz {

	@DescriptionsList(descriptionProperties="trabajoNro, rotulo")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TRABAJO_ID")
	private TrabajosCab trabajo ;

	@DefaultValueCalculator(CurrentDateCalculator.class)	
	@Stereotype("DATE")
	@Column(name="FECHA",nullable=true)
	private Date fecha; 
	
	@Stereotype("TIME")
	@Required
	@Column(name="HORA",nullable=true)
	private String hora;
	
	@Stereotype("MEMO")
	@Column(length=500,nullable=true,name="COMENTARIO")
	private String comentario;
	
	@Required
	@Stereotype("MONEY")
	@Column(length=19,nullable=false,name="MONTOCOBRADO")
	private Double montoCobrado ;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="FORMADEPAGO_ID")
	private FormasDePago pagadocon ;		
	

	@Hidden
	@Column(name="rotulo")
	private String rotulo;	
	
	public TrabajosCab getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(TrabajosCab trabajo) {
		this.trabajo = trabajo;
	}

	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
		this.hora = hora;
	}



	public String getComentario() {
		return comentario;
	}



	public void setComentario(String comentario) {
		this.comentario = comentario.toUpperCase().trim();
	}



	public Double getMontoCobrado() {
		return montoCobrado;
	}



	public void setMontoCobrado(Double montoCobrado) {
		this.montoCobrado = montoCobrado;
	}



	public FormasDePago getPagadocon() {
		return pagadocon;
	}



	public void setPagadocon(FormasDePago pagadocon) {
		this.pagadocon = pagadocon;
	}



	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@PrePersist
	private void antesDeGrabar() {
		this.setRotulo(this.getTrabajo().getRotulo());
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
			this.setRotulo(this.getTrabajo().getRotulo());
	}	

}
