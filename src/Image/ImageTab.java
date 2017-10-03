package Image;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ImageTab extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Image image;
	private JPanel infoPanel;
	
	public ImageTab(BufferedImage image) {
		setUpInfo(image);
		this.setLayout(new BorderLayout());
		this.add(this.image, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.LINE_END);
	}
	
	private void setUpInfo(BufferedImage buffImage) {
		infoPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawString(image.getMousePixel(), 40, 40);
				g.dispose();
			}
		};
		image = new Image(buffImage, infoPanel);
		image.setInfo(infoPanel);
		infoPanel.setBorder(new EmptyBorder(0,3,0,3));
		infoPanel.setPreferredSize(new Dimension(300, getHeight()));
	}
	
	public Image getImage() {
		return image;
	}
}
