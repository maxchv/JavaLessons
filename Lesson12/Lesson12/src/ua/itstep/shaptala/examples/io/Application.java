package ua.itstep.shaptala.examples.io;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Locale;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;

public class Application {

	private static final String INPUT_FILE = "d:\\input.txt";

	public static void main(String[] args) throws IOException {

        // InputStream / OutputStream

		// Byte Streams
		// FileInputStream/FileOutputStream
		inOutStream();
		
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

        // File, Files, Paths

        // Walking the File Tree
	}

	private static void scannerExample() throws UnsupportedEncodingException, FileNotFoundException {

	}

	private static void characterIO() {

	}

	private static void bufferedInput() {

	}

	private static void inOutStream() {

	}




}
