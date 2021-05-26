// We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
//
// Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
//
// Return true if sentence1 and sentence2 are similar, or false if they are not similar.
//
// Two sentences are similar if:
//
//They have the same length (i.e. the same number of words)
//sentence1[i] and sentence2[i] are similar.
//Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words a and b are similar and the words b and c are similar, a and c are not necessarily similar.

import java.util.*;

// Goal: Compare if sentence1 and sentence2 are similar
// Edge Case: If the length of s1 != s2, return false
// For the similarPairs List, convert to a Map <Wordi, <HashSet of words similar to Wordi>>
// Iterate s1 and s2 together.
// If s1 not equals to s2, check:
// Case 1: s1 not in map or s1 in map but its set doesn't contain s2 return false
// Case 2: s2 not in map or s2 in map but its set doesn't contain s1 return false
// Only return true at the end
// Time: O(n), Space: O(1)
class Solution {
    public static void main(String[] args) {
        String [] s1 = new String[]{"great","acting","skills"};
        String [] s2 = new String[]{"fine","drama","talent"};
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(new String []{"great","fine"}));
        list.add(Arrays.asList(new String []{"drama","acting"}));
        list.add(Arrays.asList(new String []{"skills","talent"}));

        System.out.println(new Solution().areSentencesSimilar(s1, s2, list));
    }
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length) {
            return false;
        }
        HashMap<String, HashSet<String>> map = new HashMap();
        for(List<String> list : similarPairs) {
            String w1 = list.get(0);
            String w2 = list.get(1);
            map.computeIfAbsent(w1, x -> new HashSet()).add(w2);
            map.computeIfAbsent(w2, x -> new HashSet()).add(w1);
        }

        int n = sentence1.length;
        for(int i = 0; i < n; i++) {
            String s1 = sentence1[i];
            String s2 = sentence2[i];
            if(!s1.equals(s2)) {
                if(!map.containsKey(s1) || !map.get(s1).contains(s2)) {
                    return false;
                }
                else if(!map.containsKey(s2) || !map.get(s2).contains(s1)) {
                    return false;
                }
            }
        }

        return true;
    }
}
