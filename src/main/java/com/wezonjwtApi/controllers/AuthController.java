package com.wezonjwtApi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wezonjwtApi.models.User;
import com.wezonjwtApi.models.ERole;
import com.wezonjwtApi.models.Role;
import com.wezonjwtApi.payload.request.LoginRequest;
import com.wezonjwtApi.payload.request.SignupRequest;
import com.wezonjwtApi.payload.response.JwtResponse;
import com.wezonjwtApi.payload.response.MessageResponse;
import com.wezonjwtApi.repository.UserRepository;
import com.wezonjwtApi.repository.RoleRepository;
import com.wezonjwtApi.security.jwt.JwtUtils;
import com.wezonjwtApi.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(roles,
				                                 jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getLast_name(),
												 userDetails.getFirst_name(),
												 userDetails.getAddress_country_code(),
												 userDetails.getState_or_province(),
												 userDetails.getCity(),
												 userDetails.getPostal_code(),
												 userDetails.getAddress(),
												 userDetails.getMobile_number(),
												 userDetails.getBirth_date(),
												 userDetails.getBirth_place(),
												 userDetails.getBirth_country_code(),
												 userDetails.getBank_account_number(),
												 userDetails.getBank_number(),
												 userDetails.getBank_phone_number(),
												 userDetails.getTax_id(),
												 userDetails.getTax_id_name(),
												 userDetails.getOccupation(),
												 userDetails.getEmployer_name(),
												 userDetails.getEmployer_address(),
												 userDetails.getLanguage_code(),
												 userDetails.getId_type(),
												 userDetails.getId_country_code(),
												 userDetails.getId_issue_date(),
												 userDetails.getId_expiration_date(),
												 userDetails.getId_number(),
												 userDetails.getPhoto_id_front(),
												 userDetails.getPhoto_id_back(),
												 userDetails.getNotary_approval_of_photo_id(),
												 userDetails.getIp_address(),
												 userDetails.getPhoto_proof_residence()
												 ));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Username n'est pas disponible"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Email déjà utilisé"));
		}

		// CREER UN COMPTE UTILISATEUR
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getLast_name(),
							 signUpRequest.getFirst_name(),
							 signUpRequest.getAddress_country_code(),
							 signUpRequest.getState_or_province(),
							 signUpRequest.getCity(),
							 signUpRequest.getPostal_code(),
							 signUpRequest.getAddress(),
							 signUpRequest.getMobile_number(),
							 signUpRequest.getBirth_date(),
							 signUpRequest.getBirth_place(),
							 signUpRequest.getBirth_country_code(),
							 signUpRequest.getBank_account_number(),
							 signUpRequest.getBank_number(),
							 signUpRequest.getBank_phone_number(),
							 signUpRequest.getTax_id(),
							 signUpRequest.getTax_id_name(),
							 signUpRequest.getOccupation(),
							 signUpRequest.getEmployer_name(),
							 signUpRequest.getEmployer_address(),
							 signUpRequest.getLanguage_code(),
							 signUpRequest.getId_type(),
							 signUpRequest.getId_country_code(),
							 signUpRequest.getId_issue_date(),
							 signUpRequest.getId_expiration_date(),
							 signUpRequest.getId_number(),
							 signUpRequest.getPhoto_id_front(),
							 signUpRequest.getPhoto_id_back(),
							 signUpRequest.getNotary_approval_of_photo_id(),
							 signUpRequest.getIp_address(),
							 signUpRequest.getPhoto_proof_residence()
							 
							 );

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Inscription réussie!"));
	}
}
