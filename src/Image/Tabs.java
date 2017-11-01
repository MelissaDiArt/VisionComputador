package Image;

import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	
	private Vector<Pair<ImageTab, String>> images;
	private Vector<Pair<HistogramTab, String>> histograms;
	private Vector<String> tabNames;
	
	public void remove(String name) {
		for(int i=0; i<this.getTabCount(); i++) {
			if(this.getTitleAt(i).equalsIgnoreCase(name)) {
				this.remove(i);
				break;
			}
		}
		if(tabNames != null) {
			for(String tab : tabNames) {
				if(tab.equalsIgnoreCase(name)) {
					tabNames.remove(tabNames.indexOf(tab));
					break;
				}
			}
		}
		if(images != null) {
			for(Pair<ImageTab, String> image : images) {
				if(image.getRight().equalsIgnoreCase(name)) {
					images.remove(images.indexOf(image));
					break;
				}
			}
		}
		if(histograms != null) {
			for(Pair<HistogramTab, String> histogram : histograms) {
				if(histogram.getRight().equalsIgnoreCase(name)) {
					histograms.remove(histograms.indexOf(histogram));
					break;
				}
			}		
		}
	}
	
	public void removeTabs(String name) {
		if(tabNames != null) {
			for(int i=0; i<tabNames.size(); i++) {
				if(tabNames.get(i).contains(name)) {
					this.remove(i);
					tabNames.remove(i);
					i--;
				}
			}
			if(images != null) {
				for(Pair<ImageTab, String> image : images) {
					if(image.getRight().contains(name)) {
						images.remove(images.indexOf(image));
						break;
					}
				}
			}
			if(histograms != null) {
				for(Pair<HistogramTab, String> histogram : histograms) {
					if(histogram.getRight().contains(name)) {
						histograms.remove(histograms.indexOf(histogram));
						break;
					}
				}
			}
		}
	}
	
	public Image getImage(String name) {
		if(images != null) {
			for(Pair<ImageTab, String> image : images) {
				if(image.getRight().equalsIgnoreCase(name)) {
					return image.getLeft().getImage();
				}
			}			
		}
		return null;
	}
	
	public BufferedImage getBuffImage(String name) {
		if(images != null) {
			for(Pair<ImageTab, String> image : images) {
				if(image.getRight().equalsIgnoreCase(name)) {
					return image.getLeft().getImage().getBufferedImage();
				}
			}			
		}
		if(histograms != null) {
			for(Pair<HistogramTab, String> histogram : histograms) {
				if(histogram.getRight().equalsIgnoreCase(name)) {
					return histogram.getLeft().getHistogram().converToImage();
				}
			}
		}
		return null;
	}
	
	public ImageTab getImageTab(String name) {
		if(images != null) {
			for(Pair<ImageTab, String> image : images) {
				if(image.getRight().equalsIgnoreCase(name)) {
					return image.getLeft();
				}
			}			
		}
		return null;
	}
	
	public HistogramTab getHistogramTab(String name) {
		if(histograms != null) {
			for(Pair<HistogramTab, String> histogram : histograms) {
				if(histogram.getRight().equalsIgnoreCase(name)) {
					return histogram.getLeft();
				}
			}			
		}
		return null;
	}
	
	public String getName(Image image) {
		if(images != null) {
			for(Pair<ImageTab, String> imageTab : images) {
				if(imageTab.getLeft().getOrigin().id == image.id) {
					return imageTab.getLeft().getImageName();
				}
			}			
		}
		return null;
	}
	
	public String[] getTabsNames() {
		String[] names = new String[tabNames.size()];
		names = tabNames.toArray(names);
		return names;
	}
	
	public boolean contains(String name) {
		if(tabNames != null) {
			for(String tab : tabNames) {
				if(tab.contains(name)) {
					return true;
				}
			}
		}
		return false;
	}	
	
	public void addImageTab(String title, ImageTab image, JButton button) {
		if(tabNames == null)
			tabNames = new Vector<String>();
		
		if(tabNames.indexOf(title) < 0) {		
			this.addTab(title, image);
			int index = this.getTabCount() -1;
			this.setTabComponentAt(index, CloseableTab.createTab(title, button));
			this.setSelectedIndex(index);
			
			if(images == null) 
				images = new Vector<Pair<ImageTab, String>>();
			images.addElement(new Pair<ImageTab, String>(image, title));

			tabNames.addElement(title);
		}else {
			this.setSelectedIndex(tabNames.indexOf(title));
		}
	}
	
	public void addHistogramTab(String title, HistogramTab histogram, JButton button) {
		if(tabNames.indexOf(title) < 0) {	
			this.addTab(title, histogram);
			int index = this.getTabCount() -1;
			this.setTabComponentAt(index, CloseableTab.createTab(title, button));
			this.setSelectedIndex(index);
			
			if(histograms == null) 
				histograms = new Vector<Pair<HistogramTab, String>>();
			histograms.addElement(new Pair<HistogramTab, String>(histogram, title));
			
			tabNames.addElement(title);
		}else {
			this.setSelectedIndex(tabNames.indexOf(title));
		}
	}	
}
