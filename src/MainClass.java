import processing.core.PApplet;

public class MainClass extends PApplet {
	public static PApplet processing;

	public static void main(String[] args) {
		PApplet.main("MainClass", args);
	}

	public void setup(){
		processing = this;
	}

	public void settings(){
		size(1000,800);
	}

	public void draw(){
		line(0,0,50,50);
	}

}
