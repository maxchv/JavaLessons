package ua.step.example;

import java.io.File;

public class Task02
{
    public static void main(String[] args)
    {
        File file = new File("c:");
        File[] files = file.listFiles();
        printFiles(files);
    }

    // FIXME переделать метод таким образом, чтобы он выводил все дериктории и
    // файлы на диске. Использовать рекурсию.
    private static void printFiles(File[] files)
    {
        for (File file : files)
        {
            if (file.isDirectory())
            {
                System.out.print("DIR ");
            }
            else
            {
                System.out.print("FILE ");
            }
            System.out.println(file.getName());
        }
    }
}
