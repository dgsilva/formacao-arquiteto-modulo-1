package br.com.ecommerce.pedidos.api.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ecommerce.pedidos.api.cryptography.MD5Cryptography;
import br.com.ecommerce.pedidos.api.dto.AuthRequestDTO;
import br.com.ecommerce.pedidos.api.dto.AuthRequestPostDTO;
import br.com.ecommerce.pedidos.api.dto.AuthResponseModel;
import br.com.ecommerce.pedidos.api.model.Auth;
import br.com.ecommerce.pedidos.api.repository.AuthRepository;
import br.com.ecommerce.pedidos.api.security.JwtSecurity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	
	private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
	
	public ResponseEntity<AuthResponseModel> acessar(@RequestBody AuthRequestDTO dto) {

		try {
			AuthResponseModel response = new AuthResponseModel();
			Auth auth = authRepository.findByNameAndKey(dto.getName(), MD5Cryptography.encrypt(dto.getKey()));

			if (auth != null) {
				response.setStatus(200);
				response.setMensagem("Usuário autenticado com sucesso.");
				log.info("Usuário autenticado com sucesso.");
				response.setAccessToken(getAccessToken(auth.getName()));
				response.setName(auth.getName());
				response.setClient_id(auth.getClient_id());
				response.setExpirationDate(Instant.now());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setStatus(401);
				response.setMensagem("Acesso negado");
				log.error("Acesso negado");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	public Auth create(AuthRequestPostDTO dto) {
		
		if(authRepository.findByName(dto.getName())!= null) {
			throw new IllegalArgumentException("O name informado não está cadastrado");
		}
		Auth auth = new Auth();
		auth.setName(dto.getName());
		auth.setClient_id(dto.getClient_id());
		auth.setKey(MD5Cryptography.encrypt(dto.getKey()));
		return authRepository.save(auth);
	}
	
	
	private String getAccessToken(String nameAuth) {

	    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

	    return Jwts.builder().setId("PAGAMENTO_JWT").setSubject(nameAuth)
	            .claim("authorities",
	                    grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 6000000))
	            .signWith(SignatureAlgorithm.HS512, JwtSecurity.SECRET_KEY).compact();

	}
}
