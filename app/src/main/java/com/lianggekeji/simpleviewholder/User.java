package com.lianggekeji.simpleviewholder;

/**
 * Created by Yi on 16/5/16.
 */
public class User {
    String name;
    String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
