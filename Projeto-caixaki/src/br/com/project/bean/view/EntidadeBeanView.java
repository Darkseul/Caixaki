package br.com.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;

@Controller
@SessionScoped
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
	// Essa classe possui um metodo que retorna o usuário logado 
	private ContextoBean contextoBean = new ContextoBean();

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception{
		
		return contextoBean.getEntidadeLogada().getEnt_ultimoAcesso();
	}
}
