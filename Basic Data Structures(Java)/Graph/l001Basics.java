import java.util.ArrayList;
import java.util.PriorityQueue;

public class l001Basics{
    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }

        public String toString(){
            return "( " + this.v + ", " + this.w + ")";
        }
    } 

    public static int N=7;
    public static ArrayList<Edge>[] graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int findEdge(int u,int v){
        for(int i=0;i < graph[u].size();i++){
            Edge e = graph[u].get(i);
            if(e.v == v)
                return i;
        }
        return -1;
    }

    public static void removeEdge(int u,int v){
        int idx1=findEdge(u,v);
        int idx2=findEdge(v,u);

        // if (idx1 == -1 || idx2 == -1)
        // return;

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    } 

    public static void removeVtx(int u,int v){
        while(graph[u].size() != 0){
            int n = graph[u].size();
            Edge e = graph[u].get(n-1);
            removeEdge(u,e.v);
        }
    }

    public static boolean hasPath(int src,int dest,boolean []vis){
        if(src == dest)
            return true;
        
        boolean res = false;    
        vis[src] = true;

        for(Edge e : graph[src]){
            if(!vis[e.v])
                res = res || hasPath(e.v,dest,vis);
        }    

        return res;
    }

    public static int allPaths(int src,int dest,boolean []vis,String psf){
        if(src == dest){
            System.out.println(psf + dest);
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                count += allPaths(e.v,dest,vis,psf + src);
        }
        
        vis[src]= false;
        return count;
    }

    public static void printPreOrder(int src,boolean[] vis,String psf,int wsf){

        System.out.println(src + " -> " + psf + src + " @ " + wsf);
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                printPreOrder(e.v,vis,psf + src, wsf + e.w);
        }

        vis[src]=false;
    }


    public static void printPostOrder(int src,boolean[] vis,String psf,int wsf){

        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
            printPostOrder(e.v,vis,psf + src, wsf + e.w);
        }
        
        System.out.println(src + " -> " + psf + src + " @ " + wsf);
        vis[src]=false;
    }

    public static class pair{
        int largestWeight = -(int) 1e9;
        String largestPath = "";
        int smallestWeight = (int) 1e9;
        String smallestPath = "";

        int ceil = (int) 1e9;
        String ceilPath = "";
        int floor = -(int) 1e9;
        String floorPath = "";
    }

    public static class pqPair {
        int wsf = 0;
        String psf = "";

        pqPair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }
    }

    static PriorityQueue<pqPair> pq= new PriorityQueue<>((a,b) ->{
        return a.wsf - b.wsf;
    });

    public static void allSolution(int src, int dest, boolean[] vis, pair p, String psf, int wsf, int givenWeight,int k){
        if(src == dest){
            if(wsf > p.largestWeight){
                p.largestWeight = wsf;
                p.largestPath = psf + dest; 
            }

            if(wsf < p.smallestWeight){
                p.smallestWeight = wsf;
                p.smallestPath = psf + dest; 
            }

            if(wsf < givenWeight && wsf > p.floor){
                p.floor = wsf;
                p.floorPath = psf + dest;
            }

            if(wsf > givenWeight && wsf < p.ceil){
                p.ceil = wsf;
                p.ceilPath = psf + dest;
            }

            pq.add(new pqPair(wsf, psf + dest));
            if (pq.size() > k)
                pq.remove();

            return;
        }
    
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                 allSolution(e.v,dest,vis,p,psf +src ,wsf + e.w,givenWeight,k);
        }
        
        vis[src]= false;
    }

    public static void dfs(int src, boolean []vis){
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                dfs(e.v,vis);
        }
    }

    //get connected components
    public static void gcc(){
        boolean vis[] = new boolean[N];
        int components=0;

        for(int i = 0;i<N;i++){
            if(!vis[i]){
                components++;
                dfs(i,vis);
            }
        }
        System.out.println(components);
    }

    //isGraphConnected - only 1 component is present
    public static boolean isGraphConnected(){
        boolean []vis = new boolean[N];
        int components = 0;
        for(int i=0;i<N;i++){
            if(!vis[i]){
                dfs(i,vis);
                components++;
            }
        }
        return components == 1;
    }


    public static void dfs_island(int [][]mat,int i,int j,int [][]dir,int n,int m){
        mat[i][j] = 0;  
        for(int d=0;d<4;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r>=0 && c>=0 && r<n && c<m && mat[r][c] == 1)
                dfs_island(mat,r,c,dir,n,m);
        }
    }


    public static int numberIsland(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int count=0;
        int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j] == 1){
                    count++;
                    dfs_island(mat,i,j,dir,n,m);
                }
            }
        }
        return count;
    } 


    public static void hamintonian_dfs(int src, int osrc, boolean[] vis, int NoOfEdges, String psf) {
        if(NoOfEdges == N-1){
            System.out.print(psf + src);
            int idx = findEdge(src, osrc);
            if (idx != -1)
                System.out.print("*");

            System.out.println();
            return;
        }
        
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                hamintonian_dfs(e.v,osrc,vis,NoOfEdges + 1,psf + src);
        }

        vis[src] = false;
    }

    public static void hamintonianPath(){
        boolean vis[] = new boolean[N];
        hamintonian_dfs(0, 0, vis, 0, "");
    }

    //Moon problem
    public static int dfs_moon(ArrayList<Integer> []graph,int src,boolean []vis){
        vis[src] = true;
        int size = 0;
        for(Integer v : graph[src]){
            if(!vis[v])
                size += dfs_moon(graph,v,vis);
        }

        return size + 1;
    }

    public static long journeyToMoon(int n, int[][] astronaut) {
        ArrayList<Integer> []graph= new ArrayList[N];
        for(int i=0;i<N;i++)
            graph[i]= new ArrayList<>();

        for(int a[] : astronaut){
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }
        
        ArrayList<Integer> sizeArray = new ArrayList<>();
        boolean []vis = new boolean[N]; 
        for(int i=0;i<N;i++){
            if(!vis[i])
                sizeArray.add(dfs_moon(graph,i,vis));
        }

        long ssf=0,res=0;
        for(int ele : sizeArray){
            res += ele * ssf;
            ssf += ele;
        }

        return res;
    }


    public static void BFS_WithCycle(int src,boolean []vis){
        LinkedList<Integer> queue = new LinkedList<>();
        int level=0,cycleCount=0;
        queue.addLast(src);

        while(queue.size()!=0){
            int size = queue.size();
            System.out.print(level + "->");
            while(size--> 0){
                int rvtx= queue.removeFirst();

                if(vis[rvtx]){
                    cycleCount++;
                    continue;
                }

                System.out.print(rvtx+ " ");
                vis[rvtx] = true;
                for(Edge e : graph[rvtx]){
                    if(!vis[e.v])
                        queue.addLast(e.v);
                }
            }

            System.out.println();
            level++;
        }

    }


    public static void BFS_WithoutCycle(int src,boolean []vis){
        LinkedList<Integer> queue = new LinkedList<>();
        int level = 0;
        queue.addLast(src);
        vis[src] = true;

        while(queue.size()!= 0){        //level maintain
            int size = queue.size();
            System.out.print(level + "->");
            while(size-- > 0){  //adding neighbours
                int rvtx= queue.removeFirst();
                
                System.out.print(rvtx + " ");
                for(Edge e : graph[rvtx]){
                    if(!vis[e.v]){
                        queue.addLast(e.v);
                        vis[e.v] = true;
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    public static boolean isTree() {
        // No cycle and 1 GCC cunt
        return true;
    }

    public static boolean isForest() {
        // No cycle and more than 1 GCC coun
        return true;
    }


    public static boolean isBipartite(int src , int vis[]){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);
        int color = 0; //same as level in BFS_WithCycle

        // -1 -> undefine, 0 -> red, 1 -> green.
        while(queue.size()!=0){
            int size =queue.size();
            while(size-- > 0){
                int rvtx = queue.removeFirst();
                if(vis[rvtx] != -1){
                    if(vis[rvtx] != color)  //conflict
                        return false;

                    continue;    
                }
                
                vis[rvtx] = color;

                for(Edge e : graph[rvtx]){
                    if(e.v == -1)
                        queue.addLast(e.v)
                }
            }

            color = (color + 1) % 2;    //color oscillates between red(0) and green(1)
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
            addEdge(0, 1, 10);
            addEdge(0, 3, 40);
            addEdge(1, 2, 10);
            addEdge(2, 3, 10);
            addEdge(3, 4, 2);
            addEdge(4, 5, 3);
            addEdge(4, 6, 8);
            addEdge(5, 6, 3);
            addEdge(2, 5, 5);
            // addEdge(0, 6, 16);
        
        // display();
        
        boolean[] vis = new boolean[N];
        // System.out.println(hasPath(0, 6, vis));
        // printPostOrder(0, vis, "", 0);
        pair p = new pair();
        allSolution(0, 6, vis, p, "", 0, 30, 4);
        System.out.println("Smallest Path =" + p.smallestPath + "@" +
        p.smallestWeight);
        System.out.println("Largest Path =" + p.largestPath + "@" + p.largestWeight);
        System.out.println("Ceil of 30 =" + p.ceilPath+ "@"+p.ceil);
        System.out.println("Floor of 30 =" + p.floorPath+ "@"+p.floor);
        System.out.println("Kth Largest Path =" + pq.peek().psf + "@" +
        pq.peek().wsf);

    }
}