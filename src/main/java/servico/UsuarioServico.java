package servico;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;

import Util.ConstantesSistema;
import model.Usuario;
import repositorio.UsuarioRepostorio;

public class UsuarioServico {

	/**
	 * metodo responsável por salvar o usuario na base de dados.
	 * @param usuario
	 */
	public void cadastrarUsuario(Usuario usuario) {
		new UsuarioRepostorio().salvar(usuario,true);
	}
	/**
	 * Metodo responsável por validar o login, buscando na base o usuario através do email e senha
	 * @param usuario
	 * @return Usuario
	 */
	public Usuario login(String email, String senha) {
		if(!email.isEmpty() && !senha.isEmpty())
			return new UsuarioRepostorio().obterUsuarioPorEmailESenha(email, senha);
		else
			return null;
	}
	
	/**
	 * Metodo que retorna um usuario a partir do email e senha;
	 * @param email
	 * @param senha
	 * @return Usuario
	 */
	public Usuario consultarUsuarioPorEmail(String email,String senha) {
		return new UsuarioRepostorio().obterUsuarioPorEmailESenha(email, senha);
	}
	
	/**
	 * Metodo que retorna um usuario locanzando pelo email
	 * @param email
	 * @return Usuario
	 */
	public Usuario obterUsuarioPorEmail(String email) {
		return new UsuarioRepostorio().obterUsuarioPorEmail(email);
	}
	
	/**
	 * Metodo que retorna todos os usuarios cadastrados
	 * @return List<Usuario>
	 */
	public List<Usuario> obterTodosUsuarios(){
		return new UsuarioRepostorio().obterTodosUsuarios();
	}
	/**
	 * Metodo que valida se o email é válido 
	 * @param email
	 * @return FacesMessage
	 */
	public FacesMessage validateEmail(String email) {
		FacesMessage message = null;

		if (email != null && !email.isEmpty()) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches())
				message = null;
			else
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						email + " " + ConstantesSistema.EMAIL_INVALIDO, ConstantesSistema.EMAIL_INVALIDO);
		}
		return message;
	}
	
	/**
	 * Metodo que valida os atributos obrigatorios para cadastrar o usuario
	 * @param usuario
	 * @return Boolean 
	 */
	public boolean validarCadastrarUsuario(Usuario usuario) {
		if(usuario.getNome().isEmpty() || usuario.getEmail().isEmpty()
				|| usuario.getTelefone().isEmpty() 
				|| usuario.getSenha().isEmpty())
			return false;
		else
			return true;
	}
}
