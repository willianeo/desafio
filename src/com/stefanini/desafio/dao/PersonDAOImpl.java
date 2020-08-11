package com.stefanini.desafio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.stefanini.desafio.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	public Person createOrUpdate(Person entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

    @Override
    public void addPerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(p);
        logger.info("Person saved successfully, Person Details="+p);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Person> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        for(Person p : personsList){
            logger.info("Person List::"+p);
        }
        return personsList;
    }

	@Override
	public Person getPersonById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Person where id=:id");
	      query.setInteger("id", id);
	      return (Person) query.uniqueResult();
		//if (null != p)
			//logger.info("Person loaded successfully, Person details="+p);
//		return p;
	}

	@Override
	public void removePerson(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, id);
		if (null != p) {
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}
 
}

