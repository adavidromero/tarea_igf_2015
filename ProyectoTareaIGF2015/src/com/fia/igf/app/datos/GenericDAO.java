package com.fia.igf.app.datos;

import java.util.List;

import com.fia.igf.app.dominio.Aplicativo;

public interface GenericDAO<T,PK> {
	public 	void guardaActualiza (T obj);
	public 	void eliminar (T obj);
	public List<T> obtenerTodos();
	public T obtenerPorId(Class<T> clazz, PK id);

}
