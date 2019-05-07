package me.cxis.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jvm")
public class JvmController {

    private static final int _1M = 1024 * 1024;

    @RequestMapping(value = "/allocateMem", method = RequestMethod.GET)
    public String allocateMemory(@RequestParam int number, @RequestParam int size) {
        for (int i = 0; i < number; i++) {
            byte[] memory = new byte[size * _1M];
        }
        return "success";
    }
}
