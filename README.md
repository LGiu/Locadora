# Exercício de seleção Desenvolvedor Back-End Java - Webservice Locadora
O desafio:
- Você irá criar um sistema de uma locadora de filmes. O sistema deve permitir a criação de
usuários (clientes), logon e logoff de um usuário, listagem de filmes disponíveis, locação de
um filme, devolução de um filme, e pesquisa de filme pelo título.
- Um filme deve possuir um título e um diretor.
- A locadora pode possuir múltiplas cópias de um mesmo filme.
- Um usuário deve possuir um e-mail para se identificar no sistema, um nome (para
exibição) e uma senha.
- O sistema pode ser acessado concorrentemente por múltiplos usuários, que
competirão pela locação dos filmes.
- Você deve escrever uma especificação de webservices para este sistema de locação de
filmes. Os webservices devem usar JSON sobre HTTP. A especificação precisa oferecer
detalhes suficientes para que um desenvolvedor não familiarizado com o sistema consiga
criar uma integração com este sistema (por exemplo, um website ou um app mobile), mas
deve ser concisa a ideia é que não se perca muito tempo escrevendo esta especificação;
uma simples listagem das chamadas com parâmetros de entrada e saída, e um descritivo
do que a chamada faz, é suficiente. Por exemplo, se você estiver criando um documento
com capa, título, índice, então já está fazendo mais do que o necessário seja econômico
aqui.
- O sistema não terá 'interface gráfica'! Será acessado somente via os webservices
acima.
- Você deve modelar o banco de dados para a aplicação. O script SQL de criação do banco
deve ser enviado para nós. Qualquer banco pode ser usado, desde que seja relacional e
suporte SQL, sugerimos que use MySQL.
- Popular o banco de dados da locadora com filmes está fora do escopo do trabalho; isto
pode ser feito 'na mão', direto no banco de dados (usando por exemplo o MySQL
WorkBench), ou então com inserts no próprio script de criação do BD.
- Após as especificações dos webservices e do banco de dados estarem prontas, o sistema
deve ser implementado em Java.
- Após concluir e testar sua implementação, nos envie os códigos fontes Java, o script de
criação do banco de dados e a especificação dos webservices através de um link, conforme
as instruções no início deste documento.

________________________________________________



O sistema foi desenvolvido em Java, utilizando o framework Spring Boot. A lingugem de banco utilizada foi o Mysql.

Configurações iniciais do sistema:

Deve-se possui o Mysql Server instalado;

Com o Mysql instalado, deve-ser rodar o comando abaixo:

  CREATE DATABASE locadora;

Feito isso deve-se startar o projeto pra a criação das tabelas e colunas.

Obs: A o usuário e senha padrões do banco são 'root' e 'root' respectivamente. Caso já possu o Mysql instalado com outro usuário e senha
deve-se altera-los no arquivo application.properties do projeto.

Após deve-ser rodar no banco alguns dados padrões no banco:

  INSERT INTO `diretor` (`id`, `nome`) VALUES (3, 'Steven Spielberg');
  INSERT INTO `diretor` (`id`, `nome`) VALUES (2, 'Stanley Kubrick');
  INSERT INTO `diretor` (`id`, `nome`) VALUES (1, 'Quentin Tarantino');
  INSERT INTO `filme` (`id`, `quantidade_atual`, `quantidade_total`, `titulo`, `i_diretor`) VALUES (1, 2, 2, 'Jurassic Park', 3);
  INSERT INTO `filme` (`id`, `quantidade_atual`, `quantidade_total`, `titulo`, `i_diretor`) VALUES (2, 1, 1, 'Kill Bill Vol. I', 1);
  INSERT INTO `usuario` (`id`, `email`, `nome`, `senha`) VALUES (5, 'admin@admin.com', 'Admin', '+F3Cjuk=');

Para realizar o login no sistema deve-se utilizar a seguinte requisição:
 
  POST - http://127.0.0.1:8090/logon 
  HEADER - Content-Type:application/json
  JSON - 
    {
          "email": "admin@admin.com",
          "senha": "admin"
    }
  
