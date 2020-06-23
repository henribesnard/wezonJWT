package com.wezonjwtApi.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.wezonjwtApi.exception.ResourceNotFoundException;
import com.wezonjwtApi.models.User;
import com.wezonjwtApi.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	// RECUPERER TOUS LES COMPTES 
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//RECUPERER UN COMPTE EN FONCTION DE L'ID
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
    		 throws ResourceNotFoundException {
        User user = userRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Aucun compte trouver avec l'ID :: " + id));
        return ResponseEntity.ok().body(user);
    }
    
    //MODIFIER UN USER
    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
         @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Aucun compte trouver avec l'ID :: " + id));
        
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setRoles(userDetails.getRoles());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    //SUPPRIMER UN COMPTE
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
         throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("Aucun compte trouver avec le numéro :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Suppression", Boolean.TRUE);
        return response;
        
    }
    

}
