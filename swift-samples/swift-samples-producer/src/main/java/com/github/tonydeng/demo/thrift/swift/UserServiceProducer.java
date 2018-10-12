package com.github.tonydeng.demo.thrift.swift;


import org.apache.thrift.TException;
import org.springframework.stereotype.Service;


@Service("userServiceProducer")
public class UserServiceProducer implements UserService {

    @Override
    public User getUser(int id) throws TException {
        User user = new User();
        user.setId(id);
        user.setAge(18);
        user.setName("bomb");
        return user;
    }
}
