package ua.step.example.model;

import java.io.Serializable;

public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

    transient private char firstLetter;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
        if (!name.isEmpty())
        {
            this.firstLetter = name.charAt(0);
        }
    }

    public char getFirstLetter()
    {
        return firstLetter;
    }

    @Override
    public String toString()
    {
        return name + " " + age;
    }
}