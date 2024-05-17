package net.codejava;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
	private UserService service;
	private ModelMapper modelMapper;

	protected UserApiController(UserService service, ModelMapper mapper) {
		this.service = service;
		this.modelMapper = mapper;
	}

	@PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid User user) {
        // Check if tags field is empty
        if (user.getTags() == null || user.getTags().isEmpty()) {
            // Handle the case where tags are not provided
            return ResponseEntity.badRequest().body("Tags must be provided");
        }

        // Proceed with saving the user
        User persistedUser = service.add(user);
        UserDTO userDto = entity2Dto(persistedUser);

        URI uri = URI.create("/api/v1/users/" + userDto.getId());

        return ResponseEntity.created(uri).body(userDto);
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		try {
			User user = service.get(id);
			return ResponseEntity.ok(entity2Dto(user));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<?> list() {
		List<User> listUsers = service.list();
		if (listUsers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list2Dto(listUsers));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid User user) {
		try {
			user.setId(id);
			User updatedUser = service.update(user);
			return ResponseEntity.ok(entity2Dto(updatedUser));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	private UserDTO entity2Dto(User entity) {
		return modelMapper.map(entity, UserDTO.class);
	}

	private List<UserDTO> list2Dto(List<User> listUsers) {
		return listUsers.stream().map(this::entity2Dto).collect(Collectors.toList());
	}
}
