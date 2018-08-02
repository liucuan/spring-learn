package com.tone.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/31
 */
@Log4j2
@RestController
public class AppRestController {

    @RequestMapping("/")
    public String index() {
        log.info("request index.");
        return "index";
    }

    @RequestMapping("/day/{day}")
    public String p1(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date day) {
        log.info("request /p1. day={}", new SimpleDateFormat("yyyy-MM-dd").format(day));
        return "p1";
    }

    @RequestMapping("/ve/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public String handle(@PathVariable String symbolicName, @PathVariable String version,
            @PathVariable String extension) {
        return symbolicName + "-" + version + extension;
    }

    @RequestMapping("/products/{category}/{filters}")
    public String products(@PathVariable("category") String category,
            @MatrixVariable(pathVar = "filters") Map<String, List<String>> filterParams) {
        System.out.println(filterParams);
        return category + "_" + filterParams;
    }
}
