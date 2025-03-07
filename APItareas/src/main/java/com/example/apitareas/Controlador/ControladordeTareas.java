package com.example.apitareas.Controlador;

import com.example.apitareas.Entrega.EntregadeTarea;
import com.example.apitareas.Servicio.ServiciodeTareas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
public class ControladordeTareas {
    private final ServiciodeTareas serviciodeTareas;

    // Inyección de dependencia del servicio de tareas
    public ControladordeTareas(ServiciodeTareas serviciodeTareas) {
        this.serviciodeTareas = serviciodeTareas;
    }

    // Endpoint para obtener todas las tareas
    @GetMapping
    public List<EntregadeTarea> getAllTareas() {
        return serviciodeTareas.getAllTareas();
    }

    // Endpoint para obtener una tarea por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTareaById(@PathVariable Long id) {
        return serviciodeTareas.getTareaById(id);
    }

    // Endpoint para crear una nueva tarea
    @PostMapping
    public ResponseEntity<?> createTarea(@RequestBody EntregadeTarea tarea) {
        return serviciodeTareas.addTarea(tarea);
    }

    // Endpoint para actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarea(@PathVariable Long id, @RequestBody EntregadeTarea tarea) {
        return serviciodeTareas.updateTarea(id, tarea);
    }

    // Endpoint para eliminar una tarea por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long id) {
        return serviciodeTareas.deleteTarea(id);
    }
}

