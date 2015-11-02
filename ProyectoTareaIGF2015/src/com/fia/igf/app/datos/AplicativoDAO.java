package com.fia.igf.app.datos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class AplicativoDAO implements GenericDAO<Aplicativo, Short>{
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public void guardar(Aplicativo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Aplicativo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aplicativo> obtenerTodos(Class<Aplicativo> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aplicativo obtenerPorId(Aplicativo obj) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