Após realizada a requisição deve-se guardar o token gerado. Com este token é possível realizar as requisções abaixo:


________________________________________________

DIRETOR 

  HEADER - 
    Content-Type:application/json
    token:07491607-e426-4790-af47-555dd9e48c7a

  SALVAR
    POST - http://127.0.0.1:8090/diretor 
    JSON - 
      {
            "nome": "Martin Scorsese"
      }
      
  ALTERAR
    POST - http://127.0.0.1:8090/diretor 
    JSON - 
      {
            "id": "1",
            "nome": "Martin Scorsese Alterado"
      }
      
  EXCLUIR
    DELETE - http://127.0.0.1:8090/diretor/1
      
  BUSCA 1 REGISTRO
    GET - http://127.0.0.1:8090/diretor/1
    
  BUSCA TODOS
    GET - http://127.0.0.1:8090/diretores
    
    
________________________________________________

FILME

  HEADER - 
    Content-Type:application/json
    token:07491607-e426-4790-af47-555dd9e48c7a

  SALVAR
    POST - http://127.0.0.1:8090/filme 
    JSON - 
      {
            "titulo": "Taxi Driver",
            "diretor": {
              "id": 1
            }
      }
      
  ALTERAR
    POST - http://127.0.0.1:8090/filme 
    JSON - 
      {
            "id": "1",
            "titulo": "Taxi Driver",
            "diretor": {
              "id": 1
            }
      }
      
  EXCLUIR
    DELETE - http://127.0.0.1:8090/filme/1
      
  BUSCA 1 REGISTRO
    GET - http://127.0.0.1:8090/filme/1
    
  BUSCA TODOS
    GET - http://127.0.0.1:8090/filmes
    
  BUSCA TODOS PRO NOME
    GET - http://127.0.0.1:8090/filmes/Taxi
    

________________________________________________

USUARIO

  HEADER - 
    Content-Type:application/json
    token:07491607-e426-4790-af47-555dd9e48c7a

  SALVAR
    POST - http://127.0.0.1:8090/usuario 
    JSON - 
      {
            "nome": "João",
            "senha": "Silva",
            "email": joao@silva.com"
      }
      
  ALTERAR
    POST - http://127.0.0.1:8090/usuario 
    JSON - 
      {
            "id": "1",
            "nome": "João",
            "senha": "Silva",
            "email": joao@silva.com"
      }
      
  EXCLUIR
    DELETE - http://127.0.0.1:8090/usuario/1
      
  BUSCA 1 REGISTRO
    GET - http://127.0.0.1:8090/usuario/1
        
  BUSCA TODOS
    GET - http://127.0.0.1:8090/usuarios


________________________________________________

LOCACAO

  HEADER - 
    Content-Type:application/json
    token:07491607-e426-4790-af47-555dd9e48c7a

  SALVAR
    POST - http://127.0.0.1:8090/locacao 
    JSON - 
      {
            "usuario":{
              "id": 1
            },
            "filme":{
              "id": 1
            },
            "dataLocacao": "2019-10-10",
            "dataPervistaDevolucao": "2019-10-15"
      }
      
  ALTERAR
    POST - http://127.0.0.1:8090/locacao 
    JSON - 
      {
            id": 3,
            "filme": {
              "id": 1
            },
            "dataLocacao": "2019-10-10",
            "dataDevolucao": "2019-10-14",
            "dataPervistaDevolucao": "2019-10-15"
     }
      
  EXCLUIR
    DELETE - http://127.0.0.1:8090/locacao/1
      
  BUSCA 1 REGISTRO
    GET - http://127.0.0.1:8090/locacao/1
        
  BUSCA TODOS
    GET - http://127.0.0.1:8090/locacoes    
        
________________________________________________
   
        
Para realizar o logoff no sistema deve-se utilizar a seguinte requisição:
 
  POST - http://127.0.0.1:8090/logoff 
  HEADER - Content-Type:application/json
  JSON - 
    {
          "chave": "07491607-e426-4790-af47-555dd9e48c7a"
    }

Lembrando que o token expira em 30 minutos sem uso.
