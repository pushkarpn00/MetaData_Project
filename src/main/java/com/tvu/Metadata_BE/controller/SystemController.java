package com.tvu.Metadata_BE.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "this is used to provide system info")
public class SystemController {

    @Value("${buildVersion}")
    private String buildVersion;

    @GetMapping("base/version")
    public String baseVersion() {
        return buildVersion + "|";
    }
}
