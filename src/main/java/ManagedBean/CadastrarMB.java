package ManagedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Util.ConstantesSistema;
import excecao.UsuarioException;
import fachada.Fachada;
import model.Telefone;
import model.Usuario;

@ManagedBean(name = "cadastrarMB")
@ViewScoped
public class CadastrarMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Telefone telefone;
	private List<Telefone> listaDeTelefones;
	private String telefonePrincipal;
	private String telefoneRecado;
	private String tipoTelefonePrincipal;
	private String tipoTelefoneRecado;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		telefone = new Telefone();
		listaDeTelefones = new ArrayList<Telefone>();
	}

	public void cadastrar() {
		try {
			validarCadastrar();
		} catch (UsuarioException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
		}
	}

	public void validarCadastrar() {
		try {
			if (usuario.getNome().isEmpty() || usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty())
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_OS_CAMPOS_OBRIGATORIOS);
			if (Fachada.getInstancia().obterUsuarioPorEmail(usuario.getEmail()) != null) {
				throw new UsuarioException(ConstantesSistema.USUARIO_JA_CADASTRADO);
			}
			if (telefonePrincipal.isEmpty() && telefoneRecado.isEmpty())
				throw new UsuarioException(ConstantesSistema.TELEFONE_OBRIGATORIO);
			if (!telefonePrincipal.isEmpty() && tipoTelefonePrincipal.isEmpty())
				throw new UsuarioException(ConstantesSistema.TIPO_TELEFONE_PRINCIPAL_OBRIGATORIO);
			if (telefonePrincipal.isEmpty() && !tipoTelefonePrincipal.isEmpty())
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_O_TIPO_DO_TELEFONE_PREENCHIDO);
			if (!telefoneRecado.isEmpty() && tipoTelefoneRecado.isEmpty())
				throw new UsuarioException(ConstantesSistema.TIPO_TELEFONE_PRINCIPAL_OBRIGATORIO);
			if (telefoneRecado.isEmpty() && !tipoTelefoneRecado.isEmpty())
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_O_TIPO_DO_TELEFONE_PREENCHIDO);
			atualizarListaDeTelefones();
			for(int i = 0;i<listaDeTelefones.size();i++) {
				listaDeTelefones.get(i).setUsuario(usuario);
			}
			usuario.setTelefone(listaDeTelefones);
			
			if (Fachada.getInstancia().validarCadastrarUsuario(usuario) == false)
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_OS_CAMPOS_OBRIGATORIOS);
			Fachada.getInstancia().cadastrarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					ConstantesSistema.USUARIO_CADASTRADO_SUCESSO, ConstantesSistema.VOCE_SERA_REDIRECIONADO));
		} catch (UsuarioException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
		}
	}

	public void atualizarListaDeTelefones() {
		if (!telefonePrincipal.isEmpty())
			listaDeTelefones
					.add(Fachada.getInstancia().retornarObjetoPorString(telefonePrincipal, tipoTelefonePrincipal));
		if (!telefoneRecado.isEmpty())
			listaDeTelefones.add(Fachada.getInstancia().retornarObjetoPorString(telefoneRecado, tipoTelefoneRecado));

	}

	public void paginaPrincipal() {
		try {
			if (usuario.getId() != 0)
				FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_PRINCIPAL);
			else
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.FACA_CADASTRO_PRIMEIRO, null));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
		}
	}

	public void paginaDeLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.PAGINA_LOGIN);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					ConstantesSistema.ERRO_AO_REDIRECIONAR, e.getMessage()));
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getTelefoneRecado() {
		return telefoneRecado;
	}

	public void setTelefoneRecado(String telefoneRecado) {
		this.telefoneRecado = telefoneRecado;
	}

	public List<Telefone> getListaDeTelefones() {
		return listaDeTelefones;
	}

	public void setListaDeTelefones(List<Telefone> listaDeTelefones) {
		this.listaDeTelefones = listaDeTelefones;
	}

	public String getTipoTelefonePrincipal() {
		return tipoTelefonePrincipal;
	}

	public void setTipoTelefonePrincipal(String tipoTelefonePrincipal) {
		this.tipoTelefonePrincipal = tipoTelefonePrincipal;
	}

	public String getTipoTelefoneRecado() {
		return tipoTelefoneRecado;
	}

	public void setTipoTelefoneRecado(String tipoTelefoneRecado) {
		this.tipoTelefoneRecado = tipoTelefoneRecado;
	}
}
