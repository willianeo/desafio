package com.stefanini.desafio.service;

import java.util.List;


import com.stefanini.desafio.model.Person;

public interface PersonService {
 
    public String addPerson(Person p);
    public List<Person> listPersons();
    public String getPersonById(int id);
	public String removePerson(int id);
	public String updatePerson(Person p);
    
}

