# Validate JWT API

Este projeto é uma aplicação Java que expõe uma API web que recebe por parametro um JWT (string) e verifica se é valida conforme regras abaixo:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

# Pré-requitos

Antes de iniciar, certifique-se de ter o seguinte instalado em seu sistema:

- Java JDK 11 ou superior
- Maven 3.6.0 ou superior (para gerenciamento de dependências e construção do projeto)

## Configuração e Execução

Siga estas etapas para configurar e executar o projeto:

```bash
git clone https://github.com/taureliano/validateJwt.git
cd validateJwt
```

### 2. Construa o projeto

Dentro do diretório do projeto, execute o seguinte comando Maven para construir o projeto e baixar as dependências necessárias:

```bash
mvc clean install
```

A aplicação será iniciada e estará disponível `http://localhost:8080/validateJwt`

## Uso da API

Para validar um JWT, envie uma requisição GET para o endpoint `/validateJwt` com o parâmetro `token` contendo o JWT a ser validado.

Exemplo de uso com cURL:

```bash
curl -X GET "http://localhost:8080/validateJwt?token=seu_jwt_aqui"
```

Substitua `seu_jwt_aqui` pelo JWT que você deseja validar.

### Regras de Validação

A API valida o JWT de acordo com as seguinte regras:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

