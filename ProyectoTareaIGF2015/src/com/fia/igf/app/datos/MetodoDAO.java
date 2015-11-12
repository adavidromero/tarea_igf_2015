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

import com.fia.igf.app.dominio.*;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class MetodoDAO implements GenericDAO<Metodo, String>{
	@Autowired
	private HibernateUtil hibernateUtil;
	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;
	
	@Autowired
	public MetodoDAO(HibernateUtil hibernateUtil) {
		// TODO Auto-generated constructor stub
		this.hibernateUtil = hibernateUtil;
	}
	//Metodo extraido de AplicativoDAO
	private void iniciaOperacion() throws HibernateException{
		sesion = sessionFactory.openSession();
		tx = sesion.beginTransaction();
	}
		
		//Metodo extraido de AplicativoDAO
	private void manejaExcepcion(HibernateException he) throws HibernateException{
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa DAO",he);
	}

	@Override
	public void guardaActualiza(Metodo metodo) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(metodo);
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
	public void eliminar(Metodo metodo) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.delete(metodo);
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
	public List<Metodo> obtenerTodos() {
		// TODO Auto-generated method stub
		iniciaOperacion();
		//Revisar bien esta sentencia
		Criteria criteria = sesion.createCriteria(Aplicativo.class)
				.addOrder(Order.asc("cMetodo"));
		List<Metodo> metodo =(List<Metodo>)criteria.list();
		sesion.close();
		return metodo;
	}

	@Override
	public Metodo obtenerPorId(Class<Metodo> clazz, String id) {
		// TODO Auto-generated method stub
		iniciaOperacion();
		Metodo metodo = (Metodo)sesion.get(clazz, id);
		sesion.close();
		return metodo;
	}

}
