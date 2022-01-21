package model;

import java.util.List;

public class CtrResponse {
	private InfoData infoData;
	private List<InfoData> naverData;
	
	
	public InfoData getInfoData() {
		return infoData;
	}
	public void setInfoData(InfoData infoData) {
		this.infoData = infoData;
	}
	public List<InfoData> getNaverData() {
		return naverData;
	}
	public void setNaverData(List<InfoData> naverData) {
		this.naverData = naverData;
	}
}
