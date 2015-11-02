package com.fia.igf.app.datos;

import java.util.List;

import com.fia.igf.app.dominio.Aplicativo;

public interface GenericDAO<T,PK> {
	public 	void guardarActualiza (T obj);
	public 	void eliminar (T obj);
	public List<T> obtenerTodos(Class<T> clazz);
	public T obtenerPorId(Class<T> clazz, PK id);

}
