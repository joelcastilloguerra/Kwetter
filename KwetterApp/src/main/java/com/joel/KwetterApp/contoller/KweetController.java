package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.KweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kweet")
public class KweetController {

    @Autowired
    private KweetService kweetService;

    @RequestMapping(method= RequestMethod.POST, value = "/add")
    public void add(@RequestBody Kweet kweet){

        kweetService.add(kweet);

    }



}
