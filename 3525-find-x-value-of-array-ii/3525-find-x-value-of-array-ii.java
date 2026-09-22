import java.util.*;

class Solution {

    int n, k;
    Node[] tree;

    class Node {
        int product;
        int[] count;

        Node() {
            count = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.count[x];
        }

        return ans;
    }

    void build(int p, int l, int r, int[] nums) {
        tree[p] = new Node();

        if (l == r) {
            int x = nums[l] % k;
            tree[p].product = x;
            tree[p].count[x] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(p * 2, l, mid, nums);
        build(p * 2 + 1, mid + 1, r, nums);

        merge(p, tree[p * 2], tree[p * 2 + 1]);
    }

    void update(int p, int l, int r, int index, int value) {
        if (l == r) {
            Arrays.fill(tree[p].count, 0);

            int x = value % k;
            tree[p].product = x;
            tree[p].count[x] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(p * 2, l, mid, index, value);
        } else {
            update(p * 2 + 1, mid + 1, r, index, value);
        }

        merge(p, tree[p * 2], tree[p * 2 + 1]);
    }

    void merge(int p, Node a, Node b) {

        Arrays.fill(tree[p].count, 0);

        // Prefixes completely inside left part
        for (int i = 0; i < k; i++) {
            tree[p].count[i] += a.count[i];
        }

        // Prefixes that continue into right part
        for (int i = 0; i < k; i++) {
            int rem = (a.product * i) % k;
            tree[p].count[rem] += b.count[i];
        }

        tree[p].product = (a.product * b.product) % k;
    }

    Node query(int p, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[p];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(p * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(p * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(p * 2, l, mid, ql, qr);
        Node right = query(p * 2 + 1, mid + 1, r, ql, qr);

        Node result = new Node();
        mergeNodes(result, left, right);

        return result;
    }

    void mergeNodes(Node result, Node a, Node b) {

        for (int i = 0; i < k; i++) {
            result.count[i] = a.count[i];
        }

        for (int i = 0; i < k; i++) {
            int rem = (a.product * i) % k;
            result.count[rem] += b.count[i];
        }

        result.product = (a.product * b.product) % k;
    }
}