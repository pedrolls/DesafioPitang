package ManagedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import Util.ConstantesSistema;
import excecao.UsuarioException;
import fachada.Fachada;
import model.Telefone;
import model.Usuario;
import repositorio.UsuarioRepostorio;

@ManagedBean(name = "atualizarMB")
@ViewScoped
public class AtualizarMB extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Usuario objUsuario = new Usuario();
	private List<Telefone> listaDeTelefones;
	private String telefonePrincipal;
	private String telefoneRecado;
	private String tipoTelefonePrincipal;
	private String tipoTelefoneRecado;
	
	@PostConstruct
	public void init() {
		listaDeTelefones = new ArrayList<Telefone>();
	}
	
	
	public void atualizar() {
		
		try {
			if(!getEmail().isEmpty() && !getSenha().isEmpty()) {
				new UsuarioRepostorio().setarEntidade(Usuario.class);
				objUsuario = Fachada.getInstancia().consultarUsuarioPorEmailESenha(getEmail(), getSenha());
			}				
			if(objUsuario == null)
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_EMAIL_SENHA);
			validarAtualizar();
			objUsuario.setNome(getNome());
			objUsuario.setSenha(getSenha());
			Fachada.getInstancia().atualizar(objUsuario, true);
		} catch (UsuarioException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantesSistema.VERIFIQUE_EMAIL_SENHA, ConstantesSistema.USUARIO_NAO_ENCONTRADO));
		}
	}
	/**
	 * Meotodo que valida os dados antes de atualizar
	 */
	public void validarAtualizar() {
		try {
			if (getNome().isEmpty() || getEmail().isEmpty() || getSenha().isEmpty())
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_OS_CAMPOS_OBRIGATORIOS);
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
			//Aqui recupeo os IDs dos telefones a serem atualizados
			for(int x=0;x<objUsuario.getTelefone().size();) {
				if(objUsuario.getTelefone().size()>1) {
					listaDeTelefones.get(0).setId(objUsuario.getTelefone().get(x).getId());
					listaDeTelefones.get(1).setId(objUsuario.getTelefone().get(x+1).getId());
					break;
				}else {
					listaDeTelefones.get(0).setId(objUsuario.getTelefone().get(x).getId());
					break;
				}					
			}
			//Aqui seto o usuario no telefone
			for(int i = 0;i<listaDeTelefones.size();i++) {
				listaDeTelefones.get(i).setUsuario(objUsuario);
			}
			//Aqui seto o telefone no usuario
			objUsuario.setTelefone(listaDeTelefones);
			
			if (Fachada.getInstancia().validarCadastrarUsuario(objUsuario) == false)
				throw new UsuarioException(ConstantesSistema.VERIFIQUE_OS_CAMPOS_OBRIGATORIOS);
			Fachada.getInstancia().cadastrarUsuario(objUsuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantesSistema.USUARIO_ATUALIZADO_SUCESSO, null));
		} catch (UsuarioException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
		}
	}
	
	/**
	 * Metodo que recebe a Sting completa com o telefone e retorna um obj preenchido
	 * @param telefone
	 * @return Telefone
	 */
	public void atualizarListaDeTelefones() {
		if (!telefonePrincipal.isEmpty())
			listaDeTelefones
					.add(Fachada.getInstancia().retornarObjetoPorString(telefonePrincipal, tipoTelefonePrincipal));
		if (!telefoneRecado.isEmpty())
			listaDeTelefones.add(Fachada.getInstancia().retornarObjetoPorString(telefoneRecado, tipoTelefoneRecado));

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

	public List<Telefone> getListaDeTelefones() {
		return listaDeTelefones;
	}

	public void setListaDeTelefones(List<Telefone> listaDeTelefones) {
		this.listaDeTelefones = listaDeTelefones;
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
