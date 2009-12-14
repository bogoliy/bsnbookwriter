package com.bookwriter.store.tree;

import com.bookwriter.model.Story;

/**
 * <p>
 * The PanelStackBean is responsible for storing the name of the panel in the
 * panelStack which should be displayed when rendered. The default panel stack
 * panel name is "splash".
 * </p>
 * <p>
 * If a selectedPanel name is not found in the panel stack the panelStack
 * comonent will not change the current selected PanelStack
 * </p>
 */
public class PanelStackBean { 

	// default panel name
	private Story story = null;

	public void setStory(Story story) {
		this.story = story;
	}

	public Story getStory() {
		return story;
	}
}
