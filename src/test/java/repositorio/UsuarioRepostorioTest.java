package repositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.Telefone;
import model.Usuario;

@TestMethodOrder(OrderAnnotation.class)
public class UsuarioRepostorioTest {

	@Test
	@Order(1)
	void testSalvar() {
		// Arrange
		Usuario usuario = getUsuario();

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);
			repositorio.salvar(usuario, true);

			// Assert
			assertNotNull(repositorio.encontrar(Usuario.class, 1, true));
		} catch (Exception e) {
			fail("Erro ao testar o método de salvar! " + e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testListarTodos() {
		// Arrange
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);
			listaDeUsuarios = repositorio.listarTodos();
			// Assert
			assertNotNull(listaDeUsuarios);
		} catch (Exception e) {
			fail("Erro ao testar o método de listas todos! " + e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testAtualizar() {
		// Arrange
		Usuario usuario = getUsuario();

		usuario.setId(1);
		usuario.setNome("Pedro Leonardo");
		usuario.getTelefone().get(0).setNumero("99554488");

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);
			repositorio.atualizar(usuario, true);

			// Assert
			assertEquals(repositorio.encontrar(Usuario.class, usuario.getId(), true).getNome(), usuario.getNome());
		} catch (Exception e) {
			fail("Erro ao testar o método de Atualizar! " + e.getMessage());
		}

	}

	@Test
	@Order(4)
	void testEncontrar() {
		// Arrange
		Usuario usuario = getUsuario();

		usuario.setId(1);

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);

			// Assert
			assertNotNull(repositorio.encontrar(Usuario.class, usuario.getId(), true));
		} catch (Exception e) {
			fail("Erro ao testar o método de encontrar! " + e.getMessage());
		}
	}

	@Test
	@Order(5)
	void testObterUsuarioPorEmailESenha() {
		// Arrange
		Usuario usuario = getUsuario();

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);
			
			// Assert
			assertNotNull(repositorio.obterUsuarioPorEmailESenha(usuario.getEmail(),usuario.getSenha()));
		} catch (Exception e) {
			fail("Falha ao buscar usuario por email e senha! " + e.getMessage());
		}
					
	}

	@Test
	@Order(6)
	void testRemover() {
		// Arrange
		Usuario usuario = getUsuario();

		usuario.setId(1);

		// Act
		try {
			UsuarioRepostorio repositorio = new UsuarioRepostorio();
			repositorio.setarEntidade(Usuario.class);
			repositorio.remover(Usuario.class, usuario.getId(), true);

			// Assert
			assertNull(repositorio.encontrar(Usuario.class, usuario.getId(), true));
		} catch (Exception e) {
			fail("Erro ao testar o método de salvar! " + e.getMessage());
		}
	}

	@Ignore
	public Usuario getUsuario() {
		// Arrange
		Usuario usuario = new Usuario();
		Telefone telefone = new Telefone();
		List<Telefone> listaDeTelefone = new ArrayList<Telefone>();

		usuario.setNome("Pedro");
		usuario.setEmail("pedro.plls@hotmail.com");
		usuario.setSenha("123");

		telefone.setDdd(81);
		telefone.setNumero("996377623");
		telefone.setTipo("celular");

		listaDeTelefone.add(telefone);
		usuario.setTelefone(listaDeTelefone);

		telefone.setUsuario(usuario);

		return usuario;
	}

}
