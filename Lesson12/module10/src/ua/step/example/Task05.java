package ua.step.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task05
{
    public static void main(String[] args) throws IOException
    {
        FileWriter fileWriter = new FileWriter(Task01.FILE_NAME);
        try
        {
            String text;
            do 
            {
                Scanner scaner = new Scanner(System.in);
                text = scaner.nextLine().trim();
                text +="\n";
                fileWriter.write(text);
            } while (!text.equals("\n"));
        }
        finally
        {
            fileWriter.close();
        }
        //FIXME прочитать все строки из файла используя класс FileReader 
    }
}
