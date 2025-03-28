package twopointers;

/*
 * Valid Palindrome
 * Given a string s, return true if it is a palindrome, otherwise return false.
 *
 * A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all
 * non-alphanumeric characters.
 *
 * Example 1:
 *
 * Input: s = "Was it a car or a cat I saw?"
 *
 * Output: true
 * Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
 *
 * Example 2:
 *
 * Input: s = "tab a cat"
 *
 * Output: false
 * Explanation: "tabacat" is not a palindrome.
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s is made up of only printable ASCII characters.
 *
 * Recommended Time & Space Complexity
 * You should aim for a solution with O(n) time and O(1) space, where n is the length of the input string.
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        int i = moveToNextLetterOrDigit(0, s, true);
        int j = moveToNextLetterOrDigit(s.length() - 1, s, false);

        while (j > i) {
            char iValue = Character.toLowerCase(s.charAt(i));
            char jValue = Character.toLowerCase(s.charAt(j));
            if (iValue != jValue) {
                return false;
            } else {
                i = moveToNextLetterOrDigit(i + 1, s, true);
                j = moveToNextLetterOrDigit(j - 1, s, false);
            }
        }

        return true;
    }

    public static int moveToNextLetterOrDigit(int currentIndex, String s, boolean isLeftPointer) {
        char currentValue = s.charAt(currentIndex);
        if (isLeftPointer) {
            while (currentIndex < s.length() - 1 && !Character.isLetterOrDigit(currentValue)) {
                currentIndex++;
                currentValue = s.charAt(currentIndex);
            }
        } else {
            while (currentIndex > 0 && !Character.isLetterOrDigit(currentValue)) {
                currentIndex--;
                currentValue = s.charAt(currentIndex);
            }
        }

        return currentIndex;
    }

    public static void main(String[] args) {
        String s1 = "Was it a car or a cat I saw?";
        boolean isPalindrome1 = isPalindrome(s1);
        System.out.println("isPalindrome1=" + isPalindrome1);

        String s2 = "tab a cat";
        boolean isPalindrome2 = isPalindrome(s2);
        System.out.println("isPalindrome2=" + isPalindrome2);

        String s3 = "No lemon, no melon";
        boolean isPalindrome3 = isPalindrome(s3);
        System.out.println("isPalindrome3=" + isPalindrome3);

        String s4 = "0P";
        boolean isPalindrome4 = isPalindrome(s4);
        System.out.println("isPalindrome4=" + isPalindrome4);
    }
}
