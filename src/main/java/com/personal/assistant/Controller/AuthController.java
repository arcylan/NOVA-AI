package com.personal.assistant.Controller;
import com.personal.assistant.Config.JwtUtil;
import com.personal.assistant.Model.User;
import com.personal.assistant.Repository.UserRepository;
import com.personal.assistant.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody User user){

        if(user.getPassword() == null || user.getUsername() == null
        || user.getPassword().isBlank() || user.getUsername().isBlank()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Credentials Cannot Be null");
        }
        if(userRepository.findByUsername(user.getUsername())  != null){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User Already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        //emailService.SendWelcomeMail(user.getEmail());

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Registerd");


    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        User dbUser = userRepository.findByUsername(user.getUsername());

        if(dbUser != null &&
        passwordEncoder.matches(user.getPassword(),dbUser.getPassword()))
        {
            return jwtUtil.generateToken(dbUser.getUsername());
        }

return "Invalid User";
    }
}
