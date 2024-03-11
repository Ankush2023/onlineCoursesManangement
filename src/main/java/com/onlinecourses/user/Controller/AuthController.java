package com.onlinecourses.user.Controller;

import com.onlinecourses.user.config.TokenProvider;
import com.onlinecourses.user.service.IUserAcessService;
import com.onlinecourses.user.model.User;
import com.onlinecourses.user.model.requests.UserLoginRequest;
import com.onlinecourses.user.model.response.EntityResponse;
import com.onlinecourses.user.model.response.LoginResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Api(value = "Auth controller ", description = "Auth Controller", tags = {"Auth Controller"})
public class AuthController {

    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserAcessService iUserAcessService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){
        try {
            final Authentication authentication = authenticate(userLoginRequest.getEmail().trim(), userLoginRequest.getPassword().trim());
            final UserDetails candidateDetails = iUserAcessService.loadUserByUsername(userLoginRequest.getEmail().trim());
            User team = iUserAcessService.findByEmail(candidateDetails.getUsername());


            String token = "";
            try {
                token = jwtTokenUtil.generateToken(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUserId(team.getUserId());

            return new ResponseEntity<>(new EntityResponse(loginResponse, 0), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.UNAUTHORIZED);

        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (Exception e) {
            throw new Exception("Please Check Username and Password", e);
        }

    }
}
