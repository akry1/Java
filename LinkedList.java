/**
 * Created by aravind on 2/2/2016.
 */
public class LinkedList {

    Node head;

    static class Node{
        int value;
        Node next;

        Node(int value){
            this.value = value;
        }
    }

    public void print(){
        Node current = head;
        while(current != null){
            System.out.println(current.value);
            current = current.next;
        }
    }

    public void reversePrint(Node head){
        if(head == null) return;
        reversePrint(head.next);
        System.out.println(head.value);
    }

    public void insertAtStart(int val){
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtAddress(Node node, int val){
        Node temp = node.next;
        node.next = new Node(val);
        node.next.next = temp;
    }

    public void insertAtEnd(int val){
        if (head==null) {
            head=new Node(val);
            return;
        }
        Node next = head;
        while(next.next!=null){
            next = next.next;
        }
        next.next = new Node(val);
    }

    public void reverseList(){
        if(head==null) return;
        Node prev = null;
        Node current = head;
        Node next;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public Node findMid(){
        if(head==null) return null;
        if(head.next==null) return head;
        if(head.next.next==null) return head.next;
        Node nextNode = head.next;
        Node nextNextNode = head.next.next;
        while(nextNextNode.next!=null){
            nextNode = nextNode.next;
            nextNextNode = nextNextNode.next.next;
            if(nextNextNode==null) break;
        }
        return nextNode;
    }

    public void deleteNode(int val){
        if(head==null) return;
        Node prev = null;
        Node current = head;
        while(current!=null){
            if (current.value == val){
                if(prev==null)
                    head = head.next;
                else
                    prev.next = current.next;
                break;
            }
            prev = current;
            current = current.next;
        }
    }

    public void deleteNodeAtPos(int val){
        if(head==null) return;
        int pos = 0;
        Node prev = null;
        Node current = head;
        while(current!=null){
            if (pos == val){
                if(prev==null)
                    head = head.next;
                else
                    prev.next = current.next;
                break;
            }
            prev = current;
            current = current.next;
            pos++;
        }
    }

    public void swapnodes(int x,int y){
        if (head==null|x==y) return;
        Node current= head;
        Node prev = null;
        Node first = null;
        Node firstPrev=null;
        Node second=null;
        Node secondPrev = null;

        while (current!=null){
            if(current.value==x || current.value==y){
                if(first ==null){
                    first = current;
                    firstPrev = prev;
                }
                else{
                    second = current;
                    secondPrev = prev;
                }
            }
            prev = current;
            current=current.next;
        }

        if (first == null || second==null)
            return;
        else
        {
            if(firstPrev!=null)
                firstPrev.next = second;
            else{
                head=second;
            }
            secondPrev.next = first;
            Node temp = second.next;
            second.next = first.next;
            first.next = temp;
        }
        return;
    }

    public Node mergeSorted(LinkedList a, LinkedList b){
        if(a==null&&b==null) return null;
        if(a==b) return null;
        else if (a==null) return b.head;
        else if (b==null) return a.head;

        Node currentA = a.head;
        Node currentB = b.head;
        Node result = currentA;
        Node prev = null;
        while(currentA!=null ){
            if (currentB==null) break;
            if(currentA.value < currentB.value) {
                prev= currentA;
                currentA = currentA.next;
            }
            else{
                Node BNext = currentB.next;
                currentB.next = currentA;
                if(prev!=null) {
                    prev.next = currentB;
                    prev = prev.next;
                }
                else{
                    prev = currentB;
                    result = currentB;
                }
                currentB = BNext;
            }
        }
        if(currentB!=null) prev.next = currentB;
        return result;
    }


    public Node mergeSortedRecursive(Node a, Node b){
        if(a==null&&b==null) return null;
        if (a==b) return null;
        else if (a==null) return b;
        else if (b==null) return a;
        Node result = null;
        if(a.value < b.value) {
            result = a;
            result.next = mergeSortedRecursive(a.next,b);
        }
        else{
            result = b;
            result.next = mergeSortedRecursive(a,b.next);
        }
        return result;
    }

    public Boolean findLoop(){
        if (head==null || head.next==null) return false;
        Node current = head.next;
        Node dCurrent = head.next.next;
        while(dCurrent!=null){
            if(current==dCurrent) return true;
            current = current.next;
            if(dCurrent.next!=null)
                dCurrent = dCurrent.next.next;
            else break;
        }
        return false;
    }

    public void removeLoop(){
        if (head==null || head.next==null) return;
        Node current = head.next;
        Node dCurrent = head.next.next;
        while(dCurrent!=null){
            if(current==dCurrent) break;
            current = current.next;
            if(dCurrent.next!=null)
                dCurrent = dCurrent.next.next;
            else return;
        }
        if(dCurrent==null) return;
        int loopsize =1;
        current = current.next;
        while(current!=dCurrent) {
            current = current.next;
            loopsize += 1;
        }

        if(loopsize==1) current.next = null;
        else{
            int temp =loopsize;
            current = head;
            while(temp!=0){
                current=current.next;
                temp -=1;
            }
            dCurrent = head;
            while(loopsize!=0){
                current = current.next;
                dCurrent = dCurrent.next;
                loopsize -=1;
            }
            current.next = null;
        }
    }

    public void reverseInGroups(int size){

    }

    public Node sum(Node a, Node b){
        return null;
    }
    public void rotateList(int dist){

    }


    public static void main(String []args){
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);

        LinkedList ll = new LinkedList();

        ll.head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        //ll.print();
        //ll.reversePrint(ll.head);
        //ll.insertAtStart(40);
        //ll.insertAtAddress(node2,50);
        //ll.insertAtEnd(50);
        //ll.reverseList();
        //Node mid = ll.findMid();
        //if (mid!=null) System.out.println(mid.value);

        LinkedList ll2 = new LinkedList();
        for(int i=5;i<10;i++){
            ll2.insertAtEnd(i);
        }


        //ll.deleteNodeAtPos(12);
        //ll.swapnodes(4,15);
        //Node merged = ll.mergeSorted(ll2,ll);
        //Node merged = ll.mergeSortedRecursive(ll2.head,ll.head);
        //LinkedList ll3 = new LinkedList();
        //ll3.head = merged;

        //ll.print();
        //System.out.print(ll.findLoop());
        ll.removeLoop();

        ll.print();


    }
}
