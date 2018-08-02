package com.tone.controller;

import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiang.liu
 * @date 2018/8/1
 */
@Log4j2
@RestController
@RequestMapping("/owners/{ownerId}")
public class RelativePathUriTemplateController {

    @RequestMapping("/pets/{petId}")
    public String findPet(@PathVariable String ownerId, @PathVariable String petId) {
        return ownerId + "_" + petId;
    }

    @RequestMapping(value = "/pets/{filters}", method = RequestMethod.GET)
    public void findPet(@PathVariable String ownerId,
            @MatrixVariable(pathVar = "filters") Map<String, String> petMatrixVars) {
        System.out.println(ownerId);
        System.out.println(petMatrixVars);
    }
}
