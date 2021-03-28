# UML Generator

## TODO

### Diagrama de Atividades (ActivityDiagram)

- [ ] Tipos de elementos (ActivityDiagramElements)
    - [ ] Nodos de inicio e fim
      - [ ] Nodo inicial (StartNode)
        - [ ] **Nodo inicial** possui apenas **um fluxo de saída** e **não deve possuir fluxos de entrada**
      - [ ] Nodo final (FinalNode)
        - [ ] **Nodo final** pode possuir **vários fluxos de entrada** e **nenhum de saída**
      - [ ] Somente pode existir no Diagrama de Atividades **um Nodo Inicial e um Nodo Final**
    - [ ] Nodo de decisão (DecisionNode)
      - [ ] **Nodos de decisão** somente possuem **uma entrada** podem ser seguidos por **vários fluxos de saída** (máximo 3)
    - [ ] Nodo de fusão (MergeNode)
      - [ ] **Nodos de fusão** podem possuir **vários fluxos de entrada** e apenas **um fluxo de saída**
    - [ ] Atividade (Activity)
      - [ ] Uma **Atividade** somente **pode ser seguida por um fluxo de entrada e outro fluxo de saída**
      - [ ] Cada **Atividade** possui *um único Diagrama de Sequência associado*, para representar o comportamento da atividade em detalhes
      - [ ] No caso de não haver um Diagrama de Sequência associado a uma **Atividade**, uma exceção é lançada e capturada do tipo **ActivityRepresentationException**
    - [ ] Lançamento de Exceções no caso de violação de alguma regra com o tipo **ActivityDiagramRuleException**


- [ ] Relacionamento entre os elementos através de associações (ActivityDiagramTransitions)
    - [ ] Associação (Transition)
      - [ ] Cada associação entre elementos de um diagrama de atividades possui **um nodo de origem (source)**, **um nodo de destingo (target)**, **um nome** (String) e um **valor de probabilidade associada**


- [ ] Persistência do Diagrama de Atividades em arquivo XML

### Diagramas de Sequência (SequenceDiagrams)

- [ ] Componentes de linhas de vida (Lifelines)
    - [ ] Linha de vida (Lifeline)


- [ ] Componentes dos fragmentos (Fragments)
    - [ ] Fragmento Opcional (Optional)
      - [ ] Um fragmento opcional possui um Diagrama de Sequência como seu conteúdo, ou seja, o comportamento de um fragmento opcional é determinado por um Diagrama de Sequência associado a ele. Caso **não seja atribuído um Diagrama de Sequência associado**, **uma exceção do tipo EmptyOptionalFragment deve ser lançada e capturada**


- [ ] Componentes de um Diagrama de Sequência (SequenceDiagram)
    - [ ] Cada diagrama de sequência possui um **nome** e uma **condição de guarda**
    - [ ] Mensagem
      - [ ] Cada mensagem deve possuir um **nome**, **valor de probabilidade**, **uma lifeline de origem e outra de destino**. Caso contrário, uma exceção do tipo **MessageFormatException** é lançada
      - [ ] Uma mensagem **pode possuir a mesma lifeline** como origem e destino (auto-mensagem)
      - [ ] Mensagem síncrona (SyncMessage)  
      - [ ] Mensagem assíncrona (AssyncMessage)
    - [ ] Resposta (Reply)
    - [ ] Um Diagrama de Sequência não pode possuir uma condição de guarda nula. Caso **não seja atribuída uma expressão lógica para a condiçãoo de guarda** de uma Diagrama de Sequência (true/false) **uma exceção do tipo EmptyGuardConditionException deve ser lançada e capturada**


- [ ] Persistência do Diagrama de Sequência em arquivo XML
