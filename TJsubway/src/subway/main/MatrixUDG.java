package subway.main;

import java.util.ArrayList;
import java.util.List;

public class MatrixUDG {
	private List<String> mVexs;
	private int[][] mMatrix;
	private static int INF=Integer.MAX_VALUE;

	
	public int[][] getmMatrix(){
		return mMatrix;
	}
	 public MatrixUDG(List<String> vexs, ArrayList<ArrayList<String>> edges) {
	        
	        // 初始化"顶点数"和"边数"
	        int vlen = vexs.size();
	        int elen = edges.size();
	        // 初始化"顶点"
	        mVexs = new ArrayList<String>();
	        mVexs.addAll(vexs);

	        // 初始化邻接矩阵
	        mMatrix=new int[vlen][vlen];
	        for(int i=0;i<vlen;i++) {
	        	for(int j=0;j<vlen;j++)
	        		mMatrix[i][j] =INF;
	        }
	        
	        for (int i = 0; i < elen; i++) {
	            // 读取边的起始顶点和结束顶点
	            int p1 = getPosition(edges.get(i).get(0));
	            int p2 = getPosition(edges.get(i).get(1));	          
	            

	            
	            mMatrix[p1][p2] = 1;
	            mMatrix[p2][p1] = 1;
	        }
	        
	    }
	 
    private int getPosition(String ch) {
        for(int i=0; i<mVexs.size(); i++) {
            if(mVexs.get(i).contains(ch))
                return i;
        }
        return -1;
    }
    
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.size(); i++) {
            for (int j = 0; j < mVexs.size(); j++)
                System.out.printf("%d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }
    



}
