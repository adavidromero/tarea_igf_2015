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
import com.fia.igf.app.dominio.ClaseInterface;
import com.fia.igf.utilidades.datos.HibernateUtil;
@Repository
public class ClaseInterfaceDAO implements GenericDAO<ClaseInterface,Integer>{

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public ClaseInterfaceDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(ClaseInterface obj) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(obj);
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
	public void eliminar(ClaseInterface obj) {
		try{
			iniciaOperacion();
			sesion.delete(obj);
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
	public List<ClaseInterface> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(ClaseInterface.class).addOrder(Order.asc("id"));
		List<ClaseInterface> clases =(List<ClaseInterface>)criteria.list();
		sesion.close();
		return clases;
	}

	@Override
	public ClaseInterface obtenerPorId(Class<ClaseInterface> clazz, Integer id) {
		iniciaOperacion();
		ClaseInterface claseInterface = (ClaseInterface) sesion.get(clazz, id);
		sesion.close();
		return claseInterface;
	}

}
