package com.example.aopdemo.service;

import com.example.aopdemo.exception.GeneralNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {
    private final Logger LOG  =  LoggerFactory.getLogger(getClass());

    public void runGeneralService(){

        LOG.info("********** GeneralService | runGeneralService is called **********");
        throw new GeneralNotFoundException("Exception in generalService!");
    }
}
