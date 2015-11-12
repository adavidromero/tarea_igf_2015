package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.AtributoDAO;
import com.fia.igf.app.datos.TipoAtributoDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Atributo;

@Transactional
@Service
public class CtrlAtributo {
	@Autowired 
	private AtributoDAO atributoDao;

	@Autowired 
	private TipoAtributoDAO tipoAtributoDao ;

	@Autowired
	public CtrlAtributo(AtributoDAO atributoDao, TipoAtributoDAO tipoAtributoDao){
		this.atributoDao=atributoDao;
		this.tipoAtributoDao=tipoAtributoDao;
	}

	public boolean crearAtributo(Integer id, String descripcion, Date fechaIngreso){
		if(atributoDao.obtenerPorId(Atributo.class, id)==null){
				Atributo atributo = new Atributo();
				atributoDao.guardaActualiza(atributo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraAtributo(Integer id){
		if(atributoDao.obtenerPorId(Atributo.class, id)!=null){
			Atributo atributo = atributoDao.obtenerPorId(Atributo.class, id);
			atributoDao.eliminar(atributo);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Atributo> obtenerTodosAtributos(){
		return atributoDao.obtenerTodos();
	}

	public Atributo obtenerPorId(Integer id){
		return atributoDao.obtenerPorId(Atributo.class, id);
	}
	
	public boolean actualizar(Atributo atributo){
		atributoDao.guardaActualiza(atributo);
		return false;
	}

}
