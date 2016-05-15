package services;

import java.util.List;

public interface ReflectionJdbcDao<T> {

	// inserts info about object in table T
	public void insert(T object);
	
	// updates info about object in table T
	public void update(T object);
	
	// deletes info about object from table T, where all fields of that 
	// object equal all not-null fields of key 
	public void deleteByKey(T key);
	
	// returns object from table T, where all fields of that 
	// object equal all not-null fields of key 	
	public T selectByKey(T key);

	// returns all objects from table clazz.name
	public List<T> selectAll(Class<?> clazz);
}