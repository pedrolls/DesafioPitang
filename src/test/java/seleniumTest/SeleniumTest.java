package seleniumTest;

import static org.hamcrest.CoreMatchers.either;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Util.ConstantesSistema;
import model.Telefone;
import model.Usuario;
import repositorio.UsuarioRepostorio;

public class SeleniumTest {

	private static WebDriver driver;
	public final static Usuario usuario = new Usuario();
	public final static Telefone telefoneUm = new Telefone();
	public final static Telefone telefoneDois = new Telefone();
	public final static UsuarioRepostorio repositorio = new UsuarioRepostorio();

	@Test
	public void fluxoTest() throws InterruptedException {

		/* Arrange */
		System.setProperty(ConstantesSistema.CHORME_DRIVER_WEB, new FindChomeDriver().getChormeDriverLocation());

		driver = new ChromeDriver();

		usuario.setNome("Pedro Leonardo");
		usuario.setEmail("pedro@pitang.com");
		usuario.setSenha("123");

		telefoneUm.setNumero("81988445566");
		telefoneUm.setTipo("Principal");

		telefoneDois.setNumero("81955447788");
		telefoneDois.setTipo("Recado");

		repositorio.setarEntidade(Usuario.class);
		try {

			driver.get("localhost:8080/Desafio.Pitang/login.xhtml");

			Thread.sleep(3000);

			dadosEeventosDeCadastroEAtualizacao(true);
			
			/*  Fim fluxo de novo Cadastro */

			/* Modulo abertura e Cadastro no sistema */
			WebElement element = driver.findElement(By.id("mainForm:btnListar"));
			element.click();
			
			Thread.sleep(2000);
			
			element = driver.findElement(By.id("mainForm:btnAtualizar"));
			element.click();
			
			usuario.setNome("Pedro Silva");
			telefoneUm.setNumero("81335544889");
			telefoneDois.setNumero("81788554466");
			dadosEeventosDeCadastroEAtualizacao(false);
			
			element = driver.findElement(By.id("mainForm:atualizar"));
			element.click();
			
			Thread.sleep(2000);
			
			element = driver.findElement(By.id("mainForm:link"));
			element.click();
			
			element = driver.findElement(By.id("mainForm:btnListar"));
			element.click();			
		
			Thread.sleep(2000);
			
			element = driver.findElement(By.id("mainForm:btnDeletar"));
			element.click();
			
			element = driver.findElement(By.id("mainForm:email"));
			element.click();
			element.clear();
			element.sendKeys(usuario.getEmail());
			
			element = driver.findElement(By.id("mainForm:deletar"));
			element.click();
			
			Thread.sleep(2000);
			
			element = driver.findElement(By.id("mainForm:link"));
			element.click();
			
			
			// Act
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	void dadosEeventosDeCadastroEAtualizacao(boolean b) {
		try {
			WebElement element;
			if(b) {
				element = driver.findElement(By.id("mainForm:link"));
				element.click();
			}
			/* Modulo abertura e Cadastro no sistema */
			

			Thread.sleep(5000);
			element = driver.findElement(By.id("mainForm:nome"));
			element.click();
			element.clear();
			element.sendKeys(usuario.getNome());

			element = driver.findElement(By.id("mainForm:email"));
			element.click();
			element.clear();
			element.sendKeys(usuario.getEmail());

			element = driver.findElement(By.id("mainForm:telefone"));
			element.click();
			element.clear();
			element.sendKeys(telefoneUm.getNumero());

			element = driver.findElement(By.id("mainForm:tipoPrincipal"));
			element.click();
			element = driver.findElement(By.id("mainForm:tipoPrincipal_1"));
			element.click();

			element = driver.findElement(By.id("mainForm:numero"));
			element.click();
			element.clear();
			element.sendKeys(telefoneDois.getNumero());

			element = driver.findElement(By.id("mainForm:tipoRecado"));
			element.click();
			element = driver.findElement(By.id("mainForm:tipoRecado_2"));
			element.click();

			element = driver.findElement(By.id("mainForm:senha"));
			element.click();
			element.clear();
			element.sendKeys(usuario.getSenha());
			
			if(b) {
				element = driver.findElement(By.id("mainForm:cadastrar"));
				element.click();

				Thread.sleep(4000);

				element = driver.findElement(By.id("mainForm:link"));
				element.click();
				
				Thread.sleep(2000);
				
				element = driver.findElement(By.id("mainForm:email"));
				element.click();
				element.clear();
				element.sendKeys(usuario.getEmail());
				
				element = driver.findElement(By.id("mainForm:senha"));
				element.click();
				element.clear();
				element.sendKeys(usuario.getSenha());
				
				Thread.sleep(2000);
				
				element = driver.findElement(By.id("mainForm:login"));
				element.click();
				
				Thread.sleep(2000);
			}

			
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
	}
}
