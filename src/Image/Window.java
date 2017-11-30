package Image;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.jaiimageio.*;

public class Window {
	private JFrame frame;
	private MenuBar menuBar;
	private Tabs tabs;
	
	public Window() {
		frame = new JFrame("Image");
		setUpMenu();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(900, 500);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	private void setUpMenu() {
		menuBar = new MenuBar();

		JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);
	    
	    JMenuItem OpenFileItem = new JMenuItem("Open File", KeyEvent.VK_O);
	    OpenFileItem.setIcon(new ImageIcon("openFile.png"));
	    setUpOpenItem(OpenFileItem);
	    fileMenu.add(OpenFileItem);
	    
	    JMenu SaveFileSubmenu = new JMenu("Save File");
	    SaveFileSubmenu.setMnemonic(KeyEvent.VK_S);
	    SaveFileSubmenu.setIcon(new ImageIcon("saveFile.png"));
	    SaveFileSubmenu.setEnabled(false);
	    fileMenu.add(SaveFileSubmenu);
	    
	    JMenu editMenu = new JMenu("Edit");
	    editMenu.setMnemonic(KeyEvent.VK_E);
	    menuBar.add(editMenu);
	    
	    JMenuItem ConvertToGrayItem = new JMenuItem("Convert to Gray", KeyEvent.VK_G);
	    ConvertToGrayItem.setIcon(new ImageIcon("RGBtoGray.png"));
	    ConvertToGrayItem.setEnabled(false);
	    setUpConvertToGray(ConvertToGrayItem);
	    editMenu.add(ConvertToGrayItem);
	    
	    JMenuItem HistogramItem = new JMenuItem("Histogram", KeyEvent.VK_H);
	    HistogramItem.setIcon(new ImageIcon("histogram.png"));
	    HistogramItem.setEnabled(false);
	    setUpHistogram(HistogramItem);
	    editMenu.add(HistogramItem);
	    
	    JMenuItem HistogramAccumulatedItem = new JMenuItem("Histogram Accumulated", KeyEvent.VK_H);
	    HistogramAccumulatedItem.setIcon(new ImageIcon("histogramAccumulated.png"));
	    HistogramAccumulatedItem.setEnabled(false);
	    setUpHistogramAccumulated(HistogramAccumulatedItem);
	    editMenu.add(HistogramAccumulatedItem);
	    
	    JMenuItem LinearTransformtionItem = new JMenuItem("Linear Transformation", KeyEvent.VK_L);
	    LinearTransformtionItem.setIcon(new ImageIcon("linearTransformations.png"));
	    LinearTransformtionItem.setEnabled(false);
	    setUpLinearTransformation(LinearTransformtionItem);
	    editMenu.add(LinearTransformtionItem);
	    
	    JMenuItem BrightnessContrastItem = new JMenuItem("Brightness Contrast", KeyEvent.VK_B);
	    BrightnessContrastItem.setIcon(new ImageIcon("brightnessContrast.png"));
	    BrightnessContrastItem.setEnabled(false);
	    setUpBrightnessContrast(BrightnessContrastItem);
	    editMenu.add(BrightnessContrastItem);
	    
	    JMenuItem EcualizeItem = new JMenuItem("Ecualized image", KeyEvent.VK_E);
	    EcualizeItem.setIcon(new ImageIcon("ecualizeImage.png"));
        EcualizeItem.setEnabled(false);
        setUpEcualized(EcualizeItem);
        editMenu.add(EcualizeItem);
        
        JMenuItem EcualizeExternItem = new JMenuItem("Ecualized image from external image", KeyEvent.VK_E);
	    EcualizeExternItem.setIcon(new ImageIcon("histogramAccumulated.png"));
        EcualizeExternItem.setEnabled(false);
        setUpEcualizedExtern(EcualizeExternItem);
        editMenu.add(EcualizeExternItem);
        
        JMenuItem GammaCorrectionItem = new JMenuItem("Gamma correction", KeyEvent.VK_G);
        GammaCorrectionItem.setIcon(new ImageIcon("correctionGamma.png"));
        GammaCorrectionItem.setEnabled(false);
        setUpGammaCorrection(GammaCorrectionItem);
        editMenu.add(GammaCorrectionItem);
        
        JMenuItem DiffImagesItem = new JMenuItem("Difference between images", KeyEvent.VK_D);
        DiffImagesItem.setIcon(new ImageIcon("differenceImages.png"));
        DiffImagesItem.setEnabled(false);
        setUpDiffImage(DiffImagesItem);
        editMenu.add(DiffImagesItem);
        
        JMenuItem DiffImagesChangesItem = new JMenuItem("Changes Map between images", KeyEvent.VK_C);
        DiffImagesChangesItem.setIcon(new ImageIcon("differenceImagesChanges.png"));
        DiffImagesChangesItem.setEnabled(false);
        setUpDiffImageChanges(DiffImagesChangesItem);
        editMenu.add(DiffImagesChangesItem);
        
        JMenuItem SubImageItem = new JMenuItem("SubImage", KeyEvent.VK_S);
        SubImageItem.setIcon(new ImageIcon("cutImage.png"));
        SubImageItem.setEnabled(false);
        setUpSubImage(SubImageItem);
        editMenu.add(SubImageItem);
        
        JMenuItem DigitalizeItem = new JMenuItem("Digitalize Image", KeyEvent.VK_D);
        DigitalizeItem.setIcon(new ImageIcon("cutImage.png"));
        DigitalizeItem.setEnabled(false);
        setUpDigitalizeImage(DigitalizeItem);
        editMenu.add(DigitalizeItem);

        JMenu imageMenu = new JMenu("Image");
	    imageMenu.setMnemonic(KeyEvent.VK_I);
	    menuBar.add(imageMenu);
	    
	    JMenuItem FlipVerticalItem = new JMenuItem("Flip Vertical", KeyEvent.VK_V);
	    FlipVerticalItem.setIcon(new ImageIcon("flipVertical.png"));
	    setUpFlipVertical(FlipVerticalItem);
	    FlipVerticalItem.setEnabled(false);
	    imageMenu.add(FlipVerticalItem);
	    
	    JMenuItem FlipHorizontalItem = new JMenuItem("Flip Horizontal", KeyEvent.VK_H);
	    FlipHorizontalItem.setIcon(new ImageIcon("flipHorizontal.png"));
	    setUpFlipHorizontal(FlipHorizontalItem);
	    FlipHorizontalItem.setEnabled(false);
	    imageMenu.add(FlipHorizontalItem);
	    
	    JMenuItem TransposeItem = new JMenuItem("Transpose", KeyEvent.VK_T);
	    TransposeItem.setIcon(new ImageIcon("transpose.png"));
	    setUpTranspose(TransposeItem);
	    TransposeItem.setEnabled(false);
	    imageMenu.add(TransposeItem);
	    
	    JMenuItem RotateItem = new JMenuItem("Rotate", KeyEvent.VK_R);
	    RotateItem.setIcon(new ImageIcon("rotate.png"));
	    setUpRotate(RotateItem);
	    RotateItem.setEnabled(false);
	    imageMenu.add(RotateItem);
	    
	    JMenuItem ScaleItem = new JMenuItem("Scale", KeyEvent.VK_S);
	    ScaleItem.setIcon(new ImageIcon("scale.png"));
	    setUpScale(ScaleItem);
	    ScaleItem.setEnabled(false);
	    imageMenu.add(ScaleItem);
	            
        JMenu viewMenu = new JMenu("View");
	    viewMenu.setMnemonic(KeyEvent.VK_V);
	    menuBar.add(viewMenu);
	    
	    JMenuItem MultipleViewItem = new JMenuItem("Multiple Images", KeyEvent.VK_M);
	    MultipleViewItem.setIcon(new ImageIcon("multipleImages.png"));
	    setUpMultipleView(MultipleViewItem);
	    MultipleViewItem.setEnabled(false);
	    viewMenu.add(MultipleViewItem);
	    
	    frame.setJMenuBar(menuBar);
	    
	    tabs = new Tabs();
	    tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    frame.add(tabs);	    
	}
	
