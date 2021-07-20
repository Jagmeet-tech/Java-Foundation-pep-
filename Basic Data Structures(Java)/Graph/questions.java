import java.util.LinkedList;
public class questions{
    public static int N=7;
    public static ArrayList<Edge>[] graph=new ArrayList[N];
    
 //785   
 public static boolean isBipartite(int [][]graph,int src , int vis[]){
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

                for(int v : graph[rvtx]){
                    if(vis[v] == -1)
                        queue.addLast(v);
                }
            }

            color = (color + 1) % 2;    //color oscillates between red(0) and green(1)
        }
        return true;
    }

    public boolean isBipartite(int[][] graph){
        int N = graph.length;
        int vis[] = new int[N];
        Arrays.fill(vis,-1);
            
        for(int i=0;i<N;i++){
            if(vis[i] == -1 && !isBipartite(graph,i,vis))
                return false;
        }
        return true;
    }


    public static int spreadInfection(int src, int timeLimit) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean vis[] = new boolean[N];
        int time = 0,infectedCount = 1; 
        queue.addLast(src);
        vis[src] = true;

        while(queue.size()!=0){
            int size = queue.size();
            while(size-- > 0){
                int rvtx = queue.removeFirst();
                for(Edge e : graph[rvtx]){
                    if(!vis[e.v]){
                        vis[e.v] = true;
                        queue.addLast(e.v);
                        if(time + 1 < timeLimit)
                            infectedCount++;
                        else
                            return infectedCount;    
                    }
                }
            }
            time++;
        }
        return infectedCount;
    }   
}