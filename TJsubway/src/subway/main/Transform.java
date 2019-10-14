package subway.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import subway.main.MatrixUDG;
import subway.main.SubwayLine;
import subway.main.FloydInGraph;


public class Transform {
    public  static String FILE_PATH;
    public  static String WRITE_PATH;
    public	static Hashtable subwaylines=new Hashtable();//地铁线路集合，结构为 x号线=[站点1,···,站点n]
    public	static List<String> subwaystationnames=new ArrayList<>();//地铁站点集合
    public	static String[] edge;
    public	static MatrixUDG pg;
    public	static FloydInGraph graph;
    
    public	static ArrayList<ArrayList<String>> createdges=new ArrayList<ArrayList<String>>();//所有站点边长集合
    public	static ArrayList<String> edges;
    

    
    public	static void getAllEdges() {//提取站点的所有边长
    	Transform ts=new Transform();
    	for(int i=0;i<subwaystationnames.size();i++) {
    		ts.getLinkedStations(subwaystationnames.get(i));
    	}

    }
    
    public	static	void createMatrix() {//创建邻接矩阵,以INF,1分布
    	 pg=new MatrixUDG(subwaystationnames,createdges);
    	//pg.print();
    }
 
    public	static	List<Integer> getRoute(int begin,int end){
    	graph=new FloydInGraph(subwaystationnames.size());
    	graph.findCheapestPath(begin, end, pg.getmMatrix());
    	
    	return graph.getResult();
    }
    
    public	static	int getStationIndex(String station) {
    	for(int i=0;i<subwaystationnames.size();i++) {
    		if(subwaystationnames.get(i).contains(station))
    			return i;
    	}
    	return -1;
    }
    
    public	static String getStationName(int index) {
    	
    	return subwaystationnames.get(index);
    	
    }
    
    public	 List<String> getLinkedStations(String station) {
        List<String> linkedStaions = new ArrayList<>();
        
       for ( Iterator it=subwaylines.keySet().iterator();it.hasNext();) {
    	   String key=(String) it.next();
    	   List<String> value=(List<String>) subwaylines.get(key);
            for (int i = 0; i < value.size(); i++) {
                //遍历每条地铁线，若地铁线中存在该站点，则判断，如果该站点位于地铁线的起始站，则相邻站为地铁线的第二个站点(i+1)，
                //如果该站点位于地铁线的最后一个站，则相邻站为地铁线的倒数第二个站点（i-1），
                //如果该站点位于地铁线的其余位置，则相邻站点为该站点前后位置（i-1/i+1）
                if (station.equals(value.get(i))) {
                    if (i == 0) {
                        linkedStaions.add(value.get(i + 1));
                    } else if (i == (value.size() - 1)) {
                        linkedStaions.add(value.get(i - 1));
                    } else {
                        linkedStaions.add(value.get(i + 1));
                        linkedStaions.add(value.get(i - 1));
                    }
                }
            }
        }
       for(int i=0;i<linkedStaions.size();i++) {//创建边长{A,B}
    	   edges=new ArrayList<>();
    	   edges.add(station);
    	   edges.add(linkedStaions.get(i));
    	   createdges.add(edges);
       }
        return linkedStaions;
    }
    
 
    
	public static void ReadSubway() {
		File file = new File(FILE_PATH);
		FileInputStream fis = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null; 
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String lineTxt = null;
			String[] stations;
			List<String> subwaystations;
			SubwayLine sl=new SubwayLine();
			Station st=new Station();
			//Hashtable subwaylines=new Hashtable();
			
			//List<String> placedsubwaylines;
			
			LinkedHashSet stationnames=new LinkedHashSet();
			//List<String> subwaystationnames;
			
			st.setIsTransferStation(false);//首先给站点的是否换乘赋值为false
			
			while ((lineTxt = br.readLine()) != null) {
				
					sl.setName(lineTxt);
				if((lineTxt = br.readLine()) != null) {
					stations=lineTxt.split(" ");
					subwaystations=new ArrayList<>(stations.length);
					List<String> updatesubwaystations=new ArrayList<>(stations.length);//存放更新后的站点名,不包含换乘信息
					Collections.addAll(subwaystations, stations);//将String[]的stations名字转换成List<String>集合
					//sl.setInLineSubwayStationsName(subwaystations);
					for(String station:subwaystations) {
//						int count=0;
						int m=0,n=0;
						for(int i=0;i<station.length();i++) {
							
							if(station.charAt(i)=='[') {
								 m=i;
								 break;
							}
//							if(station.charAt(i)==']') {
//								count--;
//								if(count==0) n=i;
//							}
											
						}
					
//						placedsubwaylines=new ArrayList<>();
//						if(m+1<n)
//						placedsubwaylines.add(station.substring(m+1,n));
//						else	placedsubwaylines.add("null");
						
						if(m!=0)
						st.setName(station.substring(0,m));
						else	st.setName(station);
							
//						st.setPlacedSubwayLineName(placedsubwaylines);
						
						Pattern p=Pattern.compile("(\\[[^\\]]*\\])");
						Matcher mat=p.matcher(station);
						while(mat.find()) {
						st.setIsTransferStation(true);
						
						}
						
						
						stationnames.add(st.getName());
						updatesubwaystations.add(st.getName());
						
					}
					sl.setInLineSubwayStationsName(updatesubwaystations);
				}
				else break;
				
				subwaylines.put(sl.getName(), sl.getInLineSubwayStationsName());//将所有subwaylines存入一个Hashtable
				
				}
			subwaystationnames.addAll(stationnames);//将linkedhashset转换成ArrayList,可以通过索引来锁定站点名

			
			
			
		}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (isr != null) {
					try {
						isr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}



		}
	}

