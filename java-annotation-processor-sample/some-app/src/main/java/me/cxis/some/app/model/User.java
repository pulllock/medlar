package me.cxis.some.app.model;

import me.cxis.annotation.processor.custom.Getter;

import java.util.List;

@Getter
public class User {

    private Long id;

    private String name;

    private Boolean active;

    private List<String> phones;

}
