package com.bookwriter.rss;

import java.util.List;

import org.dom4j.Document;


import com.bookwriter.model.Part;
import com.bookwriter.rss.builders.RSS20Builder;

public abstract class RSSBuilder {
	public static final String RSS_1_0 = "RSS 1.0";
	public static final String RSS_2_0 = "RSS 2.0";
	
	public static RSSBuilder getInstance (String version) {
		RSSBuilder builder = null;
		if (version.equals(RSS_2_0)) {
			builder  = new RSS20Builder();
		}
		return builder;
	}
	
	public abstract void setParts (List<Part> parts);
	public abstract Document getRss();
}
