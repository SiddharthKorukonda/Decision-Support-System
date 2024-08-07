/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework4;

/**
 * Represents a node in a 9-ary tree.
 */
public class TreeNode {
    private TreeNode parent;
    private TreeNode[] children;
    private String label;
    private String prompt;
    private String message;

    /**
     * Constructs a new TreeNode with the given label, prompt, and message.
     *
     * @param label   the label of the node
     * @param prompt  the prompt of the node
     * @param message the message of the node
     * @throws InvalidTreeException if any of the parameters are null or empty
     */
    public TreeNode(String label, String prompt, String message) throws InvalidTreeException {
        if (label == null || label.isEmpty()) {
            throw new InvalidTreeException("Label cannot be null or empty");
        }
        if (prompt == null || prompt.isEmpty()) {
            throw new InvalidTreeException("Prompt cannot be null or empty");
        }
        if (message == null || message.isEmpty()) {
            throw new InvalidTreeException("Message cannot be null or empty");
        }

        this.label = label.trim();
        this.prompt = prompt.trim();
        this.message = message.trim();
        this.children = new TreeNode[9];
    }

    /**
     * Gets the parent of this node.
     *
     * @return the parent node
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node.
     *
     * @param parent the parent node to set
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * Gets the children of this node.
     *
     * @return an array of the children nodes
     */
    public TreeNode[] getChildren() {
        return children;
    }

    /**
     * Gets the label of this node.
     *
     * @return the label of this node
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label of this node.
     *
     * @param label the label to set
     * @throws InvalidTreeException if the label is null or empty
     */
    public void setLabel(String label) throws InvalidTreeException {
        if (label == null || label.isEmpty()) {
            throw new InvalidTreeException("Label cannot be null or empty");
        }
        this.label = label.trim();
    }

    /**
     * Gets the prompt of this node.
     *
     * @return the prompt of this node
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the prompt of this node.
     *
     * @param prompt the prompt to set
     * @throws InvalidTreeException if the prompt is null or empty
     */
    public void setPrompt(String prompt) throws InvalidTreeException {
        if (prompt == null || prompt.isEmpty()) {
            throw new InvalidTreeException("Prompt cannot be null or empty");
        }
        this.prompt = prompt.trim();
    }

    /**
     * Gets the message of this node.
     *
     * @return the message of this node
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of this node.
     *
     * @param message the message to set
     * @throws InvalidTreeException if the message is null or empty
     */
    public void setMessage(String message) throws InvalidTreeException {
        if (message == null || message.isEmpty()) {
            throw new InvalidTreeException("Message cannot be null or empty");
        }
        this.message = message.trim();
    }

    /**
     * Checks if this node is a leaf (i.e., it has no children).
     *
     * @return true if this node has no children, false otherwise
     */
    public boolean isLeaf() {
        for (TreeNode child : children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}
