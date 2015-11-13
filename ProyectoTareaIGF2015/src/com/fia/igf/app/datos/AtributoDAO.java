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
import com.fia.igf.app.dominio.Atributo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class AtributoDAO implements GenericDAO<Atributo, Integer>{
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public AtributoDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(Atributo atributo) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(atributo);
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
	public void eliminar(Atributo atributo) {
		try{
			iniciaOperacion();
			sesion.delete(atributo);
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
	public List<Atributo> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(Atributo.class)
				.addOrder(Order.asc("id"));
		List<Atributo> atributos =(List<Atributo>)criteria.list();
		sesion.close();
		return atributos;
	}

	/* No usar */
	@Override
	public Atributo obtenerPorId(Class<Atributo> clazz, Integer id) {
		return null;
	}
	
	public Atributo obtenerPorIdCompuesto(Integer cAtributo, Integer cClase){
		iniciaOperacion();
		String query="from Atributo a where a.id.cAtributo=:cAtributo and "+
						"a.id.cClase.cClase=:cClase";
		Query consulta = sesion.createQuery(query);
		consulta.setParameter("cAtributo", cAtributo);
		consulta.setParameter("cClase", cClase);
		Atributo atributo = (Atributo)consulta.uniqueResult();
		sesion.close();
		return atributo;
	}
	

}
