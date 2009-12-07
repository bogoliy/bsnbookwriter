package com.bookwriter.service;

import com.bookwriter.model.Part;
import com.bookwriter.model.Video;
import com.bookwriter.persist.PartManager;
import com.bookwriter.persist.VideoManager;

public class BookService {

	public com.bookwriter.model.Part[] getParts(int bookId) {
		PartManager partManager = new PartManager();
		return partManager.getParts(bookId).toArray(new Part[] {});
	}

	public com.bookwriter.model.Video[] getVideos() {
		VideoManager videoManager = new VideoManager();
		System.out.println(videoManager.getVideos().size() + "--------");
		return videoManager.getVideos().toArray(new Video[] {});
	}
	
}
