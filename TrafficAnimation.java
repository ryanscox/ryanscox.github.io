
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates an alien kidnaping a cow with the police chasing after them. 
 *
 * @author BSU CS 121 Instructors
 * @author Ryan Cox
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 10; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = -500;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 1;

	private final Color BACKGROUND_COLOR = new Color(0,19,127);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// Calculate the new xOffset position of the moving object.
		
		// The boolean variable is used to determine when the 
		// object is off the screen to the right. 
		
		boolean distance = false;
		if ((width)+(height/2)+(int)(height/1.5) <= (xOffset + stepSize-5)) {
			distance = true;
		}
				
		if (distance == true) {xOffset = 	((xOffset + stepSize)% width)-(int)(height*2.1)-200; }
		if (distance == false) {xOffset  = 	xOffset + stepSize;}
			
		// This draws a ufo. 
		int squareSide = height / 5;
		int squareY = height / 5 - squareSide / 2;
		
		g.setColor(Color.gray);
		g.fillOval(xOffset, squareY+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), (2*squareSide), squareSide);
		g.setColor(new Color(35,214,225));
		g.fillArc(xOffset+(squareSide/2), squareY-(height/15)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide, (squareSide), 0, 180);
		g.setColor(new Color(38,255,81));
		g.fillOval(xOffset+(int)(squareSide/1.2), squareY-(height/17)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/3, (int)(squareSide/2.2));
		g.fillArc(xOffset+(int)(squareSide/1.35), squareY+(height/300)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/2, (int)(squareSide/3.2),0,180);
		g.setColor(Color.black);
		g.fillOval(xOffset+(int)(squareSide/1.09), squareY-(height/30)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/25, squareSide/25);
		g.fillOval(xOffset+(int)(squareSide*1.04), squareY-(height/30)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/25, squareSide/25);
		g.fillOval(xOffset+(int)(squareSide/1.06), squareY-(height/130)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/9, squareSide/25);
		
		//Here I am drawing the road. 
		int rectY = ((int)(height / 1.7)) - squareSide / 50;
		g.setColor(Color.black);
		g.fillRect(0,rectY,width, height-rectY);
		g.setColor(Color.yellow);
		g.fillRect(0, height-((int)(rectY/2.5)), width/5, height/20);
		g.fillRect((width*2)/6, height-((int)(rectY/2.5)), width/5, height/20);
		g.fillRect((width*4)/6, height-((int)(rectY/2.5)), width/5, height/20);
		g.fillRect((width*150)/151, height-((int)(rectY/2.5)), width/5, height/20);
		
		//Here I am drawing a cow body. The if statement forces the cow to be captured if the cow and alien have the same x value as each other. 
		if (xOffset < width-xOffset-(height)) {
			g.setColor(Color.white);
			g.fillRect(width-xOffset-(height), rectY, (2*squareSide), squareSide);
			g.fillOval(width-xOffset-(height)-(squareSide/2), rectY-(squareSide/2), (squareSide), (squareSide)); 
			g.drawLine(width-xOffset-(height), rectY+squareSide, width-xOffset-(height)+(((int)((height/10)*Math.sin(xOffset/8)))), rectY+squareSide+(height/10));
			g.drawLine(width-xOffset-((int)(height*.95)), rectY+squareSide, width-xOffset-((int)(height*.95))+(((int)((height/10)*Math.cos(xOffset/8)))), rectY+squareSide+(height/10));
			g.drawLine(width-xOffset-((int)(height*.65)), rectY+squareSide, width-xOffset-((int)(height*.65))+(((int)((height/10)*Math.sin(xOffset/8)))), rectY+squareSide+(height/10));
			g.drawLine(width-xOffset-((int)(height*.61)), rectY+squareSide, width-xOffset-((int)(height*.61))+(((int)((height/10)*Math.cos(xOffset/8)))), rectY+squareSide+(height/10));
		 
			g.setColor(Color.black);
			g.fillOval(width-xOffset-(height), rectY+(squareSide/2), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(width-xOffset-((int)(height*.9)), rectY+(squareSide/3), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(width-xOffset-((int)(height*.7)), rectY+(squareSide/2), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(width-xOffset-((int)(height*.8)), rectY+(squareSide/4), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(width-xOffset-((int)(height*.85)), rectY+(squareSide/2), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
					
			g.fillOval(width-xOffset-((int)(height*1.07)), rectY-(squareSide/7), ((int)(squareSide*.15)), ((int)(squareSide*.15)));
			g.drawArc(width-xOffset-(height)-(squareSide/2), rectY+(squareSide/7), squareSide/3, squareSide/5, 270, 90);
			
			
			//Here I draw the words at the bottom.
			g.setColor(Color.white);
			
			if (xOffset <= ((-2)*squareSide)) {
				g.setFont(new Font("Arial", Font.BOLD, (squareSide/4)));
				g.drawString("Out for a nice stroll.", width-xOffset-(height)-(squareSide/2), ((int)(height*.99)));
				
			}
			
			if (xOffset > ((-2)*squareSide)) {
				g.setFont(new Font("Arial", Font.BOLD, (squareSide/4)));
				g.drawString("WITH THE ALIENS?!?!", width-xOffset-(height)-(squareSide/2), ((int)(height*.99)));
				
			}
		} else {
			
			g.setColor(new Color(0,242,255));
			int xPoints[] = {xOffset+squareSide, xOffset-(height/15), xOffset+2*squareSide+(height/15)};
			int yPoints[] = {squareY+squareSide+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), rectY+squareSide+(height/10)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)),rectY+squareSide+(height/10)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005))};
			int n = 3;
			g.fillPolygon(xPoints, yPoints, n);
			
			//Here I make the cow turn around and get kidnaped once it reaches the alien. 
			g.setColor(Color.white);
			
			g.fillRect(xOffset, rectY+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), (2*squareSide), squareSide);
			g.fillOval(xOffset-(squareSide/2), rectY-(squareSide/2)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), (squareSide), (squareSide));
			g.drawLine(xOffset, rectY+squareSide+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), xOffset+(((int)((height/10)*Math.sin(xOffset/8)))),(rectY+squareSide+(height/10))+(int)((height/25)*Math.sin((width-xOffset-(height))*Math.PI*.01)));
			g.drawLine(xOffset+((int)(height*.05)), rectY+squareSide+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), xOffset+((int)(height*.05))+(((int)((height/10)*Math.cos(xOffset/8)))), rectY+squareSide+(height/10)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)));
			g.drawLine(xOffset+((int)(height*.35)), rectY+squareSide+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), xOffset+((int)(height*.35))+(((int)((height/10)*Math.sin(xOffset/8)))), rectY+squareSide+(height/10)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)));
			g.drawLine(xOffset+((int)(height*.39)), rectY+squareSide+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), xOffset+((int)(height*.39))+(((int)((height/10)*Math.cos(xOffset/8)))), rectY+squareSide+(height/10)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)));	
			g.setColor(Color.black);
			g.fillOval(xOffset, rectY+(squareSide/2)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(xOffset+((int)(height*.1)), rectY+(squareSide/3)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(xOffset+((int)(height*.15)), rectY+(squareSide/2)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(xOffset+((int)(height*.2)), rectY+(squareSide/4)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(xOffset+((int)(height*.3)), rectY+(squareSide/2)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.5)), ((int)(squareSide*.25)));
			g.fillOval(xOffset-((int)(height*.07)), rectY-(squareSide/7)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), ((int)(squareSide*.15)), ((int)(squareSide*.15)));
			g.drawArc(xOffset-(squareSide/2), rectY+(squareSide/7)+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)), squareSide/3, squareSide/5, 270, 90);
			
			g.setColor(Color.white);
			if (xOffset > ((-2)*squareSide)) {
				g.setFont(new Font("Arial", Font.BOLD, (int)(((squareSide/2)))));
				g.drawString("HELP!!!!!!", xOffset-(squareSide/2), ((int)(height*.97))+(int)((height/25)*Math.sin((xOffset-(width-xOffset-(height)))*Math.PI*.005)));
				
			}
		}
		//Now I am drawing the car chasing the alien
		g.setColor(Color.white);
		g.fillRect(xOffset-(height), rectY, height/2, height/10);
		g.fillRect(xOffset-(height)+(height/8), (int)(rectY*.85), height/4, height/8);
		g.fillArc(xOffset-height+(height/8), (int)(rectY*.71), height/4, height/6, 0, 180);
		g.fillArc(xOffset-(height), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10)))))), 90);
		g.fillArc(xOffset-(height), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+180, 90);
		int carPointsX [] = {xOffset-height+(height/8)+(height/4),xOffset-height+(height/8)+(int)(height/2.65)-1,xOffset-height+(height/8)};
		int carPointsY [] = {(int)(rectY*.83), (int)(rectY*.85)+(height/11),(int)(rectY*.85)+(height/11)};
		int nPoints = 3;
		g.fillPolygon(carPointsX, carPointsY, nPoints);
		int backHoodX []= { xOffset-(height)+(height/7),xOffset-(height)+(height/18),xOffset-(height)+(height/8)};
		int backHoodY [] = {(int)(rectY*.78), (int)(rectY*.85)+(height/10),(int)(rectY*.85)+(height/10)};
		
		g.fillPolygon(backHoodX, backHoodY, nPoints);
		
		g.setColor(new Color(116,123,135));
		g.fillArc(xOffset-(height), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+90, 90);
		g.fillArc(xOffset-(height), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+270, 90);
		
		g.setColor(Color.white);
		g.fillArc(xOffset-(int)(height/1.7), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10)))))), 90);
		g.fillArc(xOffset-(int)(height/1.7), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+180, 90);
		g.setColor(new Color(116,123,135));
		g.fillArc(xOffset-(int)(height/1.7), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+90, 90);
		g.fillArc(xOffset-(int)(height/1.7), rectY+(height/10), height/10, height/10, (int)(((int)(-582.5*Math.sin(((.0025*xOffset)%(Math.PI/10))))))+270, 90);
		
		g.setColor(Color.black);
		g.fillRect(xOffset-height+height/7, (int)(rectY*.87), height/12, height/15);
		g.fillRect(xOffset-height+(int)(height/3.6), (int)(rectY*.87), height/12, height/15);
		g.setFont(new Font("Arial", Font.BOLD, (squareSide/2)));
		g.drawString("Police", xOffset-(int)(height/1.1), rectY+(height/11));
		
		
		if (0==((xOffset/45)%2)) {
			g.setColor(Color.red);
		}	else {
			g.setColor(new Color(0,101,255));
		}
		g.fillArc(xOffset-(int)(height/1.282), rectY-(int)(height/4.51), height/17, (int)(height/9.2), 0, 180);
		
		ImageIcon avatar = new ImageIcon("myAvatar.png");
		g.drawImage(avatar.getImage(), 0, height-((rectY/5)+(height/150)), rectY-(height/2), rectY-(height/2), null);
		
		if (xOffset>width/2) {
			if (xOffset<width) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, (squareSide/5)));
				g.drawString("Squeak", rectY-(height/2)+10 , height-(height/20));
			}
		}
		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 660;
		int initHeight = 480;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}