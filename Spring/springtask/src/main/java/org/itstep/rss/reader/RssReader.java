package org.itstep.rss.reader;

import org.itstep.rss.model.Feed;
import org.itstep.rss.model.FeedMessage;
import org.itstep.rss.parser.RSSFeedParser;

public class RssReader {
    RSSFeedParser feedParser;

    public RssReader(String url) {
        feedParser = new RSSFeedParser(url);
    }

    public Feed getFeed() {
        return feedParser.readFeed();
    }

    public void printChannelInfo() {
        Feed feed = getFeed();
        System.out.println(feed);
    }

    public void printFeedMessages() {
        Feed feed = getFeed();
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }
}
