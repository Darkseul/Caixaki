package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * Essa classe cria uma tabela no banco de dados para cadastro e login de pessoas
 * @author willi
 *
 */
@Entity
@Audited
public class Entidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long ent_codigo;
	
	private String ent_login = null;
	private String ent_senha = null;
	
	private boolean ent_inativo = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoAcesso;
	
	public Date getEnt_ultimoAcesso() {
		return ent_ultimoAcesso;
	}
	
	public void setEnt_ultimoAcesso(Date ent_ultimoAcesso) {
		this.ent_ultimoAcesso = ent_ultimoAcesso;
	}
	
	public String getEnt_login() {
		return ent_login;
	}
	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}
	public String getEnt_senha() {
		return ent_senha;
	}
	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}
	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}
	public Long getEnt_codigo() {
		return ent_codigo;
	}
	public boolean getEnt_inativo() {
		return ent_inativo;
	}
	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}
}
