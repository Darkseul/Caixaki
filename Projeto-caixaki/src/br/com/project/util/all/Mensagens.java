package br.com.project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe respons�vel pelas mensagens do JSF
 * @author willi
 *
 */
public abstract class Mensagens extends FacesContext implements Serializable{

	private static final long serialVersionUID = 1L;

	public Mensagens() {

	}
	
	/**
	 * Mensagem gen�rica para aparecer na tela do usu�rio
	 * @param msg
	 */
	public static void msg(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}
	}
	
	/**
	 * De acordo com o tipo de par�metro informando o detalhe da opera��o, ser� informado ao usu�rio uma mensagem
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
	 * Uma mensagem constante ao usu�rio informado sucesso na opera��o realizada
	 */
	public static void sucesso() {
		
		if(facesContextValido()) {
			
			msgSeverityInfo(Constante.SUCESSO);
		}
	}
	
	/**
	 * Mensagem constante caso ocorra algum erro na opera��o
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
	 * Uma mensagem constante de aviso ao usu�rio
	 * @param msg
	 */
	public static void msgSeverityWarn(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}
	
	/**
	 * Uma mensagem constante de erro fatal no sistema para o usu�rio
	 * @param msg
	 */
	public static void msgSeverityFatal(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}
	
	/**
	 * Uma mensagem constante de erro normal para o usu�rio
	 * @param msg
	 */
	public static void msgSeverityError(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}
	
	/**
	 * Mensagem constante para o usu�rio com uma informa��o
	 * @param msg
	 */
	public static void msgSeverityInfo(String msg) {
		
		if(facesContextValido()) {
			
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}
}
