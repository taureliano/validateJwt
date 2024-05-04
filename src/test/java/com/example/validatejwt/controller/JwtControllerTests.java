/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.validatejwt.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.validatejwt.bean.JwtClaims;

@SpringBootTest
public class JwtControllerTests {

	@Autowired
	private JwtController controller;

	@Test
	public void validateClaims_OK_RoleWith_Admin() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name Numero Um",
				"Admin",
				14627
				);				
		
		assertThat(controller.validateClaims(claims)).isTrue();
	}

	@Test
	public void validateClaims_OK_RoleWith_Member() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name Numero Dois",
				"Member",
				14627
				);				
		
		assertThat(controller.validateClaims(claims)).isTrue();
	}

	@Test
	public void validateClaims_OK_RoleWith_External() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name Numero Tres",
				"External",
				14627
				);				
		
		assertThat(controller.validateClaims(claims)).isTrue();
	}

	@Test
	public void validateClaims_NOK_RoleWithERROR() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name Numero Quatro",
				"Outro valor",
				14627
				);				
		
		assertThat(controller.validateClaims(claims)).isFalse();
	}

	@Test
	public void validateClaims_NOK_NameWithNumber() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name 1",
				"Member",
				14627
				);				
		
		assertThat(controller.validateClaims(claims)).isFalse();
	}

	@Test
	public void validateClaims_NOK_SeedNotPrime() throws Exception {
		
		JwtClaims claims = new JwtClaims(
				"Name Numero Cinco",
				"Member",
				123
				);				
		
		assertThat(controller.validateClaims(claims)).isFalse();
	}

	@Test
	public void validateClaims_NOK_ClaimsIsNull() throws Exception {
		
		assertThat(controller.validateClaims(null)).isFalse();
	}

	@Test
	public void isPrime_NOK_Minor1() throws Exception {
		
		assertThat(controller.isPrime(1)).isFalse();
	}

	@Test
	public void extractClaims_OK() throws Exception {
		
		// Token Válido
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
		
		DecodedJWT jwt = JWT.decode(token);
		
		assertThat(controller.extractClaims(jwt)).isNotNull();
	}

	@Test
	public void extractClaims_NOK_WithMore3Claims() throws Exception {
		
		// Token com mais de 3 claims
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
		
		DecodedJWT jwt = JWT.decode(token);
		
		assertThat(controller.extractClaims(jwt)).isNull();
	}

	@Test
	public void validateJwt_NOK_JWTisNull() throws Exception {
		
		assertThat(controller.validateJwt(null)).isFalse();
	}

	@Test
	public void validateJwt_OK() throws Exception {

		// Token Valido
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
		
		assertThat(controller.validateJwt(token)).isTrue();
	}

	@Test
	public void validateJwt_NOK_NameWithNumber() throws Exception {

		// Claim Name com numeros
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
		
		assertThat(controller.validateJwt(token)).isFalse();
	}

	@Test
	public void validateJwt_NOK_JWTInvalido() throws Exception {

		// JWT Invalido
		String token = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
		
		assertThat(controller.validateJwt(token)).isFalse();
	}

}