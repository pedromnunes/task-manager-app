package com.example;

import java.time.LocalDate;
import java.util.UUID;

import com.example.model.Task.Status;
import com.example.service.TaskManager;

public class TaskManagerAppApplication {

	public static void main(String[] args) {
		System.out.println("Executou ...");

		TaskManager taskManager = new TaskManager();

		// Adiciona tarefas
		taskManager.adicionarTarefa("Estudar Java", "Revisar POO", LocalDate.now().plusDays(2));
		taskManager.adicionarTarefa("Ler Livro", "Ler capítulo 3", LocalDate.now().plusDays(4));

		// Listar todas as tarefas
		taskManager.listarTarefas();

		// Atualizar tarefa
		UUID id = taskManager.repository.buscarTodas().get(0).getId();
		taskManager.atualizarTarefa(id, "Estudar Java Avançado", "Revisar Streams", LocalDate.now().plusDays(5),
				Status.EM_PROGRESSO);

		// Listar por status
		taskManager.listarTarefasPorStatus(Status.EM_PROGRESSO);

		// Remover tarefa
		taskManager.removerTarefa(id);
	}

}
