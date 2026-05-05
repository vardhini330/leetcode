class Solution {
    public boolean containsCycle(char[][] grid) {
        int width = grid[0].length;
        int[] parents = new int[grid.length * width+1];
        //Arrays.fill(parents, -1);
        char[] prev = new char[width];
        int idx = 1;
        for (int i = 0; i < grid.length; i++) {
            char last = 0;
            char[] cur = grid[i];
            for (int j = 0; j < width; j++) {
                char c = cur[j];
                if (c == last) {
                    if (join(parents, idx, idx-1)) return true;
                }
                if (c == prev[j]) {
                    if (join(parents, idx, idx-width)) return true;
                }
                last = c;
                idx ++;
            }
            prev = cur;
        }
        return false;
    }

    boolean join(int[] parents, int a, int b) {
        int pA = getParent(parents, a);
        int pB = getParent(parents, b);
        if (pA == pB) return true;
        parents[pA] = pB;
        return false;
    }

    int getParent(int[] parents, int a) {
        int p = parents[a];
        if (p == 0) return a;
        return parents[a] = getParent(parents, p);
    }
}
