package com.ks.webservice.impl;

import com.ks.DateUtils;
import com.ks.webservice.UserService;
import com.ks.webservice.resp.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebService(
        serviceName = "userService"
        ,
        targetNamespace = "http://webservice.ks.com"//与接口相同，不然找不到
)
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getDefUser() throws ParseException {
        User user = new User();
        user.setAge(25);
        user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-05-20"));
        user.setName("KiisawS");
        return user;
    }

    @Override
    public List<User> getList(Integer size) {
        if (size == null) {
            throw new RuntimeException("size must not be null!");
        }
        List<User> userList = new ArrayList<>(size);
        try {
            for (int i = 0; i < size; i++) {
                userList.add(new User("user" + i,25, parse(i)));
            }
        } catch (ParseException e) {
            //
        }
        return userList;
    }

    Date parse(int i) throws ParseException {
        Date date = DateUtils.parse("1993-05-20", DateUtils.YYYY_MM_DD);
        Random random = new Random(60);
        return new Date(date.getTime()+ i * 1000 * random.nextInt());
    }
}
