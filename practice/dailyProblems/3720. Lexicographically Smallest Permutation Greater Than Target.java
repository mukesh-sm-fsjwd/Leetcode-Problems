import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Try to match the prefix of target using characters from s
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n && count[target.charAt(i) - 'a'] > 0) {
            count[target.charAt(i) - 'a']--;
            sb.append(target.charAt(i));
            i++;
        }
        
        // If we matched the entire string, we need to backtrack and find a larger character
        if (i == n) {
            while (i > 0) {
                i--;
                char prev = sb.charAt(i);
                sb.deleteCharAt(i);
                count[prev - 'a']++;
                
                // Find a character strictly greater than target.charAt(i)
                for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--;
                        sb.append((char) ('a' + c));
                        // Fill the rest with the smallest available characters
                        appendRemaining(sb, count, n - sb.length());
                        return sb.toString();
                    }
                }
            }
            return "";
        } else {
            // At index i, pick the smallest available character strictly greater than target.charAt(i)
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;
                    sb.append((char) ('a' + c));
                    appendRemaining(sb, count, n - sb.length());
                    return sb.toString();
                }
            }
            
            // If no character is greater, backtrack further
            while (i > 0) {
                i--;
                char prev = sb.charAt(i);
                sb.deleteCharAt(i);
                count[prev - 'a']++;
                
                for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--;
                        sb.append((char) ('a' + c));
                        appendRemaining(sb, count, n - sb.length());
                        return sb.toString();
                    }
                }
            }
            return "";
        }
    }
    
    private void appendRemaining(StringBuilder sb, int[] count, int rem) {
        for (int c = 0; c < 26 && rem > 0; c++) {
            while (count[c] > 0 && rem > 0) {
                sb.append((char) ('a' + c));
                count[c]--;
                rem--;
            }
        }
    }
}
