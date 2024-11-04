package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Task;
import com.example.model.Task.Status;
import com.example.service.TaskManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void adicionarTarefa_deveAdicionarNovaTarefa() {
        taskManager.adicionarTarefa("Tarefa Teste", "Descricao da tarefa teste", LocalDate.now().plusDays(1));

        List<Task> tasks = taskManager.getTaskRepository().buscarTodas();
        assertEquals(1, tasks.size());
        assertEquals("Tarefa Teste", tasks.get(0).getTitulo());
    }

    @Test
    void adicionarTarefa_comTituloVazio_deveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> taskManager.adicionarTarefa("", "Descricao valida", LocalDate.now().plusDays(1)));

        assertEquals("Titulo nao pode ser vazio.", exception.getMessage());
    }

    @Test
    void adicionarTarefa_comDescricaoVazia_deveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> taskManager.adicionarTarefa("Titulo valido", "", LocalDate.now().plusDays(1)));

        assertEquals("Descricao nao pode ser vazia.", exception.getMessage());
    }

    @Test
    void adicionarTarefa_comDataConclusaoAnteriorDataCriacao_deveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> taskManager.adicionarTarefa("Titulo valido", "Descricao valida", LocalDate.now().minusDays(1)));

        assertEquals("Data de conclusao nao pode ser anterior a data de criacao.", exception.getMessage());
    }

    @Test
    void listarTarefas_deveRetornarTarefasOrdenadasPorDataCriacao() {
        taskManager.adicionarTarefa("Tarefa 1", "Descricao 1", LocalDate.now().plusDays(1));
        taskManager.adicionarTarefa("Tarefa 2", "Descricao 2", LocalDate.now().plusDays(2));

        List<Task> tasks = taskManager.repository.buscarTodas();
        assertEquals("Tarefa 1", tasks.get(0).getTitulo());
        assertEquals("Tarefa 2", tasks.get(1).getTitulo());
    }

    @Test
    void atualizarTarefa_deveAtualizarDadosDaTarefa() {
        taskManager.adicionarTarefa("Tarefa Original", "Descrição Original", LocalDate.now().plusDays(1));

        Task task = taskManager.repository.buscarTodas().get(0);
        UUID taskId = task.getId();
        taskManager.atualizarTarefa(taskId, "Título Atualizado", "Descrição Atualizada", LocalDate.now().plusDays(2),
                Status.EM_PROGRESSO);

        Task tarefaAtualizada = taskManager.repository.buscarTodas().get(0);
        assertEquals("Título Atualizado", tarefaAtualizada.getTitulo());
        assertEquals("Descrição Atualizada", tarefaAtualizada.getDescricao());
        assertEquals(Status.EM_PROGRESSO, tarefaAtualizada.getStatus());
    }

    @Test
    void removerTarefa_deveRemoverTarefaPorId() {
        taskManager.adicionarTarefa("Tarefa Removível", "Descrição Removível", LocalDate.now().plusDays(1));

        Task task = taskManager.repository.buscarTodas().get(0);
        UUID taskId = task.getId();

        taskManager.removerTarefa(taskId);

        List<Task> tasks = taskManager.repository.buscarTodas();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void listarTarefasPorStatus_deveRetornarApenasTarefasComStatusEspecifico() {
        taskManager.adicionarTarefa("Tarefa Pendente", "Descrição Pendente", LocalDate.now().plusDays(1));
        taskManager.adicionarTarefa("Tarefa Concluída", "Descrição Concluída", LocalDate.now().plusDays(2));

        Task task = taskManager.repository.buscarTodas().get(1);
        taskManager.atualizarTarefa(task.getId(), task.getTitulo(), task.getDescricao(), task.getDataConclusao(),
                Status.CONCLUIDA);

        List<Task> tasksPendentes = taskManager.repository.buscarTodas().stream()
                .filter(t -> t.getStatus() == Status.PENDENTE)
                .toList();
        List<Task> tasksConcluidas = taskManager.repository.buscarTodas().stream()
                .filter(t -> t.getStatus() == Status.CONCLUIDA)
                .toList();

        assertEquals(1, tasksPendentes.size());
        assertEquals(1, tasksConcluidas.size());
        assertEquals("Tarefa Concluída", tasksConcluidas.get(0).getTitulo());
    }

}
