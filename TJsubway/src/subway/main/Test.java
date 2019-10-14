package subway.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import subway.main.Transform;;

public class Test {
	   public static void main(String[] args) throws IOException {
		   
	       switch (args[0]){
	           case "-map":
	               //-map subway.txt
	               if(args.length==2){
	                   Transform.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\Content\\" + args[1];
	                   //根据路径，读取地铁信息，并打印。
	                   Transform.ReadSubway();
	                   System.out.println("成功读取subway.txt文件");
	               }else{
	                   System.out.println("验证参数格式！");
	               }
	               break;

	           case "-a":
	               //-a 1号线 -map subway.txt -o station.txt
	               if(args.length==6){
	            	   Transform.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\Content\\" + args[3];
	                   Transform.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\Content\\" + args[5];
	                   Transform.ReadSubway();
	                   for(Iterator itr = Transform.subwaylines.keySet().iterator();itr.hasNext();){
	                	   String key = (String) itr.next();
	                	   List<String> value =  (List<String>) Transform.subwaylines.get(key);
	                	   BufferedWriter bw = null;
	                	  if(key.contains(args[1]))
	                	   {
	                		  try {
	       	        	       bw = new BufferedWriter( new FileWriter(Transform.WRITE_PATH) );
	       	        	       for(int i = 0; i <value.size(); i++ ) {
	       	        	    	   	bw.write( value.get(i) );
	       	        	   			bw.newLine();
	       	        	       		}
	       	        	       bw.close();
	       	        	       }catch(IOException e){
	       	        	    	   e.printStackTrace();
	       	        	       }
	                	   }
	                   }
	                   
	                   System.out.println("已将结果写入station.txt文件");
	               }else{

	                   System.out.println("验证参数格式！");
	               }
	               break;
	           case "-b":
	               //-b 洪湖里 复兴路 -map subway.txt -o routine.txt

	               if(args.length==7){
	            	   Transform.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\Content\\" + args[4];
	            	   Transform.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\Content\\" + args[6];
	            	   Transform.ReadSubway();
	            	   Transform.getAllEdges();//得到边长信息
	        	       Transform.createMatrix();//创建邻接矩阵
	        	       int begin=Transform.getStationIndex(args[1]);
	        	       int end=Transform.getStationIndex(args[2]);
	        	       List<Integer> routes=new ArrayList<>(Transform.getRoute(begin, end));
	        	       List<String>	route=new ArrayList<>();
	        	       route.add("共计"+routes.size()+"个站点");
	        	       for(int i=0;i<routes.size();i++) {
	        	    	   route.add(Transform.getStationName(routes.get(i)));
	        	       }
	        	       System.out.println(route);
	        	       
	        	       BufferedWriter bw = null;
	        	       try {
	        	       bw = new BufferedWriter( new FileWriter(Transform.WRITE_PATH) );
	        	       for(int i = 0; i < route.size(); i++ ) {
	        	    	   	bw.write( route.get(i) );
	        	   			bw.newLine();
	        	       		}
	        	       bw.close();
	        	       }catch(IOException e){
	        	    	   e.printStackTrace();
	        	       }
	                   System.out.println("已将结果写入routine. txt文件");
	               }else{
	                   System.out.println("验证参数格式！");
	               }
	               break;
	       }
	    }

}
