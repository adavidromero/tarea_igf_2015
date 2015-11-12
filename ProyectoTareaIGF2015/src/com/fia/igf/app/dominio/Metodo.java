package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
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
@Table(name="as_metodo")
public class Metodo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "c_metodo")
    private Integer cMetodo;
	
	@Id
	@ManyToOne 
	@JoinColumn(name = "c_clase", referencedColumnName = "c_clase")
	private Clase cClase;
	
	@ManyToOne 
	@JoinColumn(name = "c_tipo_metodo", referencedColumnName = "c_tipo_metodo")
	private TipoMetodo cTipoMetodo;

    @Basic(optional = false)
    @Column(name = "d_metodo", length=50)
    private String dMetodo;

    @Basic(optional = false)
    @Column(name = "d_tipo_retorno",length=50)
    private String dTipoRetorno;

    @Basic(optional = false)
    @Column(name = "c_usuario",length=30)
    private String cUsuario;
    
    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;

    @Basic(optional = false)
    @Column(name = "b_activo")
    private Integer bActivo;

    @Basic(optional = false)
    @Column(name = "n_parametros")
    private Integer nParametros;
    

	@OneToMany(cascade=CascadeType.ALL, mappedBy="cMetodo")
	private List<Atributo> atributos;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="cMetodo")
	private List<Observacion> observaciones;
	
	public Metodo(){
		
	}

	public Metodo(Integer cMetodo, Clase cClase, TipoMetodo cTipoMetodo, String dMetodo, String dTipoRetorno, String cUsuario, Date fIngreso, Integer bActivo, Integer nParametros){
			this.cMetodo = cMetodo;
			this.dMetodo = dMetodo;
			this.fIngreso = fIngreso;
		}

	public String getdMetodo() {
		return dMetodo;
	}

	public void setdMetodo(String dMetodo) {
		this.dMetodo = dMetodo;
	}

	public String getdTipoRetorno() {
		return dTipoRetorno;
	}

	public void setdTipoRetorno(String dTipoRetorno) {
		this.dTipoRetorno = dTipoRetorno;
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

	public Integer getbActivo() {
		return bActivo;
	}

	public void setbActivo(Integer bActivo) {
		this.bActivo = bActivo;
	}

	public Integer getnParametros() {
		return nParametros;
	}

	public void setnParametros(Integer nParametros) {
		this.nParametros = nParametros;
	}

	public String getTipoMetodo() {
		return cTipoMetodo.getcTipoMetodo();
	}

	public void setcTipoMetodo(TipoMetodo cTipoMetodo) {
		this.cTipoMetodo = cTipoMetodo;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public List<Observacion> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<Observacion> observaciones) {
		this.observaciones = observaciones;
	}


	public Integer getcMetodo() {
		return cMetodo;
	}


	public void setcMetodo(Integer cMetodo) {
		this.cMetodo = cMetodo;
	}


	public String getcClase() {
		return cClase.getcClase().toString();
	}


	public void setcClase(Clase cClase) {
		this.cClase = cClase;
	}

}
