package com.hxd.controller;

import com.google.gson.Gson;
import com.hxd.constraint.ResponseEntity;
import com.hxd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JedisSentinelPool jedisSentinelPool;


    @PostMapping()
    public ResponseEntity<Void> insertUser(@RequestBody User user){
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set(user.getUsername(), new Gson().toJson(user));
        System.out.println(jedisSentinelPool.getCurrentHostMaster());
        System.out.println("IP："+jedis.getClient().getHost());
        System.out.println("端口："+jedis.getClient().getPort());

        jedis.close();
        return new ResponseEntity(200,"操作成功","");
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser(){
        Jedis jedis = jedisSentinelPool.getResource();
        Gson gson = new Gson();
        List<User> list = new ArrayList<>();
        jedis.keys("hxd*").forEach(key -> {
            String value = jedis.get(key);
            list.add(gson.fromJson(value,User.class));
        });
        jedis.close();
        return new ResponseEntity(200,"操作成功",list);
    }


}
