package ua.step.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ua.step.example.model.Person;

/**
 * 
 * Чтение объекта из файла
 * 
 */
public class Task07
{
    public static void main(String[] args) throws IOException,
            ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        Person person = (Person) oin.readObject();
        System.out.println(person);
        // FIXME измените объект Person (добавте поле) и снова попробуйте
        // считать сериализованный файл
    }
}
