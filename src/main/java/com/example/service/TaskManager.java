package com.example.service;
 
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.model.Task;
import com.example.model.Task.Status;
import com.example.repository.InMemoryTaskRepository;
import com.example.repository.TaskRepository;
 
public class TaskManager {
    private final TaskRepository repository;
 
    public TaskManager() {
        this.repository = new InMemoryTaskRepository();
    }
 
    public void adicionarTarefa(String titulo, String descricao, LocalDate dataConclusao) {
        Task task = new Task(titulo, descricao, dataConclusao);
        repository.salvar(task);
        System.out.println("Tarefa adicionada: " + task);
    }
 
    public void listarTarefas() {
        List<Task> tasks = repository.buscarTodas();
        tasks.sort((t1, t2) -> t1.getDataCriacao().compareTo(t2.getDataCriacao()));
        tasks.forEach(System.out::println);
    }
 
    public void atualizarTarefa(UUID id, String novoTitulo, String novaDescricao, LocalDate novaDataConclusao, Status novoStatus) {
        Task task = repository.buscarTodas().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (task != null) {
            task.setTitulo(novoTitulo);
            task.setDescricao(novaDescricao);
            task.setDataConclusao(novaDataConclusao);
            task.setStatus(novoStatus);
            repository.atualizar(task);
            System.out.println("Tarefa atualizada: " + task);
        } else {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
        }
    }
 
    public void removerTarefa(UUID id) {
        repository.remover(id);
        System.out.println("Tarefa removida com ID: " + id);
    }
 
    public void listarTarefasPorStatus(Status status) {
        List<Task> tasks = repository.buscarTodas();
        tasks.stream()
                .filter(task -> task.getStatus() == status)
                .forEach(System.out::println);
    }
}