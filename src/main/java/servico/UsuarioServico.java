package servico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;

import Util.ConstantesSistema;
import model.Usuario;
import repositorio.UsuarioRepostorio;

public class UsuarioServico {

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
}
