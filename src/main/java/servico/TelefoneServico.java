package servico;

import java.util.List;

import model.Telefone;
import repositorio.TelefoneRepositorio;

public class TelefoneServico {

	/**
	 * Valida se pelo menos um telefone foi preenchido na lista
	 * @param telefone
	 * @return boolean 
	 */
	public boolean validarTelefone(List<Telefone> telefone) {
		if (telefone.size() == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Metodo que recebe a Sting completa com o telefone e retorna um obj preenchido
	 * @param telefone
	 * @return Telefone
	 */
	public Telefone retornarObjetoPorString(String telefone, String tipo) {
		Telefone objTelefone = new Telefone();
		if(!telefone.isEmpty() || telefone != null) {
			objTelefone.setDdd(Integer.parseInt(telefone.substring(1,3)));
			objTelefone.setNumero(telefone.substring(4,telefone.length()));
			objTelefone.setTipo(tipo);
		}
		return objTelefone;
	}
	
	/**
	 * Metodo que retorna todos os telefones cadastrados 
	 * @return List<Telefone>
	 */
	public List<Telefone> listaTodosTelefones(){
		return new TelefoneRepositorio().listaTodosTelefones();
	}

}
