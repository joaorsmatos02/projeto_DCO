MigrantMatcher

Nesta fase 2 do project, pretendemos implementar o projecto numa perspectiva orientada a objectos. De momento, queremos apenas ter objectos em memória e não há necessidade de base de dados ou persistência.
Objectivos:

O projecto será avaliado em dois critérios:

    Avaliação Funcional: O projecto será avaliado conforme a riqueza dos testes JUnit desenhados para testar os dois casos de uso (identificados na meta 1)
    Avaliação Não-Funcional: A qualidade do desenho e organização do código será avaliado, com particular ênfase aos Padrões de Uso aplicados. Teremos em consideração, para além dos padrões GRASP, os padrões Façade, DTO, Strategy, Adapter, Pure Fabrication, Factory, Singleton, Composite, Builder, Chain of Command e Observer. Destes padrões, deverá usar apenas que fizerem sentido. Será também avaliada a granulosidade dos commits do git.

Alterações nos casos de uso

Deverá ter em consideração os seguintes requisitos adicionais:

    O sistema deve ser configurado com formas de ordenar as ajudas disponíveis. Uma das formas possíveis é por data de disponibilização crescente. Outra forma será primeiro por alojamentos, depois pelos outros items, ordenados dentro de cada categoria de forma aleatória.
    Devem ser incorporados os fornecedores de gateway SMS disponibilizados no ficheiro em anexo. Poderá criar outros, se se revelarem necessários para os testes.

Entrega:

    O trabalho deverá ser feito por grupos de 1 ou 2 elementos.
    Devem entregar um repositório git comprimido em formato zip. Outros formatos levarão à anulação da nota.
    Para a segunda frequência/exame, devem levar uma listagem de todos os ficheiros (não o conteúdo!!) e um diagrama de classes implementadas.

-----------------------------------------------------------------------------------------------------------------------------------------------------
Projeto desenvolvido por:

João Matos nº 56292;
João Santos nº 57103

-----------------------------------------------------------------------------------------------------------------------------------------------------

Foram encontrados detalhes do projeto-fase1 que não correspondem à implementação realizada no projeto-fase2, dos quais:
- Discrepâncias entre a implementação do diagrama de interação do método indicaRegiao(regiao) e o diagrama de classes. Por escolha do grupo, mantém   se a coerência com o diagrama de classes, isto é, é utilizado o método criaLista(regiao) presente na classe CatalogoDeAjudas. 

- removeAjudas(lam) devolve uma instância de lista de Voluntários (e não void como representado no diagrama de classes).

- Atributo contacto da classe Utilizador passa a ser protected (e não private).

- Método associaAjuda(ajuda) (presente no ID indicaAjuda(ajuda)) da classe Migrante não foi inserido no diagrama de classes.

- Método enviaSmsVoluntario() não foi representado no diagrama de classes, no entanto pertence à classe CatalogoDeUtilizadores. (representado no ID do método confirmaAjuda() da classe ProcurarAjudaHandler).

- Adicionado atributo nome a Regiao. 

Para a realização do suposto login de Voluntario - contacto dos Voluntarios default do MigrantMatcher:
    - "123456789"
    - "987654321"    