package com.itau.br.app.controller;

import com.itau.br.app.config.Parameter;
import com.itau.br.app.service.ParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@Api(tags = "parameter-controller")
@RequestMapping("/api/v1")
public class ParameterController {

    private ParameterService service;

    @Autowired
    public ParameterController(ParameterService service) {
        this.service = service;
    }

    @ApiOperation(value = "get-parameter")
    @GetMapping(value = "/parameter/{parameterName}", produces = "application/json")
    public ResponseEntity<Parameter> getFeature(@PathVariable String parameterName) {

        return ResponseEntity.ok(service.getParameter(parameterName));
    }
}
