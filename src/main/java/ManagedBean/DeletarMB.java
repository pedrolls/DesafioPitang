package ManagedBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Util.ConstantesSistema;
import excecao.UsuarioException;
import fachada.Fachada;
import model.Usuario;

@ManagedBean(name = "deletarMB")
@ViewScoped
public class DeletarMB extends Usuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que remove um usuario da Base
	 */
	public void deletar() {
		Usuario objUsuario = new Usuario();
		if(!getEmail().isEmpty()) {
			try {
				//Aqui valido o email
				Fachada.getInstancia().validateEmail(getEmail());
				objUsuario = Fachada.getInstancia().obterUsuarioPorEmail(getEmail());
				if(objUsuario != null)
					Fachada.getInstancia().remover(objUsuario.getId(), true);
				else
					throw new UsuarioException(ConstantesSistema.USUARIO_NAO_ENCONTRADO);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						ConstantesSistema.USUARIO_REMOVIDO_SUCESSO, null));
			} catch (UsuarioException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.USUARIO_NAO_ENCONTRADO, ConstantesSistema.USUARIO_NAO_ENCONTRADO));
			}
		}
	}
	
	/**
	 * Volta para a pag inicial
	 */
	public void voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_PRINCIPAL);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}

}
