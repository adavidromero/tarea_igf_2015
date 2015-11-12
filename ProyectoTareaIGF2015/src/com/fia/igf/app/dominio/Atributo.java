package com.fia.igf.app.dominio;

import java.io.Serializable;
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
@Table(name="as_atributo")
public class Atributo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_atributo")
    private Integer cAtributo;

	@ManyToOne 
	@JoinColumn(name = "c_clase", referencedColumnName = "c_clase")
	private Clase cClase;

	@ManyToOne 
	@JoinColumn(name = "c_metodo", referencedColumnName = "c_metodo")
	private Metodo cMetodo;

    @Basic(optional = false)
    @Column(name = "d_atributo",length=50)
    private String dAtributo;

    @Basic(optional = false)
    @Column(name = "d_tipo_dato_atributo", length=50)
    private String dTipoDatoAtributo;
	
    @Basic(optional = false)
    @Column(name = "c_usuario", length=30)
    private String cUsuario;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;

	@ManyToOne 
	@JoinColumn(name = "c_tipo_atributo", referencedColumnName = "c_tipo_atributo")
	private TipoAtributo cTipoAtributo ;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cAtributo")
	private List<Atributo> atributos;


	public Integer getcAtributo() {
		return cAtributo;
	}

	public void setcAtributo(Integer cAtributo) {
		this.cAtributo = cAtributo;
	}

	public Clase getcClase() {
		return cClase;
	}

	public void setcClase(Clase cClase) {
		this.cClase = cClase;
	}

	public Metodo getcMetodo() {
		return cMetodo;
	}

	public void setcMetodo(Metodo cMetodo) {
		this.cMetodo = cMetodo;
	}

	public String getdAtributo() {
		return dAtributo;
	}

	public void setdAtributo(String dAtributo) {
		this.dAtributo = dAtributo;
	}

	public String getdTipoAtributo() {
		return dTipoDatoAtributo;
	}

	public void setdTipoAtributo(String dTipoAtributo) {
		this.dTipoDatoAtributo = dTipoAtributo;
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

	public TipoAtributo getcTipoAtributo() {
		return cTipoAtributo;
	}

	public void setcTipoAtributo(TipoAtributo cTipoAtributo) {
		this.cTipoAtributo = cTipoAtributo;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
	

}
