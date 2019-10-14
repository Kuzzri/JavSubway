package subway.main;

import java.util.List;

public class SubwayLine {
	public String Name;
	public List<String> InLineSubwayStationsName;
	
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name=Name;
	}
	public List<String> getInLineSubwayStationsName(){
		return InLineSubwayStationsName;
	}
	public void setInLineSubwayStationsName(List<String> InLineSubwayStationsName) {
		this.InLineSubwayStationsName=InLineSubwayStationsName;
	}
	
	public SubwayLine(String Name,List<String> InLineSubwayStationsName) {
		this.Name=Name;
		this.InLineSubwayStationsName=InLineSubwayStationsName;
	}
	public SubwayLine() {
		
	}
}
