class Solution {
    public String smallestSubsequence(String str) {
        int[] freq = new int[26];
        for(char c : str.toCharArray()) freq[c-'a'] += 1;
        boolean[] used = new boolean[26];
        ArrayDeque<Character> s = new ArrayDeque<>();
        for(char c : str.toCharArray()){
            freq[c - 'a']--;
            if(used[c-'a']) continue;
            while(!s.isEmpty() && s.peek() > c && freq[s.peek() - 'a'] > 0){
                used[s.pop() - 'a'] = false;
            }
            s.push(c);
            used[c-'a'] = true;
        }
        StringBuilder res = new StringBuilder();
        for(char c : s) res.append(c);
        return res.reverse().toString();
    }
}
