package model;

public class InfoData {
	private String krLastNm;
	private String krFirstNm;
	private String enLastNm;
	private String enFirstNm;
	
	private InfoData() {};
	
	public static class InfoDataBuilder {
		private String krLastNm;
		private String krFirstNm;
		private String enLastNm;
		private String enFirstNm;
		
		public InfoDataBuilder krLastNm(String krLastNm) {
			this.krLastNm = krLastNm;
			return this;
		}
		
		public InfoDataBuilder krFirstNm(String krFirstNm) {
			this.krFirstNm = krFirstNm;
			return this;
		}
		
		public InfoDataBuilder enLastNm(String enLastNm) {
			this.enLastNm = enLastNm;
			return this;
		}
		
		public InfoDataBuilder enFirstNm(String enFirstNm) {
			this.enFirstNm = enFirstNm;
			return this;
		}
		
		public InfoData build() {
			InfoData infoData = new InfoData();
			infoData.setEnFirstNm(this.enFirstNm);
			infoData.setEnLastNm(this.enLastNm);
			infoData.setKrFirstNm(this.krFirstNm);
			infoData.setKrLastNm(this.krLastNm);
			return infoData;
		}
	}
	
	
	public String getKrLastNm() {
		return krLastNm;
	}
	public void setKrLastNm(String krLastNm) {
		this.krLastNm = krLastNm;
	}
	public String getKrFirstNm() {
		return krFirstNm;
	}
	public void setKrFirstNm(String krFirstNm) {
		this.krFirstNm = krFirstNm;
	}
	public String getEnLastNm() {
		return enLastNm;
	}
	public void setEnLastNm(String enLastNm) {
		this.enLastNm = enLastNm;
	}
	public String getEnFirstNm() {
		return enFirstNm;
	}
	public void setEnFirstNm(String enFirstNm) {
		this.enFirstNm = enFirstNm;
	}
	
	@Override
	public String toString() {
		return "krLastNm: " + krLastNm
				+ "\nkrFirstNm: " + krFirstNm
				+ "\nenLastNm: " + enLastNm
				+ "\nenFirstNm: " + enFirstNm;
	}
}
