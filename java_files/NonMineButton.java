package USAMinesweeper;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



public class NonMineButton extends JButton implements MouseListener, ButtonInterface{

		/**
	 * 
	 */
//	private static final long serialVersionUID = 5483424835111220983L;
	
		ImageIcon flag, numZero, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight;
		int numBombs = 0;
		int clickCount = 1;
		boolean flagged = false;
		
		public NonMineButton(int bombTotal){
			
			numBombs = bombTotal;
			numZero = new ImageIcon(getClass().getResource("/resources/numZero.jpg"));
			numOne = new ImageIcon(getClass().getResource("/resources/mineSweepOne.png"));
			numTwo = new ImageIcon(getClass().getResource("/resources/mineSweepTwo.png"));
			numThree = new ImageIcon(getClass().getResource("/resources/mineSweepThree.png"));
			numFour = new ImageIcon(getClass().getResource("/resources/mineSweepFour.png"));
			numFive = new ImageIcon(getClass().getResource("/resources/mineSweepFive.png"));
			numSix = new ImageIcon(getClass().getResource("/resources/mineSweepSix.png"));
			numSeven = new ImageIcon(getClass().getResource("/resources/mineSweepSeven.png"));
			numEight = new ImageIcon(getClass().getResource("/resources/mineSweepEight.png"));
			flag = new ImageIcon(getClass().getResource("/resources/flag.jpg"));
			setVisible(true);
			this.addMouseListener(this);
		}
		public boolean getFlagged(){
			return flagged;
		}
		//ID number associated with the nonMineButtons
		public int getID(){
			return 1;
		}
		//determines the course of action when one of the mouse buttons is pressed
		public void mousePressed(MouseEvent e){
			if(SwingUtilities.isLeftMouseButton(e)){
				iconSetter();
			}
			if(SwingUtilities.isRightMouseButton(e)){
				if(clickCount == 1){
					setIcon(flag);
					clickCount++;
					flagged = true;
				} else if (clickCount == 2){
					setIcon(null);
					clickCount--;
					flagged = false;
				}
			}
		}
		
		//sets the number icon corresponding to how many bombs the tile is touching
		public void iconSetter(){
			if(numBombs == 0){
				setIcon(numZero);
			}
			if(numBombs == 1){
				setIcon(numOne);
			}
			if(numBombs == 2){
				setIcon(numTwo);
			}
			if(numBombs == 3){
				setIcon(numThree);
			}
			if(numBombs == 4){
				setIcon(numFour);
			}
			if(numBombs == 5){
				setIcon(numFive);
			}
			if(numBombs == 6){
				setIcon(numSix);
			}
			if(numBombs == 7){
				setIcon(numSeven);
			}
			if(numBombs == 8){
				setIcon(numEight);
			}
		}
				
		//unused mouse methods
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
}

