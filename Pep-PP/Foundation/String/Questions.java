public class Questions {

    // 1. PRINT ALL PALINDROMIC SUBSTRINGS ===========================
    public static void printSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String ans = str.substring(i, j);
                if (isPalindrome(ans)) {
                    System.out.println(ans);
                }
            }
        }
    }

    public static boolean isPalindrome(String str) {
        int sp = 0;
        int ep = str.length() - 1;
        int mid = (ep + 1) / 2;
        for (sp = 0; sp < str.length(); sp++) {
            if (str.charAt(sp) != str.charAt(ep)) {
                return false;
            }
            ep--;
        }
        return true;
    }



    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        printSubstrings(str);
    }
}