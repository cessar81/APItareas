package com.example.apitareas.Servicio;

import com.example.apitareas.Entrega.EntregadeTarea;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiciodeTareas {

    // Lista en memoria para almacenar las tareas
    private final List<EntregadeTarea> tareas = new ArrayList<>();
    private Long currentId = 1L; // ID autoincremental

    // Constructor: agrega algunas tareas de ejemplo
    public ServiciodeTareas() {
        tareas.add(new EntregadeTarea(currentId++, "Comprar alimentos", "Comprar pan, leche y huevos", false));
        tareas.add(new EntregadeTarea(currentId++, "Estudiar Java", "Repasar conceptos de Spring Boot", false));
        tareas.add(new EntregadeTarea(currentId++, "Hacer ejercicio", "Correr 5 km en el parque", false));
    }

    // Obtiene la lista completa de tareas
    public List<EntregadeTarea> getAllTareas() {
        return tareas;
    }

    // Obtiene una tarea por su ID
    public ResponseEntity<?> getTareaById(Long id) {
        Optional<EntregadeTarea> tarea = tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        return tarea.isPresent() ? ResponseEntity.ok(tarea.get())
                : ResponseEntity.badRequest().body(Map.of("Error", "Tarea con ID " + id + " no encontrada"));
    }

    // Agrega una nueva tarea
    public ResponseEntity<?> addTarea(EntregadeTarea tarea) {
        // Si se envía un ID en la petición, se puede verificar si ya existe
        boolean exists = tareas.stream()
                .anyMatch(t -> t.getId() != null && t.getId().equals(tarea.getId()));

        if (exists) {
            return ResponseEntity.badRequest().body(Map.of("Error", "La tarea con ID " + tarea.getId() + " ya existe"));
        }
        tarea.setId(currentId++);
        tareas.add(tarea);
        return ResponseEntity.ok(Map.of("Mensaje", "Tarea agregada exitosamente", "tarea", tarea));
    }

    // Actualiza una tarea existente
    public ResponseEntity<?> updateTarea(Long id, EntregadeTarea tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(id)) {
                tarea.setId(id);
                tareas.set(i, tarea);
                return ResponseEntity.ok(Map.of("Mensaje", "Tarea actualizada exitosamente", "tarea", tarea));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("Error", "Tarea con ID " + id + " no existe"));
    }

    // Elimina una tarea por su ID
    public ResponseEntity<?> deleteTarea(Long id) {
        Optional<EntregadeTarea> tareaToRemove = tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (tareaToRemove.isPresent()) {
            tareas.remove(tareaToRemove.get());
            return ResponseEntity.ok(Map.of("Mensaje", "Tarea eliminada exitosamente"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("Error", "Tarea con ID " + id + " no encontrada"));
        }
    }
}

