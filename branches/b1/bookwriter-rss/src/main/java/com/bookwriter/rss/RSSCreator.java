package com.bookwriter.rss;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;

public class RSSCreator {
	PartManager partManager;

	public PartManager getPartManager() {
		return partManager;
	}

	public void setPartManager(PartManager partManager) {
		this.partManager = partManager;
	}

	/**
	 * Returns xml(RSS 2.0 protocol) with latest parts of book
	 * 
	 * @return
	 */
	public Document getRSS() {
		List<Part> rssParts = partManager.getLatestParts();
		RSSBuilder builder = RSSBuilder.getInstance(RSSBuilder.RSS_2_0);
		builder.setParts(rssParts);
		return builder.getRss();
	}

	public void writeRSS(PrintWriter printWriter, Document document) {
		// lets write to a writer
		XMLWriter writer = new XMLWriter(printWriter);
		// Pretty print the document to System.out
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			writer.write(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
