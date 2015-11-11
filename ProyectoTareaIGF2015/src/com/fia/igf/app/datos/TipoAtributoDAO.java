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

import com.fia.igf.app.dominio.TipoAtributo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class TipoAtributoDAO implements GenericDAO<TipoAtributo, String>{

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	public TipoAtributoDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(TipoAtributo tipoAtributo) {
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(tipoAtributo);
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
	public void eliminar(TipoAtributo tipoAtributo) {
		try{
			iniciaOperacion();
			sesion.delete(tipoAtributo);
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
	public List<TipoAtributo> obtenerTodos() {
		iniciaOperacion();
		Criteria criteria = sesion.createCriteria(TipoAtributo.class)
				.addOrder(Order.asc("id"));
		List<TipoAtributo> tiposAtributos =(List<TipoAtributo>)criteria.list();
		sesion.close();
		return tiposAtributos;
	}

	@Override
	public TipoAtributo obtenerPorId(Class<TipoAtributo> clazz, String id) {
		iniciaOperacion();
		TipoAtributo aplicativo = (TipoAtributo)sesion.get(clazz, id);
		sesion.close();
		return aplicativo;
	}

}
