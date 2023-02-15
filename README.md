# Challenger-sicredi

Overview do projeto 

Ele foi feito em multmodules e webflux, para assim ter uma segurança maior de suas entidades principais e e ter uma boa escalabilidade.
 Para que o os módulos de acesso mais próximo ao usuário seja abstraido do resto da aplicação, é necessário ter vários arquivos gradle e assim cada um tendo suas dependências relevantes e a implementação de dos projetos que seja mais relevante para funcionar, exemplo o controller nunca vai ter acesso a service apenas a service vai ter acesso a controller.

Instalação do projeto: 

- Escolher a ide de sua preferencia.
- Abrir a pasta chamada challenger sicredi.
- Apos ir na raiz do projeto onde fica o gradle principal e baixar as dependências 
- Depois só executar o projeto com com o main de inicialização mostrado na foto abaixo
- Não é necessario se preucupar com banco de dados ele é na nuvem pelo MongoDb Atlas
- Nos resourses vai ter o link de acesso usuario e senha caso precise.

![image](https://user-images.githubusercontent.com/33350348/219103670-cfb62457-5877-41d4-a79d-2ed0b13bc818.png)

Caso não ache o main para executar aqui fica o link guia 
https://github.com/sonytom/challenger-sicredi/blob/a9071f1e25f23a17f4bdf5c39932985fec4f83ba/src/main/java/br/com/challengersicredi


![image](https://user-images.githubusercontent.com/33350348/218972373-c839f8c7-2d43-4e08-8212-3a3a1e106610.png)
Tecnologias usadas para fazer o projeto

Link do ci : https://github.com/sonytom/challenger-sicredi/actions

- Java 17
- Spring Boot WebFlux 
- Lombok 
- Mongo
- Intellij
- Heroku

Endpoints da aplicação.
Todos os end points estão ai tanto local como do heroku.
https://www.postman.com/warped-rocket-85190/workspace/newchallenger/collection/19716956-ad49153a-436e-42a6-99e8-c1a334f2a290?action=share&creator=19716956

Sugestão de funcionamento da aplicação 

Criar um Customer pegar o id -> Usar o Post para criar  uma Schedule ->  Usar o Pacth para abrir a sessao com nome da schedule e minutos desejados -> Usar o post de votos para votar na sessao -> usar o get com o nome da sesssao para retornar a quantidade de votos -> para fechar a sessao e retornar o total de votos o horario tem que estar expirado e passar o final 1 na request do get.


Link do Heroku : https://challenger-sicredi.herokuapp.com/

Uso da maquina no heroku.

![image](https://user-images.githubusercontent.com/33350348/218972373-c839f8c7-2d43-4e08-8212-3a3a1e106610.png)


