package com.ihai.accounting.tax.controller;

import com.ihai.accounting.tax.service.IncomeTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/incomeTaxs")
public class IncomeTaxController {

    @Autowired
    private IncomeTaxService incomeTaxService;

    @RequestMapping(value = {"/{type}"}, method = RequestMethod.GET)
    ResponseEntity byType(@PathVariable Byte type) {
        if (!incomeTaxService.existsByType(type))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404

        return new ResponseEntity<>(incomeTaxService.findByType(type), HttpStatus.OK); // HTTP 200
    }
}
