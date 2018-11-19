import java.util.Scanner;


public class TreeCompare{
	public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
		BST<Integer> first_tree = new BST<Integer>();
		BST<Integer> second_tree = new BST<Integer>();

		Scanner scan = new Scanner(System.in);
		String input;
		String task;

		int first_input_number = Integer.parseInt(scan.nextLine());
		
		int count_number = 0;
		int second_count_number = 0;
		while (scan.hasNextLine() && (count_number < first_input_number)){
			input = scan.nextLine();
			count_number += 1;
			String[] phrases = input.split(" ");
			task = phrases[0];
			switch(task) {
				case "insert":
				first_tree.insert(Integer.parseInt(phrases[1]));
				break;
				case "find":
				first_tree.find(Integer.parseInt(phrases[1]));
				break;
				case "delete":
				first_tree.delete(Integer.parseInt(phrases[1]));
				break;
				case "inorder":
				first_tree.traverse("inorder",first_tree.getRoot());
				System.out.println();
				break;
				case "preorder":
				first_tree.traverse("preorder",first_tree.getRoot());
				System.out.println();
				break;
				case "postorder":
				first_tree.traverse("postorder",first_tree.getRoot());
				System.out.println();
				break;
			}
		}
		
		
		if(scan.hasNextLine()){
			int second_input_number = Integer.parseInt(scan.nextLine());
			while (scan.hasNext() && (second_count_number < second_input_number)){
				input = scan.nextLine();
				second_count_number += 1;
				String[] phrases = input.split(" ");
				task = phrases[0];
				switch(task) {
					case "insert":
					second_tree.insert(Integer.parseInt(phrases[1]));
					break;
					case "find":
					second_tree.find(Integer.parseInt(phrases[1]));
					break;
					case "delete":
					second_tree.delete(Integer.parseInt(phrases[1]));
					break;
					case "inorder":
					second_tree.traverse("inorder",second_tree.getRoot());
					System.out.println();
					break;
					case "preorder":
					second_tree.traverse("preorder",second_tree.getRoot());
					System.out.println();
					break;
					case "postorder":
					second_tree.traverse("postorder",second_tree.getRoot());
					System.out.println();
					break;
				}
			}
		}
		scan.close();

		result(first_tree.getRoot(),second_tree.getRoot());
		
	}

	public static boolean compare(Node<Integer> first_tree_leaf, Node<Integer> second_tree_leaf){
		if (first_tree_leaf == null && second_tree_leaf == null){
			return true;
		}
		else if (first_tree_leaf.getData() != second_tree_leaf.getData()){
			return false;
		}
		else if ((first_tree_leaf != null && second_tree_leaf == null) || (first_tree_leaf ==  null && second_tree_leaf != null)){
			return false;
		} 
		else{
			return compare(first_tree_leaf.getLeftChild(),second_tree_leaf.getLeftChild()) && compare(first_tree_leaf.getRightChild(),second_tree_leaf.getRightChild());
		}
	}

	public static void result(Node<Integer> first_tree, Node<Integer> second_tree){
		if (compare(first_tree,second_tree) == true){
			System.out.println("The trees have the same shape.");
		}
		else{
			System.out.println("The trees do not have the same shape.");
		}
	}
}