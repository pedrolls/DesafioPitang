package repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import jpaUtil.JpaUtil;
import model.Usuario;

public class UsuarioRepostorio extends BasicoAbstratoRepositorio<Usuario, Integer>{
	
	@Override
	public void setarEntidade(Class<Usuario> entity) {
		super.setarEntidade(entity);
	}
	/**
	 * Metodo que retorna um usuario a partir do email e senha
	 * @param usuario
	 * @return Usuario
	 */
	public Usuario obterUsuarioPorEmailESenha(String email, String senha) {
		Usuario objetoUsuario = new Usuario();
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();

		String query = ("select u from Usuario u ");
		query+= (" where u.email = '"+email+"'");
		query+= (" and u.senha = '"+ senha+"'");

		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
			objetoUsuario = typedQuery.getSingleResult();			
		} catch (NoResultException e) {
			objetoUsuario = null;
		}finally {
			em.close();
		}
		return objetoUsuario;		
	}
	
	/**
	 * Metodo que retorna um usuario locanzando pelo email
	 * @param email
	 * @return Usuario
	 */
	public Usuario obterUsuarioPorEmail(String email) {
		Usuario objetoUsuario = new Usuario();
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();

		String query = ("select u from Usuario u ");
		query+= (" where u.email = '"+email+"'");

		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
			objetoUsuario = typedQuery.getSingleResult();			
		} catch (NoResultException e) {
			objetoUsuario = null;
		}finally {
			em.close();
		}
		return objetoUsuario;		
	}
	/**
	 * Metodo personalizado que retorna todos os usuarios 
	 * @return List<Usuario>
	 */
	public List<Usuario> obterTodosUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();

		String query = ("select distinct u from Usuario u "
				+ " join  fetch u.telefone");
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
			 lista = typedQuery.getResultList();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return lista;
	}
}
