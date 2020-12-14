package br.com.caelum.pm73.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {

	private Session session;
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() {
		session = new CriadorDeSessao().getSession();
		usuarioDao = new UsuarioDao(session);
		// boa pr�tica: contexto de transa��o (em testes com BD)
		session.beginTransaction();
	}

	@After
	public void closeSession() {
		// boa pr�tica: damos um rollback no BD para limpar os dados inseridos no teste
		session.getTransaction().rollback();
		// fechando conex�o do BD
		session.close();
	}

	@Test
	public void deveEncontrarPeloNomeEEmail() {
		// criando um usuario e salvando antes de
		// chamar o m�todo porNomeEEmail
		Usuario novoUsuario = new Usuario("Jo�o da Silva", "joao@dasilva.com.br");
		usuarioDao.salvar(novoUsuario);

		// agora buscamos no banco
		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Jo�o da Silva", "joao@dasilva.com.br");

		assertEquals("Jo�o da Silva", usuarioDoBanco.getNome());
		assertEquals("joao@dasilva.com.br", usuarioDoBanco.getEmail());
		// lembrar de executar a classe "CriaTabelas" antes de executar o teste
	}

	@Test
	public void deveRetornarNullSeUsuarioNaoExistir() {
		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Joaquim Peixoto", "joaquinzeira@email.com");

		assertNull(usuarioDoBanco);
	}
	
	@Test
	public void deveDeletarUmUsuario() {
		// criando um usuario, salvando e deletando do BD
		Usuario hech = new Usuario("Jorge Hecherat", "jorge@email.com.br");
		usuarioDao.salvar(hech);
		usuarioDao.deletar(hech);
		
		// o hibernate coloca muita coisa no cash
		// portanto, vamos for�ar a execu��o das querys
		session.flush();	// faz com que o comando realmente v� para o BD
		session.clear();	// apaga o cash
		
		// buscando o usuario
		Usuario usuarioDeletado = usuarioDao.porNomeEEmail("Jorge Hecherat", "jorge@email.com");
		// esperando que o retorno seja null
		assertNull(usuarioDeletado);
	}
	
	@Test
	public void deveAlterarUmUsuario() {
		// criando um usuario, salvando no BD
		Usuario hech = new Usuario("Jorge Hecherat", "jorge@email.com.br");
		
		usuarioDao.salvar(hech);
		// atualizando usuario
		hech.setNome("Alan");
		hech.setEmail("alan@email.com");
		
		usuarioDao.atualizar(hech);
		
		// o hibernate coloca muita coisa no cash
		// portanto, vamos for�ar a execu��o das querys
		session.flush();	// faz com que o comando realmente v� para o BD
		session.clear();	// apaga o cash
		
		// buscando os usuarios
		Usuario usuarioAntigo = usuarioDao.porNomeEEmail("Jorge Hecherat", "jorge@email.com.br");
		Usuario usuarioAlterado = usuarioDao.porNomeEEmail("Alan", "alan@email.com");
		// testando
		assertNull(usuarioAntigo);
		assertNotNull(usuarioAlterado);
		assertEquals("Alan", usuarioAlterado.getNome());
		assertEquals("alan@email.com", usuarioAlterado.getEmail());
	}
}
