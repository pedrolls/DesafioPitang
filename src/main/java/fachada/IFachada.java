package fachada;

import javax.faces.application.FacesMessage;

import model.Usuario;

public interface IFachada {

	/**
	 * Metodo que valida se o email é válido 
	 * @param email
	 * @return FacesMessage
	 */
	public FacesMessage validateEmail(String email);
	
	public Usuario login(String email,String senha);
}
