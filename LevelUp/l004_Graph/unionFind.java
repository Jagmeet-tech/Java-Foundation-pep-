public class unionFind{
    
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] par, size;

    public static int findParent(int u){
        if(par[u] == u)
            return u;
        return par[u] = findParent(par[u]);
        
        // return par[u] == u ? u : par[u] = find(par[u]);
    }

    public static void union(int par1,int par2){
        if(size[par1] < size[par2]){
            par[par1] = par2;
            size[par2] += size[par1];
        }else{
            par[par2] = par1;
            size[par1] += size[par2];
        }
    }

    // {{u1,v1,w1},{u2,v2,w2}...}
    public static void unionFind(int [][]edges){
        int n = edges.length;
        par = new int[n];
        size = new int[n];

        ArrayList<Edge>[] graph = new ArrayList<>();
        for(int i = 0;i<n;i++)
            graph[i] = new ArrayList; 
        
        for(int i=0;i<n;i++){
            par[i] = i;
            size[i] = 1;
        }

        for(int []edge : edges){
            int u = edge[0], v = edge[1], w = edge[2];

            int par1 = findParent(u);
            int par2 = findParent(v);
            
            if(par1 != par2){
                union(par1,par2);
                addEdge(graph,u,v,w);
            }
        }
    }
}