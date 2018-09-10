package fr.jenvrin.simon.controller;

import fr.jenvrin.simon.exception.ResourceNotFoundException;
import fr.jenvrin.simon.model.Task;
import fr.jenvrin.simon.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Example;
import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Request to get the list of all the tasks.
     * @param pageable
     * @param complete - query parameters, if null we get all the tasks
     * @return page of task
     */
    @GetMapping("/tasks")
    public Page<Task> getTasks(Pageable pageable,
                               @RequestParam(name="complete", required = false) Boolean complete) {

        // 'Complete' in the request query can be null. In that case, we get the list of all the tasks
        Task t = new Task();
        if(complete != null) {
            t.setComplete(complete); // Get only the completed or uncompleted tasks
        }
        Example<Task> query = Example.of(t);

        return taskRepository.findAll(query,pageable);
    }

    /**
     * Add, Create a new task
     * @param task
     * @return
     */
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }

    /**
     * Get one task
     * @param taskId
     * @return task
     */
    @GetMapping("/tasks/{taskId}")
    public Task getOneTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }

    /**
     * Update one task
     * @param taskId
     * @param taskRequest
     * @return
     */
    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId,
                                   @Valid @RequestBody Task taskRequest) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setLabel(taskRequest.getLabel());
                    task.setComplete(taskRequest.getComplete());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }

    /**
     * Delete one task
     * @param taskId
     * @return
     */
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }
}
