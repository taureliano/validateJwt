# Validate JWT API

Este projeto é uma aplicação Java que expõe uma API web para validar JWTs (JSON Web Tokens) de acordo com regras específicas.

# Pré-requitos

Antes de iniciar, certifique-se de ter o seguinte instalado em seu sistema:

- Java JDK 11 ou superior
- Maven 3.6.0 ou superior (para gerenciamento de dependências e construção do projeto)

## Configuração e Execução

Siga estas etapas para configurar e executar o projeto:

### 1. Clone o repositório

Clone este repositório em sua máquina local usando o seguinte comando:

```bash
git clone https://github.com/taureliano/validateJwt.git
cd validateJwt
```

### 2. Construa o projeto

Dentro do diretório do projeto, execute o seguinte comando Maven para construir o projeto e baixar as dependências necessárias:

```bash
mvc clean install
```

### 3. Execute a aplicação 

Após a construção bem-sucedida, inicie a aplicação com o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação será iniciada e estará disponível em `http://localhost:8080/validateJwt`

## Uso da API

Para validar um JWT, envie uma requisição GET para o endpoint `/validateJwt` com o parâmetro `token` contendo o JWT a ser validado.

Exemplo de uso com cURL:

```bash
curl -X GET "http://localhost:8080/validateJwt?token=seu_jwt_aqui"
```

Substitua `seu_jwt_aqui` pelo JWT que você deseja validar.

## Regras de Validação

A API valida o JWT de acordo com as seguinte regras:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

## Detalhes dos Métodos

A API expõe o seguinte método:

- validateJwt (GET)

## Métodos Internos

Dentro deste método seguimos com mais alguns métodos de tratamento:

### 1. extractClaims

Neste método recebemos uma classe DecodedJWT, o qual contém o token decodificado.

```bash
extractClaims(DecodedJWT jwt)
```

### 2. validateClaims

Neste método recebemos um bean criado JwtClaim, para armazenarmos as informações dos Claims e podermos enviá-los entre os métodos.

```bash
validateClaims(JwtClaims claims)
```

### 3. isPrime

Neste método recebemos um número e validamos se ele é um número primo.

```bash
isPrime(int n)
```
