package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextFieldSlider extends JTextField
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int limit;
	public TextFieldSlider() 
    {
		
        if (limit>0)
        {
            addKeyListener(new KeyListener()
            {
                public void keyTyped(KeyEvent e) 
                {
                    String text = getText();
                    if (text.length()>=limit)
                        setText(text.substring(0, limit-1));
                }
                
                public void keyPressed(KeyEvent e) {}
                public void keyReleased(KeyEvent e)    {}            
            });
        }
    }

}
