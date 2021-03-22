public class LinkedList {
    Node head;
    Node tail;
    int size;

    public void insert(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void insert(Node newNode){
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void display(){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+"-> ");
            temp = temp.next;
        }
        System.out.print("NULL\n");
    }

    public void insertAt(int data, int k){

    }
}
