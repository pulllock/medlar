package me.cxis.spring.resource;

import org.springframework.stereotype.Repository;

@Repository
public class ResourceDao {

    public String getName() {
        return "resource";
    }
}
