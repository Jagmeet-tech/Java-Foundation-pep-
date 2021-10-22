public class algoQuestions{

    static int []disc,lowdisc;
    static boolean []vis;
    static int time=0;
    
    // 1192 : Atriculation edge
    public static void dfs(ArrayList<Integer>[] graph,int src,int par,List<List<Integer>> ans){
        disc[src] = lowdisc[src] = time++;
        vis[src] = true;
        
        for(Integer v : graph[src]){
            if(v == par)
                continue;
            else if(vis[v])
                lowdisc[src] = Math.min(lowdisc[src] , disc[v]);
            else{
                dfs(graph,v,src,ans);
                if(lowdisc[v] > disc[src]){
                    List<Integer> edge = new ArrayList<>(Arrays.asList(src,v));
                    ans.add(edge);
                }
                 
                lowdisc[src] = Math.min(lowdisc[src],lowdisc[v]);
            }
        }
    }
    
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(List<Integer> arr : connections){
            graph[arr.get(0)].add(arr.get(1));
            graph[arr.get(1)].add(arr.get(0));
        }
        
        
        disc = new int[n];
        lowdisc = new int[n];
        vis = new boolean[n];
        List<List<Integer>> ans =new ArrayList<>();
        
        for(int i=0;i<n;i++){
            if(!vis[i])
                dfs(graph,i,-1,ans);
        }
        return ans;
    }

    // 743
    public int networkDelayTime(int[][] times, int n, int k) {
        // {v,w}
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++)
            graph[i] = new ArrayList<>();
        
        for(int []time : times){
            int u = time[0] , v = time[1], w = time[2];
            graph[u].add(new int[]{v,w});       
        }
        
        int dist[] = new int[n+1];
        Arrays.fill(dist,(int)1e9);
        
        // {vtx,wsf}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        
        pq.add(new int[]{k,0});
        dist[k] = 0;
        
        while(pq.size()!=0){
            int []rp = pq.remove();  //remove pair
            int vtx = rp[0], wsf = rp[1];
            
            if(wsf > dist[vtx]) continue;
            
            for(int e[] : graph[vtx]){
                int v = e[0] ,w = e[1];
                if(w + wsf < dist[v]){
                    dist[v] = w + wsf;
                    pq.add(new int[]{v,w + wsf});
                }
            }
        }
        
        int max =0;
        for(int i =1;i<=n;i++){
            if(dist[i] == (int)1e9) return -1;
            max = Math.max(max,dist[i]);
        }
        return max;    
    }

    //787
    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K){
        int prev[] = new int[N];
        Arrays.fill(prev,(int)1e9);

        prev[src] = 0;

        for(int i=1;i<=K+1;i++){
            int curr[] = new int[N];
            for(int j=0;j<N;j++)
                curr[j] = prev[j];
             
            boolean anyUpdate = false;
            
            for(int e[] : flights){
                int u = e[0],v = e[1],w = e[2];
                if(prev[u] != (int)1e9 && prev[u] + w < curr[v]){
                    curr[v] = prev[u] + w;
                    anyUpdate = true;
                }
            }

            if(!anyUpdate)
                break;

            prev = curr;    
        }

        return prev[dst] != (int)1e9 ? prev[dst] : -1;
    }

    //1334
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
       int mat[][] = new int[n][n];
       for(int []d : mat)
           Arrays.fill(d,(int)1e9);
      
        
       for(int []e : edges){
           int u =e[0],v = e[1],w = e[2];
           mat[u][v] = w;
           mat[v][u] = w;
       }
        for(int i=0;i<n;i++)
           mat[i][i] = 0; 
        
       for(int k = 0 ;k<n;k++){
           for(int i=0;i<n;i++){
               for(int j=0;j<n;j++){
                   mat[i][j] = Math.min(mat[i][j] , mat[i][k] + mat[k][j]);
               }
           }
       }
       
       int minCity=(int)1e9; 
       int city =0; 
       for(int i=0;i<n;i++){
           int count =0;
           for(int j=0;j<n;j++){
               if(mat[i][j] != (int)1e9 && mat[i][j] != 0 && mat[i][j] <= distanceThreshold)
                   count++;
           }
           if(count <= minCity){
               minCity = count;
               city = i;
           }    
       }
       return city; 
    }
}