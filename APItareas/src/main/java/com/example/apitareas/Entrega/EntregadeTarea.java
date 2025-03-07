package com.example.apitareas.Entrega;

public class EntregadeTarea {
    private Long id;               // Identificador único de la tarea
    private String title;          // Título de la tarea
    private String description;    // Descripción de la tarea
    private Boolean completed;     // Estado de la tarea (completada o no)

    // Constructor parametrizado
    public EntregadeTarea(Long id, String title, String description, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Constructor vacío necesario para la conversión JSON → Objeto
    public EntregadeTarea() {
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "EntregadeTarea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}

