# Object Oriented Programming Laboratory

Repository for "Object Oriented Programming Laboratory" [(**LPOO**)](https://sigarra.up.pt/feup/pt/UCURR_GERAL.FICHA_UC_VIEW?pv_ocorrencia_id=420000) pratical classes course

## Project



### LPOO_411 Eternal Defense

* A ideia deste projeto é criar um jogo do tipo *Tower Defense*, existente num universo em eterna evolução pelo tempo, tendo como principal objetivo o uso de torres de defesa e armadilhas, colocadas de forma estratégica pelo jogador, em prol da defesa do seu castelo contra vagas progressivamente mais difíceis de inimigos.
* Á medida que o jogador vai progredindo no jogo e derrotando vagas e vagas de inimigos, vai evoluindo no tempo, passando pelas várias gerações (Pré-história -> Medieval -> Moderna -> Futurística) passando a possuir, em cada geração, torres, armadilhas e inimigos relacionados com esse tempo.

Este projeto foi desenvolvido por José Rodrigues (up201708806) e Raul Viana (up201208089) na Unidade Currricular de Laboratório de Programação Orientada a Objetos 2018⁄19.

#### Game Summary
O jogo começa com uma janela onde é pedido ao jogador que introduza o nome que pretende utilizar e que escolha a dificuldade.<br>

![Menu Inicial](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Starting_Menu.png)

Posteriormente o jogador é levado para o mapa de jogo, onde terá que comprar o material defensivo, composto por torres e armadilhas e colocá-lo nas posições que achar mais vantajosas.<br>

![Fase de colocação das dafesas](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Game_Start.png)

É colocado ao dispor do jogador um menu com as torres e as armadilhas disponíveis e o seu preço.<br> 

![Fase de colocação das defesas - Ponto permitido](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Placing_Menu_Can_Place.png)<br>

Existem situações em que não é permitido ao jogador colocar uma defesa num determinado ponto, tais como bloquando entradas,
 colocando defesas em cima de paredes ou ainda quando não tem dinheiro suficiente. <br>
 
![Fase de colocação das defesas - Ponto não permitido](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Placing_Menu_Cant_Place.png)

Exste ainda um quadro informativo na zona inferior da janela de jogo que contém as informações pertinentes para optimizar a colocação da torres e das armadilhas em conformidade com os tipos de inimigos e a geração em que o jogo se encontra.
Depois de colocadas todas as defesas que o jogador escolheu o jogo passa para a fase de simulação, em que vagas de inimigos tentam chegar à base e destruí-la.
Entre cada ronda o jogador tem a possibilidade de acrescentar defesas, se dispuser de dinheiro suficiente.<br>

![Fase de Simulação](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Game_Ongoing.png)

No fim do jogo é mostrada uma janela com o resultado final. Vitória, no caso do jogador ter conseguido defender a base através das gerações:<br>

![Ecrân de fim de jogo - Vitória](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Game_End_Win.png)<br>

Ou derrota, na eventualidade de a sua base não ter resistido aos ataques inimigos:<br>

![Ecrân de fim de jogo - Derrota](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/Game_End_Lost.png)

#### Implemented Features

* **Escolher dificuldade** - Antes do jogo começar, o jogador tem a possibilidade de escolher entre três dificuldades, 
*"easy"*, *"medium"* e *"hard"*. A dificuldade irá alterar também o balanço inicial do jogador.

* **Colocar torres e armadilhas** - Quando o jogo se encontra em fase de construção, o jogador consegue movimentar um 
*"placer"* que lhe permite escolher uma posição e uma torre ou armadilha para lá colocar, se a posição for válida.

* **Avançar no tempo** - Á medida que se vão derrotando as várias rondas de inimigos, o *tempo* do jogo vai avançando, 
atravessando as várias gerações da humanidade (Pré-história, Medieval, Moderna e Futurística), o que irá alterar o nome, 
a cor, o dano e a vida dos variados inimigos, das torres e armadilhas do jogador e da sua base.

* **Estatísticas de final de jogo** - Após o final do jogo, seja por o ter vencido seja por o ter perdido, é permitido 
visualizar algumas informações, tal como o balanço que possuia quando o jogo terminou e a ronda e a geração máxima alcançadas.

#### Planned Features

* **Interface de Swing** - Converter todo o interface para *"Swing"* de forma a poder ilustrar o jogo com imagens, em vez 
de texto.

* **Registo dos high-scores** - Implementar um registo de *"high-scores"* que registasse os melhores resultados numa lista 
de forma a permitir a comparação e um ranking dos melhores jogadores.

* **Upgrade de torres/base** - Implementar a opção de se poder fazer *"upgrade"* às torres já colocadas ou à base.

#### Code Coverage

Foram implementados variados testes unitários, de forma a cobrir a maioria do código. As partes mais importantes e sujeitas 
a erro foram cobertas pelos testes unitários.

 ![Code Coverage](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/codecoveragereport.png)

Tentámos posteriormente aplicar *"mutation tests"*, mas infelizmente não foi possível correr o comando *gradlew pitest* pois irremediavelmente 
retornou um erro de *"unresolved symbol"* em relação às dependências de *"com.intellij.uiDesigner.core"*. 
Optámos por utilizar este recurso do IDE, para implementar a interface gráfica, mas acabou por se revelar bastante problemático, 
principalmente entre cada atualização remota: *"git pull"* e *"git push"*, pois tornava-se necessário voltar a adicionar 
a dependência manualmente.

#### Design (patterns)

* **Os diversos elementos do jogo todos partilham a função *draw* e todos são criados pela classe *Arena***

###### Problema em contexto

Os diversos elementos do jogo são todos criados a partir de uma superclasse **Element**, que desconhece a classe dos objetos que deve criar e que pretende que as suas subclasses especifiquem essa mesma classe no momento da criação do objeto. A responsabilidade da criação dos vários objetos recai sempre na classe **Arena**, que cria os objetos e especifica a subclasse de **Element** a que pertencem, entre **Wall**, **Defense** e **Enemy**.

###### O padrão

Nós aplicamos o padrão **Factory**. Este padrão permite definir uma interface **Element** para a criação dos objetos, mas delegar para as subclasses **Wall**, **Defense** e **Enemy** a instanciação da classe.

###### Implementação

A figura a seguir mostra como as funções do padrão foram mapeadas para as classes.

![UML](https://github.com/raulviana/FEUP-LPOO/blob/master/project/images/UML_Factory.png)

Estas classes podem ser encontradas nos seguintes ficheiros:

* [Element](https://github.com/raulviana/FEUP-LPOO/blob/master/project/src/main/java/Element.java)

* [Defense](https://github.com/raulviana/FEUP-LPOO/blob/master/project/src/main/java/Defense.java)

* [Wall](https://github.com/raulviana/FEUP-LPOO/blob/master/project/src/main/java/Wall.java)

* [Enemy](https://github.com/raulviana/FEUP-LPOO/blob/master/project/src/main/java/Enemy.java)

* [Arena](https://github.com/raulviana/FEUP-LPOO/blob/master/project/src/main/java/Arena.java)

###### Consequências

Os métodos de **Factory** eliminam a necessidade de vincular classes específicas no código (**Wall**, **Defense** e **Enemy**). O código só precisa lidar com a interface do objeto (**Element**). Dessa forma ele pode trabalhar com qualquer classe **Element** definida pela **Arena**.

#### Known Code Smells and Refactoring Suggestions

Ao percorrer o código é possível encontrar diversos *"code smells"* que fomentam um apodrecimento prematuro do código, que se degrada sempre que existe necessidade de acrescentar novas funcionalidades ou alterar as que estão implementadas da forma como estão.

###### Código *"Bloater"*

* As classes "Arena" e "UI" contêm uma enorme quantidade de métodos e linhas de código. Apesar de cada método não apresentar
 demasiadas linhas, que o tornem demasiado extenso, a classe contém um número exagerado de métodos. A solução passaria por 
 dividir a classe em diversas classes que poderiam agrupar grupos de métodos cujo comportamento se relacione. Por exemplo 
 os métodos destinados a criar os objetos para a arena, ou mesmo os métodos destinados a mover esses objetos.

###### Abuso da orientação a objetos

* Existe pelo código uma exagerada utilização de *"switch statments"*. Isto leva a que, se for necessário adicionar uma 
nova condição, esta tenha que ser realizada em vários pontos através do código. São exemplo as classes Enemy, Round e 
WallShapes. Para corrigir esta situação seria necessário substituir a tipagem dentro da classe por subclasses e posteriormente 
implementar polimorfismo. Estas acções levariam a uma melhor legibilidade do código.  

###### Dispensáveis

* Classes como Color e GameStatus são consideradas *"Data Classes"*, classes que apenas contém dados. A solução passaria
por encapsular estes campos e migrá-los para as classes que os utilizam, no entanto, neste caso isso poderia 
aumentar a quantidade de dependências, o que não é aconselhado de todo.



#### Bibliografia

###### Code Smells
 * [sourcemaking.com](https://sourcemaking.com)
 * [Sihui.io](https://www.sihui.io)
 
###### Path Finder
 * [growingwiththeweb.com](https://www.growingwiththeweb.com/2012/06/a-pathfinding-algorithm.html)
 * [qiao.github.io](https://qiao.github.io/PathFinding.js/visual/)
 
###### General Development
 * [mabe02.github.io/lanterna/apidocs/](http://mabe02.github.io/lanterna/apidocs/3.0/)

#### Self-evaluation

* José Rodrigues : 60%
* Raul Viana : 40%
