package br.com.ecommerce.cliente.api.helpers;

import br.com.ecommerce.cliente.api.dtos.request.EmailMessageDto;
import br.com.ecommerce.cliente.api.models.Cliente;

public class ClienteEmailHelper {

	public static EmailMessageDto gerarMensagemDeCriacaoDeConta(Cliente cliente) {
		
		String to = cliente.getEmail();
		String subject = "Parabéns, sua conta de cliente foi criada com sucesso!";
		String body = "Olá " + cliente.getNome()
					+ "\n\n"
					+ "Sua conta de cliente foi cadastrada com sucesso no Ecommerce DBS"
					+ "\n"
					+ "\nSeus dados são:"
					+ "\nNome: " +  cliente.getNome()
					+ "\n\nEmail: " + cliente.getEmail()
					+ "\nTelefone: " + cliente.getTelefone()
					+ "\n\nAtt"
					+ "\nEquipe DBS Informátoca";
		
		EmailMessageDto dto = new EmailMessageDto();
		dto.setTo(to);
		dto.setSubject(subject);
		dto.setBody(body);
		return dto;
	}
}
