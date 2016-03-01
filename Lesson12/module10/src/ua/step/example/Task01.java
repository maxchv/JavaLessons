package ua.step.example;

import java.io.File;
import java.io.IOException;

/**
 * 
 * Создание нового файла
 *
 */
public class Task01
{
    public static final String FILE_NAME = "test.txt";

    public static void main(String[] args)
    {
        File file = new File(FILE_NAME);
        if (file.exists())
        {
            System.out.println("File exist");
            file.delete();
        }
        try
        {
            if (file.createNewFile())
            {
                System.out.println("File created");
            }
            else
            {
                System.out.println("File not created.");
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
