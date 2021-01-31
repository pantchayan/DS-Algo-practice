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

    // STRING-COMPRESSION-OFFICIAL ========================================
    // Sample Input
    // wwwwaaadexxxxxx
    // Sample Output
    // wadex
    // w4a3dex6

    public static String compression1(String str) {
        // write your code here
        char prev = str.charAt(0);

        String ans = "";
        ans = ans + prev;
        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr != prev) {
                ans += curr;
                prev = curr;
            }
        }

        return ans;
    }

    public static String compression2(String str) {
        char prev = str.charAt(0);

        String ans = "";
        ans = ans + prev;

        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr != prev) {
                if (count > 1)
                    ans += count;
                ans += curr;
                prev = curr;
                count = 0;
            }

            count++;
        }
        if (count > 1)
            ans += count;

        return ans;
    }

    // TOGGLE-CASE official ===============================================================================

    public static String toggleCase(String str) {

        String ans = "";

        for (int i = 0; i < str.length(); i++) {
            int asc = (int) str.charAt(i);
            if (asc >= 65 && asc <= 90) {
                ans += (char) ((asc - 'A') + 'a');
            } else if (asc >= 97 && asc <= 122) {
                ans += (char) ((asc - 'a') + 'A');
            } else {
                ans += str.charAt(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        printSubstrings(str);
    }
}