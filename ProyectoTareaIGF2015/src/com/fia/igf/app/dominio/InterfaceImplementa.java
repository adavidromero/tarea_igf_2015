package com.fia.igf.app.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "as_interface_implementa")
public class InterfaceImplementa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "c_interface_implementa")
	private String cInterfaceImplementa = new String();

	@ManyToOne
	@JoinColumn(name = "c_interface_hijo", referencedColumnName = "c_interface")
	private Interface interfaceHijo;

	@ManyToOne
	@JoinColumn(name = "c_interface_padre", referencedColumnName = "c_interface")
	private Interface interfacePadre;

	public InterfaceImplementa() {

	}

	public InterfaceImplementa(String cInterfaceImplementa,
			Interface interfaceHijo, Interface interfacePadre) {
		this.cInterfaceImplementa = cInterfaceImplementa;
		this.interfaceHijo = interfaceHijo;
		this.interfacePadre = interfacePadre;
	}

	public String getcInterfaceImplementa() {
		return cInterfaceImplementa;
	}

	public void setcInterfaceImplementa(String cInterfaceImplementa) {
		this.cInterfaceImplementa = cInterfaceImplementa;
	}

	public Interface getInterfaceHijo() {
		return interfaceHijo;
	}

	public void setInterfaceHijo(Interface interfaceHijo) {
		this.interfaceHijo = interfaceHijo;
	}

	public Interface getInterfacePadre() {
		return interfacePadre;
	}

	public void setInterfacePadre(Interface interfacePadre) {
		this.interfacePadre = interfacePadre;
	}

}
