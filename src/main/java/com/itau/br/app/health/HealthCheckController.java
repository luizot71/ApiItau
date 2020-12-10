package com.itau.br.app.health;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = RequestMethod.POST)
@Api(tags = "health-check-controller")
public class HealthCheckController {

    @ApiOperation(value = "health-check")
    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {

        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }
}
