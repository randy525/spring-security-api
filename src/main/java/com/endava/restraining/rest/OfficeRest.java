package com.endava.restraining.rest;

import com.endava.restraining.service.OfficeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/office")
public class OfficeRest {
    private final OfficeService officeService;
    @PostMapping("/add/{id}")
    public ResponseEntity<Object> addNewOffice(@PathVariable Long id ){
        try {
            officeService.add(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bylocation")
    public ResponseEntity<Object> getAllLocations(@RequestParam(required = true) String name) {
        try {
            return new ResponseEntity<>(officeService.findByCity(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
