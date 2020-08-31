package ManagedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ManagedBean.modelo.ListaBean;
import Util.ConstantesSistema;
import fachada.Fachada;
import model.Usuario;

@ManagedBean(name = "principalMB")
@ViewScoped
public class PrincipalMB extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Usuario> listaTodosUsuarios;
	//Lista que exibe os usuários na tela
	private List<ListaBean> listaUsuarios;
	
	@PostConstruct
	public void init() {
		listaTodosUsuarios = new ArrayList<Usuario>();
		listaUsuarios = new ArrayList<ListaBean>();
	}

	/**
	 * Metodo que retorna todos os usuarios cadastrados
	 * 
	 * @return List<Usuario>
	 */
	public List<ListaBean> listaTodosUsuarios() {
//		
		listaTodosUsuarios = Fachada.getInstancia().obterTodosUsuarios();

		this.listaUsuarios = new ListaBean().formatarTelefone(listaTodosUsuarios);
		
		return listaUsuarios;
	}
	
	/**
	 * Redirecionamento para cadastro
	 */
	public void paginaCadastrar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_CADASTRAR);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}
	
	/**
	 * Redireciona para Atualizar
	 */
	public void paginaAtualizar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_ATUALIZAR);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}
	
	/**
	 * Redireciona para Deletar
	 */
	public void paginaDeletar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_DELETAR);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}

	public List<Usuario> getListaTodosUsuarios() {
		return listaTodosUsuarios;
	}

	public void setListaTodosUsuarios(List<Usuario> listaTodosUsuarios) {
		this.listaTodosUsuarios = listaTodosUsuarios;
	}

	public List<ListaBean> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<ListaBean> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
