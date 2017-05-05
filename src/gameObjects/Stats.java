package gameObjects;

public class Stats {
	
	//Fortuity
	public int RFF;
	public int ERF;
	public int AMF;
	public int SRF;
	//Will
	public int WIL;
	public int RFW;
	public int ERW;
	public int AMW;
	//Gemlight
	public int EML;
	public int SPL;
	public int RBL;
	public int SRL;
	//Misc
	public int MNC;
	public int AFF;
	public int VNT;
	public int HAS;
	
	public Stats(int rFF, int eRF, int aMF, int sRF, int wIL, int rFW, int eRW,
			int aMW, int eML, int sPL, int rBL, int sRL, int mNC, int aFF,
			int vNT, int hAS) {
		RFF = rFF;
		ERF = eRF;
		AMF = aMF;
		SRF = sRF;
		WIL = wIL;
		RFW = rFW;
		ERW = eRW;
		AMW = aMW;
		EML = eML;
		SPL = sPL;
		RBL = rBL;
		SRL = sRL;
		MNC = mNC;
		AFF = aFF;
		VNT = vNT;
		HAS = hAS;
	}
	
	

	@Override
	public String toString() {
		return "Stats [RFF=" + RFF + ", ERF=" + ERF + ", AMF=" + AMF + ", SRF="
				+ SRF + ", WIL=" + WIL + ", RFW=" + RFW + ", ERW=" + ERW
				+ ", AMW=" + AMW + ", EML=" + EML + ", SPL=" + SPL + ", RBL="
				+ RBL + ", SRL=" + SRL + ", MNC=" + MNC + ", AFF=" + AFF
				+ ", VNT=" + VNT + ", HAS=" + HAS + "]";
	}
	
	
	
	

}
