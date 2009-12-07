package com.bookwriter.store.tree;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;

import com.bookwriter.model.Story;
import com.bookwriter.model.User;
import com.icesoft.faces.component.tree.IceUserObject;

public class DynamicNodeUserObject extends IceUserObject {

	// panel stack which will be manipulated when a command links action is
	// fired.
	private StoryTree treeBean;
	private Story story;
	
    public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	// panel stack which will be manipulated when a command links action is fired.
    private PanelStackBean panelStack;

	private static String nodeToolTip;

	/**
	 * Default contsructor for a PanelSelectUserObject object. A reference is
	 * made to a backing bean with the name "panelStack", if possible.
	 * 
	 * @param wrapper
	 */
	public DynamicNodeUserObject(DefaultMutableTreeNode wrapper, StoryTree tree) {
		super(wrapper);
		treeBean = tree;
		setLeafIcon("xmlhttp/css/xp/css-images/tree_document.gif");
		setBranchContractedIcon("xmlhttp/css/xp/css-images/tree_folder_close.gif");
		setBranchExpandedIcon("xmlhttp/css/xp/css-images/tree_folder_open.gif");
		setText(generateLabel());
		setTooltip(nodeToolTip);
		setExpanded(true);
        // get a reference to the PanelStackBean from the faces context
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Object panelStackObject =
                facesContext.getApplication()
                        .createValueBinding("#{panelStack}")
                        .getValue(facesContext);
        if (panelStackObject instanceof PanelStackBean){
            panelStack = (PanelStackBean)panelStackObject;
        }
	}

	/**
	 * Generates a label for the node based on an incrementing int.
	 * 
	 * @return the generated label (eg. 'Node 5')
	 */
	private String generateLabel() {
		return "Node " + Integer.toString(treeBean.getIncreasedLabelCount());
	}

	/**
	 * Deletes this not from the parent tree.
	 * 
	 * @param event
	 *            that fired this method
	 */
	public void deleteNode(ActionEvent event) {
		((DefaultMutableTreeNode) getWrapper().getParent())
				.remove(getWrapper());
	}

	/**
	 * Copies this node and adds a it as a child node.
	 * 
	 * @param event
	 *            that fired this method
	 */
	public void copyNode(ActionEvent event) {
		DefaultMutableTreeNode clonedWrapper = new DefaultMutableTreeNode();
		DynamicNodeUserObject clonedUserObject = new DynamicNodeUserObject(
				clonedWrapper, treeBean);
		DynamicNodeUserObject originalUserObject = (DynamicNodeUserObject) getWrapper()
				.getUserObject();
		clonedUserObject.setAction(originalUserObject.getAction());
		clonedUserObject.setBranchContractedIcon(originalUserObject
				.getBranchContractedIcon());
		clonedUserObject.setBranchExpandedIcon(originalUserObject
				.getBranchExpandedIcon());
		clonedUserObject.setExpanded(originalUserObject.isExpanded());
		clonedUserObject.setLeafIcon(originalUserObject.getLeafIcon());
		clonedWrapper.setUserObject(clonedUserObject);
		getWrapper().insert(clonedWrapper, 0);
	}

	/**
	 * Registers a user click with this object and updates the selected node in
	 * the TreeBean.
	 * 
	 * @param event
	 *            that fired this method
	 */
	public void nodeClicked(ActionEvent event) {
		treeBean.setSelectedNodeObject(this);
	}

	// default panel name
	private String selectedPanel = "splash";

	/**
	 * Gets the name of the selected panel stack.
	 * 
	 * @return selected panel stack name
	 */
	public String getSelectedPanel() {
		return selectedPanel;
	}

	/**
	 * 
	 * @param selectedPanel
	 */
	public void setSelectedPanel(String selectedPanel) {
		this.selectedPanel = selectedPanel;
	}
	
    /**
     * ActionListener method called when a node in the tree is clicked.  Sets
     * the selected panel of the reference panelStack to the value of the instance
     * variable #displayPanel.   
     *
     * @param action JSF action event.
     */
    public void selectPanelStackPanel(ActionEvent action){
        if (panelStack != null){
            panelStack.setStory(story);
        }
    }


}
