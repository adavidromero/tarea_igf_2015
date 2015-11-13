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
import com.fia.igf.app.dominio.InterfaceImplementa;
import com.fia.igf.app.dominio.Metodo;
import com.fia.igf.utilidades.datos.HibernateUtil;

@Repository
public class InterfaceImplementaDAO implements GenericDAO<InterfaceImplementa,Integer> {
	@Autowired
	private HibernateUtil hibernateUtil;
	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;
	
	@Autowired
	public InterfaceImplementaDAO(HibernateUtil hibernateUtil){
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
	public void guardaActualiza(InterfaceImplementa interfImplementa) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.saveOrUpdate(interfImplementa);
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
	public void eliminar(InterfaceImplementa interfImplementa) {
		// TODO Auto-generated method stub
		try{
			iniciaOperacion();
			sesion.delete(interfImplementa);
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
	public List<InterfaceImplementa> obtenerTodos() {
		// TODO Auto-generated method stub
		System.out.println("Verificando problemas");
		iniciaOperacion();
		//Revisar bien esta sentencia
		Criteria criteria = sesion.createCriteria(InterfaceImplementa.class)
				.addOrder(Order.asc("cInterfaceImplementa"));
		List<InterfaceImplementa> interImplementa =(List<InterfaceImplementa>)criteria.list();
		sesion.close();
		return interImplementa;
	}

	@Override
	public InterfaceImplementa obtenerPorId(Class<InterfaceImplementa> clazz,
			Integer id) {
		iniciaOperacion();
		InterfaceImplementa interImplementa = (InterfaceImplementa)sesion.get(clazz, id);
		sesion.close();
		return interImplementa;
	}
	

}
