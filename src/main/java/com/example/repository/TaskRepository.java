package com.example.repository;

import java.util.List;
import java.util.UUID;

import com.example.model.Task;
 
public interface TaskRepository {
 
    void salvar(Task task);
 
    List<Task> buscarTodas();
 
    void atualizar(Task task);
 
    void remover(UUID id);
}