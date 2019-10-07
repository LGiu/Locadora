# WebService de Locadora

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
