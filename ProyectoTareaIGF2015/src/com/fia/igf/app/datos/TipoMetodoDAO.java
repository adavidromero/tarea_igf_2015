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

import com.fia.igf.app.dominio.TipoMetodo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class TipoMetodoDAO implements GenericDAO<TipoMetodo, String>{
	@Autowired
	private HibernateUtil hibernateUtil;
	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;
	
	@Autowired
	public TipoMetodoDAO(HibernateUtil hibernateUtil) {
		// TODO Auto-generated constructor stub
		this.hibernateUtil=hibernateUtil;
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
	public void guardaActualiza(TipoMetodo tipoMetodo) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(tipoMetodo);
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
	public void eliminar(TipoMetodo tipoMetodo) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.delete(tipoMetodo);
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
	public List<TipoMetodo> obtenerTodos() {
		// TODO Auto-generated method stub
		iniciaOperacion();
		//Revisar bien esta sentencia
		Criteria criteria = sesion.createCriteria(TipoMetodo.class)
				.addOrder(Order.asc("cTipoMetodo"));
		List<TipoMetodo> tipoMetodo =(List<TipoMetodo>)criteria.list();
		sesion.close();
		return tipoMetodo;
	}

	@Override
	public TipoMetodo obtenerPorId(Class<TipoMetodo> clazz, String id) {
		// TODO Auto-generated method stub
		iniciaOperacion();
		TipoMetodo tipoMetodo = (TipoMetodo)sesion.get(clazz, id);
		sesion.close();
		return tipoMetodo;
	}

}
