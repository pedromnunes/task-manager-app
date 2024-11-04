package com.example.model;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private Status status;

    // Construtor, getters, setters e validações
    public Task(String titulo, String descricao, LocalDate dataConclusao) {

        if (titulo == null || titulo.isEmpty() || descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Título e descrição não podem ser vazios.");
        }

        LocalDate hoje = LocalDate.now();
        if (dataConclusao != null && dataConclusao.isBefore(hoje)) {
            throw new IllegalArgumentException("Data de conclusão não pode ser anterior à data de criação.");
        }

        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = hoje;
        this.dataConclusao = dataConclusao;
        this.status = Status.PENDENTE;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {

        if (dataConclusao != null && dataConclusao.isBefore(this.dataCriacao)) {
            throw new IllegalArgumentException("Data de conclusão não pode ser anterior à data de criação.");
        }
        this.dataConclusao = dataConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +

                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataConclusao=" + dataConclusao +
                ", status=" + status +
                '}';
    }

    //
    public enum Status {
        PENDENTE,
        EM_PROGRESSO,
        CONCLUIDA
    }

}
