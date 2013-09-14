package net.renecura.voting.alternatives.rgbcolor;

import java.awt.Color;

import net.renecura.voting.alternatives.Alternative;

public class RgbColorAlternative implements Alternative {
	
	protected final String descriptor;
	protected final Color color;
	
	public RgbColorAlternative(String descriptor, Color c){
		this.descriptor = descriptor;
		this.color = c;		
	}
	
	public int[] getRGB(){
		
		int rgb[] = new int[3];
		
		rgb[0] = this.color.getRed();
		rgb[1] = this.color.getGreen();
		rgb[2] = this.color.getBlue();
		
		return rgb;
	}

	public Color getColor() {
		return color;
	}	
	public String toString(){
		return descriptor;
	}

}
