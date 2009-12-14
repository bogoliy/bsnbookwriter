package com.bookwriter.model;



import com.bookwriter.persist.StoryManager;
import com.bookwriter.persist.PartManager;
import com.bookwriter.persist.VideoManager;

import junit.framework.TestCase;

public class PartTest extends TestCase {
	public void testParts() {
		PartManager partManager = new PartManager();
		this.assertNotNull(partManager.getParts(1));
		StoryManager genreManager = new StoryManager();
		this.assertNotNull(genreManager.getStories());
	}
}
