package com.fia.igf.app.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="as_parametro")
public class Parametro {

	private static final long serialVersionUID = 1L;

    @Basic(optional = false)
	@ManyToOne 
	@JoinColumn(name = "c_clase", referencedColumnName = "c_clase")
	private Clase cClase;

    @Basic(optional = false)
	@ManyToOne 
	@JoinColumn(name = "c_metodo", referencedColumnName = "c_metodo")
	private Atributo cMetodo;

	@Id
    @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cParametro")
    private Integer cParametro;

    @Basic(optional = false)
    @Column(name = "d_parametro", length=50)
    private String dParametro;

    @Basic(optional = false)
    @Column(name = "d_tipo_parametro", length=50)
    private String dTipoParametro;

    @Basic(optional = false)
    @Column(name = "c_usuario", length=50)
    private String cUsuario;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;
    
    @Basic(optional = true)
 	@OneToMany(cascade=CascadeType.ALL, mappedBy="cParametro")
 	private List<Observacion> observaciones;

	public Clase getcClase() {
		return cClase;
	}

	public void setcClase(Clase cClase) {
		this.cClase = cClase;
	}

	public Atributo getcMetodo() {
		return cMetodo;
	}

	public void setcMetodo(Atributo cMetodo) {
		this.cMetodo = cMetodo;
	}

	public Integer getcParametro() {
		return cParametro;
	}

	public void setcParametro(Integer cParametro) {
		this.cParametro = cParametro;
	}

	public String getdParametro() {
		return dParametro;
	}

	public void setdParametro(String dParametro) {
		this.dParametro = dParametro;
	}

	public String getdTipoParametro() {
		return dTipoParametro;
	}

	public void setdTipoParametro(String dTipoParametro) {
		this.dTipoParametro = dTipoParametro;
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

	public List<Observacion> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<Observacion> observaciones) {
		this.observaciones = observaciones;
	}

}
