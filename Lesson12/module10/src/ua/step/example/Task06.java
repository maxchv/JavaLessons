package ua.step.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ua.step.example.model.Person;


/**
 * 
 * Запись объекта в файл
 * 
 */
public class Task06
{
    public static void main(String[] args) throws IOException
    {
        Person person = new Person("Вася", 23);
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();
    }
}

