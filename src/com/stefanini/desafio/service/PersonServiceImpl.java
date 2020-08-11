package com.stefanini.desafio.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.desafio.dao.PersonDAO;
import com.stefanini.desafio.model.Person;

@Service
@ManagedBean(name="personService")
@SessionScoped
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;
	 
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
 
    @Override
    @Transactional
    public String addPerson(Person p) {
        this.personDAO.addPerson(p);
        FacesMessage message = new FacesMessage("Salvo com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "/person.xhtml?faces-redirect=true";
    }
    
    public void onCreateOrUpdate(Person person) {
		this.personDAO.createOrUpdate(person);
	}
    
    @Override
    @Transactional
    public List<Person> listPersons() {
        return this.personDAO.listPersons();
    }

	@Override
    @Transactional
	public String getPersonById(int id) {
			Person p = this.personDAO.getPersonById(new Integer(id));
			FacesContext context = FacesContext.getCurrentInstance();
			if (null == p) {
				FacesMessage msg = new FacesMessage("Id não encontrado.");
				context.addMessage(null, msg);
				context.getExternalContext().getFlash().setKeepMessages(true);
			}

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("person", p);
			
			String uri = context.getExternalContext().getRequestHeaderMap().get("referer");
			String fileName = FilenameUtils.getName(uri);
			if ("edit-person.xhtml".equals(fileName)) {
				return "/edit-person.xhtml?faces-redirect=true";
			}
			
	        return "/delete-person.xhtml?faces-redirect=true";
	}

	@Override
    @Transactional
	public String removePerson(int id) {
		this.personDAO.removePerson(new Integer(id));
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("person", null);
		
        FacesMessage message = new FacesMessage("Deletado com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "/delete-person.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	public String updatePerson(Person p) {
		this.personDAO.updatePerson(p);

        FacesMessage message = new FacesMessage("Atualizado com sucesso!", "");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "/edit-person.xhtml?faces-redirect=true";
	}
 
}

