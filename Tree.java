/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework4;

/**
 * Represents the tree
 */
public class Tree {
    private TreeNode root;

    /**
     * Constructor for the tree
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Gets the reference for the node
     * @param label of the node
     * @return the helper method
     */
    public TreeNode getNodeReference(String label) {
        return getNodeReferenceHelper(root, label);
    }

    /**
     * Gets the reference for the node
     * @param node of the tree
     * @param label of the node
     * @return the node
     */
    private TreeNode getNodeReferenceHelper(TreeNode node, String label) {
        if (node == null) {
            return null;
        } else if (node.getLabel().equals(label)) {
            return node;
        }

        TreeNode tempNode = null;

        for (int i = 0; i < node.getChildren().length; i++) {
            TreeNode child = node.getChildren()[i];
            if (child != null) {
                tempNode = getNodeReferenceHelper(child, label);
                if (tempNode != null) {
                    break;
                }
            }
        }
        return tempNode;
    }

    /**
     * Adds a new node to the tree
     * @param label of the node
     * @param prompt of the node
     * @param message of the node
     * @param parentLabel of the node
     * @return true if there is a node. False if there is no node
     * @throws InvalidTreeException if the tree is invalid
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) throws InvalidTreeException {
        TreeNode newNode = new TreeNode(label, prompt, message);
        if (parentLabel == null) {
            if (root != null) {
                throw new IllegalArgumentException("Root node already exists.");
            }
            root = newNode;
            return true;
        }

        TreeNode parent = getNodeReference(parentLabel);

        if (parent == null) {
            throw new InvalidTreeException("Invalid parent label: " + parentLabel);
        } else {
            for (int i = 0; i < parent.getChildren().length; i++) {
                if (parent.getChildren()[i] == null) {
                    parent.getChildren()[i] = newNode;
                    newNode.setParent(parent);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Traverses the tree in preorder
     * @throws InvalidTreeException if the tree is invalid
     */
    public void preOrder() throws InvalidTreeException {
        if (root == null) {
            throw new InvalidTreeException("The tree is empty");
        }
        preOrderHelper(root);
    }

    /**
     * Helper method to traverse the tree in preorder
     * @param node of the tree
     */
    private void preOrderHelper(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println("Label: " + node.getLabel() + ", Prompt: " + node.getPrompt() + ", Message: " + node.getMessage());
        for (int i = 0; i < node.getChildren().length; i++) {
            TreeNode child = node.getChildren()[i];
            if (child != null) {
                preOrderHelper(child);
            }
        }
    }

    /**
     * Begins to traverse the tree
     * @param node of the tree
     * @throws InvalidTreeException if the tree is invalid
     */
    public void beginSession(TreeNode node) throws InvalidTreeException {
        if (root == null) {
            throw new InvalidTreeException("The tree is not set up");
        }
        beginSessionHelper(node);
    }

    /**
     * Helper method for the method ti begin traversing the tree
     * @param node of the tree
     */
    private void beginSessionHelper(TreeNode node) {
        if (node.isLeaf()) {
            System.out.println(node.getMessage());
            System.out.println("Thank you for using our automated help system.");
            return;
        }

        System.out.println(node.getMessage());
        for (int i = 0; i < node.getChildren().length; i++) {
            if (node.getChildren()[i] != null) {
                System.out.println((i + 1) + ") " + node.getChildren()[i].getPrompt());
            }
        }
        System.out.println("0) Exit Session.");
        System.out.println("B) Go Back.");
    }
}
