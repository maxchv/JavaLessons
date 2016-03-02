package ua.itstep.shaptala.examples.io;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;

public class Application {

	
	private static final String INPUT_FILE = "d:\\input.txt";

	public static void main(String[] args) throws IOException {
		// InputStream / OutputStream
		
		// Byte Streams
		// FileInputStream/FileOutputStream
		//inOutStream();
		
		// Buffered Streams
		// BufferedInputStream/BufferedOutputStream
		//bufferedInput();
		
		// Character Streams
		// Reader/Writer		
		// FileReader/FileWriter		
		//characterIO();
		
		// BufferedReader/BufferedWriter
		
		// Scanning and Formatting
		// Scanner/PrintStream/PrintWriter
		Scanner scanner = new Scanner(
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(INPUT_FILE), "UTF8"
								)
						)
				);		
		while(scanner.hasNext()) {
			System.out.println(scanner.next());
		}
		scanner.close();
		
		// IO Command line
		// Console

	}

	private static void characterIO() throws FileNotFoundException, IOException {
		File file = new File(INPUT_FILE);
		if(file.exists()) {
			Reader reader = new FileReader(INPUT_FILE);
			char[] buff = new char[(int)file.length()];
			reader.read(buff);
			System.out.println(buff);
			reader.close();
		}
	}

	private static void bufferedInput() throws FileNotFoundException, IOException {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(INPUT_FILE));
		if(inputStream.markSupported()) { // поддерживает ли метки
			inputStream.mark(10); // метка с резервом на возрат до 10 байт
			System.out.println((char)inputStream.read());			
			inputStream.reset();  // возврат к метке
			System.out.println((char)inputStream.read());
		}
		inputStream.close();
	}

	private static void inOutStream() {
		InputStream inputStream = null;
		OutputStream outStream = null;
		try {
			//inputStream = new FileInputStream("d:\\input.txt");
			//inputStream = new ByteArrayInputStream(new byte[]{65, 66, 67, 68});
			inputStream = new URL("http://google.com").openStream();
			
			//readFullFileByByte(inputStream);
			//readFullFileByByteArray(inputStream);
			outStream = new FileOutputStream("d:\\index.html");
			readAndWrite(inputStream, outStream);			
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			closeFileStream(inputStream, outStream);
			System.out.println("OK");
		}
	}

	private static void readAndWrite(InputStream inputStream, OutputStream outStream) throws IOException {
		int b;
		while((b =inputStream.read()) != -1) {
			outStream.write(b);
		}
	}

	private static void readFullFileByByteArray(InputStream inputFileStream) throws IOException {
		int num = inputFileStream.available();
		inputFileStream.skip(6);
		byte[] bytes = new byte[num];
		System.out.println(inputFileStream.read(bytes));
		for(byte b: bytes) {
			System.out.print((char)b);
		}
	}
	
	private static void closeFileStream(InputStream inputFileStream, OutputStream outStream) {
		if(inputFileStream != null) {
			try {
				inputFileStream.close();				
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}
		if(outStream != null) {
			try {
				outStream.close();				
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}
	}

	private static void readFullFileByByte(InputStream inputFileStream) throws IOException {
		int b;
		while((b = inputFileStream.read()) != -1) {
			System.out.print((char)b);
		}
	}

}
