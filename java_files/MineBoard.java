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
import javax.swing.SwingUtilities;

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
	static int time = 0;
	static boolean gameEnd = false;
	static boolean victory = false;
	static JLabel bombsRemainLabel = new JLabel();
	
	
//main method is commented out when converted to an Applet
	public static void main(String[]args){
	//public void init(){
			

		//JFrame is commented out when converted to an Applet
			//instantiates all gui items
			JFrame frame = new JFrame();
			JButton newGameButton = new JButton("New Game");
			JButton revealButton = new JButton("Reveal Board");
			JPanel headingPanel = new JPanel();
			Container contentPane = new Container();
			MineScreen screen = new MineScreen();
			addMidGameMouseListeners(screen);
			
		//The following are commented out when converted to an Applet
			//sets defaults of JFrame
			frame.setTitle("USA Minesweeper");
			frame.setSize(width,height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			//sets defaults of container
			contentPane.setLayout(new BorderLayout());
			
			initialBombs = screen.countInitialBombs(screen.list);
			bombsRemainLabel.setText("Bombs Remaining: " + initialBombs);
			bombsRemaining = initialBombs;
			
			//adds the components to the heading panel
			headingPanel.add(newGameButton);
			headingPanel.add(bombsRemainLabel);
			headingPanel.add(revealButton);
			contentPane.add(headingPanel, BorderLayout.PAGE_START);
			contentPane.add(screen, BorderLayout.CENTER);
			frame.add(contentPane);
			contentPane.setVisible(true);
			frame.setVisible(true);
			
			revealButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e){
							screen.revealBoard(screen.list);
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
						bombsRemaining = initialBombs;
						bombsRemainLabel.setText("Bombs Remaining: " + initialBombs);
						addMidGameMouseListeners(screen);
						
					}
					
					//unused mouse methods
					public void mouseEntered(MouseEvent e){}
					public void mouseClicked(MouseEvent e){}
					public void mouseExited(MouseEvent e){}
					public void mouseReleased(MouseEvent e){}
				});
	}
	
	public static void addMidGameMouseListeners(MineScreen sc){

		for(int i=0; i<sc.list.size(); i++){
			 JButton placeButton = (JButton) sc.list.get(i);
			 placeButton.addMouseListener(new MouseListener()
					{
						public void mousePressed(MouseEvent e){
							
							if(SwingUtilities.isLeftMouseButton(e)){
								if(((ButtonInterface) placeButton).getID() == 2)
									endGameGUI(initialBombs);
							}
							

							if(SwingUtilities.isRightMouseButton(e)){
								if(((ButtonInterface) placeButton).getFlagged() == true){
									bombsRemaining--;
									bombsRemainLabel.setText("Bombs Remaining: " + bombsRemaining);
								}
								else{
									bombsRemaining++;
									bombsRemainLabel.setText("Bombs Remaining: " + bombsRemaining);
								}
								
								gameEnd = gameOver(initialBombs, sc);
								if(gameEnd == true){
									endGameGUI(initialBombs);
								}
						
							}
						}
						public void mouseClicked(MouseEvent e){}
						public void mouseEntered(MouseEvent e){}
						public void mouseExited(MouseEvent e){}
						public void mouseReleased(MouseEvent e){}
					});
		}
	}
	
	public static void endGameGUI(int initialB){
		
		JFrame endFrame = new JFrame();
		JPanel endPanel = new JPanel();
		JLabel endLabel = new JLabel();
	
		if(victory == true){
			endLabel.setText("Congratulations! You successfully found all " + initialB + " bombs!");
		}
		if(victory == false){
			endLabel.setText("You Lost.");
		}
		
		endPanel.add(endLabel);
		endFrame.add(endPanel);
		endFrame.setSize(500, 200);
		endFrame.setVisible(true);
	}
	
	public static boolean gameOver(int totalBombs, MineScreen sc){
		int totalFound = 0;
		boolean end = false;
		for(int i=0; i<sc.list.size(); i++){
			if(sc.list.get(i).getFound() == true){
				totalFound++;
			}
		}
		if(totalFound == totalBombs){
			end = true;
			victory = true;
		}
		
		return end;
	}

}