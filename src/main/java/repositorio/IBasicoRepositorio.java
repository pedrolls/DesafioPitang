package repositorio;

public interface IBasicoRepositorio<T,I> {
	
	/**
	 * Metodo generico para salvar Entidades
	 * @param entity
	 * @param b True, para fechar a transação do managerFactory, 
	 * false para deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T salvar(T entity, boolean b);
	
	/**
	 * Metodo generico para salvar/atualizar Entidades
	 * @param entity
	 * @param b True, para fechar a transação do managerFactory, 
	 * false para deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T atualizar(T entity, boolean b);
	
	/**
	 * 
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id Int com o id do objeto.
	 * @param b True, para fechar a transação do managerFactory, 
	 * false para deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T encontrar(Class<T> entity, I id, boolean b);
	
	/**
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id Int com o id do objeto.
	 * @param b True, para fechar a transação do managerFactory, 
	 * false para deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public void remover(Class<T> entity, I id, boolean b);

}
