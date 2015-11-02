package com.fia.igf.app.datos;

import java.util.List;

public interface GenericDAO<T,PK> {
	public 	void guardar (T obj);
	public 	void eliminar (T obj);
	public List<T> obtenerTodos(Class<T> clazz);
	public T obtenerPorId(T obj);

}
