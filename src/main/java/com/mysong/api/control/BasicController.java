package com.mysong.api.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysong.api.model.User;
import com.mysong.api.repository.UserRepository;
import com.mysong.api.util.JwtBody;
import com.mysong.api.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@CrossOrigin(origins = "*", maxAge = 3600,
        allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token", "authorization", "content-type"})
public class BasicController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepository;

    public User getUserLogado() throws IOException {
        String jwt = request.getHeader("authorization").substring(7);
        JwtBody jwtBody = new ObjectMapper().readValue(new JwtService().decode(jwt), JwtBody.class);
        User user = userRepository.findByEmail(jwtBody.getSub());
        return user;
    }

}
