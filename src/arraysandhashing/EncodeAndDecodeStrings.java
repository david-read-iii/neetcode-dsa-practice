package arraysandhashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Encode and Decode Strings
 *
 * Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the
 * original list of strings.
 *
 * Please implement encode and decode
 *
 * Example 1:
 *
 * Input: ["neet","code","love","you"]
 * Output:["neet","code","love","you"]
 *
 * Example 2:
 *
 * Input: ["we","say",":","yes"]
 * Output: ["we","say",":","yes"]
 *
 * Constraints:
 *
 * 0 <= strs.length < 100
 * 0 <= strs[i].length < 200
 * strs[i] contains only UTF-8 characters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m) time for each encode() and decode() call and O(m+n) space, where m is the sum
 * of lengths of all the strings and n is the number of strings.
 */
public class EncodeAndDecodeStrings {

    private static final char DELIMITER = '␞';

    public static String encode(List<String> strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strs) {
            stringBuilder.append(s).append(DELIMITER);
        }
        return stringBuilder.toString();
    }

    public static List<String> decode(String str) {
        List<String> decodedList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == DELIMITER) {
                decodedList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(c);
            }
        }
        return decodedList;
    }

    public static void printExample(List<String> strs, String exampleId) {
        String encode = encode(strs);
        System.out.println("encode" + exampleId + "=" + encode);
        List<String> decode = decode(encode);
        System.out.println("decode" + exampleId + "=" + decode);
    }

    public static void main(String[] args) {
        printExample(new ArrayList<>(Arrays.asList("neet", "code", "love", "you")), "1");
        printExample(new ArrayList<>(Arrays.asList("we", "say", ":", "yes")), "2");
    }
}
