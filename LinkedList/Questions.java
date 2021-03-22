public class Questions{

    // kth element from last
    public static void kthLastElement(Node head, int k){
        Node fast = head;
        while(k-->0){
            if(fast==null){
                System.out.println("Invalid value of K (overflow)");
                return;
            }
            fast = fast.next;
        }

        Node slow = head;

        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }

        System.out.println("Element is : "+slow.data);

    }
    
    // Middle of LinkedList 
    public static void middleElement(Node head){
        Node slow = head;
        Node fast = head;

        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("Middle element :"+ slow.data);
    }




    public static void main(String[] args){
        LinkedList ll1 = new LinkedList();
        
        ll1.insert(10);
        ll1.insert(20);
        ll1.insert(30);
        ll1.insert(40);
        ll1.insert(50);
        ll1.insert(60);
        ll1.insert(70);

        ll1.display();

        System.out.println(ll1.head.data +" " + ll1.tail.data);

        middleElement(ll1.head);
        kthLastElement(ll1.head, 2);
    }
}