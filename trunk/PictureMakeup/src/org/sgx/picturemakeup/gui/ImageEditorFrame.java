package org.sgx.picturemakeup.gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileFilter;

import org.sgx.picturemakeup.gui.editors.TransformationEditor;
import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.PropertyListener;
import org.sgx.picturemakeup.persistence.Loader;
import org.sgx.picturemakeup.persistence.PersistenceFactory;
import org.sgx.picturemakeup.persistence.Saver;
import org.sgx.picturemakeup.test.PropHaverEditorTest;
import org.sgx.picturemakeup.transformations.BlurTransformation;
import org.sgx.picturemakeup.transformations.EmbossingTransformation;
import org.sgx.picturemakeup.transformations.FTransformation;
import org.sgx.picturemakeup.transformations.GaussianSmoothTransformation;
import org.sgx.picturemakeup.transformations.HighPassTransformation;
import org.sgx.picturemakeup.transformations.LoGTransformation;
import org.sgx.picturemakeup.transformations.RobertCrossTransformation;
import org.sgx.picturemakeup.transformations.SharpTransformation;
import org.sgx.picturemakeup.transformations.ShearTransformation;
import org.sgx.picturemakeup.transformations.SobelTransformation;
import org.sgx.picturemakeup.transformations.bounds.FlipTransformation;
import org.sgx.picturemakeup.transformations.bounds.RotationTransformation;
import org.sgx.picturemakeup.transformations.bounds.ScaleTransformation;
import org.sgx.picturemakeup.transformations.colors.BrightTransformation;
import org.sgx.picturemakeup.transformations.colors.ToGrayTransformation;
import org.sgx.utils.FSUtils;
import org.sgx.utils.ImageUtils;


 

