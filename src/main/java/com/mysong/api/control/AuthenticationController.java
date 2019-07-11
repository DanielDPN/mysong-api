package com.mysong.api.control;

import com.mysong.api.model.RegisterCredentials;
import com.mysong.api.model.Role;
import com.mysong.api.model.User;
import com.mysong.api.service.RoleService;
import com.mysong.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController extends BasicController {

    private final UserService userService;
    private final RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Método para registrar novo usuário
     * @param u
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterCredentials u) {
        User user = userService.findByEmail(u.getEmail());
        final Map<String, Object> result = new HashMap<>();
        try {
            if (user != null) {
                result.put("success", false);
                result.put("error", "E-mail já cadastrado");
                result.put("body", null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            } else {
                user = new User();
                user.setEmail(u.getEmail());
                user.setName(u.getUsername());
                user.setPassword(passwordEncoder.encode(u.getPassword()));
                user.setEnabled(true);

                Role role = roleService.findByRole("ROLE_DEFAULT");

                Set<Role> roles = new HashSet<>();
                roles.add(role);

                user.setRoles(roles);
                userService.save(user);

                result.put("success", true);
                result.put("error", null);
                result.put("body", user);
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
