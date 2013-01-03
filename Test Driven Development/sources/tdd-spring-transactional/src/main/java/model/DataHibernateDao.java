package model;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Collection;

public class DataHibernateDao extends HibernateDaoSupport implements DataDao {

    @Override
    public Data get(Long id) {
        return (Data) getSession().get(Data.class, id);
    }

    @Override
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate result = super.createHibernateTemplate(sessionFactory);
        result.setAllowCreate(false);
        return result;
    }

    @Override
    public Data save(Data objectToSave) {
        getSession().saveOrUpdate(objectToSave);
        return objectToSave;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Data> find(String text) {
        return getSession().createQuery("from Data d where d.text like :text").setString("text", text).list();
    }

    @Override
    public void shutdown() {
        getSession().createSQLQuery("SHUTDOWN").executeUpdate();
    }

    @Override
    public void checkpoint() {
        getSession().createSQLQuery("CHECKPOINT").executeUpdate();
    }
}
