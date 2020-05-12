package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.configuration.JwtTokenProvider;
import br.com.murilo.agenda.dto.request.UserCredential;
import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.dto.response.TokenDTO;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.User;
import br.com.murilo.agenda.facade.UserFacade;
import br.com.murilo.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> signing(@RequestBody UserCredential credential){

        String username = credential.getEmail();
        String password = credential.getPassword();

        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        Optional<User> userOptional = userRepository.findByEmail(username);
        String token;
        if (userOptional.isPresent()){
            token = tokenProvider.createToken(username, userOptional.get().getRoleName());
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

        TokenDTO tokenDTO = new TokenDTO(username, token);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = userFacade.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
