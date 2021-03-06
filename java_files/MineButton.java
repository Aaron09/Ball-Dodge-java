package USAMinesweeper;


import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



public class MineButton extends JButton implements MouseListener, ButtonInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4540881930846519304L;
	
	ImageIcon bombIcon, flag;
	int clickCount = 1;
	boolean flagged = false;
	boolean found = false;

	
	public MineButton(){
		bombIcon = new ImageIcon(getClass().getResource("/resources/mineSweepBomb.png"));
		flag = new ImageIcon(getClass().getResource("/resources/flag.jpg"));
		setVisible(true);
		addMouseListener(this);
		
		
	}
	public boolean getFound(){
		return found;
	}
	public boolean getFlagged(){
		return flagged;
	}
	
	//ID number corresponding to a mineButton
	public int getID(){
		return 2;
	}

	//necessary for the interface; this method is unused otherwise
	public void iconSetter(){
		setIcon(bombIcon);
	}
	
	//determines the course of action when a mouse button is pressed
	public void mousePressed(MouseEvent e){
		if(SwingUtilities.isLeftMouseButton(e)){
				iconSetter();
		}
		if(SwingUtilities.isRightMouseButton(e)){
			if(clickCount == 1){
				setIcon(flag);
				clickCount++;
				flagged = true;
				found = true;
			} else if(clickCount == 2){
				setIcon(null);
				clickCount--;
				flagged = false;
				found = false;
			}
		}
	}
	

	
	//unused mouse methods
	public void mouseEntered(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}


}
