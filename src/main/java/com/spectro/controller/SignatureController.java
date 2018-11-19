package com.spectro.controller;

import com.spectro.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * Created by farhans on 11/18/18.
 */
@Controller
public class SignatureController {

    @Autowired
    SignatureService signatureService;

    @RequestMapping(value = "/getSignature", method = RequestMethod.POST)
    public @ResponseBody String getSignature(@RequestParam String parameters) throws Exception {
        return signatureService.getSignature(parameters);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody Boolean validate(@RequestParam String parameters,@RequestParam String responseSign) throws Exception {
        return signatureService.validate(parameters,responseSign);
    }
}
