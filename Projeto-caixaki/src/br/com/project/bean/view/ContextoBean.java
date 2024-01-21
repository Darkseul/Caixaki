package br.com.project.bean.view;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.SessionController;
import br.com.project.geral.controller.SessionControllerImpl;
import br.com.project.model.classes.Entidade;

@Scope(value = "session")
@Component(value = "contextBean")
public class ContextoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String USER_LOGADO_SESSAO = "userLogadoSessao";
	
	private EntidadeController entidadeController = new EntidadeController();
	
	private SessionController sessionController = new SessionControllerImpl();

	/**
	 * Retorna todas as informações do usuário logado
	 * @return
	 */
	public Authentication getAuthentication() {
		
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * Cria sessões e maps para o usuário logado
	 * @return
	 * @throws Exception
	 */
	public Entidade getEntidadeLogada() throws Exception {
		
		Entidade entidade = (Entidade) getExternalContext().getSessionMap().get(USER_LOGADO_SESSAO);
		
		if(entidade == null || (entidade != null && !entidade.getEnt_login().equals(getUserPrincipal()))) {
			
			if(getAuthentication().isAuthenticated()) {
				
				String name = getAuthentication().getName();
				
				// atualiza o ultimo acesso
				entidadeController.updateUltimoAcesso(name);
				
				// resgata o usuário no banco após o serviço de data
				entidade = entidadeController.findUserLogado(getAuthentication().getName());
				getExternalContext().getSessionMap().put(USER_LOGADO_SESSAO, entidade);
				sessionController.addSession(entidade.getEnt_login(), (HttpSession) getExternalContext().getSession(true));
			}
		}
		return entidade;
	}
	
	private String getUserPrincipal() {
		
		return getExternalContext().getUserPrincipal().getName();
	}

	public ExternalContext getExternalContext() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		return externalContext;
	}
}
