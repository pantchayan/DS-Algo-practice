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


    // find intersection
    public static void intersection(LinkedList ll1, LinkedList ll2){
        int diff = (ll1.size>ll2.size)?ll1.size-ll2.size:ll2.size-ll1.size;

        Node head1 = ll1.head;
        Node head2 = ll2.head;

        if (ll1.size>ll2.size) {
            while(diff-->0){
                head1 = head1.next;
            }
        }
        else{
            while(diff-->0){
                head2 = head2.next;
            }
        }
        while(head1!=head2){
            head1 = head1.next;
            head2 = head2.next;
        }

        System.out.println("Intersection element :"+ head1.data);
    }

    public static void intersectionMain(LinkedList ll1){
        LinkedList ll2 = new LinkedList();

        ll2.insert(80);
        ll2.insert(90);
        ll2.insert(100);

        Node temp = ll1.head;
        int k=4;
        while(k-->0){
            temp=temp.next;
        }
        while(temp!=null){
            ll2.insert(temp);
            temp = temp.next;
        }

        ll2.display();
        System.out.println(ll2.head.data +" " + ll2.tail.data);


        intersection(ll1, ll2);

    }

    public static void oddEven(LinkedList ll1){
        Node fwd = ll1.head;
        Node trav = ll1.head;
        Node prev = ll1.head;
        while(trav!=null){  
            if(trav.data%2!=0){
                Node temp = trav.next;
                prev.next = trav;
                trav.next = fwd;
                fwd = fwd.next;
                prev = trav;

                trav = temp;
                continue;
            }
            trav = trav.next;
        }

        ll1.display();
    }


    public static void main(String[] args){
        LinkedList ll1 = new LinkedList();
        
        ll1.insert(2);
        ll1.insert(9);
        ll1.insert(7);
        ll1.insert(6);
        ll1.insert(5);
        ll1.insert(4);
        ll1.insert(12);
        ll1.insert(3);

        ll1.display();
        System.out.println(ll1.head.data +" " + ll1.tail.data);

        oddEven(ll1);
        // intersectionMain(ll1);
        // middleElement(ll1.head);
        // kthLastElement(ll1.head, 2);
    }
}