# Challenger-sicredi

Overview do projeto 

Ele foi feito em multmodules e webflux, para assim ter uma segurança maior de suas entidades principais e e ter uma boa escalabilidade.
 Para que o os módulos de acesso mais próximo ao usuário seja abstraido do resto da aplicação, é necessário ter vários arquivos gradle e assim cada um tendo suas dependências relevantes e a implementação de dos projetos que seja mais relevante para funcionar, exemplo o controller nunca vai ter acesso a service apenas a service vai ter acesso a controller.

Como baixar e  Executar o projeto : 
- 1 Abrir o terminal no local desejado.
- 2 git clone https://github.com/sonytom/challenger-sicredi.git
- 3 cd challenger-sicredi
- 5 ./gradlew clean build
- 6 ./gradlew bootrun
- 7 Acessar o link http://localhost:8080/ se estiver funcionando vai aparecer a seguinte mensagem "Olá Bem vindo. para 
- 8 Todos os end points estão aqui, para poder pegar mais facilmente o curl
https://www.postman.com/warped-rocket-85190/workspace/newchallenger/collection/19716956-ad49153a-436e-42a6-99e8-c1a334f2a290?action=share&creator=19716956

- Não é necessario se preucupar com banco de dados ele é na nuvem pelo MongoDb Atlas
- Nos resourses vai ter o link de acesso usuario e senha caso precise.


Tecnologias usadas para fazer o projeto

Link do ci : https://github.com/sonytom/challenger-sicredi/actions

- Java 17
- Spring Boot WebFlux 
- Lombok 
- Mongo
- Intellij
- Heroku
- Gradle 8.0 


Sugestão de funcionamento da aplicação 

Criar um Customer pegar o id -> Usar o Post para criar  uma Schedule ->  Usar o Pacth para abrir a sessao com nome da schedule e minutos desejados -> Usar o post de votos para votar na sessao -> usar o get com o nome da sesssao para retornar a quantidade de votos -> para fechar a sessao e retornar o total de votos o horario tem que estar expirado e passar o final 1 na request do get.


Link da aplicação no  Heroku : https://challenger-sicredi.herokuapp.com/

Uso da maquina no heroku.

![image](https://user-images.githubusercontent.com/33350348/218972373-c839f8c7-2d43-4e08-8212-3a3a1e106610.png)


