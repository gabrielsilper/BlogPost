# BlogPost - Backend

Levantando requisitos para o backend do BlogPost.

***Aqui estou exercitando o levantamento de requisitos para um sistema de blog. Eu com o tempo vou levar todos os pontos, os requisitos funcionais, as estórias de usuários e os critérios de aceitação.*** 

Manter usuário:
 - Cadastrar usuário;
 - Atualizar usuário;
 - Deletar usuário;

Manter post:
 - Cadastrar post;
 - Listar post;
 - Atualizar post;
 - Deletar post;

Manter comentário:
 - Cadastrar comentário;
 - Listar comentário de um post;
 - Atualizar comentário;
 - Deletar comentário;

Manter curtida:
 - Cadastrar curtida;
 - Contar curtida de um post;
 - Deletar curtida;

Login:
 - Autenticar usuário;
 - Gerar token de autenticação;
 - Validar token de autenticação;

## Requisitos Funcionais:

------------------------

***Quero levantar esses pontos, pois assim consigo saber o que devo usar como base para criar os testes de integração e unitários.***

 - RF01 - Cadastrar usuário;
   - Descrição: O sistema deve permitir o cadastro de um usuário com username, e-mail e senha;
   - Estória: Eu como usuário quero me cadastrar no sistema para poder postar no blog, eu irei informar meu username, e-mail e senha;
   - Critérios de aceitação:
     - O sistema deve permitir o cadastro de um usuário com username, e-mail e senha;
     - O sistema deve retornar o usuário cadastrado;
     - O sistema deve retornar uma mensagem de erro caso o e-mail já esteja cadastrado;
     - O sistema deve retornar uma mensagem de erro caso o e-mail seja inválido;
     - O sistema deve retornar uma mensagem de erro caso o e-mail não seja informado;
     - O sistema deve retornar uma mensagem de erro caso o username não seja informado;
     - O sistema deve retornar uma mensagem de erro caso a senha não seja informada;
     - O sistema deve retornar uma mensagem de erro caso a senha tenha menos de 8 caracteres;

 - RF02 - Atualizar usuário;
   - Descrição: O sistema deve permitir que um usuário autenticado atualize suas informações (username, e-mail e senha).
   - Estória: "Eu, como usuário cadastrado, quero atualizar minhas informações (username, e-mail ou senha) para manter meus dados sempre corretos. Para isso, preciso estar logado e informar os novos dados desejados."
   - Critérios de Aceitação: 
     - O sistema só permite a atualização se o usuário estiver autenticado (token válido).
     - O sistema deve retornar o usuário atualizado.
     - O sistema deve entender que os campos são opcionais, então só atualiza o que for informado.
     - O sistema deve permitir a atualização de username, e-mail e senha.
     - O sistema deve retornar uma mensagem de erro caso o e-mail já esteja cadastrado.
     - O sistema deve retornar uma mensagem de erro caso o e-mail seja inválido.
     - O sistema deve retornar uma mensagem de erro caso a nova senha tenha menos de 8 caracteres;

***Vou continuar levantando os requisitos funcionais com o tempo, pois acredito que sempre vai tá em construção, isso a medida que as ideias forem surgindo e quando tiver tempo para isso.***

## Entidades/Modelos:

------------------------

### User
Descrição: 
 - Modelo que vai armazenar os usuários do blog.

Campos:
 - id: long
 - username: string
 - email: string
 - password: string
 - createdAt: LocalDateTime
 - updatedAt: LocalDateTime

### Post
Descrição: 
 - Modelo que vai armazenar os posts do blog.

Campos:
 - id: long
 - title: string
 - content: string
 - user: User - Relacionamento N:1
 - createdAt: LocalDateTime
 - updatedAt: LocalDateTime

### Comment
Descrição: 
 - Modelo que vai armazenar os comentários dos posts.

Campos:
 - id: long
 - content: string
 - user: User - Relacionamento N:1
 - post: Post - Relacionamento N:1
 - createdAt: LocalDateTime
 - updatedAt: LocalDateTime

### Like
Descrição: 
 - Modelo que vai armazenar os likes dos posts.
 - Vai ser uma entidade que vai ter um relacionamento N:N entre User e Post. Usar tabela intermediária para armazenar os likes.

Campos:
 - user: User
 - post: Post

