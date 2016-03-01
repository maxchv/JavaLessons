package ua.step.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Task04
{
    public static void main(String[] args) throws IOException
    {
        File file = new File(Task01.FILE_NAME);
        if (file.exists())
        {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            System.out.println(new String(bytes));
        }
        else
        {
            System.out.printf("File %s not found", Task01.FILE_NAME);
        }
    }
}
