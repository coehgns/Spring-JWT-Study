package com.example.springjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {

        return "AdminController";
    }

}
