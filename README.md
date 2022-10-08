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