package com.stefanini.desafio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.desafio.dao.PersonDAO;

import lombok.Data;
 
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */

//@Data
@Entity
@Table(name="pessoa")
@ManagedBean(name="person")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 7051858827280976094L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	private String nome;
	
	private String sexo;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	@Column(unique = true)
	private String cpf;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", naturalidade=" + naturalidade + ", nacionalidade=" + nacionalidade + ", cpf="
				+ cpf + "]";
	}
    
}
