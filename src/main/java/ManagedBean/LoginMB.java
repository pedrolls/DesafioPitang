package ManagedBean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import Util.ConstantesSistema;
import excecao.UsuarioException;
import fachada.Fachada;
import model.Usuario;

@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginMB extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioBean;
	
	@PostConstruct
	public void init() {
		usuarioBean = new Usuario();
	}
	/**
	 * Metodo responsável por efetuar login
	 */
	public void login() {
		FacesMessage validacao = null;
		try {
			 validacao = validarEmail();
			 if(validacao!=null)
				 throw new UsuarioException(ConstantesSistema.EMAIL_INVALIDO);
			 if(!validarAtributosObrigatorios())
				 throw new UsuarioException(ConstantesSistema.VERIFIQUE_OS_CAMPOS_OBRIGATORIOS);
			if(Fachada.getInstancia().login(getEmail(), getSenha())!=null)
					//Aqui redireciono para a página principal.
					FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_PRINCIPAL);
			else
				throw new NoResultException();			 
		}catch (UsuarioException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
		}catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.USUARIO_NAO_ENCONTRADO, e.getMessage()));
		}		
	}
	
	/**
	 * Metodo que valida se todos campos para login foram preenchidos.
	 * @return FacesMessage
	 */
	public boolean validarAtributosObrigatorios() {
		boolean flag = true;
		if(getEmail().isEmpty() || getSenha().isEmpty())
			flag = false;
		return flag;
	}
	/**
	 * Metodo que verifica se o email informado é valido.
	 * @return FacesMessage
	 */
	public FacesMessage validarEmail() {
		FacesMessage message = Fachada.getInstancia().validateEmail(getEmail());
		
		return message;
	}
	
	/**
	 * Metodo que faz o redirecionamento para a página de cadastro
	 */
	public void novoCadastro() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_NOVO_CADASTRO);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}
	
	public Usuario getUsuarioBean() {
		return usuarioBean;
	}
	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

}
