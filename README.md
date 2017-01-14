**API Rest para simulação de um conjunto de sondas em um planalto**
----
* **Criar planalto**

  URL: /plane
  Método: PUT
  Retorno: JSON do planalto

* Consultar todos planaltos
URL: /plane
Método: GET
Retorno: JSON de todos os planaltos

* Consultar planalto
URL: /plane/{planeId}
Método: GET
Retorno: JSON do planalto

* Apagar planalto
URL: /plane/{planeId}
Método: DELETE
Retorno: Vazio

* Apagar todos planaltos
URL: /plane
Método: DELETE
Retorno: Vazio

* Criar sonda
URL: /plane/{planeId}/probe?x=0&y=0&direction=N
Método: PUT
Retorno: Sonda criada

* Virar sonda para esquerda
URL: /plane/{planeId}/probe/{probeId}/turnLeft
Método: GET
Retorno: Vazio

* Virar sonda para direita
URL: /plane/{planeId}/probe/{probeId}/turnRight
Método: GET
Retorno: Vazio

* Mover sonda
URL: /plane/{planeId}/probe/{probeId}/move
Método: GET
Retorno: Vazio

* Virar sonda para esquerda
URL: /plane/{planeId}/probe/{probeId}/turnLeft
Método: GET
Retorno: Vazio

* Consultar sonda
URL: /probe/{probeId}
Método: GET
Retorno: JSON da sonda

* Apagar sonda
URL: /probe/{probeId}
Método: DELETE
Retorno: Vazio

* Apagar todas as sondas
URL: /probe
Método: DELETE
Retorno: Vazio

**Arquivos de configuração do Solr 6.3.0:** ./solr/
**Teste da API:** ./src/test/scripts/rest-test.sh
**Testes integrados:** ./src/test/java
