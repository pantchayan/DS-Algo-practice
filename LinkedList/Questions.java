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
    }
    // DISPLAY REVERSE USING RECURSION
    public static void displayReverse(Node head){
        if(head==null) return;

        displayReverse(head.next);
        System.out.print(head.data+" ");
    }

    // REVERSE A LL USING RECURSION
    public static void reverseRecurPtrHelper(Node head){
        if(head == null){
            return;
        }

        reverseRecurPtrHelper(head.next);
        if(head.next!=null){
            head.next.next = head;
        }
    }


    public static void reverseRecursivePtr(LinkedList ll){
        reverseRecurPtrHelper(ll.head);
        ll.head.next = null;
        Node temp = ll.head;
        ll.head = ll.tail;
        ll.tail = temp;
    }
    
    // PALINDROME USING RECURSION
    static Node left;
    private static boolean isPalindromeHelper(Node head) {
        if(head==null){
            return true;
        } 

        boolean flag = false;
        
        flag = isPalindromeHelper(head.next);
        if(flag){
            if(left.data == head.data){
                left = left.next;
                return true;
            } 
            else return false;
        }
        else{
            return false;
        }
    }


    public static void isPalindromic(){
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(3);
        ll.insert(2);
        ll.insert(1);

        left = ll.head;
        if(isPalindromeHelper(ll.head)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }


    // FOLDING LINKEDLIST
    static Node temp;
    static int size;
    public static void foldLLHelper(Node head,int idx){
        if(head==null) return;

        foldLLHelper(head.next, idx+1);

        if(idx>size/2){
            Node nbr = temp.next;
            temp.next = head;
            head.next = nbr;
            temp = nbr;
        }
        else if(size/2 == idx){
            head.next = null;
        }
    }
    public static void foldLL(){
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);

        ll.display();

        temp = ll.head;
        size = ll.size;
        foldLLHelper(ll.head,0);

        ll.display();
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

        // oddEven(ll1);

        // displayReverse(ll1.head);

        // reverseRecursivePtr(ll1);
        // ll1.display();

       foldLL();
        // intersectionMain(ll1);
        // middleElement(ll1.head);
        // kthLastElement(ll1.head, 2);
    }
}