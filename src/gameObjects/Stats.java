package gameObjects;

import java.text.DecimalFormat;

public class Stats {
	
	public double multiplier;
	public double magicFind;
	DecimalFormat df = new DecimalFormat("####0.00");

	public Stats(double multiplier, double magicFind) {
		this.multiplier = Double.parseDouble(df.format(multiplier));
		this.magicFind = Double.parseDouble(df.format(magicFind));
	}
	
	public void updateStat(double multiplier, double magicFind){
		this.multiplier = Double.parseDouble(df.format(multiplier));
		this.magicFind = Double.parseDouble(df.format(magicFind));
	}

	@Override
	public String toString() {
		return "Resource Multiplier : " + multiplier + "\n\nMagic Find : " + magicFind;
	}

}
