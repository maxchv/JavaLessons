package ua.step.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ua.step.example.model.Student;

/**
 * 
 * Остановка сериализации объекта
 *
 */
public class Task08
{
    public static void main(String[] args) throws IOException
    {
        Student person = new Student("Вася", 23, "java 1");
        FileOutputStream fos = new FileOutputStream("student.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();
    }
}

