
import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] tin, tout, depth, parent, bit;
    int[][] up;
    int timer;
    char[] s;
    int[] fw;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.s = s.toCharArray();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];

        int log = 1;
        while ((1 << log) <= n) log++;
        up = new int[log][n];

        timer = 0;
        dfs(0, -1);

        fw = new int[2 * n + 2];
        for (int i = 0; i < n; i++) {
            int mask = 1 << (this.s[i] - 'a');
            add(tin[i], mask);
            add(tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);
                int change = (1 << (this.s[u] - 'a')) ^ (1 << (c - 'a'));
                add(tin[u], change);
                add(tout[u], change);
                this.s[u] = c;
            } else {
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int mask = prefix(tin[u]) ^ prefix(tin[v])
                         ^ (1 << (this.s[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    void dfs(int u, int p) {
        parent[u] = p == -1 ? u : p;
        up[0][u] = parent[u];
        tin[u] = ++timer;

        for (int j = 1; j < up.length; j++)
            up[j][u] = up[j - 1][up[j - 1][u]];

        for (int v : adj[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u);
        }

        tout[u] = ++timer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }

        int d = depth[u] - depth[v];
        for (int j = 0; j < up.length; j++)
            if ((d & (1 << j)) != 0) u = up[j][u];

        if (u == v) return u;

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    void add(int i, int val) {
        for (; i < fw.length; i += i & -i)
            fw[i] ^= val;
    }

    int prefix(int i) {
        int ans = 0;
        for (; i > 0; i -= i & -i)
            ans ^= fw[i];
        return ans;
    }
}
