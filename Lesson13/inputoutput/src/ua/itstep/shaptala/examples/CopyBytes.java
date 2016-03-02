package ua.itstep.shaptala.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFileChooser;

public class CopyBytes {
	
	

	public static void main(String[] args) throws IOException {
		// usingBufferReader();
		// usingConsole();
		// printStreamAndBufferReader();		
		// dataInputOutputExample();		
		// fileChooser();
		// comparePerson();
		// serialazeObjects();
		//usingPath();
		pathDirs();
	}

	private static void pathDirs() throws IOException {
		File file = new File(".");
		System.out.println(Arrays.toString(file.list()));
		System.out.println(Arrays.toString(file.listFiles()));
		String[] fileList = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {				
				return name.endsWith(".txt");
			}
		});
		System.out.println(Arrays.toString(fileList));
		
		try(DirectoryStream<Path> ds = Files.newDirectoryStream(file.toPath())) {
			for(Path p: ds) {
				if(Files.isDirectory(p)) {
					System.out.print(p.getFileName());					
				}
			}
		}		
	}

	@SuppressWarnings("unused")
	private static void usingPath() {
		Path path = Paths.get(".").toAbsolutePath();
		System.out.println(path.getNameCount());
		System.out.println(path.isAbsolute());
		System.out.println(path.getFileName());
		System.out.println(path.getFileSystem().getRootDirectories());
		System.out.println(path.getParent());
		System.out.println(path.getRoot());
	}

	@SuppressWarnings("unused")
	private static void serialazeObjects() throws IOException, FileNotFoundException {
		List<Person> people = Person.createRoster();
		try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("people.bin"))) {
			stream.writeObject(people);
		}
		
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("people.bin"))) {
			List<Person> cpyPeople = (List<Person>) stream.readObject();
			System.out.println(cpyPeople);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void comparePerson() {
		List<Person> people = Person.createRoster();
		System.out.println(people);
		//Collections.sort(people);
		people.sort(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {				
				return o1.name.compareTo(o2.name);
			}
		});
		System.out.println(people);
	}

	@SuppressWarnings("unused")
	private static void fileChooser() {
		JFileChooser of = new JFileChooser();
		of.showOpenDialog(null);
	}

	@SuppressWarnings("unused")
	private static void dataInputOutputExample() throws IOException, FileNotFoundException {
		Integer[] origIntArray = {1,2,3,4,5,6};
		Double[] origDoubleArray = {1.5, 2.5, 3.5, 3.5, 5.6, 6.6 };
		String[] origStringArray = {"One", "Two", "Tree", "Four", "Five", "Six" };
		try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
			for(int i=0; i<origIntArray.length; i++) {
				out.writeInt(origIntArray[i]);
				out.writeDouble(origDoubleArray[i]);
				out.writeUTF(origStringArray[i]);
			}
		}
		Integer[] cpyIntArray = new Integer[origIntArray.length];
		Double[] cpyDoubleArray = new Double[origDoubleArray.length];
		String[] cpyStringArray = new String[origStringArray.length];
		try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("result.txt")))) {
			for(int i=0; i<origIntArray.length; i++) {
				cpyIntArray[i]= in.readInt();
				cpyDoubleArray[i] = in.readDouble();
				cpyStringArray[i] = in.readUTF();
			}
		}
		checkArrays(origIntArray, cpyIntArray);
		checkArrays(origDoubleArray, cpyDoubleArray);
		checkArrays(origStringArray, cpyStringArray);
	}

	private static <T> void checkArrays(T[] origIntArray, T[] cpyIntArray) {
		System.out.println(Arrays.toString(cpyIntArray));
		assert Arrays.equals(origIntArray, cpyIntArray);
	}

	@SuppressWarnings("unused")
	private static void printStreamAndBufferReader()
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		try(PrintStream stream = new PrintStream("result.txt", "UTF8")) {
			stream.println("Тест");
			stream.println("Еще тест");
			stream.println("Еще тест");
		}
		try(BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream("result.txt"), "UTF8"))) {
			while(stream.ready()) {
				System.out.println(stream.readLine());
			}
		}
	}

	@SuppressWarnings("unused")
	private static void usingConsole() {
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}		
	}

	@SuppressWarnings("unused")
	private static void usingBufferReader() {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL("http://itstep.org").openStream(), "UTF8"))) {
			while (reader.ready()) {
				System.out.println(reader.readLine());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
