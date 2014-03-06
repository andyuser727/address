package exercise.dao;

import exercise.domain.PersistentObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDaoImpl<T extends PersistentObject> implements GenericDao<T>{

    Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public GenericDaoImpl() {
    }

    @Autowired
    protected SessionFactory sf;

    @Override
    public T find(Long id) {
        return (T) sf.getCurrentSession().get(type, id);
    }

    @Override
    public void delete(T obj) {
        sf.getCurrentSession().delete(obj);
    }

    @Override
    public void save(T obj) {
        sf.getCurrentSession().save(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return (List<T>) sf.getCurrentSession().createCriteria(type).list();
    }
}