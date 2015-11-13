package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="as_observacion")
public class Observacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "c_observacion")
    private Integer cObservacion;

    @Basic(optional = false)
    @Column(name = "d_observacion", length=4000)
    private String dObservacion;

    @Basic(optional = false)
    @Column(name = "c_usuario", length=30)
    private String cUsuario;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;

    @Basic(optional = false)
    @Column(name = "b_activo")
    private Integer bActivo;

    @Basic(optional = true)
    @ManyToOne 
	@JoinColumns({
		@JoinColumn(name = "c_metodo", referencedColumnName = "c_metodo",insertable = false, updatable = false),
		@JoinColumn(name = "c_clase", referencedColumnName = "c_clase",insertable = false, updatable = false)
	})
	private Metodo cMetodo;

    @Basic(optional = true)
    @ManyToOne 
	@JoinColumns({
		@JoinColumn(name = "c_clase", referencedColumnName = "c_clase"),
		@JoinColumn(name = "c_metodo", referencedColumnName = "c_metodo"),
		@JoinColumn(name = "c_parametro", referencedColumnName = "c_parametro"),
	})
	private Parametro cParametro;


	public Integer getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(Integer cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getdObservacion() {
		return dObservacion;
	}

	public void setdObservacion(String dObservacion) {
		this.dObservacion = dObservacion;
	}

	public String getcUsuario() {
		return cUsuario;
	}

	public void setcUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
	}

	public Date getfIngreso() {
		return fIngreso;
	}

	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}

	public Integer getcActivo() {
		return bActivo;
	}

	public void setcActivo(Integer cActivo) {
		this.bActivo = cActivo;
	}

	

	public Metodo getcMetodo() {
		return cMetodo;
	}

	public void setcMetodo(Metodo cMetodo) {
		this.cMetodo = cMetodo;
	}

	public Parametro getcParametro() {
		return cParametro;
	}

	public void setcParametro(Parametro cParametro) {
		this.cParametro = cParametro;
	}

}

