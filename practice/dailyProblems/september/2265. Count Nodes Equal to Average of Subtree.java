class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // Returns an array of size 2: [sum of subtree, number of nodes in subtree]
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // Traverse left and right subtrees
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Calculate current subtree sum and count
        int sum = left[0] + right[0] + node.val;
        int n = left[1] + right[1] + 1;

        // Check if the node's value equals the average of its subtree
        if (sum / n == node.val) {
            count++;
        }

        return new int[]{sum, n};
    }
}
