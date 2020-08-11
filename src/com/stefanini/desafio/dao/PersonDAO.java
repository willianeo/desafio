package com.stefanini.desafio.dao;

import java.util.List;

import com.stefanini.desafio.model.Person;
 
public interface PersonDAO {
 
	public Person createOrUpdate(Person entity);
    public void addPerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(Integer id);
    public void removePerson(Integer id);
    public void updatePerson(Person p);
    
}
