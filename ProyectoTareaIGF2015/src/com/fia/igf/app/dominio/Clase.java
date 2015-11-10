package com.fia.igf.app.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "as_clase")
public class Clase {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "c_clase")
    private Integer cClase;

    @Basic(optional = false)
    @Column(name = "d_clase")
    private String dClase;

    @Basic(optional = false)
    @Column(name = "c_usuario")
    private String cUsuario;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;

	@ManyToOne 
	@JoinColumn(name = "c_aplicativo", referencedColumnName = "c_aplicativo")
	private Aplicativo cAplicativo;

	@ManyToOne
	@JoinColumn(name = "c_clase_padre", referencedColumnName = "c_clase")
	private Clase clasePadre;

	@ManyToOne
	@JoinColumn(name = "c_tipo_clase", referencedColumnName = "c_tipo_clase")
	private TipoClase cTipoClase;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="clasePadre")
	private List<Clase> clasesHijas;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cClase")
	private List<ClaseInterface> clasesInterfaces;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cClase")
	private List<Metodo> metodos;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cClase")
	private List<Atributo> atributos;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cClase")
	private List<Observacion> observaciones;

    
    public Clase(){
    	
    }
    

	public Clase(Integer cClase, String dClase, String cUsuario, Date fIngreso,
			Aplicativo cAplicativo, Clase clasePadre, TipoClase cTipoClase) {
		super();
		this.cClase=cClase;
		this.dClase = dClase;
		this.cUsuario = cUsuario;
		this.fIngreso = fIngreso;
		this.cAplicativo = cAplicativo;
		this.clasePadre = clasePadre;
		this.cTipoClase = cTipoClase;
	}



	public void setcClase(Integer cClase) {
		this.cClase = cClase;
	}

	public Integer getcClase() {
		return cClase;
	}

	public String getdClase() {
		return dClase;
	}

	public void setdClase(String dClase) {
		this.dClase = dClase;
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

	public Clase getClasePadre() {
		return clasePadre;
	}

	public void setClasePadre(Clase clasePadre) {
		this.clasePadre = clasePadre;
	}

	public List<Clase> getClasesHijas() {
		return clasesHijas;
	}

	public void setClasesHijas(List<Clase> clasesHijas) {
		this.clasesHijas = clasesHijas;
	}

	public Aplicativo getcAplicativo() {
		return cAplicativo;
	}

	public void setcAplicativo(Aplicativo cAplicativo) {
		this.cAplicativo = cAplicativo;
	}


	public TipoClase getcTipoClase() {
		return cTipoClase;
	}

	public void setcTipoClase(TipoClase cTipoClase) {
		this.cTipoClase = cTipoClase;
	}

	public List<ClaseInterface> getClasesInterfaces() {
		return clasesInterfaces;
	}

	public void setClasesInterfaces(List<ClaseInterface> clasesInterfaces) {
		this.clasesInterfaces = clasesInterfaces;
	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
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

	
	
}
