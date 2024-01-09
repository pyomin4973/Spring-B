package hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;

@RestController
public class RootController {

    @GetMapping("/health")
    public String healthCheck(){
        return "I'm healthy";
    }
}
