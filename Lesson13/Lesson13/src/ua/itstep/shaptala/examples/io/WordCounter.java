package ua.itstep.shaptala.examples.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCounter {

	static class Word implements Comparable<Word> {
		String content;
		Integer count;

		Word(String content, Integer count) {
			this.content = content;
			this.count = count;
		}

		@Override
		public int compareTo(Word o) {
			return o.count - count;
		}

		@Override
		public String toString() {
			return content + ": " + count;
		}

	}

	String fname;
	Map<String, Integer> words = new TreeMap<>();

	public WordCounter(String fname) {
		this.fname = fname;
	}

	List<Word> getMostUsing(int count) {
		List<Word> mostUsing = new ArrayList<>();
		try (Scanner scanner = new Scanner(
				new InputStreamReader(new BufferedInputStream(new FileInputStream(fname)), "UTF8"))) {
			while (scanner.hasNext()) {
				final String regex_filter = "[_\\.,:;\"\'\\)\\(\\?\\!\\-\\>\\<]";
				final String regex_rus_words = "[Р-пр-џ]{4,}";

				String word = scanner.next().replaceAll(regex_filter, "").toLowerCase();
				if (word.matches(regex_rus_words)) {
					if (!words.containsKey(word)) {
						words.put(word, 1);
					} else {
						words.put(word, words.get(word) + 1);
					}
				}
			}

			for (String w : words.keySet()) {
				Word nw = new Word(w, words.get(w));
				mostUsing.add(nw);
			}
			mostUsing.sort(null);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return mostUsing.subList(0, count);
	}

	public static void main(String[] args) {
		WordCounter wordCounter = new WordCounter("alice.txt");
		for (Word word : wordCounter.getMostUsing(5)) {
			System.out.println(word);
		}
	}

}
