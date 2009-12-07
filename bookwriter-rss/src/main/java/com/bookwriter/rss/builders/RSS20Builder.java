package com.bookwriter.rss.builders;

import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bookwriter.model.Part;
import com.bookwriter.rss.RSSBuilder;
public class RSS20Builder extends RSSBuilder {
	List<Part> parts = null;

	@Override
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@Override
	public Document getRss() {
		/**
		 * Creating rss
		 */
		Document document = DocumentHelper.createDocument();
		Element rssElement = document.addElement("rss");
		rssElement.addAttribute("version", "2.0");

		Element channelElement = rssElement.addElement("channel");
		channelElement.addElement("title").addText("Latest parts of books!");
		channelElement.addElement("link").addText("http://bookwriter.com");
		channelElement.addElement("description").addText(
				"Let's write a book together!");
		channelElement.addElement("language").addText("ru-ru");
		channelElement.addElement("pubDate").addText(
				(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z"))
						.format(new java.util.Date()));

		for (Part part : parts) {
			Element item = channelElement.addElement("item");
			item.addElement("title").addText(
					part.getBook().getTitle() + " : part " + part.getPartId()
							+ " by " + part.getUser().getName());
			item.addElement("link").addText(
					"http://localhost:8080/bookwriter-webapp/part.x#"
							+ part.getPartId());
			item.addElement("description").addText("New part on the book");
			item.addElement("pubDate").addText(
					(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z"))
							.format(part.getDatetime()));
			item.addElement("guid").addText(
					"http://localhost:8080/bookwriter-webapp/part.x#"
							+ part.getPartId());
		}

		return document;
	}

}
