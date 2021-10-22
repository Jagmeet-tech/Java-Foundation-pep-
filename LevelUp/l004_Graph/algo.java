import java.util.ArrayList;
import java.util.PriorityQueue;

public class algo{
    static class Edge{
        int u = 0;
        int v = 0;
    }

    static int []disc, lowdisc;
    static int time = 0,rootCalls = 0;
    static boolean articulation[];
    static boolean vis[];


    public static void dfs(ArrayList<Edge>[] graph,int src,int par){
        disc[src] = lowdisc[src] = time++;
        vis[src] = true;

        for(Edge e : graph[src]){
            if(e.v == par)
                continue;
            else if(vis[e.v]){
                lowdisc[src] = Math.min(lowdisc[src] , disc[e.v]);
            }else{
                if(par == -1)
                    rootCalls++;
                dfs(graph,e.v,src);

                if(par == -1){
                    if(rootCalls >= 2)                  //root case handle in articulation point but no need in articulation edge
                        articulaion[src] = true;
                }else{
                    if(lowdisc[e.v] >= disc[src])       //condition for articulaion point.
                        articulation[src] = true;
                }

                if(lowdisc[e.v] > disc[src])            // condition for articulation edge.
                   System.out.println("Articulation Edge : ["+ src + " , " + e.v +"]");    

                lowdisc[src] = Math.min(lowdisc[src] , low[e.v]);
            }    
        }
    }

    public static void articulationPointAndBridges(ArrayList<Edge>[] graph , int N){
        disc = new int[N];
        lowdisc = new int[N];
        articulation = new boolean[N];
        vis = new boolean[N];

        for(int i = 0;i<N;i++){
            if(!vis[i])
                dfs(graph,i,-1);
        }
    }


    static class pair{
        int vtx, par, w, wsf;

        //dijkstra01 constructor
        pair(int vtx,int par,int w,int wsf){
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }

        //dijkstra02 constructor
        pair(int vtx,int wsf){
            this.vtx = vtx;
            this.wsf = wsf; 
        }
    }

