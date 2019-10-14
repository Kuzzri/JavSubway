package subway.main;
import java.util.ArrayList;
import java.util.List;
/**
 * java-用邻接矩阵求图的最短路径  弗洛伊德算法
 */
public class FloydInGraph {
 private static int INF=Integer.MAX_VALUE;
 private int[][] dist;
 private int[][] path;
 private List<Integer> result=new ArrayList<Integer>();
 
 public List<Integer> getResult(){
	 return result;
 }
 public FloydInGraph(int size){
  this.path=new int[size][size];
  this.dist=new int[size][size];
 }
 public void findPath(int i,int j){
  int k=path[i][j];
  if(k==-1)return;
  findPath(i,k);
  result.add(k);
  findPath(k,j);
 }
 public  void findCheapestPath(int begin,int end,int[][] matrix){
  floyd(matrix);
  result.add(begin);
  findPath(begin,end);
  result.add(end);
 }
 public  void floyd(int[][] matrix){
  int size=matrix.length;
  for(int i=0;i<size;i++){
   for(int j=0;j<size;j++){
    path[i][j]=-1;
    dist[i][j]=matrix[i][j];
   }
  }
  for(int k=0;k<size;k++){
   for(int i=0;i<size;i++){
    for(int j=0;j<size;j++){
     if(dist[i][k]!=INF&&
      dist[k][j]!=INF&&
      dist[i][k]+dist[k][j]<dist[i][j]){//dist[i][k]+dist[k][j]>dist[i][j]-->longestPath
      dist[i][j]=dist[i][k]+dist[k][j];
      path[i][j]=k;
     }
    }
   }
  }
   
 }
// public static void main(String[] args) {
//  FloydInGraph graph=new FloydInGraph(4);
//  int[][] matrix={
//    {INF,1,1,INF},
//    {1,INF,1,INF},
//    {1,1,INF,1},
//    {INF,INF,1,INF}
//    
//  };
//  int begin=0;
//  int end=3;
//  graph.findCheapestPath(begin,end,matrix);
//  List<Integer> list=graph.result;
//  System.out.println(begin+" to "+end+",the cheapest path is:");
//  System.out.println(list.toString());
//  System.out.println(graph.dist[begin][end]);
// }
}