package ua.itstep.shaptala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public class Graphic extends JFrame {
	private static final long serialVersionUID = -9004845133199887302L;
	private static class Size {
		private int width;
		private int height;
		
		public Size(int width, int height) {
			this.setWidth(width);
			this.setHeight(height);
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}
	
	Size size = new Size(800, 600);
	

	private void initUI() {
		add(new Surface());
		setTitle("Amdalh's Law");
		setSize(size.getWidth(), size.getHeight());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Graphic() {
		initUI();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Graphic ex = new Graphic();
				ex.setVisible(true);
			}
		});

	}
}

class Surface extends Component {	
	private static final long serialVersionUID = 435032912031502233L;

	int offset = 50;
	int fontOffset = 6;
	int fontSize = 16;	
	int gridWidth = 1;
	int nHoriz = 20;
	int nVert = 12;
	
	// Закон Амдала
	private float lawAmdahl(float p, int n) {
		return 1.0f/((1.0f-p)+p/n);
	}
	
	private void doDrawing(Graphics g) {		
		Graphics2D g2d = (Graphics2D) g;		
		drawAxes(g2d);
		drawGrid(g2d);		
		drawGraphics(g2d);
		
	}

	private void drawGraphics(Graphics2D g2d) {
		float[] ps = {0.5f, 0.75f, 0.9f, 0.95f};
		Color[] colors = {Color.red, Color.orange, Color.magenta, Color.green};
		
		float s1 = 0, s2 = 0;
		int dx = (getWidth() - offset*2)/nVert;
		g2d.setStroke(new BasicStroke(3));
		
		for(int i=0; i<ps.length; i++) {
			g2d.setColor(colors[i]);
			g2d.drawString((new Integer((int)(ps[i]*100))).toString()+"%",offset*2, offset*2+30*i);			
		}
		
		
		int c = 0;
		for(float p: ps) {			
			g2d.setColor(colors[c]);
			c++;
			for(int i=0; i<nVert; i++) {
				s1 = lawAmdahl(p, (int)Math.pow(2, i));
				s2 = lawAmdahl(p, (int)Math.pow(2, i+1));
				int y1 = (int)(getHeight() - offset - s1/nHoriz*(getHeight() - 2*offset));
				int y2 = (int)(getHeight() - offset - s2/nHoriz*(getHeight() - 2*offset));
				int x1 = offset + dx * i;
				int x2 = offset + dx * (i+1);
				g2d.drawLine(x1, y1, x2, y2);
			}
		}
	}

	private void drawGrid(Graphics2D g2d) {
		g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
		g2d.setPaint(Color.black);
		
		g2d.setStroke(new BasicStroke(gridWidth));		
		int dx = (getWidth() - offset*2)/nVert;
		int dy = (getHeight() - offset*2)/nHoriz;
		int y;
		int x = offset;
		for(int i=2; i<nHoriz + 1; i+=2) {
			y = getHeight()-offset-dy*i;
			g2d.drawLine(x, y, getWidth()-x, y);			
			g2d.drawString((new Integer(i)).toString(), offset-offset/2, y+fontOffset);
		}
		
		y = getHeight() - offset + fontOffset*3;
		for(int i=1; i<nVert + 1; i++) {
			x = offset + dx * i;
			g2d.drawLine(x, offset, x, getHeight()-offset);
							    
		    g2d.drawString((new Integer((int)Math.pow(2, i))).toString(), x - fontOffset, y);
		}
	}

	private void drawAxes(Graphics2D g2d) {
		BasicStroke axesStroke = new BasicStroke(4); 
		g2d.setStroke(axesStroke);
		g2d.setPaint(Color.blue);
		
		// horizontal axe
		int y = getHeight()-offset;
		g2d.drawLine(offset, y, getWidth() - offset, y);
		
		// vertical axe
		g2d.drawLine(offset, y, offset, offset);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		doDrawing(g);
	}
	
}
