package com.keyboardshop.dao;

import java.util.List;

public interface DAO<T>
{    
    public T get(long id);
    
    public List<T> getAll();
    
    public void create(T t);
    
    public void update(T t);
    
    public void delete(long id);
}
