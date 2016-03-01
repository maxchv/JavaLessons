package ua.step.example.model;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Student extends Person
{
    private String group;

    public Student(String name, int age, String group)
    {
        super(name, age);
        this.group = group;
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        throw new NotSerializableException("Не сегодня!");
    }

    private void readObject(ObjectInputStream in) throws IOException
    {
        throw new NotSerializableException("Не сегодня!");
    }
}
