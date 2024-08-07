/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework4;

import java.util.*;
import java.io.*;

/**
 * Accesses the tree and lets the user use the tree
 */
public class TreeDriver {
    private static Tree tree = new Tree();
    private static TreeNode currentNode;

    /**
     * Uses the tree so that the user can use it
     */
    public static void loadTree() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the file name> ");
        String fileName = s.nextLine().trim();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            tree = new Tree();

            String label;
            if (fileScanner.hasNextLine()) {
                label = fileScanner.nextLine().trim();
                String prompt = fileScanner.nextLine().trim();
                String message = fileScanner.nextLine().trim();
                tree.addNode(label, prompt, message, null);
            } else {
                System.out.println("File is empty");
                return;
            }

            while (fileScanner.hasNextLine()) {
                String parentLabel = fileScanner.next().trim();
                int number_of_children = fileScanner.nextInt();
                fileScanner.nextLine();

                for (int i=0; i < number_of_children; i++) {
                    String childLabel = fileScanner.nextLine().trim();
                    String childPrompt = fileScanner.nextLine().trim();
                    String childMessage = fileScanner.nextLine().trim();

                    try {
                        tree.addNode(childLabel, childPrompt, childMessage, parentLabel);
                    } catch (InvalidTreeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            currentNode = tree.getNodeReference(label);
            System.out.println("Tree created successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Helps the user use the tree
     */
    public static void startHelpSession() {
        Scanner s = new Scanner(System.in);

        try {
            if (currentNode == null) {
                currentNode = tree.getNodeReference("root");
                if (currentNode == null) {
                    System.out.println("The tree is not set up.");
                    return;
                }
            }

            System.out.println("Help Session Starting...");
            while (currentNode != null) {
                tree.beginSession(currentNode);
                System.out.print("Choice> ");
                String input = s.nextLine().trim().toUpperCase();

                if (input.equals("B")) {
                    if (currentNode.getParent() != null) {
                        currentNode = currentNode.getParent();
                    } else {
                        System.out.println("Cannot go back any further.");
                    }
                } else if (input.equals("0")) {
                    break;
                } else {
                    try {
                        int choice = Integer.parseInt(input);
                        if (choice > 0 && choice <= currentNode.getChildren().length && currentNode.getChildren()[choice-1] != null) {
                            currentNode = currentNode.getChildren()[choice - 1];
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main method
     * @param args of the main method
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean menu = true;

        while (menu) {
            System.out.println("\n\nMenu:\n" +
                    "L - Load a Tree\n" +
                    "H - Begin a Help Session\n" +
                    "T - Traverse the Tree in preorder\n" +
                    "Q - Quit\n\n" +
                    "Choice> ");
            String choice = s.nextLine().trim().toUpperCase();

            switch (choice) {
                case "L" -> loadTree();
                case "H" -> startHelpSession();
                case "T" -> {
                    try {
                        tree.preOrder();
                    } catch (InvalidTreeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "Q" -> menu = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}