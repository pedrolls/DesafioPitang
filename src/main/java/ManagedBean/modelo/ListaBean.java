package ManagedBean.modelo;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class ListaBean {

	private String nome;
	private String email;
	private String telefoneUm;
	private String telefoneDois;
	private String tipoUm;
	private String tipoDois;
	private List<ListaBean> lista = new  ArrayList<ListaBean>();

	public List<ListaBean> formatarTelefone(List<Usuario> listaUsuario) {

		ListaBean obj = new ListaBean();
		for (Usuario list : listaUsuario) {
			if (!list.getTelefone().isEmpty()) {
				for (int x = 0; x < list.getTelefone().size();) {
					if (list.getTelefone().size() == 2) {
						obj.setNome(list.getNome());
						obj.setEmail(list.getEmail());
						obj.setTipoUm(list.getTelefone().get(x).getTipo());
						obj.setTelefoneUm("(" + list.getTelefone().get(x).getDdd() + ") "
								+ list.getTelefone().get(x).getNumero());
						obj.setTelefoneDois("(" + list.getTelefone().get(x+1).getDdd() + ") "
								+ list.getTelefone().get(x+1).getNumero());
						obj.setTipoDois(list.getTelefone().get(x+1).getTipo());
						lista.add(obj);
						obj = new ListaBean();
						break;

					} else {
						obj.setNome(list.getNome());
						obj.setEmail(list.getEmail());
						obj.setTipoUm(list.getTelefone().get(x).getTipo());
						obj.setTelefoneUm(
								"(" + list.getTelefone().get(x).getDdd() + ")" + list.getTelefone().get(x).getNumero());
						lista.add(obj);
						obj = new ListaBean();
						break;
					}
				}

			}

		}
		return lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneUm() {
		return telefoneUm;
	}

	public void setTelefoneUm(String telefoneUm) {
		this.telefoneUm = telefoneUm;
	}

	public String getTelefoneDois() {
		return telefoneDois;
	}

	public void setTelefoneDois(String telefoneDois) {
		this.telefoneDois = telefoneDois;
	}

	public String getTipoUm() {
		return tipoUm;
	}

	public void setTipoUm(String tipoUm) {
		this.tipoUm = tipoUm;
	}

	public String getTipoDois() {
		return tipoDois;
	}

	public void setTipoDois(String tipoDois) {
		this.tipoDois = tipoDois;
	}

	public List<ListaBean> getLista() {
		return lista;
	}

	public void setLista(List<ListaBean> lista) {
		this.lista = lista;
	}
}