public class ImageEditorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar jJMenuBar = null;

	private JMenu file = null;

	private JMenuItem export = null;

	private JMenuItem importMenu = null;

	private JMenuItem exit = null;

	private JMenu image = null;

	private JMenuItem scale = null;

	private JMenuItem rotate = null;

	private JMenuItem shear = null;

	private JMenu filters = null;

	private JMenuItem robertCross = null;

	private JMenu edgeDetection = null;

	private JMenu jMenu4 = null;

	private JMenuItem bright = null;

	private JMenuItem contrast = null;

	private JMenuItem color = null;

	private JMenuItem flip = null;

	private JMenuItem emboss = null;

	private JMenuItem NormalBlur = null;

	private JMenuItem sharp = null;

	private JMenuItem sobel = null;

	private JMenu colors = null;

	private JMenuItem grayScale = null;

	private JMenuItem colorSwitching = null;

	private ImageWidgetImpl img;

	private JSplitPane jSplitPane = null;

	private JPanel jPanel = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JPanel optionsPanel = null;

	private JPanel jPanel2 = null;

	private JMenu jMenu6 = null;

	private JMenu help = null;

	private JMenuItem manual = null;

	private JMenuItem about = null;

	private JMenuItem FBlur;

	private JMenuItem highPass = null;

	private JMenuItem log = null;

	private JMenuItem importFromUrl = null;

	private JMenuItem license = null;

	private JMenu blur = null;

	private JMenuItem gaussianSmooth = null;

	private JMenu Noise = null;

	private JMenuItem ditherRandom = null;

	private JMenu jMenu1 = null;

	/**
	 * This is the default constructor
	 */
	public ImageEditorFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJSplitPane());		
		this.setTitle("Image Editor");
		
	}

	
    /**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFile());
			jJMenuBar.add(getImage());
			jJMenuBar.add(getFilters());
			jJMenuBar.add(getColors());
			jJMenuBar.add(getJMenu4());
			jJMenuBar.add(getJMenu6());
			jJMenuBar.add(getHelp());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes file	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFile() {
		if (file == null) {
			file = new JMenu("File");
			file.add(getExport());
			file.add(getImportMenu());			
			file.add(getImportFromUrl());
			file.addSeparator();
			file.add(getExit());
		}
		return file;
	}

	/**
	 * This method initializes export	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExport() {
		if (export == null) {
			export = new JMenuItem("Export...");
			export.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					//	primero reguntamos por la extensión
					String ext = (String)JOptionPane.showInputDialog(
						ImageEditorFrame.this,
	                    "Please select the image format:\n",
	                    "Image format",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    org.sgx.picturemakeup.persistence.PersistenceFactory.getInstance().getSupportedExtensions(),
	                    PersistenceFactory.getInstance().getDefaultExtension());
					
					JFileChooser fileDialog = new JFileChooser();
					File f = new File(img.getImgPath());
					String defaultName =f.getName()+"."+ext;					
					File selectedFile = new File(defaultName);
					fileDialog.setSelectedFile(selectedFile);
					fileDialog.setDialogTitle("Save Image as " + ext.toUpperCase() + " File");
					int option = fileDialog.showSaveDialog(ImageEditorFrame.this);  // Presents the "Save File" dialog to the user.
					if (option != JFileChooser.APPROVE_OPTION)
						return;  // user canceled
					selectedFile = fileDialog.getSelectedFile();  // The file the user has elected to save.
					if (selectedFile.exists()) {
						int response = JOptionPane.showConfirmDialog(ImageEditorFrame.this,
								"The file \"" + selectedFile.getName() + "\" already exists.\nDo you want to replace it?",
								"Replace file?",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (response == JOptionPane.NO_OPTION)
							return; // user does not want to replace existing file
						else {
							Saver saver = PersistenceFactory.getInstance().getSaverForExtension(ext);
							try {
								saver.save(img.getBufferredImage(), selectedFile.getAbsolutePath());
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(ImageEditorFrame.this,
									"An error occurs when trying to save the image: \n"+e1.getLocalizedMessage()+"\n",
									"Error",
									JOptionPane.ERROR_MESSAGE);								
							}						
						}
					}
//					try {
//						if ( ! ImageIO.write(image,imageFormat,selectedFile) )  // This actually writes the image to the file.
//							JOptionPane.showMessageDialog(mosaic,"Sorry, it looks like " + imageFormat + " files are not supported!");
//					}
//					catch (Exception e) {
//						JOptionPane.showMessageDialog(mosaic,"Sorry, an error occurred while trying to save the file:\n" + e.getMessage());
//					}
				}
			});
		}
		return export;
	}

	/**
	 * This method initializes importMenu	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getImportMenu() {
		if (importMenu == null) {
			importMenu = new JMenuItem("Import...");
			importMenu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fd = new JFileChooser(new File("directorio donde se quiere que aparezca por defecto"));
					fd.setFileFilter(new FileFilter() {
						public boolean accept(File f) {
							if (f.isDirectory())
								return false;
							String extension = FSUtils.getFileExtension(f.getAbsolutePath());
							String [] sup_ext = PersistenceFactory.SupportedExtensions;
							for(int i=0; i<sup_ext.length; i++)
								if(sup_ext[i].equals(extension))
									return true;
							return false;
						}
						public String getDescription() {
							return "Supported Images";
						}					
					});
					int x = fd.showOpenDialog(new JFrame());
					if(x == JFileChooser.APPROVE_OPTION){
						File f = fd.getSelectedFile();
						Loader loader  = PersistenceFactory.getInstance().getLoaderForExtension(FSUtils.getFileExtension(f.getAbsolutePath()));
						
						if(loader!=null) {
							try {
								BufferedImage buffi = loader.load(f.getAbsolutePath());
								img.setImage(buffi, f.getAbsolutePath());
							} catch(Exception e1) {
								JOptionPane.showMessageDialog(ImageEditorFrame.this,
										"An error occurs when trying to load the image: \n"+e1.getLocalizedMessage()+"\n",
										"Error",
										JOptionPane.ERROR_MESSAGE);		
							}
						}
						else {
							JOptionPane.showMessageDialog(ImageEditorFrame.this,
									"Can't find a Loader for the file "+f.getAbsolutePath(),
									"Error",
									JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
			});
		}
		return importMenu;
	}

	/**
	 * This method initializes exit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExit() {
		if (exit == null) {
			exit = new JMenuItem("Exit");
			this.dispose();
		}
		return exit;
	}

	/**
	 * This method initializes image	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getImage() {
		if (image == null) {
			image = new JMenu("Image");
			image.add(getScale());
			image.add(getRotate());
			image.add(getShear());
			image.add(getFlip());
		}
		return image;
	}

	/**
	 * This method initializes scale	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getScale() {
		if (scale == null) {
			scale = new JMenuItem("Scale");
			scale.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new ScaleTransformation(img.getBufferredImage());					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
					
				}
			});
		}
		return scale;
	}

	/**
	 * This method initializes rotate	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRotate() {
		if (rotate == null) {
			rotate = new JMenuItem("Rotate");
			rotate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new RotationTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
					
				}
			});
		}
		return rotate;
	}

	/**
	 * This method initializes shear	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getShear() {
		if (shear == null) {
			shear = new JMenuItem("Shear");
			shear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new ShearTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return shear;
	}

	/**
	 * This method initializes filters	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFilters() {
		if (filters == null) {
			filters = new JMenu("Filters");
			filters.add(getEdgeDetection());
			filters.add(getEmboss());
			filters.add(getSharp());
			filters.add(getHighPass());
			filters.add(getLog());
			filters.add(getBlur());
			filters.add(getNoise());
		}
		return filters;
	}

	/**
	 * This method initializes robertCross	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRobertCross() {
		if (robertCross == null) {
			robertCross = new JMenuItem("Robert Cross");
			robertCross.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new RobertCrossTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return robertCross;
	}

	/**
	 * This method initializes edgeDetection	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEdgeDetection() {
		if (edgeDetection == null) {
			edgeDetection = new JMenu("Edge detection");
			edgeDetection.add(getRobertCross());
			edgeDetection.add(getSobel());
		}
		return edgeDetection;
	}

	/**
	 * This method initializes jMenu4	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu("Selection");
		}
		return jMenu4;
	}

	/**
	 * This method initializes bright	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBright() {
		if (bright == null) {
			bright = new JMenuItem("Bright");
			bright.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					optionsPanel.add(new TransformationEditor(new BrightTransformation(), img ));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return bright;
		
	}

	/**
	 * This method initializes contrast	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getContrast() {
		if (contrast == null) {
			contrast = new JMenuItem("Contrast");
		}
		return contrast;
	}

	/**
	 * This method initializes color	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getColor() {
		if (color == null) {
			color = new JMenuItem("Color");
		}
		return color;
	}

	/**
	 * This method initializes flip	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFlip() {
		if (flip == null) {
			flip = new JMenuItem("Flip");
			flip.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {			
					optionsPanel.removeAll();
					ImageTransformation t = new FlipTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return flip;
	}

	/**
	 * This method initializes emboss	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getEmboss() {
		if (emboss == null) {
			emboss = new JMenuItem("Emboss");
			emboss.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new EmbossingTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return emboss;
	}

	private JMenuItem getFBlur() {
		if (FBlur == null) {
			FBlur = new JMenuItem("FBlur");
			FBlur.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new FTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return FBlur;
	}
	
	/**
	 * This method initializes NormalBlur	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNormalBlur() {
		if (NormalBlur == null) {
			NormalBlur = new JMenuItem("Blur");
			NormalBlur.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new BlurTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return NormalBlur;
	}

	/**
	 * This method initializes sharp	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSharp() {
		if (sharp == null) {
			sharp = new JMenuItem("Sharp");
			sharp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new SharpTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return sharp;
	}

	/**
	 * This method initializes sobel	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSobel() {
		if (sobel == null) {
			sobel = new JMenuItem("Sobel");
			sobel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new SobelTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return sobel;
	}

	/**
	 * This method initializes colors	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getColors() {
		if (colors == null) {
			colors = new JMenu("Colors");
			colors.add(getBright());
			colors.add(getContrast());
			colors.add(getColor());
			colors.add(getGrayScale());
			colors.add(getColorSwitching());
		}
		return colors;
	}

	/**
	 * This method initializes grayScale	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getGrayScale() {
		if (grayScale == null) {
			grayScale = new JMenuItem("Gray scale");
			grayScale.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new ToGrayTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return grayScale;
	}

	/**
	 * This method initializes colorSwitching	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getColorSwitching() {
		if (colorSwitching == null) {
			colorSwitching = new JMenuItem("Color switching");
		}
		return colorSwitching;
	}


	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			img = new ImageWidgetImpl(ImageWidgetImpl.defaultImgPath);
			jSplitPane.setLeftComponent(img);			
			jSplitPane.setRightComponent(getJPanel());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getOptionsPanel(), null);
			jPanel.add(getJPanel2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Apply");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					img.applyCurrentTransformation();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Original");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					img.restore();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes optionsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOptionsPanel() {
		if (optionsPanel == null) {
			optionsPanel = new JPanel();
			optionsPanel.setLayout(new BoxLayout(getOptionsPanel(), BoxLayout.X_AXIS));
		}
		return optionsPanel;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.X_AXIS));
			jPanel2.add(getJButton(), null);
			jPanel2.add(getJButton1(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jMenu6	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu6() {
		if (jMenu6 == null) {
			jMenu6 = new JMenu("Tools");
		}
		return jMenu6;
	}

	/**
	 * This method initializes help	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelp() {
		if (help == null) {
			help = new JMenu("Help");
			help.add(getManual());
			help.add(getLicense());
			help.add(getAbout());
			help.addSeparator();
		}
		return help;
	}

	/**
	 * This method initializes manual	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getManual() {
		if (manual == null) {
			manual = new JMenuItem("Manual");
		}
		return manual;
	}

	/**
	 * This method initializes about	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAbout() {
		if (about == null) {
			about = new JMenuItem("About");
			about.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String msg = "Picture Makeup - Java image editing facilities\nAUTHOR: Sebastián Gurin (cancerbero_sgx at sourceforge dot net)\n\n" +
							"This program is free software and cames without warranty. \nDo what you want with it. ";
					JOptionPane.showMessageDialog(ImageEditorFrame.this,
							msg,
							"Error",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			});
		}
		return about;
	}

	
	/**
	 * This method initializes highPass	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getHighPass() {
		if (highPass == null) {
			highPass = new JMenuItem("HighPass");
			highPass.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
					ImageTransformation t = new HighPassTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return highPass;
	}

	/**
	 * This method initializes log	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLog() {
		if (log == null) {
			log = new JMenuItem("Laplacian of Gaussian");
			log.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					optionsPanel.removeAll();
					ImageTransformation t = new LoGTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return log;
	}

	/**
	 * This method initializes importFromUrl	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getImportFromUrl() {
		if (importFromUrl == null) {
			importFromUrl = new JMenuItem("Import from URL...");
			importFromUrl.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String urlstr = (String)JOptionPane.showInputDialog(
						ImageEditorFrame.this,
				        "Please type the image's url:",
	                    "Import from URL",
	                    JOptionPane.QUESTION_MESSAGE);
					if(urlstr==null)
						return;
					URL url;
					try {
						url = new URL(urlstr);
						BufferedImage urlimg = ImageIO.read(url);
						img = new ImageWidgetImpl(urlimg);
					} catch (MalformedURLException e1) {
						JOptionPane.showMessageDialog(ImageEditorFrame.this,
								"The URL " + urlstr + " is malformed ("+e1.getLocalizedMessage()+")",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(ImageEditorFrame.this,
								"I couldn't get the image from "+urlstr+". Perhaps you are not connected ?",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return importFromUrl;
	}

	/**
	 * This method initializes license	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLicense() {
		if (license == null) {
			license = new JMenuItem();
		}
		return license;
	}

	/**
	 * This method initializes blur	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getBlur() {
		if (blur == null) {
			blur = new JMenu("Blur");
			blur.add(getNormalBlur());
			blur.add(getFBlur());
			blur.add(getGaussianSmooth());
		}
		return blur;
	}

	/**
	 * This method initializes gaussianSmooth	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getGaussianSmooth() {
		if (gaussianSmooth == null) {
			gaussianSmooth = new JMenuItem("Gaussian Smoothing");
			gaussianSmooth.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					optionsPanel.removeAll();
					ImageTransformation t = new GaussianSmoothTransformation();					
					optionsPanel.add(new TransformationEditor(t,img));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return gaussianSmooth;
	}

	/**
	 * This method initializes Noise	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getNoise() {
		if (Noise == null) {
			Noise = new JMenu();
			Noise.add(getDitherRandom());
			Noise.add(getJMenu1());
		}
		return Noise;
	}

	/**
	 * This method initializes ditherRandom	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenuItem getDitherRandom() {		
		if (ditherRandom == null) {
			ditherRandom = new JMenuItem("Random Dithering");
			ditherRandom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					optionsPanel.removeAll();
//					optionsPanel.add(new DitherRandomPannel(img, new DitherRandomTransformation()));
					optionsPanel.updateUI();
					jSplitPane.updateUI();
				}
			});
		}
		return ditherRandom;
	}

	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
		}
		return jMenu1;
	}

	public static void main( String[] argv ) {
             
        JFrame frame = new ImageEditorFrame();
        frame.addWindowListener( new WindowAdapter(){
            public void windowClosing( WindowEvent e ){
                System.exit( 0 );
            }
        });                     
        frame.pack();              
        frame.setSize( 550, 400 );
        frame.setVisible(true);
   }

}
