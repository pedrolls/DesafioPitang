package fachada;

import java.util.List;

import javax.faces.application.FacesMessage;

import model.Telefone;
import model.Usuario;
import servico.TelefoneServico;
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
	
	/**
	 * metodo responsável por salvar o usuario na base de dados.
	 * @param usuario
	 */
	public void cadastrarUsuario(Usuario usuario) {
		new UsuarioServico().cadastrarUsuario(usuario);
	}
	
	/**
	 * Metodo que valida os atributos obrigatorios para cadastrar o usuario
	 * @param usuario
	 * @return Boolean 
	 */
	public boolean validarCadastrarUsuario(Usuario usuario) {
		return new UsuarioServico().validarCadastrarUsuario(usuario);
	}
	
	/**
	 * Valida se pelo menos um telefone foi preenchido na lista
	 * @param telefone
	 * @return boolean 
	 */
	public boolean validarTelefone(List<Telefone> telefone) {
		return new TelefoneServico().validarTelefone(telefone);
	}
	
	/**
	 * Metodo que recebe a Sting completa com o telefone e retorna um obj preenchido
	 * @param telefone
	 * @return Telefone
	 */
	public Telefone retornarObjetoPorString(String telefone, String tipo) {
		return new TelefoneServico().retornarObjetoPorString(telefone, tipo);
	}
	
	/**
	 * Metodo que retorna um usuario a partir do email e senha;
	 * @param email
	 * @param senha
	 * @return Usuario
	 */
	public Usuario consultarUsuarioPorEmailESenha(String email,String senha) {
		return new UsuarioServico().consultarUsuarioPorEmail(email, senha);
	}
	
	/**
	 * Metodo que retorna um usuario locanzando pelo email
	 * @param email
	 * @return Usuario
	 */
	public Usuario obterUsuarioPorEmail(String email) {
		return new UsuarioServico().obterUsuarioPorEmail(email);
	}
	
	/**
	 * Metodo que retorna todos os usuarios cadastrados
	 * @return List<Usuario>
	 */
	public List<Usuario> obterTodosUsuarios(){
		return new UsuarioServico().obterTodosUsuarios();
	}
	
	/**
	 * Metodo que retorna todos os telefones cadastrados 
	 * @return List<Telefone>
	 */
	public List<Telefone> listaTodosTelefones(){
		return new TelefoneServico().listaTodosTelefones();
	}
	
	/**
	 * Metodo generico para salvar/atualizar Entidades
	 * 
	 * @param entity
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public Usuario atualizar(Usuario usuario, boolean b) {
		return new UsuarioServico().atualizar(usuario, b);
	}
	
	/**
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id     Int com o id do objeto.
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public void remover(Integer id, boolean b) {
		new UsuarioServico().remover(Usuario.class, id, b);
	}
}
