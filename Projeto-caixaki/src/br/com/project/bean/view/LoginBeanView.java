package br.com.project.bean.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionController;
import br.com.project.geral.controller.SessionControllerImpl;
import br.com.srv.implementation.SrvLoginImpl;
import br.com.srv.interfaces.SrvLogin;

@Controller
@Scope(value="request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	private SessionController controller = new SessionControllerImpl();
	
	private SrvLogin srvLogin = new SrvLoginImpl();
	
	@RequestMapping(value="**/invalidar_session", method = RequestMethod.POST)
	public void invalidarSessionMetodo(HttpServletRequest http) throws Exception {
		
		String userLogado = null;
		
		if(http.getUserPrincipal() != null) {
			
			userLogado = http.getUserPrincipal().getName();
		}
		
		if(userLogado == null || (userLogado != null && userLogado.trim().isEmpty())) {
			
			userLogado = http.getRemoteUser();
		}
		
		if(userLogado != null && !userLogado.isEmpty()) {
			
			controller.invalidateSession(userLogado);
		}
	}
	
	public void invalidar(ActionEvent event) throws Exception{
		
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		
		if(srvLogin.autenticado(getUsername(), getPassword())) {
			
			controller.invalidateSession(getUsername());
			loggedIn = true;
		}else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso Negado", "Usuário ou senha incorretos");
		}
		
		if(message != null) {
			FacesContext.getCurrentInstance().addMessage("msg", message);
		}
		
		context.addCallbackParam("loggedIn", loggedIn);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
