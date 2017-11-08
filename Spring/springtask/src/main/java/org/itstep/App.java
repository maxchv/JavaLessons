package org.itstep;

import org.itstep.rss.reader.RssReader;

public class App {
    public static void main(String[] args) {
        RssReader rssReader = new RssReader("https://dou.ua/feed/");
        rssReader.printChannelInfo();
        rssReader.printFeedMessages();
    }
}
