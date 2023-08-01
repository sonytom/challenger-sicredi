# Challenger-sicredi

Overview do projeto 

A aplicação consiste em uma API que permite votar em sessões de votação para determinados temas. Cada sessão de votação possui um nome, uma data de expiração e pode receber votos com opções de "Sim" ou "Não". A aplicação é baseada em uma arquitetura reativa, o que permite lidar com várias solicitações simultaneamente de forma eficiente.

Tecnologias e escolhas:

Spring WebFlux: foi escolhido para desenvolver a API devido à sua natureza reativa e ao suporte a programação reativa com o uso de Flux e Mono.

Spring Boot: foi utilizado como base para o desenvolvimento da aplicação, pois oferece recursos poderosos para iniciar e configurar rapidamente projetos Spring.

MongoDB: foi escolhido como o banco de dados para armazenar as informações sobre as sessões de votação e votos. O MongoDB é um banco de dados NoSQL orientado a documentos e é adequado para aplicações reativas.

JUnit 5: foi utilizado para escrever testes de unidade para as classes de serviço, garantindo a qualidade e a integridade das funcionalidades implementadas.
    
 *Foi feito um teste de cada camada para exemplo de como crio os meus testes usando mockany apenas para ver a estrutura do testes, sem stubs reais...

Java 17: foi escolhido como a versão do Java para o desenvolvimento, aproveitando as melhorias e recursos mais recentes da linguagem.

Gradle: foi escolhido como o gerenciado de pacotes, para não ter problemas com eles e baixar e adiminitrar automaticamente ao rodar.


Como baixar e  Executar o projeto : 
- 1 Abrir o terminal no local desejado.
- 2 git clone https://github.com/sonytom/challenger-sicredi.git
- 3 cd challenger-sicredi
- 5 ./gradlew clean build
- 6 ./gradlew bootrun
- 7 Acessar o link http://localhost:8080/ se estiver a funcionar vai aparecer a seguinte mensagem "Olá Bem-vindo. para 
- 8 Todos os end.points estão aqui, para poder pegar mais facilmente o curl
https://www.postman.com/warped-rocket-85190/workspace/newchallenger/collection/19716956-ad49153a-436e-42a6-99e8-c1a334f2a290?action=share&creator=19716956

- Não é necessario se preucupar com banco de dados ele é na nuvem pelo MongoDb Atlas
- Nos resourses vai ter o link de acesso usuario e senha caso precise.


Tecnologias usadas para fazer o projeto

Link do ci : https://github.com/sonytom/challenger-sicredi/actions



Sugestão de funcionamento da aplicação 

Criar um Customer pegar o id -> Usar o Post para criar  uma Schedule ->  Usar o Pacth para abrir a sessao com nome da schedule e minutos desejados -> Usar o post de votos para votar na sessao -> usar o get com o nome da sesssao para retornar a quantidade de votos -> para fechar a sessao e retornar o total de votos o horario tem que estar expirado e passar o final 1 na request do get.

(Heroku foi desativado)
Link da aplicação no  Heroku : https://challenger-sicredi.herokuapp.com/

Uso da maquina no heroku.

![image](https://user-images.githubusercontent.com/33350348/218972373-c839f8c7-2d43-4e08-8212-3a3a1e106610.png)


