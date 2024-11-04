package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.model.Task;
 
public class InMemoryTaskRepository implements TaskRepository {
 
    private final List<Task> tasks = new ArrayList<>();
 
    @Override
    public void salvar(Task task) {
        tasks.add(task);
    }
 
    @Override
    public List<Task> buscarTodas() {
        return new ArrayList<>(tasks);
    }
 
    @Override
    public void atualizar(Task task) {
        tasks.replaceAll(t -> t.getId().equals(task.getId()) ? task : t);
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
}