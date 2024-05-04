package com.example.validatejwt.controller;

import org.slf4j.*;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.validatejwt.bean.JwtClaims;

@RestController
public class JwtController {
	
	private static Logger log = LoggerFactory.getLogger(JwtController.class);

	@GetMapping("/validateJwt")
	public boolean validateJwt(@RequestParam String token) {
		log.info("Inicio do metodo validateJwt ----------");
		if(token == null || token.isBlank()) {
			log.debug("JWT nulo ou vazio");
			log.info("Fim do metodo validateJwt: Saida [false] ----------");
			return false;
		}
		
		try {
			DecodedJWT jwt = JWT.decode(token);
			log.debug("Token decodificado. O Token está válido! ----------");
			JwtClaims claims = extractClaims(jwt);
			if(validateClaims(claims)) {
				log.info("Fim do metodo validateJwt: Saida [true] ----------");
				return true;
			} else {
				log.info("Fim do metodo validateJwt: Saida [false] ----------");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		log.info("Fim do metodo validateJwt: Saida [false] ----------");
		return false;
	}

	protected boolean validateClaims(JwtClaims claims) {
		log.info("Inicio do metodo validateClaims ---");
		if(claims == null) {
    		log.info("Fim do metodo validateClaims: Saida [false] ---");
            return false;
		}
		
		String name = claims.getName();
		String role = claims.getRole();
		Integer seed = claims.getSeed();

		log.debug("Claims extraídas do Token:");
		log.debug(String.format("Name: [%s]", name));
		log.debug(String.format("Role: [%s]", role));
		log.debug(String.format("Seed: [%d]", seed));
		
        if (name == null || name.isEmpty() || name.matches(".*\\d.*") || name.length() > 256) {
            log.error("A claim 'Name' deve ser uma string não vazia, sem números e com no máximo 256 caracteres");
    		log.info("Fim do metodo validateClaims: Saida [false] ---");
            return false;
        }

		List<String> validRoles = List.of("Admin", "Member", "External");
		if (role == null || !validRoles.contains(role)) {
		    log.error("A claim 'Role' deve conter apenas um dos valores: Admin, Member ou External");
    		log.info("Fim do metodo validateClaims: Saida [false] ---");
		    return false;
		}
		
		if (!isPrime(seed)) {
			log.error("A claim 'Seed' deve ser um número primo");
    		log.info("Fim do metodo validateClaims: Saida [false] ---");
			return false;
		}

		log.info("Claims validadas com sucesso!");
		log.info("Fim do metodo validateClaims: Saida [true] ---");
		return true;
	}

	protected JwtClaims extractClaims(DecodedJWT jwt) {
		Map<String, Claim> claims2 = jwt.getClaims();
		if(claims2.size() != 3 ) {
            log.error(String.format("JWT deve conter exatamente 3 claims. Este JWT tem %d claims.", claims2.size()));
			return null;
		}
		
		JwtClaims claims = new JwtClaims(
				jwt.getClaim("Name").asString(), 
				jwt.getClaim("Role").asString(), 
				Integer.parseInt(jwt.getClaim("Seed").asString())				
				);		
		return claims;
	}

	protected boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
