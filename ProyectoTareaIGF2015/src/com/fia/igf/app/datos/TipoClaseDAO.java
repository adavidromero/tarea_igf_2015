package com.fia.igf.app.datos;


import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.*;
import com.fia.igf.utilidades.datos.*;

@Repository
public class TipoClaseDAO implements GenericDAO<TipoClase, String>{
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public TipoClaseDAO(HibernateUtil hibernateUtil){
		this.hibernateUtil=hibernateUtil;
		
	}

	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException{
		sesion = sessionFactory.openSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException{
		tx.rollback();
		throw new HibernateException("Ocurri� un error en la capa DAO",he);
	}

	@Override
	public void guardaActualiza(TipoClase tipoClase) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(tipoClase);
			tx.commit();
			sesion.flush();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally{
			sesion.close();
		}
		
	}

	@Override
	public void eliminar(TipoClase tipoClase) {
		try{
			iniciaOperacion();
			sesion.delete(tipoClase);
			tx.commit();
			sesion.flush();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally{
			sesion.close();
		}	
		
	}

	@Override
	public List<TipoClase> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(TipoClase.class)
				.addOrder(Order.asc("id"));
		List<TipoClase> tiposClases =(List<TipoClase>)criteria.list();
		sesion.close();
		return tiposClases;
	}

	@Override
	public TipoClase obtenerPorId(Class<TipoClase> clazz, String id) {
		iniciaOperacion();
		TipoClase aplicativo = (TipoClase)sesion.get(clazz, id);
		sesion.close();
		return aplicativo;
	}
}
