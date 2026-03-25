package com.xandy.financaspessoais.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xandy.financaspessoais.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = Usuario
				.builder()
				.nome("Alexandre")
				.email("alexandre@fatec.gov.br")
				.senha("1234")
				.build();
		repository.save(usuario);
		
		//ação/execução
		boolean result = repository.existsByEmail("alexandre@fatec.gov.br");
		
		//verificação
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenário
		repository.deleteAll();
		
		//ação/execução
		boolean result = repository.existsByEmail("alexandre@fatec.gov.br");
				
		//verificação
		Assertions.assertThat(result).isFalse();
	}
	
}
