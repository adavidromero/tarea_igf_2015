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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="as_interface")
public class Interface {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_interface")
	public Integer cInterface;
	
	@Basic(optional = false)
    @Column(name = "d_interface", length=50)
	public String dInterface;


	@Basic(optional = false)
    @Column(name = "c_usuario", length=30)
	public String cUsuario;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;
    
    
    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="interfaceHijo")
	private List<InterfaceImplementa> interfacesPadres;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="interfacePadre")
	private List<InterfaceImplementa> interfacesHijos;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cInterface")
	private List<ClaseInterface> clasesInterfaces;
    
    public Interface(){
    	
    }

	public String getdInterface() {
		return dInterface;
	}

	public void setdInterface(String dInterface) {
		this.dInterface = dInterface;
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

	public void setcInterface(Integer cInterface) {
		this.cInterface = cInterface;
	}

	public List<InterfaceImplementa> getInterfacesPadres() {
		return interfacesPadres;
	}

	public void setInterfacesPadres(List<InterfaceImplementa> interfacesPadres) {
		this.interfacesPadres = interfacesPadres;
	}

	public List<InterfaceImplementa> getInterfacesHijos() {
		return interfacesHijos;
	}

	public void setInterfacesHijos(List<InterfaceImplementa> interfacesHijos) {
		this.interfacesHijos = interfacesHijos;
	}

	public List<ClaseInterface> getClasesInterfaces() {
		return clasesInterfaces;
	}

	public void setClasesInterfaces(List<ClaseInterface> clasesInterfaces) {
		this.clasesInterfaces = clasesInterfaces;
	}

	public Integer getcInterface() {
		return cInterface;
	}

}
