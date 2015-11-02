package com.fia.igf.app.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class AplicativoDAO implements GenericDAO<Aplicativo, String>{
	@Autowired
	private HibernateUtil hibernateUtil;

	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException{
		sesion = sessionFactory.openSession();
		tx = sesion.beginTransaction();
	}

	@Override
	public void guardarActualiza(Aplicativo aplicativo) {
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
		iniciaOperacion();
	    sesion.delete(aplicativo) ;
	    tx.commit() ;
	    sesion.flush() ;
	    sesion.close() ;
	}

	@Override
	public List<Aplicativo> obtenerTodos(Class<Aplicativo> clazz) {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(clazz);
		sesion.close();
		return (List<Aplicativo>)criteria.list();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException{
		tx.rollback();
		throw new HibernateException("Ocurri� un error en la capa DAO",he);
	}

	@Override
	public Aplicativo obtenerPorId(Class<Aplicativo> clazz, String id) {
		iniciaOperacion();
		Aplicativo aplicativo = (Aplicativo)sesion.get(clazz, id);
		sesion.close();
		return aplicativo;
	}

}
