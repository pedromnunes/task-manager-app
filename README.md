---
 
# Sistema de Gestão de Tarefas
 
Um sistema em Java para criar, listar, atualizar e remover tarefas. Utiliza conceitos de POO e boas práticas de design de software.

## Versão 

Versão cujo repositorio é um ficheiro CSV.
 
## Funcionalidades
 
- **Adicionar Tarefa**: Cria uma nova tarefa.
- **Listar Tarefas**: Exibe todas as tarefas, ordenadas por data de criação.
- **Atualizar Tarefa**: Modifica título, descrição, data de conclusão e status.
- **Remover Tarefa**: Deleta uma tarefa pelo ID.
- **Listar por Status**: Filtra tarefas por status (PENDENTE, EM_PROGRESSO, CONCLUIDA).
 
## Tecnologias
 
- **Java 8+**
- **JUnit** para testes
 
## Estrutura do Projeto
 
- **model**: Modelos de dados (`Task.java`)
- **repository**: Repositórios de dados (`TaskRepository`, `CsvTaskRepository`)
- **service**: Lógica de negócios (`TaskManager`)
- **TaskManagerAppApplication.java**: Entrada da aplicação
 
## Instalação e Execução
 
1. Clone o repositório:
   ```bash
   git clone https://github.com/pedromnunes/task-manager-app.git
   cd task-manager-app
   ```
 
2. Compile e execute:
   ```
   mvn clean install
   mvn exec:java
   ```
 
## Testes
 
Execute os testes unitários com:
```bash
mvn test
```
 
---