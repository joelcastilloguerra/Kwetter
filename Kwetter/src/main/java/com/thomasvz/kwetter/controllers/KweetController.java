package com.thomasvz.kwetter.controllers;

import com.thomasvz.kwetter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/kweet")
public class KweetController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


}
