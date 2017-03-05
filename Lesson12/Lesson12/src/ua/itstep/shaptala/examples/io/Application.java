package ua.itstep.shaptala.examples.io;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;

public class Application {

    public static void main(String[] args) throws IOException {

        // InputStream / OutputStream

        // Byte Streams
        // FileInputStream/FileOutputStream
        inOutStream();
        byteIn();
        streamFromUrl();

        // Buffered Streams
        // BufferedInputStream/BufferedOutputStream
        bufferedInput();

        // Character Streams
        // Reader/Writer
        // FileReader/FileWriter
        characterIO();

        // BufferedReader/BufferedWriter

        // Scanning and Formatting
        // Scanner/PrintStream/PrintWriter
        scannerExample();

        // ObjectOutput/ObjectInput
        objectOutput();

        // File, Files, Paths
        File file = new File("new_file.txt");
        if(!file.exists()) {
            if (file.createNewFile()) {

            }
        }

        Files.list(Paths.get("c:\\"))
                .forEach(p -> System.out.println(p.getFileName()));

        // Walking the File Tree
        Files.walkFileTree(Paths.get("."), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("dir.getFileName() = " + dir.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("file.getFileName() = " + file.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void objectOutput() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("people.bin")
        )) {
            out.writeLong(Person.createRoster().size());
            for(Person p: Person.createRoster()) {
                out.writeObject(p);
            }
        }
        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("people.bin")
        )) {
            long length = in.readLong();
            Person[] people = new Person[(int)length];
            for(int i=0; i<people.length; i++) {
                people[i] = (Person) in.readObject();
                System.out.println("people = " + people[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void streamFromUrl() {
        try( InputStream in = new URL("https://itstep.dp.ua").openStream();
             OutputStream out = new FileOutputStream("index.html"); ) {
            byte[] buff = new byte[1024];
            int count = 0;
            while((count = in.read(buff))!= -1) {
                out.write(buff, 0, count);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void byteIn() {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{65, 66, 67});
    }

    private static void scannerExample() {
        try(PrintStream out = new PrintStream("data.txt");) {
            out.println("It is PrintStream");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void characterIO() {
        try(Reader rdr = new FileReader("file.txt")) {
            char[] buff = new char[256];
            int count = rdr.read(buff);
            System.out.println("new String(rdr) = " + new String(buff, 0, count));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedInput() {
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("file.txt"));) {
            if(in.markSupported()) {
                byte[] buff = new byte[6];
                in.read(buff);
                System.out.println("new String(buff) = " + new String(buff));
                in.mark(100);
                in.read(buff);
                System.out.println("new String(buff) = " + new String(buff));
                in.reset();
                in.read(buff);
                System.out.println("new String(buff) = " + new String(buff));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inOutStream() {

        try (OutputStream out = new FileOutputStream("file.txt");) {
            String text = "Hello from code";
            out.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } finally {
//            if(out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        try {
            InputStream stream = new FileInputStream("file.txt");
            byte[] buff = new byte[256];
            int count;
            stream.skip(6);
            while ((count = stream.read(buff)) != -1) {
                String text = new String(buff, 0, count, "utf8");
                System.out.println("text = " + text);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
