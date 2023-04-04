package com.bdprojecto.demo3;

public class Person {
    private String name;
    private String lastName;
    public Person(){
    }

    public Person(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }

    public String toString(){
        return "Name: " + name + "\nLast name:" + lastName;
    }
}
