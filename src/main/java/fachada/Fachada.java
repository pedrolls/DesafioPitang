package fachada;

import javax.faces.application.FacesMessage;

import model.Usuario;
import servico.UsuarioServico;

public class Fachada implements IFachada{

	private static Fachada instancia = new Fachada();;
	
	public static Fachada getInstancia() {
		if(instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	
	/**
	 * Metodo que valida se o email é válido 
	 * @param email
	 * @return FacesMessage
	 */
	public FacesMessage validateEmail(String email) {
		return new UsuarioServico().validateEmail(email);
	}
	
	/**
	 * Metodo responsável por validar o login, buscando na base o usuario através do email e senha
	 * @param usuario
	 * @return Usuario
	 */
	public Usuario login(String email, String senha) {
		return new UsuarioServico().login(email, senha);
	}
}
