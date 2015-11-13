package com.fia.igf.app.datos;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Parametro;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class ParametroDAO implements GenericDAO<Parametro, Integer>{
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public ParametroDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(Parametro parametro) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(parametro);
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
	public void eliminar(Parametro parametro) {
		try{
			iniciaOperacion();
			sesion.delete(parametro);
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
	public List<Parametro> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(Parametro.class)
				.addOrder(Order.asc("id"));
		List<Parametro> parametros =(List<Parametro>)criteria.list();
		sesion.close();
		return parametros;
	}

	@Override
	public Parametro obtenerPorId(Class<Parametro> clazz, Integer id) {
		iniciaOperacion();
		Parametro parametro = (Parametro)sesion.get(clazz, id);
		sesion.close();
		return parametro;
	}

	public Parametro obtenerPorIdCompuesto(String cClase, String cMetodo,
			String cParametro) {
		iniciaOperacion();
		String queryStr="from Parametro p where p.id.cMetodo.cMetodo=:cMetodo and "+
						"p.id.cMetodo.cClase.cClase=:cClase and "+
						"p.id.cParametro=:cParametro";
		Query query=sesion.createQuery(queryStr);
		query.setParameter("cMetodo", Integer.parseInt(cMetodo));
		query.setParameter("cClase", Integer.parseInt(cClase));
		query.setParameter("cParametro", Integer.parseInt(cParametro));
		Parametro parametro = (Parametro)query.uniqueResult();
		sesion.close();
		return parametro;
	}

}
