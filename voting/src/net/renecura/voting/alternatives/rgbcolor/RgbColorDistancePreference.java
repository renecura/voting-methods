package net.renecura.voting.alternatives.rgbcolor;

import java.awt.Color;

import net.renecura.voting.Utility;
import net.renecura.voting.alternatives.Alternative;

public class RgbColorDistancePreference implements Utility {

	protected Color color;
	
	public RgbColorDistancePreference(Color c){
		this.color = c;
	}
	
	@Override
	public double utility(Alternative alt) {
		
		RgbColorAlternative calt = (RgbColorAlternative)alt;
		
		int rgb[] = calt.getRGB();
		
		return 765 - ( Math.abs( rgb[0] - this.color.getRed() ) + 
				Math.abs( rgb[1] - this.color.getGreen() ) +
				Math.abs( rgb[2] - this.color.getBlue() ) );
	}	
}
