public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }
    // down and slow, focus
    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        int insert_success = 0;
        Node<E> curNode = new Node<E>(null);
        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        if (root == null){
            Node<E> newNode = new Node<E>(data);
            root = newNode;
        }
        else{
            curNode = root;
            while(insert_success == 0){
                if (curNode.getData().compareTo(data) > 0){
                    if (curNode.getLeftChild() == null){
                        Node<E> leftchild = new Node<E>(data);
                        curNode.setLeftChild(leftchild);
                        leftchild.setParent(curNode);
                        insert_success = 1;
                    }
                    else {
                        curNode = curNode.getLeftChild();
                        insert_success = 0;
                    }
                }
                else {
                    if (curNode.getRightChild() == null){
                        Node<E> rightchild = new Node<E>(data);
                        curNode.setRightChild(rightchild);
                        rightchild.setParent(curNode);
                        insert_success = 1;
                    }
                    else{
                        curNode = curNode.getRightChild();
                        insert_success = 0;
                    }
                }
            }
        }
    }

    public Node<E> find(E data){
        boolean find_success = false;
        Node<E> curNode = new Node<E>(null);
        // Search the tree for a node whose data field is equal to data
        if (root == null){
            return root;
        }
        else{
            curNode = root;
            while(find_success == false){  
                if (curNode.getData() == data){
                    find_success = true;
                }
                else if (curNode.getData().compareTo(data) > 0){
                    curNode = curNode.getLeftChild();
                    if (curNode == null){
                        find_success = true;
                    }
                     else{
                        find_success = false;
                     }
                }
                else if (curNode.getData().compareTo(data) < 0){
                    curNode = curNode.getRightChild();
                    if (curNode == null){
                        find_success = true;
                    }
                    else{
                        find_success = false;

                    }
                }
            }
            return curNode;
            }
    }

    public Node<E> delete(E data){
        // Find the right node to be deleted
        int delete_success = 0;
        Node<E> curNode = new Node<E>(null);
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.

        // Hint: You may want to implement a findMin() method to find the successor when there are two children
        if (root == null){
            return null;
        }
        else{
            curNode = root;
            while(delete_success == 0){
                if (curNode.getData().compareTo(data) > 0){
                    curNode = curNode.getLeftChild();
                    if (curNode == null){
                        delete_success = 1;
                    }
                    else{
                        delete_success = 0;
                    }
                }
                else if (curNode.getData().compareTo(data) < 0){
                    curNode = curNode.getRightChild();
                    if (curNode == null){
                        delete_success = 1;
                    }
                    else{
                        delete_success = 0;
                    }
                }
                else if (curNode.getData() == data){
                    if (curNode.getRightChild() == null && curNode.getLeftChild() == null){
                        if(curNode == root){
                            root = null;
                        }
                        else if (curNode.getData().compareTo(curNode.getParent().getData()) < 0){
                            curNode.getParent().setLeftChild(null);
                        }
                        else{
                            curNode.getParent().setRightChild(null);
                        }
                    }
                    else if (curNode.getLeftChild() == null){
                        if (curNode.getParent() != null){
                            if(curNode.getParent().getLeftChild() == curNode){
                                curNode.getParent().setLeftChild(curNode.getRightChild());
                                curNode.getRightChild().setParent(curNode.getParent());
                            }
                            else{
                                curNode.getParent().setRightChild(curNode.getRightChild());
                                curNode.getRightChild().setParent(curNode.getParent());
                            }
                        }
                        else{
                            root = curNode.getRightChild();
                            root.setParent(null);
                        }
                    }
                    else if (curNode.getRightChild() == null){
                        if(curNode.getParent() != null){
                            if(curNode.getParent().getLeftChild() == curNode){
                                curNode.getParent().setLeftChild(curNode.getLeftChild());
                                curNode.getLeftChild().setParent(curNode.getParent());
                            }
                            else{
                                curNode.getParent().setRightChild(curNode.getLeftChild());
                                curNode.getLeftChild().setParent(curNode.getParent());
                            }
                        }
                        else{
                            root = curNode.getLeftChild();
                            root.setParent(null);
                        }
                    }
                    else{
                        Node<E> save_temp = new Node<E>(null);
                        save_temp = getMin(curNode.getRightChild());
                        curNode.setData(save_temp.getData());
                        if (save_temp.getData().compareTo(root.getData())<0){
                            if (save_temp.getParent() == curNode){
                                curNode.setRightChild(null);
                            }
                            else if (save_temp.getLeftChild() == null && save_temp.getRightChild() == null){
                                save_temp.getParent().setRightChild(null);
                            }
                            else if (save_temp.getRightChild() == null){
                                save_temp.getParent().setRightChild(save_temp.getRightChild());
                            }
                        }
                        else{
                            if (save_temp.getParent() == curNode){
                                curNode.setRightChild(null);
                            }
                            else if (save_temp.getLeftChild()== null && save_temp.getRightChild() == null){
                                save_temp.getParent().setLeftChild(null);
                            }
                            else if(save_temp.getLeftChild()==null){
                                save_temp.getParent().setRightChild(save_temp.getRightChild());
                            }
                        }  
                    }
                    delete_success = 1;
                }
            }  
            return null;
        }
        
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    // Your Code here
                System.out.print(top.getData() + " ");
                traverse("preorder", top.getLeftChild());
                traverse("preorder", top.getRightChild());
                break;
                case "inorder":
                    // Your Code here
                traverse("inorder", top.getLeftChild());
                System.out.print(top.getData()+ " ");
                traverse("inorder", top.getRightChild());

                break;
                case "postorder":
                    // Your Code here
                traverse("postorder", top.getLeftChild());
                traverse("postorder", top.getRightChild());
                System.out.print(top.getData()+ " ");

                break;
            }
        }
    }

    public Node<E> getMin(Node<E> top){
        // Return the min node
        while(top.getLeftChild()!= null){
            top = top.getLeftChild();
        }
        return top;
    }



}