    public static void dijkstras_01(ArrayList<Edge>[] graph, int src){
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        boolean []vis = new boolean[N];

        for(int i=0;i<N;i++)
            ngraph[i] = new ArrayList<>();
        
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf;   //for prims return a.w - b.w;
        })

        int []dist = new int[N];
        int []par = new int[N];
        Arrays.fill(dist,(int)1e9);
        Arrays.fill(par,-1);

        pq.add(new pair(src,-1,0,0));

        while(pq.size() != 0){
            pair p = pq.remove();

            if(vis[p.vtx])
                continue;

            if(p.par != -1)
                addEdge(ngraph,p.vtx,p.par,p.w);

            vis[p.vtx] = true;
            dist[p.vtx] = p.wsf;
            par[p.vtx] = p.par;
            for(Edge e : graph[p.vtx]){
                if(!vis[e.v])
                    pq.add(new pair(e.v,p.vtx,e.w,p.wsf + e.w));
            }
        }
    }

    public static void dijkstras_02(ArrayList<Edge>[] graph, int src){
        int N = graph.length;
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf; 
        })

        int []dist = new int[N];
        int []par = new int[N];
        Arrays.fill(dist,(int)1e9);
        Arrays.fill(par,-1);

        pq.add(new pair(src,0));
        dis[src] = 0;

        while(pq.size() != 0){
            pair p = pq.remove();

            if(p.wsf > dist[p.vtx])
                continue;
            for(Edge e : graph[p.vtx]){
                if(e.w + p.wsf < dist[e.v]){
                    dist[e.v] = e.w + p.wsf;
                    par[e.v] = p.vtx;
                    pq.add(new pair(e.v,p.vtx + e.w));
            
                }
            }
        }
    }


    public static class primsPair{
        int vtx,w;

        primsPair(int vtx,int w){
            this.vtx = vtx;
            this.w = w;
        }
    }

    //MST Algo..
    public static void prims(ArrayList<Edge>[] graph,int src){
        int n = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a,b)->{
            return a.w - b.w;
        })

        int []dist = new int[n];
        boolean []vis = new boolean[n];
        Arrays.fill(dist,(int)1e9);

        pq.add(new primsPair(src,0));
        dis[src] = 0;
        while(pq.size()!=0){
            primsPair p = pq.remove();

            if(vis[p.vtx])
                continue;

            vis[p.vtx] = true;
            for(Edge e : graph[p.vtx]){
                if(!vis[e.v] && e.w < dist[e.v]){
                    dist[e.v] = e.w;
                    pq.add(new primsPair(e.v,e.w));
                }
            }
        }
    }

    public static void bellmanFordAlgo(int [][]edges,int N,int src){
        int []prev = new int[N];
        Arrays.fill(prev,(int)1e9);
        prev[src] = 0;
        
        boolean negativeCycle = false;    
        for(int i = 1;i<=N;i++){
            int curr[] = new int[N];
            for(int j=0;j<N;j++)
            curr[j] = prev[j];
            
            boolean anyUpdate = false;
            for(int e[] : edges){
                int u = e[0], v = e[1], w = e[2];

                if(prev[u] != (int)1e9 && prev[u] + w < curr[v]){
                    curr[v] = prev[u] + w;
                    anyUpdate = true;

                    if(i == N){
                        negativeCycle = true;
                        break;
                    }
                }
            }    
            if(!anyUpdate)
                break;  
            prev = curr; 
        }
        System.out.println("Negative Cycle: " + negativeCycle);
    } 

    public static void bellmanFordAlgo_02(int N, int[][] edges, int src) {
        int[] curr = new int[N];
        Arrays.fill(curr, (int) 1e9);

        curr[src] = 0;
        boolean negativeCycle = false;
        for (int i = 1; i <= N; i++) {
            boolean anyUpdate = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (curr[u] != (int) 1e9 && curr[u] + w < curr[v]) {
                    curr[v] = curr[u] + w;
                    anyUpdate = true;
                    if (i == N) {
                        negativeCycle = true;
                        break;
                    }
                }
            }

            if (!anyUpdate)
                break;
        }

        System.out.println("Negative Cycle: " + negativeCycle);
    }

    // floyd warshall algorithm
    public static void floyadWarshall(int[][] edges, int n) {
        int[][] mat = new int[n][n];
        for (int[] d : mat)
            Arrays.fill(d, (int) 1e9);

        for (int[] e : edges)
            mat[e[0]][e[1]] = e[2];

        for (int i = 0; i < n; i++)
            mat[i][i] = 0;

        for (int k = 0; k < n; k++) {       // k : intermediate , i = src , j = dest
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
    }

    // SCC
    public static void dfs_topo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_topo(e.v, graph, vis, ans);
        }

        ans.add(src);
    }

    public static void dfs_SCC_Compo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> components) {
        vis[src] = true;
        components.add(src);

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_SCC_Compo(e.v, graph, vis, components);
        }
    }

    public static void kosaRaju(int N, ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < N; i++)
            if (!vis[i])
                dfs_topo(i, graph, vis, order);

        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            ArrayList<Edge> ar = graph[i];
            for (Edge e : ar) {
                ngraph[e.v].add(new Edge(i, e.w));
            }
        }

        for (int i = 0; i < N; i++)
            vis[i] = false;

        ArrayList<Integer> components = new ArrayList<>();
        for (int i = order.size() - 1; i >= 0; i--) {
            int vtx = order.get(i);
            if (!vis[vtx]) {
                dfs_SCC_Compo(vtx, ngraph, vis, components);
                System.out.println(components);
                components.clear();
            }
        }
    }

    // https://practice.geeksforgeeks.org/problems/mother-vertex/1

}