package com.fia.igf.app.dominio;

import java.util.Date;

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
    @Column(name = "c_usuario")
    private String cUsuario;
    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;
	@ManyToOne
	private TipoClase tipoClase;
}
