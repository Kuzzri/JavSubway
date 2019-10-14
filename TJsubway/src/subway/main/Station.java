package subway.main;

import java.util.List;

public class Station {
	public String Name;
	public boolean IsTransferStation ;
	public List<String> PlacedSubwayLineName;
	
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name=Name;
	}
	public List<String> getPlacedSubwayLineName(){
		return PlacedSubwayLineName;
	}
	public void setPlacedSubwayLineName(List<String> PlacedSubwayLineName) {
		this.PlacedSubwayLineName=PlacedSubwayLineName;
	}
	public boolean getIsTransferStation() {
		return IsTransferStation;
	}
	public void setIsTransferStation(boolean IsTransferStation) {
		this.IsTransferStation=IsTransferStation;
	}
	
	public Station(String Name,boolean IsTransferStation,List<String>  PlacedSubwayLineName) {
		this.Name=Name;
		this.IsTransferStation=IsTransferStation;
		this.PlacedSubwayLineName=PlacedSubwayLineName;
	}
	public Station() {
		
	}
}
