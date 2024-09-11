package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.PersonsBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonsController {
    @Autowired
    private PersonsBusiness personsBusiness;


}
