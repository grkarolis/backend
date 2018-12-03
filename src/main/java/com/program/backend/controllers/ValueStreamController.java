package com.program.backend.controllers;

import com.program.backend.beans.response.valuestream.ValueStreamResponse;
import com.program.backend.services.ValueStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@CrossOrigin(origins = "*")
@RequestMapping(value = "/valuestream")
public class ValueStreamController {

    @Autowired
    private ValueStreamService valueStreamService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ValueStreamResponse> getAll() {
        return valueStreamService.getAllValueStreamEntityResponseList();
    }
}
