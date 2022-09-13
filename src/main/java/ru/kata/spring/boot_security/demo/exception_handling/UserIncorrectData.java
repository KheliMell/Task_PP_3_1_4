package ru.kata.spring.boot_security.demo.exception_handling;

//Класс, объекты которого ответсвенны за json
public class UserIncorrectData {
    private String info;

    public UserIncorrectData(String info) {
        this.info = info;
    }

    public UserIncorrectData() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
