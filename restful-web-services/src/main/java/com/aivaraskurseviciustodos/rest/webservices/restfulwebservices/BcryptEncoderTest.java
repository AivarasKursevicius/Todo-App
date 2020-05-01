package com.aivaraskurseviciustodos.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {
  public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedString = encoder.encode("aivkur");
    String encodedString2 = encoder.encode("password@!23@~!");
    System.out.println("aivkur : " + encodedString);
    System.out.println("password@!23@~! : " + encodedString2);
  }
}
