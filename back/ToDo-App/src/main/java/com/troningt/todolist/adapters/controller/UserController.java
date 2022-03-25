package com.troningt.todolist.adapters.controller;

import com.troningt.todolist.adapters.controller.dto.UserDTO;
import com.troningt.todolist.adapters.controller.dto.model.Response;
import com.troningt.todolist.adapters.util.Utilities;
import com.troningt.todolist.application.port.in.user.*;
import com.troningt.todolist.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {
	@Autowired
	private GetUserService getUserService;

	@Autowired
	private GetAllUsersService getAllUsersService;

	@Autowired
	private GetUserByEmailService getUserByEmailService;

	@Autowired
	private UpdateUserService updateUserService;

	@Autowired
	private DeleteUserService deleteUserService;

	@Autowired
	private DeleteAllUsersService deleteAllUsersService;

	@Autowired
	private CreateUserService createUserService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	Utilities<UserDTO> utilities;

	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO) {
		createUserService.createUser(modelMapper.map(userDTO, User.class));
		Response response = utilities.generateResponse(userDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/byEmail")
	public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
		return new ResponseEntity<>(modelMapper.map(getUserByEmailService.getUserByEmail(email), UserDTO.class), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
		return new ResponseEntity<>(modelMapper.map(getUserService.getUser(id), UserDTO.class), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody @Valid UserDTO task) {
		updateUserService.updateUser(id, modelMapper.map(task, User.class));
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable int id) {
		deleteUserService.deleteUser(id);
		return new ResponseEntity<>(utilities.generateResponse("User eliminated successfully", null), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteAllUsers() {
		deleteAllUsersService.deleteAllUsers();
		return new ResponseEntity<>(utilities.generateResponse("All Users eliminated successfully", null), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(getAllUsersService.getUsers().stream().map(UserDTO::of).collect(Collectors.toList()), HttpStatus.OK);
	}
}
