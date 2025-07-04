package com.example.taskmanager.services;

import com.example.taskmanager.Command;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskDTO;
import com.example.taskmanager.model.UpdateTaskCommand;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskService implements Command<UpdateTaskCommand, TaskDTO> {

    private TaskRepository taskRepository;

    public UpdateTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<TaskDTO> execute(UpdateTaskCommand command) {
        Optional<Task> taskOptional = taskRepository.findById(command.getId());
        if (taskOptional.isPresent()) {
            Task task = command.getTask();
            task.setId(command.getId());

//            ProductValidator.execute(product);
            taskRepository.save(task);
            return ResponseEntity.ok(new TaskDTO(task));
        }

//        throw new TaskNotFoundException();

        throw new IllegalArgumentException("Task not found");
    }
}
