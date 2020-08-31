package repositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.Telefone;

@TestMethodOrder(OrderAnnotation.class)
public class TelefoneRepositorioTest {

	@Test
	@Order(1)
	void testSalvar() {
		// Arrange
		Telefone objtelefone = getTelefoneUm();
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			objtelefone = repositorio.salvar(objtelefone, true);
			// Assert
			assertNotNull(repositorio.encontrar(Telefone.class, objtelefone.getId(), true));
		} catch (Exception e) {
			fail("Falha ao Salvar Telefone");
		}
	}

	@Test
	@Order(2)
	void testListaTodosTelefones() {
		// Arrange
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			// Assert
			assertNotNull(repositorio.listaTodosTelefones());
		} catch (Exception e) {
			fail("Falha ao Listas Todos os Telefone");
		}
	}

	@Test
	@Order(3)
	void testSetarEntidadeClassOfT() {
		// Arrange
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			// Assert
			assertNotNull(repositorio.getEntityClass());
		} catch (Exception e) {
			fail("Falha ao pegar Entidade!");
		}
	}

	@Test
	@Order(4)
	void testListarTodos() {
		// Arrange
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			// Assert
			assertNotNull(repositorio.listarTodos());
		} catch (Exception e) {
			fail("Falha ao Listas Todos os Telefone");
		}
	}

	@Test
	@Order(5)
	void testAtualizar() {
		// Arrange
		Telefone objtelefone = new Telefone();
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			objtelefone = repositorio.encontrar(Telefone.class, 1, false);

			objtelefone.setDdd(89);
			objtelefone.setNumero("889988779");
			repositorio.atualizar(objtelefone, true);
			// Assert
			assertEquals(objtelefone.getNumero(),
					repositorio.encontrar(Telefone.class, objtelefone.getId(), true).getNumero());
		} catch (Exception e) {
			fail("Falha ao Atualizar o Telefone");
		}
	}

	@Test
	@Order(6)
	void testEncontrar() {
		// Arrange
		TelefoneRepositorio repositorio = new TelefoneRepositorio();

		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			// Assert
			assertNotNull(repositorio.encontrar(Telefone.class, 1, true));
		} catch (Exception e) {
			fail("Falha ao tentar localizar o Telefone");
		}
	}

	@Test
	@Order(7)
	void testRemover() {
		// Arrange
		TelefoneRepositorio repositorio = new TelefoneRepositorio();
		Telefone telefone = new Telefone();
		// act
		try {
			repositorio.setarEntidade(Telefone.class);
			telefone = repositorio.encontrar(Telefone.class, 1, true);
			repositorio.remover(Telefone.class, telefone.getId(), true);
			// Assert
			assertNull(repositorio.encontrar(Telefone.class, 1, true));
		} catch (Exception e) {
			fail("Falha ao tentar remover o Telefone");
		}
	}

	@Ignore
	Telefone getTelefoneUm() {
		Telefone telefone = new Telefone();
		telefone.setDdd(8);
		telefone.setNumero("999998877");
		telefone.setTipo("Principal");
		return telefone;
	}

	@Ignore
	Telefone getTelefoneDois() {
		Telefone telefone = new Telefone();
		telefone.setDdd(8);
		telefone.setNumero("999998877");
		telefone.setTipo("Recado");
		return telefone;
	}
}
