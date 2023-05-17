package me.cxis.mongo.controller;

import me.cxis.mongo.model.dao.User;
import me.cxis.mongo.model.dao.UserNew;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/create")
    public String create(@RequestParam String firstName, @RequestParam Integer age) {
        User user = new User();
        user.setFirstName(firstName);
        user.setAge(age);
        mongoTemplate.insert(user);
        return user.getId();
    }

    @GetMapping("/create/new")
    public String createNew(@RequestParam String firstName, @RequestParam Integer age) {
        UserNew user = new UserNew();
        user.setFirstName(firstName);
        user.setAge(age);
        user.setDeleted(false);
        mongoTemplate.insert(user);
        return user.getId();
    }

    @GetMapping("/delete")
    public Boolean delete(@RequestParam String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        update.set("deleted", true);
        mongoTemplate.updateFirst(query, update, UserNew.class);

        return true;
    }

    @GetMapping("query")
    public List<UserNew> query(@RequestParam Boolean deleted) {
        Query query = new Query(Criteria.where("deleted").is(deleted));
        List<UserNew> users = mongoTemplate.find(query, UserNew.class);
        return users;
    }
}
