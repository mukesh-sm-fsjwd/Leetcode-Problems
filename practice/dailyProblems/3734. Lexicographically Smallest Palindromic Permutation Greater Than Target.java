public class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int length = s.length();

        // Count how many times each character appears in the string
        int[] characterFrequency = new int[26];

        for (char character : s.toCharArray()) {
            characterFrequency[character - 'a']++;
        }

        // A palindrome can have only one character with an odd frequency
        int oddCharacterCount = 0;
        int middleCharacter = -1;

        for (int i = 0; i < 26; i++) {

            if (characterFrequency[i] % 2 != 0) {
                oddCharacterCount++;
                middleCharacter = i;
            }
        }

        // If there are two or more odd frequencies,
        // it is impossible to make a palindrome
        if (oddCharacterCount > 1) {
            return "";
        }

        int halfLength = length / 2;

        // We only need half of the characters.
        // The other half will be its reverse.
        int[] halfCharacterFrequency = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCharacterFrequency[i] = characterFrequency[i] / 2;
        }

        // Only the first half matters when comparing two palindromes.
        char[] targetFirstHalf = target.substring(0, halfLength).toCharArray();

        /*
         * First, check whether we can use exactly the same
         * first half as the target.
         *
         * If we can, the middle and second half might still
         * make the complete palindrome greater than target.
         */
        int[] remainingCharacters = halfCharacterFrequency.clone();

        boolean canMakeTargetFirstHalf = true;

        for (char character : targetFirstHalf) {

            int characterIndex = character - 'a';

            if (remainingCharacters[characterIndex] == 0) {
                canMakeTargetFirstHalf = false;
                break;
            }

            remainingCharacters[characterIndex]--;
        }

        if (canMakeTargetFirstHalf) {

            char[] palindrome = createPalindrome(
                    targetFirstHalf,
                    length,
                    middleCharacter
            );

            if (compareStrings(palindrome, target) > 0) {
                return new String(palindrome);
            }
        }

        /*
         * The target first half was either impossible to make,
         * or it created a palindrome that was not greater than target.
         *
         * So now we need to make the first half slightly bigger.
         *
         * We start from the right side because we want the
         * smallest possible palindrome that is greater than target.
         */
        for (int changePosition = halfLength - 1;
             changePosition >= 0;
             changePosition--) {

            remainingCharacters = halfCharacterFrequency.clone();

            // Keep the characters before changePosition the same
            boolean canKeepPrefix = true;

            for (int position = 0;
                 position < changePosition;
                 position++) {

                int characterIndex = targetFirstHalf[position] - 'a';

                if (remainingCharacters[characterIndex] == 0) {
                    canKeepPrefix = false;
                    break;
                }

                remainingCharacters[characterIndex]--;
            }

            if (!canKeepPrefix) {
                continue;
            }

            int targetCharacter = targetFirstHalf[changePosition] - 'a';

            /*
             * Try the smallest character that is greater than
             * the target character at this position.
             */
            for (int characterIndex = targetCharacter + 1;
                 characterIndex < 26;
                 characterIndex++) {

                if (remainingCharacters[characterIndex] == 0) {
                    continue;
                }

                char[] firstHalf = new char[halfLength];

                // Copy the part that is exactly the same as target
                for (int position = 0;
                     position < changePosition;
                     position++) {

                    firstHalf[position] = targetFirstHalf[position];
                }

                // Make this position slightly bigger than target
                firstHalf[changePosition] =
                        (char) ('a' + characterIndex);

                remainingCharacters[characterIndex]--;

                /*
                 * After making one character bigger,
                 * we should use the smallest available characters
                 * for all remaining positions.
                 *
                 * This gives us the smallest possible answer.
                 */
                int position = changePosition + 1;

                for (int smallerCharacter = 0;
                     smallerCharacter < 26;
                     smallerCharacter++) {

                    while (remainingCharacters[smallerCharacter] > 0) {

                        firstHalf[position] =
                                (char) ('a' + smallerCharacter);

                        position++;
                        remainingCharacters[smallerCharacter]--;
                    }
                }

                // Create the complete palindrome
                char[] palindrome = createPalindrome(
                        firstHalf,
                        length,
                        middleCharacter
                );

                if (compareStrings(palindrome, target) > 0) {
                    return new String(palindrome);
                }
            }
        }

        // No palindrome permutation is greater than target
        return "";
    }

    private char[] createPalindrome(
            char[] firstHalf,
            int length,
            int middleCharacter) {

        char[] palindrome = new char[length];

        int halfLength = length / 2;

        // Put the first half on the left and its reverse on the right
        for (int position = 0;
             position < halfLength;
             position++) {

            palindrome[position] = firstHalf[position];

            palindrome[length - 1 - position] =
                    firstHalf[position];
        }

        // If length is odd, put the odd-frequency character in the middle
        if (length % 2 != 0) {
            palindrome[halfLength] =
                    (char) ('a' + middleCharacter);
        }

        return palindrome;
    }

    private int compareStrings(char[] firstString, String secondString) {

        // Compare both strings character by character
        for (int position = 0;
             position < firstString.length;
             position++) {

            if (firstString[position] != secondString.charAt(position)) {
                return firstString[position] - secondString.charAt(position);
            }
        }

        // Both strings are equal
        return 0;
    }
}
