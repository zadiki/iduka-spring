package com.pos.iduka.controller;


import com.pos.iduka.model.AuthRequest;
import com.pos.iduka.model.UserInfo;
import com.pos.iduka.service.JwtService;
import com.pos.iduka.service.UserInfoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Tag(description = "iDuka inventory management system user controller",name = "iDuka")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserInfo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Such user cannot be added.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/addNewUser")
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo) {
         return new ResponseEntity<>(userService.addUser(userInfo), HttpStatus.OK);
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            var userInfo = userService.loadUserByEmail(authRequest.getEmail());
            return jwtService.generateToken(userInfo,authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
