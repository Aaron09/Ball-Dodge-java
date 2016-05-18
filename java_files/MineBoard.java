package USAMinesweeper;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
//import javax.swing.JApplet;

public class MineBoard{

	/**
	 * 
	 */
	//private static final long serialVersionUID = -5598131024114721355L;
	
	
	static int bombsRemaining = 0;
	static int initialBombs = 0;
	static int width = 600;
	static int height = 600;
	
	
//main method is commented out when converted to an Applet
	public static void main(String[]args){
	//public void init(){
			

		//JFrame is commented out when converted to an Applet
			//instantiates all gui items
			JFrame frame = new JFrame();
			JButton newGameButton = new JButton("New Game");
			JButton updateRemainingBombsButton = new JButton("Update Bombs Remaining");
			JButton revealButton = new JButton("Reveal Board");
			JPanel headingPanel = new JPanel();
			Container contentPane = new Container();
			JLabel bombsRemainLabel = new JLabel();
			MineScreen screen = new MineScreen();
			
		//The following are commented out when converted to an Applet
			//sets defaults of JFrame
			frame.setTitle("USA Minesweeper");
			frame.setSize(width,height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);	
			
			
			//sets defaults of container
			contentPane.setLayout(new BorderLayout());
			contentPane.setVisible(true);
			
			initialBombs = screen.countInitialBombs(screen.list);
			bombsRemainLabel.setText("Bombs Remaining: " + initialBombs);
			
			//adds the components to the heading panel
			headingPanel.add(newGameButton);
			headingPanel.add(updateRemainingBombsButton);
			headingPanel.add(bombsRemainLabel);
			headingPanel.add(revealButton);
			contentPane.add(headingPanel, BorderLayout.PAGE_START);
			contentPane.add(screen, BorderLayout.CENTER);
			frame.add(contentPane);
			
			
			revealButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e){
							screen.revealBoard(screen.list);
						}
					});
			
			updateRemainingBombsButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e){
							bombsRemaining = screen.countRemainingBombs(screen.list, initialBombs);					
							bombsRemainLabel.setText("Bombs Remaining: " + bombsRemaining);
						}
					});
			
			//function that occurs when the newGameButton is pressed; creates a fresh board
			newGameButton.addMouseListener(new MouseListener()
				{
					public void mousePressed(MouseEvent e){
						
						screen.clearPanel(screen.list);
						screen.list = screen.resetList();
						screen.genBombs(screen.list, screen.diffWidth, screen.diffHeight, screen.bombs, screen.gen);
						screen.bombAmountSetter(screen.list, screen.diffWidth, screen.diffHeight, screen.testForMine, screen.bombs);
						screen.addButtons(screen.list, screen.diffWidth, screen.diffHeight);
						screen.setOriginals(screen.list, screen.diffWidth, screen.diffHeight, screen.testForMine);
						
						initialBombs = screen.countInitialBombs(screen.list);
						bombsRemainLabel.setText("Bombs Remaining: " + initialBombs);
							
					}
					
					//unused mouse methods
					public void mouseEntered(MouseEvent e){}
					public void mouseClicked(MouseEvent e){}
					public void mouseExited(MouseEvent e){}
					public void mouseReleased(MouseEvent e){}
				});
	}
}