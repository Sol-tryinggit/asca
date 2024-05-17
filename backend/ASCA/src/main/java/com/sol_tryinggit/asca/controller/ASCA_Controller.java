package com.sol_tryinggit.asca.controller;

import com.sol_tryinggit.asca.dto.AccountDetails;
import com.sol_tryinggit.asca.dto.ResponseDTO;
import com.sol_tryinggit.asca.services.ASCA_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acsa-api")
public class ASCA_Controller {

    @Autowired
    private ASCA_services services;

    //url= http://localhost:9000/acsa-api/audit-account
    @PostMapping("/audit-account")
    public ResponseDTO startAudit(@RequestBody AccountDetails accountDetails) throws InterruptedException {

        ResponseDTO response = new ResponseDTO();
        response.setPath(services.startAzureAudit(accountDetails));
        return response;
    }
}
