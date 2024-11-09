package com.example.aopdemo.rest;

import com.example.aopdemo.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/generalService")
@RequiredArgsConstructor
public class GeneralController {
    private final GeneralService generalService;
    private final Logger LOG  =  LoggerFactory.getLogger(getClass());
    @PostMapping
    private void getGeneral(){
        LOG.info("In general Controller");
        generalService.runGeneralService();
    }
}
