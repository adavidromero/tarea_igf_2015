package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_aplicativo",catalog="modelo_proyecto", schema="")
public class Aplicativo implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="c_aplicativo",nullable = false, length=5)
	private String id;
	
	@Column(name="d_aplicativo",nullable = false, length=100)
	private String descripcion;

	@Basic(optional = false)
	@Column(name = "f_ingreso")
	private Date fechaIngreso;

	public Aplicativo(){
		
	}
	
	public Aplicativo(String id, String descripcion, Date fechaIngreso) {
		this.id=id;
		this.descripcion=descripcion;
		this.fechaIngreso=fechaIngreso;
	}

	public String getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
