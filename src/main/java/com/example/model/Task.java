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
 
    // Construtor, getters, setters e validações aqui
    // (Permanece igual ao exemplo anterior)



    //
    enum Status {
        PENDENTE,
        EM_PROGRESSO,
        CONCLUIDA
    }
     
}
