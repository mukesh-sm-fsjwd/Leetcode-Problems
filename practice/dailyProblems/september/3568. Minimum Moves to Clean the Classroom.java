import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Give each litter a number
        int[][] id = new int[m][n];

        for (int[] row : id) {
            Arrays.fill(row, -1);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                }

                if (ch == 'L') {
                    id[r][c] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        // All litter collected
        int allCollected = (1 << litterCount) - 1;

        /*
         * visited[row][column][energy][mask]
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        /*
         * State:
         * row, column, energy, mask, moves
         */
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {
            sr, sc, energy, 0, 0
        });

        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            // Done
            if (mask == allCollected) {
                return moves;
            }

            // No energy
            if (e == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                // Wall
                if (cell == 'X') {
                    continue;
                }

                int newEnergy = e - 1;
                int newMask = mask;

                // Recharge
                if (cell == 'R') {
                    newEnergy = energy;
                }

                // Collect litter
                if (cell == 'L') {
                    newMask = mask | (1 << id[nr][nc]);
                }

                if (visited[nr][nc][newEnergy][newMask]) {
                    continue;
                }

                visited[nr][nc][newEnergy][newMask] = true;

                queue.offer(new int[] {
                    nr,
                    nc,
                    newEnergy,
                    newMask,
                    moves + 1
                });
            }
        }

        return -1;
    }
}
