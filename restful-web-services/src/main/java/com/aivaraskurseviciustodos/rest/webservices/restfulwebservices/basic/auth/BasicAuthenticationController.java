package com.aivaraskurseviciustodos.rest.webservices.restfulwebservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BasicAuthenticationController {

  @GetMapping(path = "/basicauth")
  public AuthenticationBean helloWorldBean() {
    return new AuthenticationBean("Authenticated");
  }
}
