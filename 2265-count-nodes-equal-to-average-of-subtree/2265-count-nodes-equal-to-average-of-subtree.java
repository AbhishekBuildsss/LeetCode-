class Solution {

    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // Returns:
    // [0] = sum of subtree
    // [1] = number of nodes in subtree
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Process left subtree
        int[] left = dfs(node.left);

        // Process right subtree
        int[] right = dfs(node.right);

        // Calculate sum and number of nodes
        int sum = node.val + left[0] + right[0];
        int nodes = 1 + left[1] + right[1];

        // Calculate floor average
        int average = sum / nodes;

        // Check if current node equals average
        if (node.val == average) {
            count++;
        }

        return new int[]{sum, nodes};
    }
}