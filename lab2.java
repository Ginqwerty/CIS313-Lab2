import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
    	BST<Integer> tree = new BST<Integer>();
    	Scanner scan = new Scanner(System.in);
    	String input;
        String task;
    	while (scan.hasNext()){
            input = scan.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            switch(task) {
            	case "insert":
            		tree.insert(Integer.parseInt(phrases[1]));
            		break;
            	case "find":
            		tree.find(Integer.parseInt(phrases[1]));
            		break;
            	case "delete":
            		tree.delete(Integer.parseInt(phrases[1]));
            		break;
            	case "inorder":
            		tree.traverse("inorder",tree.getRoot());
            		System.out.println();
            		break;
            	case "preorder":
            		tree.traverse("preorder",tree.getRoot());
            		System.out.println();
            		break;
            	case "postorder":
            		tree.traverse("postorder",tree.getRoot());
            		System.out.println();
            		break;
            }
        }
        scan.close();
    }
}