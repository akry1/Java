/**
 * Created by aravind on 2/4/2016.
 */

class Element{
    int data;
    Element left;
    Element right;

    Element(int val){
        this.data = val;
    }
}

public class BinarySearchTree {
    private Element root;

    BinarySearchTree(int val){
        this.root = new Element(val);
    }

    BinarySearchTree(){}

    public void insertNode(Element node){
        if(this.root==null) {
            this.root = node;
            return;
        }
        Element current = this.root;
        Element prev = null;
        while(current!=null){
            prev = current;
            if(node.data>current.data)
                current = current.right;
            else
                current = current.left;
        }
        if(node.data > prev.data)
            prev.right = node;
        else prev.left = node;
    }

    public void deleteNode(int val){
        if(root==null)
            return;
        Element current = root;
        Element prev = null;
        int left=-1;
        while(current!=null){
            if(val == current.data)
                break;
            prev = current;
            if(val > current.data){
                current= current.right;
                left = 0;
            }
            else{
                current = current.left;
                left =1;
            }
        }
        if(current==null) return;
        if(current.left==null && current.right==null){
            if(left==0) prev.right = null;
            else if(left==1) prev.left = null;
            else root = null;
        }
        else if(current.left!=null && current.right!=null){
            Element rightSucessor = current.right;
            Element rightSuccPrev = current.right;
            Element right2prev = prev;
            int i=0;
            while(rightSucessor!=null) {
                i++;
                right2prev = rightSuccPrev;
                rightSuccPrev = rightSucessor;
                rightSucessor = rightSucessor.left;
            }
            if(right2prev!=prev) {
                right2prev.left = null;
                rightSuccPrev.left = current.left;
                if(i>1)
                    rightSuccPrev.right = current.right;
                else
                    rightSuccPrev.right = null;
            }
            if (left == 0)
                prev.left = rightSuccPrev;
            else if (left == 1)
                prev.right = rightSuccPrev;
            else
                root=rightSuccPrev;
        }
        else{
            if(current.left!=null){
                if(left==0) prev.right = current.left;
                else if(left==1) prev.left = current.left;
                else root = current.left;
            }
            else{
                if(left==0) prev.right = current.right;
                else if(left==1) prev.left = current.right;
                else root = current.right;
            }
        }
    }

    public void inorderTraversal(Element node){
        if(node==null) return;
        inorderTraversal(node.left);
        System.out.print(node.data+" ");
        inorderTraversal(node.right);
    }

    public void inorderTraveralNoRecursion(){


    }

    //need to fix or find better approach
    public Integer inOrderSuccessor(Element root, int value){
        if(root==null) return null;
        Element next = root;
        Element prev = null;
        while(next!=null){
            if(value > next.data){
                prev = next;
                next = next.right;
            }
            else if(value < next.data) {
                prev = next;
                next = next.left;
            }
            else break;
        }
        if(next==null || (prev==null &&next.right==null)) return null;
        if(next.right!=null) {
            if (next.right.left == null) return next.right.data;
            else {
                Element current = next.right.left;
                while (current.left != null) {
                    current = current.left;
                }
                return current.data;
            }
        }
        else if(prev.data<value) return null;
        else{
             return prev.data;
        }
    }

    //need to fix or find better approach
    public Integer inOrderPredessor(Element root,int value){
        if(root==null) return null;
        Element next = root;
        Element prev = null;
        while(next!=null){
            if(value > next.data){
                prev = next;
                next = next.right;
            }
            else if(value < next.data) {
                prev = next;
                next = next.left;
            }
            else break;
        }
        if(next==null || (prev==null && next.left==null)) return null;
        if(next.left!=null) {
            if (next.left.right == null) return next.left.data;
            else {
                Element current = next.left.right;
                while (current.right != null) {
                    current = current.right;
                }
                return current.data;
            }
        }
        else if(prev.data>value) return null;
        else return prev.data;
    }

    public boolean isBST(Element node,Integer top){
        if(node==null) return true;
        if(node.left==null && node.right==null) return true;
        //int top = node.data;
        if(node.left!=null)
            if(node.left.data <= node.data)
                if (top ==null || node.left.data < top)
                    return isBST(node.left, node.data);
        if(node.right!=null)
            if(node.right.data >= node.data)
                if (top ==null || node.left.data > top)
                    return isBST(node.right, node.data);
        return false;
    }

    public Integer lowestCommonAncentor(Element node, int a,int b){
        if (node==null) return null;
		Element current = node;
		Element prev = null;
		while(current.left!=null || current.right!=null){			
			if(current.data < a && current.data < b)
			{		prev = current;
					current = current.right;
			}
			else if((current.data > a && current.data > b)){
					prev = current;
					current = current.left;
			}
			else break;
		}
		if(prev==null) return null;
		else return prev.data;
    }

    public int diameter(Element node){
        int leftHeight = 0, rightHeight =0;
        if(node==null) return 0;
        if(node.left==null && node.right == null) {
            return 1;
        }
        leftHeight++;
        rightHeight++;
        int maxChild =  Integer.max(diameter(node.left),diameter(node.right));
        return Integer.max(maxChild,leftHeight+rightHeight+1);
    }

    public void levelorderTraversal(Element node,int parent){
        if(node==null) return;
        if(parent==1)
            System.out.print(node.data+" ");
        if(node.left!=null)
            System.out.print(node.left.data+" ");
        if(node.right!=null)
            System.out.print(node.right.data+" ");
        levelorderTraversal(node.left,0);
        levelorderTraversal(node.right,0);
    }

    public Integer kthSmallest(Element node, int k){
        return 0;
    }

    //limited space
    public void mergeIntoSortedForm(Element node1, Element node2){

    }

    //two nodes are swapped
    public void correctBST(Element node){}

    public Boolean pairWithGivenSum(Element node, int sum){
        return false;
    }

    public void convertBTtoBST(Element node){}

    public static void main(String []args){
        BinarySearchTree bt = new BinarySearchTree();
        //bt.root.left = new Element(20);
        //bt.root.right = new Element(30);

        bt.insertNode(new Element(50));
        bt.insertNode(new Element(40));
        bt.insertNode(new Element(20));
        bt.insertNode(new Element(30));
        bt.insertNode(new Element(70));
        bt.insertNode(new Element(60));
        bt.insertNode(new Element(80));
        bt.inorderTraversal(bt.root);
        //bt.levelorderTraversal(bt.root,1);

        //System.out.print(bt.diameter(bt.root));

/*        bt.deleteNode(20);
        System.out.println();
        bt.inorderTraversal(bt.root);
        bt.deleteNode(30);
        System.out.println();
        bt.inorderTraversal(bt.root);
        bt.deleteNode(50);
        System.out.println();
        bt.inorderTraversal(bt.root);
        bt.deleteNode(70);
        System.out.println();
        bt.inorderTraversal(bt.root);
        bt.deleteNode(60);
        System.out.println();
        bt.inorderTraversal(bt.root);
        bt.deleteNode(80);
        System.out.println();
        bt.inorderTraversal(bt.root);*/
        //System.out.print(bt.isBST(bt.root,null));
        System.out.println();
        //System.out.println(bt.inOrderSuccessor(bt.root,60));
        //System.out.print(bt.inOrderPredessor(bt.root,60));
		//System.out.println(bt.lowestCommonAncentor(bt.root, 20,30));
    }
}
