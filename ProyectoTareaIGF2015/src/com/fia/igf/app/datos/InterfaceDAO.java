package com.fia.igf.app.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Interface;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class InterfaceDAO implements GenericDAO<Interface, Integer>{
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public InterfaceDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(Interface interfaz) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(interfaz);
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
	public void eliminar(Interface interfaz) {
		try{
			iniciaOperacion();
			sesion.delete(interfaz);
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
	public List<Interface> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(Interface.class)
				.addOrder(Order.asc("cInterface"));
		List<Interface> interfaces =(List<Interface>)criteria.list();
		sesion.close();
		return interfaces;
	}

	@Override
	public Interface obtenerPorId(Class<Interface> clazz, Integer id) {
		iniciaOperacion();
		Interface interfaz = (Interface)sesion.get(clazz, id);
		sesion.close();
		return interfaz;
	}
}
