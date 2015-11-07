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
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class AplicativoDAO implements GenericDAO<Aplicativo, String>{
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public AplicativoDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(Aplicativo aplicativo) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(aplicativo);
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
	public void eliminar(Aplicativo aplicativo) {
		try{
			iniciaOperacion();
			sesion.delete(aplicativo);
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
	public List<Aplicativo> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(Aplicativo.class)
				.addOrder(Order.asc("cAplicativo"));
		List<Aplicativo> aplicativos =(List<Aplicativo>)criteria.list();
		sesion.close();
		return aplicativos;
	}

	@Override
	public Aplicativo obtenerPorId(Class<Aplicativo> clazz, String id) {
		iniciaOperacion();
		Aplicativo aplicativo = (Aplicativo)sesion.get(clazz, id);
		sesion.close();
		return aplicativo;
	}

}
