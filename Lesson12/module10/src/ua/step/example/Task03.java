package ua.step.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Запись в файл
 *
 */
public class Task03
{
    public static void main(String[] args) throws IOException
    {
        File file = new File(Task01.FILE_NAME);
        if (file.exists())
        {
            FileOutputStream fos = new FileOutputStream(file);
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            fos.write(text.getBytes());
            fos.close();
        }
        else
        {
            System.out.println("Запустите Task01");
        }
    }
}
