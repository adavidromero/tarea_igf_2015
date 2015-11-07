package com.fia.igf.app.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.Clase;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class ClaseDAO implements GenericDAO<Clase,Integer>{

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public ClaseDAO(HibernateUtil hibernateUtil){
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
		throw new HibernateException("Ocurrió un error en la capa DAO",he);
	}

	@Override
	public void guardaActualiza(Clase clase) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(clase);
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
	public void eliminar(Clase clase) {
		try{
			iniciaOperacion();
			sesion.delete(clase);
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
	public List<Clase> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(Clase.class)
				.addOrder(Order.asc("id"));
		List<Clase> clases =(List<Clase>)criteria.list();
		sesion.close();
		return clases;
	}

	@Override
	public Clase obtenerPorId(Class<Clase> clazz, Integer id) {
		iniciaOperacion();
		Clase clase = (Clase)sesion.get(clazz, id);
		sesion.close();
		return clase;
	}

}
