package br.com.project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe responsável pelas mensagens do JSF
 * @author willi
 *
 */
public abstract class Mensagens extends FacesContext implements Serializable{

	private static final long serialVersionUID = 1L;

	public Mensagens() {

	}
	
	/**
	 * Mensagem genérica para aparecer na tela do usuário
	 * @param msg
	 */
	public static void msg(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}
	}
	
	/**
	 * De acordo com o tipo de parâmetro informando o detalhe da operação, será informado ao usuário uma mensagem
	 * @param estatus
	 */
	public static void responseOperation(EstatusPersistencia estatus) {
		
		if(facesContextValido()) {
			
			if(estatus != null && estatus.equals(EstatusPersistencia.SUCESSO)) {
				
				sucesso();
				
			}else if(estatus != null && estatus.equals(EstatusPersistencia.ERRO)) {
				
				erroNaOperacao();
				
			}else if(estatus != null && estatus.equals(EstatusPersistencia.OBJETO_REFERENCIADO)) {
				
				msgSeverityFatal(EstatusPersistencia.OBJETO_REFERENCIADO.toString());
			}
		}
	}
	
	/**
	 * Uma mensagem constante ao usuário informado sucesso na operação realizada
	 */
	public static void sucesso() {
		
		if(facesContextValido()) {
			
			msgSeverityInfo(Constante.SUCESSO);
		}
	}
	
	/**
	 * Mensagem constante caso ocorra algum erro na operação
	 */
	public static void erroNaOperacao() {
		
		if(facesContextValido()) {
			
			msgSeverityFatal(Constante.ERRO_NA_OPERACAO);
		}
	}
	
	/**
	 * Retorna o facesContext para trabalharmos com as mensagens
	 * @return
	 */
	public static FacesContext getFacesContext() {
		
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Verifica se existe mesmo um facesContext
	 * @return
	 */
	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}
	
	/**
	 * Uma mensagem constante de aviso ao usuário
	 * @param msg
	 */
	public static void msgSeverityWarn(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}
	
	/**
	 * Uma mensagem constante de erro fatal no sistema para o usuário
	 * @param msg
	 */
	public static void msgSeverityFatal(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}
	
	/**
	 * Uma mensagem constante de erro normal para o usuário
	 * @param msg
	 */
	public static void msgSeverityError(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}
	
	/**
	 * Mensagem constante para o usuário com uma informação
	 * @param msg
	 */
	public static void msgSeverityInfo(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}
}
