package repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpaUtil.JpaUtil;

/**
 * @author pedro.silva
 *
 * @param <T>
 * @param <I>
 */
public abstract class BasicoAbstratoRepositorio<T, I> implements IBasicoRepositorio<T, I> {

	EntityManager em = JpaUtil.getEntityManager();

	//Atributo que receberá a classe para fazer a busca e retornar uma lista de qualquer entidade
	public Class<T> entityClass;
	
	/**
	 * Setando o tipo de classe antes da busca
	 * @param entity
	 */
	public void setarEntidade(Class<T> entity) {
		this.entityClass = entity;
	}
	
	/**
	 * Retorna uma lista com o tipo de entidade passado
	 * @return List<T>
	 */
	public Class<T> getEntityClass(){
		return this.entityClass;
	}
	
	public List<T> listarTodos(){
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		//Montando a query generica para busca na base
		String query = "select t from "+ getEntityClass().getSimpleName() + " t";
		//Query typada que retornará a lista com a classe passada no construtor concreto.
		TypedQuery<T> typedQuery = em.createQuery(query, getEntityClass());
		//Lista que será populada com o retorno da base
		List<T> resultSet = typedQuery.getResultList();
		//Retornando a lista
		return resultSet;
	}
	/**
	 * Metodo generico para salvar Entidades
	 * 
	 * @param entity
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T salvar(T entity, boolean b) {
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			if (b)
				em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return entity;
	}

	/**
	 * Metodo generico para salvar/atualizar Entidades
	 * 
	 * @param entity
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T atualizar(T entity, boolean b) {
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			if (b)
				em.close();			
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return entity;
	}

	/**
	 * 
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id     Int com o id do objeto.
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T encontrar(Class<T> entity, I id, boolean b) {
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();		
		return em.find(entity, id); 
	}

	/**
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id     Int com o id do objeto.
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public void remover(Class<T> entity, I id, boolean b) {
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			T entidade = em.find(entity, id);
			em.getTransaction().begin();
			em.remove(entidade);
			em.getTransaction().commit();
			if(b)
				em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
}
