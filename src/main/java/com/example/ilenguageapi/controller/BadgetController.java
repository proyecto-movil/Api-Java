package com.example.ilenguageapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "https://ilanguage-api.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class BadgetController {
}
