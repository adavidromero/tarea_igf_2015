package com.fia.igf.app.datos;


import java.util.List;

import org.hibernate.*;
import com.fia.igf.app.dominio.*;
import com.fia.igf.utilidades.datos.*;

public class TipoClaseDAO{
	private HibernateUtil hibernateUtil = new HibernateUtil() ;
	
	public void guardar (TipoClase tipoClase) {
		SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
		Session sesion = sessionFactory.openSession() ;
		Transaction tx = sesion.beginTransaction() ;
		sesion.save(tipoClase) ;
		tx.commit() ;
		sesion.flush() ;
		sesion.close() ;
	}
	public void actualizar(TipoClase tipoClase) {
	    // 1. Obtener SessionFactory
		SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	    // 2. Obtener Session
	    Session sesion = sessionFactory.openSession() ;
	    // 3. Obtener Transaccion
	    Transaction tx = sesion.beginTransaction() ;
	    // 4. Hacer Operaciones
	    sesion.update(tipoClase) ;
	    // 5. Guardar Cambios
	    tx.commit() ;
	    sesion.flush() ;
	    // 6. Cerrar la sesion
	    sesion.close() ;
	}
	public void eliminar(TipoClase tipoClase) {
	    // 1. Obtener SessionFactory
	    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	    // 2. Obtener Session
	    Session sesion = sessionFactory.openSession() ;
	    // 3. Obtener Transaccion
	    Transaction tx = sesion.beginTransaction() ;
	    // 4. Hacer Operaciones
	    sesion.delete(tipoClase) ;
	    // 5. Guardar Cambios
	    tx.commit() ;
	    sesion.flush() ;
	    // 6. Cerrar la sesion
	    sesion.close() ;
	}
	public TipoClase obtenerTipCla (String numTc) {
		SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
		Session sesion = sessionFactory.openSession();
		String query = "from tb_tipo_clase as tc where tc.c_tipo_clase='" +
		numTc + "'" ;
		List tipoClases = sesion.createQuery(query).list() ;
		sesion.close();
		if (tipoClases.isEmpty())
		return null;
		else
		return (TipoClase) tipoClases.get(0);
	}
}
