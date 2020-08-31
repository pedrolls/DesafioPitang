package repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import jpaUtil.JpaUtil;
import model.Telefone;

public class TelefoneRepositorio extends BasicoAbstratoRepositorio<Telefone, Integer>{
	
	@Override
	public void setarEntidade(Class<Telefone> entity) {
		super.setarEntidade(entity);
	}
	
	/**
	 * Metodo que retorna todos os telefones cadastrados 
	 * @return List<Telefone>
	 */
	public List<Telefone> listaTodosTelefones(){
		List<Telefone> listaDeTelefones = new ArrayList<Telefone>();
		
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();

		String query = ("select t from Usuario t ");
		try {
			em.getTransaction().begin();
			TypedQuery<Telefone> typedQuery = em.createQuery(query, Telefone.class);
			 listaDeTelefones = typedQuery.getResultList();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return listaDeTelefones;
	}

}
