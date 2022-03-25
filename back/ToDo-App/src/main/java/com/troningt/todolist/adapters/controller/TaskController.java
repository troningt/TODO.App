package com.troningt.todolist.adapters.controller;

import com.troningt.todolist.adapters.controller.dto.TaskDTO;
import com.troningt.todolist.adapters.controller.dto.model.Response;
import com.troningt.todolist.adapters.util.Utilities;
import com.troningt.todolist.application.port.in.task.*;
import com.troningt.todolist.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
//use DTO for input and output return
public class TaskController{
	@Autowired
	private GetTaskService getTaskService;

	@Autowired
	private GetAllTasksService getAllTasksService;

	@Autowired
	private GetTaskByDescriptionService getTaskByDescriptionService;

	@Autowired
	private UpdateTaskService updateTaskService;

	@Autowired
	private DeleteTaskService deleteTaskService;

	@Autowired
	private DeleteAllTasksService deleteAllTasksService;

	@Autowired
	private CreateTaskService createTaskService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	Utilities<TaskDTO> utilities;

	@PostMapping
	@ResponseBody
	public ResponseEntity<Response> createTask(@Valid @RequestBody TaskDTO taskDTO) {
		createTaskService.createTask(modelMapper.map(taskDTO, Task.class));
		Response response = utilities.generateResponse(taskDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/byDescription")
	public ResponseEntity<List<TaskDTO>> getTaskByDescription(@RequestParam String description) {
		return new ResponseEntity<>(getTaskByDescriptionService.getTaskByDescription(description).stream().map(TaskDTO::of).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable int id) {
		return new ResponseEntity<>(modelMapper.map(getTaskService.getTask(id), TaskDTO.class), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody @Valid TaskDTO task) {
		updateTaskService.updateTask(id, modelMapper.map(task, Task.class));
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteTask(@PathVariable int id) {
		deleteTaskService.deleteTask(id);
		return new ResponseEntity<>(utilities.generateResponse("Task eliminated successfully", null), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteAllTasks() {
		deleteAllTasksService.deleteAllTasks();
		return new ResponseEntity<>(utilities.generateResponse("All Tasks eliminated successfully", null), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		return new ResponseEntity<>(getAllTasksService.getTasks().stream().map(TaskDTO::of).collect(Collectors.toList()), HttpStatus.OK);
	}
}
