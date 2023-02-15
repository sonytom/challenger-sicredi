# Challenger-sicredi


Tecnologias usadas para fazer o projeto

Link do ci : https://github.com/sonytom/challenger-sicredi/actions

Java 17
Spring Boot WebFlux 
Lombok 
Mongo
Intellij
Heroku

Endpoints da aplicação.
Todos os end points estão ai tanto local como do heroku.
https://www.postman.com/warped-rocket-85190/workspace/newchallenger/collection/19716956-ad49153a-436e-42a6-99e8-c1a334f2a290?action=share&creator=19716956

Sugestão de funcionamento da aplicação 

Criar um Customer pegar o id -> Usar o Post para criar  uma Schedule ->  Usar o Pacth para abrir a sessao com nome da schedule e minutos desejados -> Usar o post de votos para votar na sessao -> usar o get com o nome da sesssao para retornar a quantidade de votos -> para fechar a sessao e retornar o total de votos o horario tem que estar expirado e passar o final 1 na request do get.


Link do Heroku : https://challenger-sicredi.herokuapp.com/

Uso da maquina no heroku.

![image](https://user-images.githubusercontent.com/33350348/218972373-c839f8c7-2d43-4e08-8212-3a3a1e106610.png)


