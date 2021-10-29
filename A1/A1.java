/*
 *	============================================================================================
 *	A1.java : Extends JFrame and contains a panel where shapes move around on the screen.
 *	Also contains start and stop buttons that starts animation and stops animation respectively.
 *	Name: Xiaolin Li
 *	UPI: xli556
 *	============================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.io.*;
import java.util.ArrayList;


public class A1<MP3Player> extends JFrame {
	AnimationPanel panel;	// panel for bouncing area
	JButton startButton, stopButton, borderButton, fillButton, infoButton;	//buttons to start and stop the animation
	JTextField heightText, widthText, arcWidthText, arcHeightText;
	JComboBox<ImageIcon> shapesComboBox, pathComboBox;
	JTextArea log;
	/** main method for A1
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new A1();
			}
		});
	}

	/** constructor to initialise components
	 */
	public A1() {
		super("Bouncing Application");
		panel = new AnimationPanel();
		add(panel, BorderLayout.CENTER);
		add(setUpToolsPanel(), BorderLayout.NORTH);
		add(setUpBottomPanel(), BorderLayout.SOUTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					panel.resetMarginSize();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		setVisible(true);
	}

		/** Set up the tools panel
		* @return toolsPanel		the Panel
		 */
		public JPanel setUpToolsPanel() {
		startButton = new JButton("Start");
		startButton.setToolTipText("Start Animation");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				panel.start();	//start the animation
			}
		});
		//Set up the stop button
		stopButton = new JButton("Stop");
		stopButton.setToolTipText("Stop Animation");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				stopButton.setEnabled(false);
				startButton.setEnabled(true); //stop the animation
				panel.stop();
			 }
		});
		// Slider to adjust the speed of the animation
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		slider.setToolTipText("Adjust Speed");
		slider.addChangeListener(new ChangeListener() {
		 public void stateChanged(ChangeEvent e) {
			 JSlider source = (JSlider)e.getSource();
			 if (!source.getValueIsAdjusting()) {
				 int value = (int) (source.getValue());	// get the value from slider
				 TitledBorder tb = (TitledBorder) source.getBorder();
				 tb.setTitle("Anim delay = " + String.valueOf(value) + " ms"); //adjust the tilted border to indicate the speed of the animation
				 panel.adjustSpeed(value); //set the speed
				 source.repaint();
			 }
			}
		});
		TitledBorder title = BorderFactory.createTitledBorder("Anim delay");
		slider.setBorder(title);
		//Set up the shape combo box
		ImageIcon recangletButtonIcon = createImageIcon("rectangle.gif");
		ImageIcon squareButtonIcon = createImageIcon("square.gif");
		ImageIcon ellipseButtonIcon = createImageIcon("ellipse.gif");
		ImageIcon circleButtonIcon = createImageIcon("circle.gif");
		ImageIcon quadcirclesButtonIcon = createImageIcon("quadcircles.gif");
		ImageIcon pixelArtButtonIcon = createImageIcon("pixelArt.gif");
		ImageIcon bounsShapeButtonIcon = createImageIcon("bounsShape2.gif");
		shapesComboBox = new JComboBox<ImageIcon>(new ImageIcon[] {recangletButtonIcon,squareButtonIcon,ellipseButtonIcon,circleButtonIcon,quadcirclesButtonIcon,pixelArtButtonIcon,bounsShapeButtonIcon } );
		shapesComboBox.setToolTipText("Set shape");
		shapesComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set the Current shape type based on the selection: 0 for Rectangle, 1 for square and so on
				panel.setCurrentShapeType(shapesComboBox.getSelectedIndex());
			}
		});
		//Set up the path combo box
		ImageIcon fallButtonIcon = createImageIcon("fall.gif");
		ImageIcon bounceButtonIcon = createImageIcon("bounce.gif");
		ImageIcon testingButtonIcon = createImageIcon("testing.gif");
		pathComboBox = new JComboBox<ImageIcon>(new ImageIcon[] {fallButtonIcon, bounceButtonIcon, testingButtonIcon});
		pathComboBox.setToolTipText("Set Path");
		pathComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set the Current path type based on the selection from combo box: 0 for fall Path, 1 for bounce path and so on
				panel.setCurrentPathType(pathComboBox.getSelectedIndex());
			}
		});
		//Set up the height TextField
		heightText = new JTextField("50");
		heightText.setToolTipText("Set Height");
		heightText.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int newValue = Integer.parseInt(heightText.getText());
					if (newValue > 0) // if the value is valid, then change the current height
						panel.setCurrentHeight(newValue);
					else
						heightText.setText(panel.getCurrentHeight()+"");
				} catch (Exception ex) {
					heightText.setText(panel.getCurrentHeight()+""); //if the number entered is invalid, reset it
				}
			}
		});
		//Set up the width TextField
		widthText = new JTextField("100");
		widthText.setToolTipText("Set Width");
		widthText.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int newValue = Integer.parseInt(widthText.getText());
					if (newValue > 0) // if the value is valid, then change the current height
						panel.setCurrentWidth(newValue);
					else
						widthText.setText(panel.getCurrentWidth()+"");
				} catch (Exception ex) {
					widthText.setText(panel.getCurrentWidth()+""); //if the number entered is invalid, reset it
				}
			}
		});

			//Set up the arcWidth TextField
			arcWidthText = new JTextField("1");
			arcWidthText.setToolTipText("Set arcWidth for bounsShape");
			arcWidthText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int newValue = Integer.parseInt(arcWidthText.getText());
						if (newValue > 0) // if the value is valid, then change the current arcWidth
							panel.setCurrentArcWidth(newValue);
						else
							arcWidthText.setText(panel.getCurrentArcWidth()+"");
					} catch (Exception ex) {
						arcWidthText.setText(panel.getCurrentArcWidth()+""); //if the number entered is invalid, reset it
					}
				}
			});
			//Set up the arcHeight TextField
			arcHeightText = new JTextField("1");
			arcHeightText.setToolTipText("Set arcWidth for bounsShape");
			arcHeightText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int newValue = Integer.parseInt(arcHeightText.getText());
						if (newValue > 0) // if the value is valid, then change the current arcHeight
							panel.setCurrentArcHeight(newValue);
						else
							arcHeightText.setText(panel.getCurrentArcHeight()+"");
					} catch (Exception ex) {
						arcHeightText.setText(panel.getCurrentArcHeight()+""); //if the number entered is invalid, reset it
					}
				}
			});
		//Set up the fill colour button
		fillButton = new JButton("Fill");
		fillButton.setToolTipText("Set Fill Color");
		fillButton.setForeground(panel.getCurrentFillColor());
		fillButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				Color newColor = JColorChooser.showDialog(panel, "Fill Color", panel.getCurrentFillColor());
				if ( newColor != null) {
					fillButton.setForeground(newColor);
					panel.setCurrentFillColor(newColor);
				}
			}
		});
		//Set up the border colour button
		borderButton = new JButton("Border");
		borderButton.setToolTipText("Set Border Color");
		borderButton.setForeground(panel.getCurrentBorderColor());
		borderButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				Color newColor = JColorChooser.showDialog(panel, "Border Color", panel.getCurrentBorderColor());
				if ( newColor != null) {
					borderButton.setForeground(newColor);
					panel.setCurrentBorderColor(newColor);
				}
			}
		});
		//Set up the info button
		infoButton = new JButton("Info");
		infoButton.setToolTipText("Get Sorted Info");
		infoButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
					log.setText(panel.getSortedInfo());
			}
		});
		JPanel toolsPanel = new JPanel();
		toolsPanel.add(startButton);
		toolsPanel.add(stopButton);
		toolsPanel.add(slider);
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthText);
		toolsPanel.add(new JLabel(" Height: ", JLabel.RIGHT));
		toolsPanel.add(heightText);
		toolsPanel.add(new JLabel(" ArcWidth: ", JLabel.RIGHT));
		toolsPanel.add(arcWidthText);
		toolsPanel.add(new JLabel(" ArcHeight: ", JLabel.RIGHT));
		toolsPanel.add(arcHeightText);
		toolsPanel.add(borderButton);
		toolsPanel.add(fillButton);
		toolsPanel.add(infoButton);
		return toolsPanel;
	}

	/** Set up the bottom panel
		 * @return bottomPanel		the Panel
	 */
	public JPanel setUpBottomPanel() {
		JPanel bottomPanel= new JPanel(new BorderLayout());
		log = new JTextArea(5, 300);
		log.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(log);
		bottomPanel.add(scrollPane, BorderLayout.CENTER);
		return bottomPanel;
	}

	/** create the imageIcon
	 * @param	filename		the filename of the image
	 * @return ImageIcon		the imageIcon
	 */
	protected static ImageIcon createImageIcon(String filename) {
		java.net.URL imgURL = A1.class.getResource(filename);
		return new ImageIcon(imgURL);
	}
}

