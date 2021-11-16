package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.NXModel;







public class View extends JFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NXModel model;
	SelectorView selectorPanel;
	EditorView editorPanel;
    public  View(NXModel m)
    {
        super();
        this.model=m;
        setTitle("Nemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //panneau principal(TOP)
        JPanel TOP=new JPanel(new GridLayout(2,1));
        setContentPane(TOP);
		
       selectorPanel=new SelectorView(model);
       selectorPanel.setPreferredSize(new Dimension(15,80));
       editorPanel=new EditorView(model);
       editorPanel.setSelector(selectorPanel);
            
       TOP.add(selectorPanel);//,BorderLayout.NORTH);
       TOP.add(editorPanel);//,BorderLayout.SOUTH);  
         
    }
    
    public void setVisible(boolean k)
		{
    		super.setVisible(k);
    	
    		setSize(900,500);
    		selectorPanel.app.upDateDataSource(selectorPanel.tableauGroup.getSelectedRows());
    		selectorPanel.upDateGroupMenu();
		}
	
    
}


