package fachada;

import java.util.List;

import javax.faces.application.FacesMessage;

import model.Telefone;
import model.Usuario;

public interface IFachada {

	public FacesMessage validateEmail(String email);
	
	public Usuario login(String email,String senha);
	
	public void cadastrarUsuario(Usuario usuario);
	
	public boolean validarCadastrarUsuario(Usuario usuario);
	
	public boolean validarTelefone(List<Telefone> telefone);
	
	public Telefone retornarObjetoPorString(String telefone, String tipo);
	
	public Usuario consultarUsuarioPorEmail(String email,String senha);
	
	public Usuario obterUsuarioPorEmail(String email);
	
	public List<Usuario> obterTodosUsuarios();
	
	public List<Telefone> listaTodosTelefones();
}
