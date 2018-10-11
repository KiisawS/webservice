package com.ks.webservice.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;

    private Date birth;

    @Override
    public String toString() {
        return "User[name="+name+",age="+age+",birth="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(birth)+"]";
    }
}
