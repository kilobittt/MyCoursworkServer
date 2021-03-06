package net.coursework.EasyStat.rest;


import net.coursework.EasyStat.dto.AuthenticationRequestDto;
import net.coursework.EasyStat.dto.NewUserDto;
import net.coursework.EasyStat.model.User;
import net.coursework.EasyStat.security.jwt.JwtTokenProvider;
import net.coursework.EasyStat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<NewUserDto> signup(@RequestBody NewUserDto newUserDto){
        User registeredUser = userService.register(newUserDto.toUser());
        newUserDto.setId(registeredUser.getId());

        return new ResponseEntity<>(newUserDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try{
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null){
                throw new UsernameNotFoundException("User with username " + username + " not found");
            }

            String token = jwtTokenProvider.CreateToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", username);
            response.put("token", token);
            response.put("firstName", user.getFirstName());
            response.put("lastname", user.getLastName());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");

        }
    }
}