	private Image getSelectedImage() {
        int index = tabs.getSelectedIndex();
        ImageTab itab = tabs.getImageTab(tabs.getTabsNames()[index]);
        HistogramTab htab = tabs.getHistogramTab(tabs.getTabsNames()[index]);
        if(itab != null) {
        	return itab.getOrigin();
        }else if(htab != null) {
        	return htab.getOrigin();
        }
        return null;
	}
	
	private void setUpOpenItem(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String username = System.getProperty("user.name");
	    		String path = "C:\\Users\\" + username + "\\Downloads";
	    		JFileChooser filePicker = new JFileChooser(path);
	    		filePicker.setDialogTitle("Select a image to open");
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPEG, JPG", "jpg", "jpeg"));
	    		filePicker.setFileFilter(new FileNameExtensionFilter("TIFF, TIF", "tiff", "tif"));
	    		int result = filePicker.showOpenDialog(frame);
	    		
	    		if(result == JFileChooser.APPROVE_OPTION) {
					try {
						JButton button = new JButton();
						File file = filePicker.getSelectedFile();
						String tabName = file.getName() + " - Original Image";
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								JMenuItem[] items = menuBar.getMenuItems("Edit");
								tabs.removeTabs(tabName);
								if(!tabs.contains("Original Image")) {
									for(JMenuItem item : items)
										item.setEnabled(false);
								}
								tabs.remove(tabName);
							}
						});
						Image res = new Image(ImageIO.read(file));
						if(res.getBufferedImage().getType() >= 10) {
							tabs.addImageTab(tabName, new ImageTab(res, res.getBufferedImage(), file.getName(), true), button);
						}else {
							tabs.addImageTab(tabName, new ImageTab(res, res.getBufferedImage(), file.getName(), false), button);
						}
						JMenuItem[] items = menuBar.getMenuItems("Edit");
						for(JMenuItem item : items)
							item.setEnabled(true);
						items = menuBar.getMenuItems("View");
						for(JMenuItem item : items)
							item.setEnabled(true);
						items = menuBar.getMenuItems("Image");
						for(JMenuItem item : items)
							item.setEnabled(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			frame.setVisible(true);	    			
	    		}else if(result == JFileChooser.ERROR_OPTION) {
	    			JOptionPane.showMessageDialog(null, "An error has ocurred, try to open the file again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	}
	
	private void setUpConvertToGray(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				if(original != null) {
					BufferedImage gray = original.RGBtoGray();
					JButton button = new JButton();
					String tabName = tabs.getName(original) + " - Gray Image";
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							tabs.remove(tabName);
							menuBar.removeFromSave(tabName);
						}
					});
					tabs.addImageTab(tabName, new ImageTab(new Image(gray), gray, tabName, true), button);
					addToSaveItem(tabName, new ImageIcon("RGBtoGray.png"), KeyEvent.VK_G);           
					JMenuItem itemBar = menuBar.getItem("File", "Save File");
	                if(itemBar != null) {
	                    itemBar.setEnabled(true);
	                }
				}else{
					JOptionPane.showMessageDialog(null, "Can't convert to gray, try again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void setUpHistogram(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
				JButton button = new JButton();
				String tabName = tabs.getName(original) + " - Histogram";
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tabs.remove(tabName);
						menuBar.removeFromSave(tabName);
					}
				});
				tabs.addHistogramTab(tabName, new HistogramTab(original, gray, false), button);
				addToSaveItem(tabName, new ImageIcon("histogram.png"), KeyEvent.VK_H);
				JMenuItem itemBar = menuBar.getItem("File", "Save File");
                if(itemBar != null) {
                    itemBar.setEnabled(true);
                }
			}
		});
	}
	
	private void setUpHistogramAccumulated(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
				JButton button = new JButton();
				String tabName = tabs.getName(original) + " - Histogram Accumulated";
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tabs.remove(tabName);
						menuBar.removeFromSave(tabName);
					}
				});
				tabs.addHistogramTab(tabName, new HistogramTab(original, gray, true), button);
				addToSaveItem(tabName, new ImageIcon("histogramAccumulated.png"), KeyEvent.VK_H);
				JMenuItem itemBar = menuBar.getItem("File", "Save File");
                if(itemBar != null) {
                    itemBar.setEnabled(true);
                }
			}
		});
	}
	
    private void setUpEcualized(JMenuItem item) {
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
                Image result = gray.EcualizedImage(); 
                JButton button = new JButton();
                String tabName = tabs.getName(original) + " - Ecualized Image";
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tabs.remove(tabName);
						menuBar.removeFromSave(tabName);
                    }
                });
                tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
                addToSaveItem(tabName, new ImageIcon("ecualizeImage.png"), KeyEvent.VK_E);
                JMenuItem itemBar = menuBar.getItem("File", "Save File");
                if(itemBar != null) {
                    itemBar.setEnabled(true);
                }               
            }
        });
    }
    
    private void setUpEcualizedExtern(JMenuItem item) {
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
				String username = System.getProperty("user.name");
	    		String path = "C:\\Users\\" + username + "\\Downloads";
	    		JFileChooser filePicker = new JFileChooser(path);
	    		filePicker.setDialogTitle("Select a image to open");
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPEG, JPG", "jpg", "jpeg"));
	    		filePicker.setFileFilter(new FileNameExtensionFilter("TIFF, TIF", "tiff", "tif"));
	    		int result = filePicker.showOpenDialog(frame);
	    		
	    		if(result == JFileChooser.APPROVE_OPTION) {
					try {
						File file = filePicker.getSelectedFile();
						Image original = getSelectedImage();
						Image gray = new Image(original.RGBtoGray());
						Image res = gray.EcualizedImageFromImage(new Image(ImageIO.read(file)));
		                JButton button = new JButton();
		                String tabName = tabs.getName(original) + " - Ecualized Image from External Image";
		                button.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                        tabs.remove(tabName);
								menuBar.removeFromSave(tabName);
		                    }
		                });
		                tabs.addImageTab(tabName, new ImageTab(res, res.getBufferedImage(), tabName, true), button);
		                addToSaveItem(tabName, new ImageIcon("ecualizeImage.png"), KeyEvent.VK_E);
		                JMenuItem itemBar = menuBar.getItem("File", "Save File");
		                if(itemBar != null) {
		                    itemBar.setEnabled(true);
		                }
					} catch (IOException e) {
						e.printStackTrace();
					}   			
	    		}else if(result == JFileChooser.ERROR_OPTION) {
	    			JOptionPane.showMessageDialog(null, "An error has ocurred, try to open the file again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
	    		}                             
            }
        });
    }
	
	private void setUpLinearTransformation(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
	    		linearTransformationFrame LTFrame = new linearTransformationFrame("Linear transformation");
	    		LTFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    		LTFrame.setLocationRelativeTo(null);
	    		LTFrame.setVisible(true);
	    		LTFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    		        int[] nodes = LTFrame.getNodeData();	    		    
			    	    if(nodes.length > 0) {		
			    	    	LTFrame.setVisible(false);
			    	    	LTFrame.dispose();
			    	    	byte[] originalGray = gray.getCopy().getVector();
			    	    	for(int i=0; i<nodes.length-3; i+=2) {
			    	    		if(nodes[i+1] != nodes[i+3]) {
			    	    			if(nodes[i] != nodes[i+2]) {
				    	    			for(int j=0; j<(nodes[i+2]-nodes[i]); j++) {	
				    	    				float dif = ((float)(nodes[i+3]-nodes[i+1])/(float)(nodes[i+2]-nodes[i]));
				    	    				gray.setPixelWithValue(originalGray, nodes[i]+j, nodes[i+1] + (dif*j));
				    	    			}
				    	    		}
			    	    		}else {
				    	    		if(nodes[i] != nodes[i+2]) {
				    	    			gray.setPixelWithValue(originalGray, nodes[i], nodes[i+2], nodes[i+1]);
				    	    		}
			    	    		}
			    	    	}
							JButton button = new JButton();
				    		String tabName = tabs.getName(original) + " - Linear transformation";
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
								}
							});
							tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
							addToSaveItem(tabName, new ImageIcon("linearTransformations.png"), KeyEvent.VK_L);
							JMenuItem itemBar = menuBar.getItem("File", "Save File");
			                if(itemBar != null) {
			                    itemBar.setEnabled(true);
			                }
						}else{
							JOptionPane.showMessageDialog(null, "Can't show the transformed image, try again",
			    					"Error", JOptionPane.ERROR_MESSAGE);
						}
	    		    }
	    		});
	    		LTFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	int[] nodes = LTFrame.getNodeData();	    		    
				    	    if(nodes.length > 0) {		
				    	    	LTFrame.setVisible(false);
				    	    	LTFrame.dispose();
				    	    	byte[] originalGray = gray.getCopy().getVector();
				    	    	for(int i=0; i<nodes.length-3; i+=2) {
				    	    		if(nodes[i+1] != nodes[i+3]) {
				    	    			if(nodes[i] != nodes[i+2]) {
					    	    			for(int j=0; j<(nodes[i+2]-nodes[i]); j++) {	
					    	    				float dif = ((float)(nodes[i+3]-nodes[i+1])/(float)(nodes[i+2]-nodes[i]));
					    	    				gray.setPixelWithValue(originalGray, nodes[i]+j, nodes[i+1] + (dif*j));
					    	    			}
					    	    		}
				    	    		}else {
					    	    		if(nodes[i] != nodes[i+2]) {
					    	    			gray.setPixelWithValue(originalGray, nodes[i], nodes[i+2], nodes[i+1]);
					    	    		}
				    	    		}
				    	    	}
								JButton button = new JButton();
					    		String tabName = tabs.getName(original) + " - Linear transformation";
								button.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
									}
								});
								tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
								addToSaveItem(tabName, new ImageIcon("linearTransformations.png"), KeyEvent.VK_L);
								JMenuItem itemBar = menuBar.getItem("File", "Save File");
				                if(itemBar != null) {
				                    itemBar.setEnabled(true);
				                }
							}else{
								JOptionPane.showMessageDialog(null, "Can't show the transformed image, try again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
							}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}

	private void setUpBrightnessContrast(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
	    		brightnessContrastFrame BGFrame = new brightnessContrastFrame("Change Brightness Contrast", gray.getBrightness(), gray.getContrast());
	    		BGFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    		BGFrame.setLocationRelativeTo(null);
	    		BGFrame.setVisible(true);
	    		BGFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				int[] data = BGFrame.getData();
	    				if(data.length == 2) {
	    					BGFrame.setVisible(false);
			    	    	BGFrame.dispose();
			    	    	gray.BCImage(data[0], data[1]);
							JButton button = new JButton();
							String tabName = tabs.getName(original) + " - Brightness Contrast";
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
								}
							});
							tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
							addToSaveItem(tabName, new ImageIcon("brightnessContrast.png"), KeyEvent.VK_B);
							JMenuItem itemBar = menuBar.getItem("File", "Save File");
			                if(itemBar != null) {
			                    itemBar.setEnabled(true);
			                }
						}else{
							JOptionPane.showMessageDialog(null, "Can't show the modified image, try again",
			    					"Error", JOptionPane.ERROR_MESSAGE);
						}
	    		    }
	    		});
	    		BGFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	int[] data = BGFrame.getData();
		    				if(data.length == 2) {
		    					BGFrame.setVisible(false);
				    	    	BGFrame.dispose();
				    	    	gray.BCImage(data[0], data[1]);
								JButton button = new JButton();
								String tabName = tabs.getName(original) + " - Brightness Contrast";
								button.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
									}
								});
								tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
								addToSaveItem(tabName, new ImageIcon("brightnessContrast.png"), KeyEvent.VK_B);
								JMenuItem itemBar = menuBar.getItem("File", "Save File");
				                if(itemBar != null) {
				                    itemBar.setEnabled(true);
				                }
							}else{
								JOptionPane.showMessageDialog(null, "Can't show the modified image, try again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
							}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
	
	private void setUpGammaCorrection(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
				Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
				GammaCorrectionFrame GCFrame = new GammaCorrectionFrame("Select Gamma Correction");
				GCFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				GCFrame.setLocationRelativeTo(null);
				GCFrame.setVisible(true);
				GCFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				Double correction = GCFrame.getData();
	    				if(correction != 0) {
	    					GCFrame.setVisible(false);
			    	    	GCFrame.dispose();
	    					gray.GammaCImage(correction);
	    					JButton button = new JButton();
							String tabName = tabs.getName(original) + " - Gamma Correction";
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
								}
							});
							tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
							addToSaveItem(tabName, new ImageIcon("correctionGamma.png"), KeyEvent.VK_G);
							JMenuItem itemBar = menuBar.getItem("File", "Save File");
			                if(itemBar != null) {
			                    itemBar.setEnabled(true);
			                }
	    				}else {
	    					JOptionPane.showMessageDialog(null, "Can't correct the gamma with exponent 0, try again",
			    					"Error", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
				});
				GCFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	Double correction = GCFrame.getData();
		    				if(correction != 0) {
		    					GCFrame.setVisible(false);
				    	    	GCFrame.dispose();
		    					gray.GammaCImage(correction);
		    					JButton button = new JButton();
								String tabName = tabs.getName(original) + " - Gamma Correction";
								button.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
									}
								});
								tabs.addImageTab(tabName, new ImageTab(gray, gray.getBufferedImage(), tabName, true), button);
								addToSaveItem(tabName, new ImageIcon("correctionGamma.png"), KeyEvent.VK_G);
								JMenuItem itemBar = menuBar.getItem("File", "Save File");
				                if(itemBar != null) {
				                    itemBar.setEnabled(true);
				                }
		    				}else {
		    					JOptionPane.showMessageDialog(null, "Can't correct the gamma with exponent 0, try again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
		    				}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
	
	private void setUpDiffImage(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String username = System.getProperty("user.name");
	    		String path = "C:\\Users\\" + username + "\\Downloads";
	    		JFileChooser filePicker = new JFileChooser(path);
	    		filePicker.setDialogTitle("Select a image to open");
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPEG, JPG", "jpg", "jpeg"));
	    		filePicker.setFileFilter(new FileNameExtensionFilter("TIFF, TIF", "tiff", "tif"));
	    		int result = filePicker.showOpenDialog(frame);
	    		
	    		if(result == JFileChooser.APPROVE_OPTION) {
					try {
						File file = filePicker.getSelectedFile();
						Image original = getSelectedImage();
						Image gray = new Image(original.RGBtoGray());
						String tabName = tabs.getName(original) + " - Difference Images";
						JButton button = new JButton();
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								tabs.remove(tabName);
								menuBar.removeFromSave(tabName);
							}
						});						
						Image resultImg = gray.DiffImage(new Image(new Image(ImageIO.read(file)).RGBtoGray()));
						tabs.addImageTab(tabName, new ImageTab(resultImg, resultImg.getBufferedImage(), tabName, false), button);
						addToSaveItem(tabName, new ImageIcon("differenceImages.png"), KeyEvent.VK_D);
					} catch (IOException e) {
						e.printStackTrace();
					}   			
	    		}else if(result == JFileChooser.ERROR_OPTION) {
	    			JOptionPane.showMessageDialog(null, "An error has ocurred, try to open the file again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	}
	
	private void setUpDiffImageChanges(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String username = System.getProperty("user.name");
	    		String path = "C:\\Users\\" + username + "\\Downloads";
	    		JFileChooser filePicker = new JFileChooser(path);
	    		filePicker.setDialogTitle("Select a image to open");
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPEG, JPG", "jpg", "jpeg"));
	    		filePicker.setFileFilter(new FileNameExtensionFilter("TIFF, TIF", "tiff", "tif"));
	    		int result = filePicker.showOpenDialog(frame);
	    		
	    		if(result == JFileChooser.APPROVE_OPTION) {
					File file = filePicker.getSelectedFile();
					Image original = getSelectedImage();
					Image gray = new Image(original.RGBtoGray());
					String tabName = tabs.getName(original) + " - Difference Images Changes Map";
					JButton button = new JButton();
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							tabs.remove(tabName);
							menuBar.removeFromSave(tabName);
						}
					});			
					ThresholdFrame TFrame= new ThresholdFrame("Select the threshold to use");
					TFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					TFrame.setLocationRelativeTo(null);
					TFrame.setVisible(true);
					TFrame.btnAceptar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int threshold = TFrame.getData();
							TFrame.setVisible(false);
					    	TFrame.dispose();
					    	try {
					    		Image resultImg = gray.DiffImageChangesMap(new Image(new Image(ImageIO.read(file)).RGBtoGray()), threshold);
								tabs.addImageTab(tabName, new ImageTab(resultImg, resultImg.getBufferedImage(), tabName, false), button);
								addToSaveItem(tabName, new ImageIcon("differenceImagesChanges.png"), KeyEvent.VK_C);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});   			
	    		}else if(result == JFileChooser.ERROR_OPTION) {
	    			JOptionPane.showMessageDialog(null, "An error has ocurred, try to open the file again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	}
	
	private void setUpSubImage(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
				CutImageFrame CIFrame = new CutImageFrame("Select a region into the image", gray);
				CIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				CIFrame.setLocationRelativeTo(null);
				CIFrame.setVisible(true);
				CIFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				Image result = CIFrame.getCuttedImage();
	    				if(result != null) {
	    					CIFrame.setVisible(false);
			    	    	CIFrame.dispose();
	    					JButton button = new JButton();
	    					String tabName = tabs.getName(original) + " - Cutted Image";
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
								}
							});
							tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
							addToSaveItem(tabName, new ImageIcon("cutImage.png"), KeyEvent.VK_C);
							JMenuItem itemBar = menuBar.getItem("File", "Save File");
			                if(itemBar != null) {
			                    itemBar.setEnabled(true);
			                }
	    				}else {
	    					JOptionPane.showMessageDialog(null, "Can't cut the image, try again",
			    					"Error", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
				});
				CIFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	Image result = CIFrame.getCuttedImage();
		    				if(result != null) {
		    					CIFrame.setVisible(false);
				    	    	CIFrame.dispose();
		    					JButton button = new JButton();
		    					String tabName = tabs.getName(original) + " - Cutted Image";
								button.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
									}
								});
								tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
								addToSaveItem(tabName, new ImageIcon("cutImage.png"), KeyEvent.VK_C);
								JMenuItem itemBar = menuBar.getItem("File", "Save File");
				                if(itemBar != null) {
				                    itemBar.setEnabled(true);
				                }
		    				}else {
		    					JOptionPane.showMessageDialog(null, "Can't cut the image, try again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
		    				}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
	
	private void setUpDigitalizeImage(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
				Image gray = new Image(original.RGBtoGray());
				DigitalizeFrame DFrame = new DigitalizeFrame("Select values to digitalize");
				DFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				DFrame.setLocationRelativeTo(null);
				DFrame.setVisible(true);
				DFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				int[] data = DFrame.getData();
	    				if(data.length == 2) {
	    					DFrame.setVisible(false);
			    	    	DFrame.dispose();
	    					JButton button = new JButton();
	    					String tabName = tabs.getName(original) + " - Digitalized Image";
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
								}
							});
							Image result = gray.digitalization(data[0], data[1]);
							tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
							addToSaveItem(tabName, new ImageIcon("cutImage.png"), KeyEvent.VK_D);
							JMenuItem itemBar = menuBar.getItem("File", "Save File");
			                if(itemBar != null) {
			                    itemBar.setEnabled(true);
			                }
	    				}else {
	    					JOptionPane.showMessageDialog(null, "Can't digitalize the image, try again",
			    					"Error", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
				});
				DFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	int[] data = DFrame.getData();
		    				if(data.length == 2) {
		    					DFrame.setVisible(false);
				    	    	DFrame.dispose();
		    					JButton button = new JButton();
		    					String tabName = tabs.getName(original) + " - Digitalized Image";
								button.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
									}
								});
								Image result = gray.digitalization(data[0], data[1]);
								tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
								addToSaveItem(tabName, new ImageIcon("cutImage.png"), KeyEvent.VK_D);
								JMenuItem itemBar = menuBar.getItem("File", "Save File");
				                if(itemBar != null) {
				                    itemBar.setEnabled(true);
				                }
		    				}else {
		    					JOptionPane.showMessageDialog(null, "Can't digitalize the image, try again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
		    				}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
	
	private void addToSaveItem(String name, ImageIcon icon, int keyEvent) {
		JMenu menu = (JMenu) menuBar.getItem("File", "Save File");
		JMenuItem exampleItem = new JMenuItem(name);
		exampleItem.setMnemonic(keyEvent);
		if(icon != null)
			exampleItem.setIcon(icon);
		exampleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem source = (JMenuItem)e.getSource();
				String tabName = source.getText();
				
				JFileChooser filePicker = new JFileChooser("C:\\Users\\" + System.getProperty("user.name") + "\\Downloads");
	    		filePicker.setDialogTitle("Select a image to save");
	    		filePicker.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpeg"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
	    		filePicker.addChoosableFileFilter(new FileNameExtensionFilter("TIFF", "tiff"));
	    		int result = filePicker.showSaveDialog(frame);
	    		
	    		if(result == JFileChooser.APPROVE_OPTION) {
						try {
							boolean res;
							if(filePicker.getSelectedFile().getAbsolutePath().indexOf(".") == -1) {
								res = ImageIO.write(tabs.getBuffImage(tabName), filePicker.getFileFilter().getDescription(), new File(filePicker.getSelectedFile().getAbsolutePath() + "." + filePicker.getFileFilter().getDescription().toLowerCase()));
							}else {
								res = ImageIO.write(tabs.getBuffImage(tabName), filePicker.getFileFilter().getDescription(), new File(filePicker.getSelectedFile().getAbsolutePath()));
							}
							if(!res) {
								JOptionPane.showMessageDialog(null, "An error has ocurred, try to save the file again",
				    					"Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
	    		}else if(result == JFileChooser.ERROR_OPTION) {
	    			JOptionPane.showMessageDialog(null, "An error has ocurred, try to save the file again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
	    		}
			}
		});;
		menu.add(exampleItem);
	}
	
	private void setUpMultipleView(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {	    	
	    		if(tabs.infoVisibility) {
		    		tabs.toogleVisibilityInfo();
		    		JMenuItem[] items = menuBar.getMenuItems("Edit");
		    		for(JMenuItem item : items)
						item.setEnabled(false);
		    		items = menuBar.getMenuItems("Image");
		    		for(JMenuItem item : items)
						item.setEnabled(false);
		    		items = menuBar.getMenuItems("View");
		    		items[0].setIcon(new ImageIcon("singleImage.png"));
		    		items[0].setMnemonic(KeyEvent.VK_S);
		    		items[0].setText("Single Image");
		    		frame.setLayout(new GridBagLayout());
		    		
		    		Tabs copy = tabs.getCopy();
		    		Tabs copy2 = tabs.getCopy();
		    		GridBagConstraints t1 = new GridBagConstraints();
		    		t1.gridx = 0;
		    		t1.gridy = 0;
		    		
		    		GridBagConstraints t2 = new GridBagConstraints();
		    		t2.gridx = 1;
		    		t2.gridy = 0;
	
		    		frame.setContentPane(new JPanel() {
						private static final long serialVersionUID = 1L;
						
		    			@Override
		    			public void paintComponent(Graphics g) {
		    				super.paintComponent(g);
		    				
		    				copy.setPreferredSize(new Dimension(frame.getWidth()/2-30, frame.getHeight()-70));
		    	    		copy2.setPreferredSize(new Dimension(frame.getWidth()/2-30, frame.getHeight()-70));
							copy.repaint();
							copy2.repaint();
		    			}
		    		});
		    		
		    		frame.addWindowStateListener(new WindowStateListener() {
						@Override
						public void windowStateChanged(WindowEvent e) {
							if(((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH && (e.getOldState() & Frame.NORMAL) == Frame.NORMAL) || ((e.getNewState() & Frame.NORMAL) == Frame.NORMAL && (e.getOldState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)) {
								frame.getContentPane().repaint();
							}
						}
					});
		    		
		    		frame.getContentPane().add(copy, t1);
		    		frame.getContentPane().add(copy2, t2);
		    		
					copy.setPreferredSize(new Dimension(frame.getWidth()/2-30, frame.getHeight()-60));
		    		copy2.setPreferredSize(new Dimension(frame.getWidth()/2-30, frame.getHeight()-60));
		    		
		    		String tabName = "Comodin";
		    		JButton button = new JButton();
					copy.addImageTab(tabName, new ImageTab(new Image(new BufferedImage(2,2,BufferedImage.TYPE_BYTE_GRAY)), new BufferedImage(2,2,BufferedImage.TYPE_BYTE_GRAY), tabName, true), button);
					copy.remove(tabName);
					copy.repaint();
	    		}else {
		    		tabs.toogleVisibilityInfo();
		    		JMenuItem[] items = menuBar.getMenuItems("Edit");
		    		for(JMenuItem item : items)
						item.setEnabled(true);
		    		items = menuBar.getMenuItems("Image");
		    		for(JMenuItem item : items)
						item.setEnabled(true);
		    		items = menuBar.getMenuItems("View");
		    		items[0].setIcon(new ImageIcon("multipleImages.png"));
		    		items[0].setMnemonic(KeyEvent.VK_M);
		    		items[0].setText("Multiple Images");
		    		frame.setLayout(new GridBagLayout());
		    		
		    		Tabs copy = tabs.getCopy();
		    		GridBagConstraints t1 = new GridBagConstraints();
		    		t1.gridx = 0;
		    		t1.gridy = 0;
		    		
		    		frame.setContentPane(new JPanel() {
						private static final long serialVersionUID = 1L;
						
		    			@Override
		    			public void paintComponent(Graphics g) {
		    				super.paintComponent(g);
		    				
		    				copy.setPreferredSize(new Dimension(frame.getWidth()-30, frame.getHeight()-70));
							copy.repaint();
		    			}
		    		});
		    		
		    		frame.addWindowStateListener(new WindowStateListener() {
						@Override
						public void windowStateChanged(WindowEvent e) {
							if(((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH && (e.getOldState() & Frame.NORMAL) == Frame.NORMAL) || ((e.getNewState() & Frame.NORMAL) == Frame.NORMAL && (e.getOldState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)) {
								frame.getContentPane().repaint();
							}
						}
					});
		    		
		    		frame.getContentPane().add(copy, t1);
		    		
		    		frame.getContentPane().getComponent(0).setPreferredSize(new Dimension(frame.getWidth()-30, frame.getHeight()-70));
		    		frame.repaint();
		    		
		    		tabs = copy;
		    		String tabName = "Comodin";
		    		JButton button = new JButton();
					tabs.addImageTab(tabName, new ImageTab(new Image(new BufferedImage(2,2,BufferedImage.TYPE_BYTE_GRAY)), new BufferedImage(2,2,BufferedImage.TYPE_BYTE_GRAY), tabName, true), button);
					tabs.remove(tabName);
	    		}
	    	}
		});
	}
	
	private void setUpFlipVertical(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
	    		Image result;
	    		if(original.getBufferedImage().getType() >= 10) {
	    			result = original.flipVerticalGray();
	    		}else {
	    			result = original.flipVerticalColor();
	    		}
	    		if(result != null) {
		    		JButton button = new JButton();
					String tabName = tabs.getName(original) + " - Vertical Flipped Image";
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							tabs.remove(tabName);
							menuBar.removeFromSave(tabName);
						}
					});
					if(result.getBufferedImage().getType() >= 10) {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
					}else {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
					}
					addToSaveItem(tabName, new ImageIcon("flipVertical.png"), KeyEvent.VK_V);
	    		}else {
					JOptionPane.showMessageDialog(null, "Can't flip vertical the image, try again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
		});
	}
	
	private void setUpFlipHorizontal(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
	    		Image result;
	    		if(original.getBufferedImage().getType() >= 10) {
	    			result = original.flipHorizontalGray();
	    		}else {
	    			result = original.flipHorizontalColor();
	    		}
	    		if(result != null) {
		    		JButton button = new JButton();
					String tabName = tabs.getName(original) + " - Horizontal Flipped Image";
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							tabs.remove(tabName);
							menuBar.removeFromSave(tabName);
						}
					});
					if(result.getBufferedImage().getType() >= 10) {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
					}else {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
					}
					addToSaveItem(tabName, new ImageIcon("flipHorizontal.png"), KeyEvent.VK_H);
	    		}else {
					JOptionPane.showMessageDialog(null, "Can't flip horizontal the image, try again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
		});
	}
	
	private void setUpTranspose(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
	    		Image result;
	    		if(original.getBufferedImage().getType() >= 10) {
	    			result = original.transposeGray();
	    		}else {
	    			result = original.transposeColor();
	    		}
	    		if(result != null) {
		    		JButton button = new JButton();
					String tabName = tabs.getName(original) + " - Transpposed Image";
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							tabs.remove(tabName);
							menuBar.removeFromSave(tabName);
						}
					});
					if(result.getBufferedImage().getType() >= 10) {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
					}else {
						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
					}
					addToSaveItem(tabName, new ImageIcon("transpose.png"), KeyEvent.VK_T);
	    		}else {
					JOptionPane.showMessageDialog(null, "Can't transpose the image, try again",
	    					"Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
		});
	}
	
	private void setUpRotate(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
	    		RotationFrame RFrame = new RotationFrame("Select the degrees to rotate");
				RFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				RFrame.setLocationRelativeTo(null);
				RFrame.setVisible(true);
				RFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				Image result;
	    				RFrame.setVisible(false);
		    	    	RFrame.dispose();
	    				int degrees = RFrame.getData();
	    				if(original.getBufferedImage().getType() >= 10) {
	    	    			result = original.rotateGray(degrees);
	    	    		}else {
	    	    			result = original.rotateColor(degrees);
	    	    		}
	    	    		if(result != null) {
	    		    		JButton button = new JButton();
	    					String tabName = tabs.getName(original) + " - Rotated Image";
	    					button.addActionListener(new ActionListener() {
	    						@Override
	    						public void actionPerformed(ActionEvent e) {
	    							tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
	    						}
	    					});
	    					if(result.getBufferedImage().getType() >= 10) {
	    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
	    					}else {
	    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
	    					}
	    					addToSaveItem(tabName, new ImageIcon("rotate.png"), KeyEvent.VK_R);
	    	    		}else {
	    					JOptionPane.showMessageDialog(null, "Can't rotate the image, try again",
	    	    					"Error", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
	    		});	    	
				RFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	Image result;
					    	RFrame.setVisible(false);
			    	    	RFrame.dispose();
					    	int degrees = RFrame.getData();
		    				if(original.getBufferedImage().getType() >= 10) {
		    	    			result = original.rotateGray(degrees);
		    	    		}else {
		    	    			result = original.rotateColor(degrees);
		    	    		}
		    	    		if(result != null) {
		    		    		JButton button = new JButton();
		    					String tabName = tabs.getName(original) + " - Rotated Image";
		    					button.addActionListener(new ActionListener() {
		    						@Override
		    						public void actionPerformed(ActionEvent e) {
		    							tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
		    						}
		    					});
		    					if(result.getBufferedImage().getType() >= 10) {
		    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
		    					}else {
		    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
		    					}
		    					addToSaveItem(tabName, new ImageIcon("rotate.png"), KeyEvent.VK_R);
		    	    		}else {
		    					JOptionPane.showMessageDialog(null, "Can't rotate the image, try again",
		    	    					"Error", JOptionPane.ERROR_MESSAGE);
		    	    		}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
	
	private void setUpScale(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		Image original = getSelectedImage();
	    		ScaleFrame SFrame = new ScaleFrame("Select the percentages to scale");
				SFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				SFrame.setLocationRelativeTo(null);
				SFrame.setVisible(true);
				SFrame.btnAceptar.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0) {
	    				Image result;
	    				SFrame.setVisible(false);
		    	    	SFrame.dispose();
	    				int[] scale = SFrame.getData();
	    				if(original.getBufferedImage().getType() >= 10) {
	    					result = original.scaleVMPGray((float)scale[0]/(float)100, (float)scale[1]/(float)100);
	    				}else {	    					
	    					result = original.scaleVMPColor((float)scale[0]/(float)100, (float)scale[1]/(float)100);
	    				}
	    	    		if(result != null) {
	    		    		JButton button = new JButton();
	    					String tabName = tabs.getName(original) + " - Scaled Image";
	    					button.addActionListener(new ActionListener() {
	    						@Override
	    						public void actionPerformed(ActionEvent e) {
	    							tabs.remove(tabName);
									menuBar.removeFromSave(tabName);
	    						}
	    					});
	    					if(result.getBufferedImage().getType() >= 10) {
	    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
	    					}else {
	    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
	    					}
	    					addToSaveItem(tabName, new ImageIcon("scale.png"), KeyEvent.VK_R);
	    	    		}else {
	    					JOptionPane.showMessageDialog(null, "Can't scale the image, try again",
	    	    					"Error", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
	    		});	    	
				SFrame.btnAceptar.addKeyListener(new KeyListener() {					
					@Override
					public void keyPressed(KeyEvent e) {
					    if (e.getKeyCode()==KeyEvent.VK_ENTER){
					    	Image result;
		    				SFrame.setVisible(false);
			    	    	SFrame.dispose();
		    				int[] scale = SFrame.getData();
		    				if(original.getBufferedImage().getType() >= 10) {
		    					result = original.scaleVMPGray((float)scale[0]/(float)100, (float)scale[1]/(float)100);
		    				}else {	    					
		    					result = original.scaleVMPColor((float)scale[0]/(float)100, (float)scale[1]/(float)100);
		    				}
		    	    		if(result != null) {
		    		    		JButton button = new JButton();
		    					String tabName = tabs.getName(original) + " - Scaled Image";
		    					button.addActionListener(new ActionListener() {
		    						@Override
		    						public void actionPerformed(ActionEvent e) {
		    							tabs.remove(tabName);
										menuBar.removeFromSave(tabName);
		    						}
		    					});
		    					if(result.getBufferedImage().getType() >= 10) {
		    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, true), button);
		    					}else {
		    						tabs.addImageTab(tabName, new ImageTab(result, result.getBufferedImage(), tabName, false), button);
		    					}
		    					addToSaveItem(tabName, new ImageIcon("scale.png"), KeyEvent.VK_R);
		    	    		}else {
		    					JOptionPane.showMessageDialog(null, "Can't scale the image, try again",
		    	    					"Error", JOptionPane.ERROR_MESSAGE);
		    				}
					    }
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
	    	}
		});
	}
}
