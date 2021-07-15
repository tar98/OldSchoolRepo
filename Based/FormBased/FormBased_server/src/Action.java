
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class Action {
    javax.swing.JTextField IpConn;
    javax.swing.JButton butCon;
    javax.swing.JCheckBox defConn;
    File_Based FB = new File_Based();

    public Action(JTextField IpConn, JButton butCon, JCheckBox defConn) {
        this.IpConn = IpConn;
        this.butCon = butCon;
        this.defConn = defConn;
        InitMethod();
    }
    
    
    class ChooseConn implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent ce) 
        {
            boolean sel = defConn.isSelected();
            if(sel)
            {
                IpConn.setEnabled(false);
                IpConn.setText("127.0.0.1");
            }
            else
            {
                IpConn.setEnabled(true);
            }
        }
        
    }
    
    class IpChange implements CaretListener
    {

        @Override
        public void caretUpdate(CaretEvent ce) 
        {
            if (IpConn.isEnabled())
            {
                if(IpConn.getText().isEmpty())
                {
                    butCon.setEnabled(false);
                }
                else
                {
                    butCon.setEnabled(true);
                }
            }
        }
    }
    
    class StartConn implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            boolean sel = defConn.isSelected();
            if(sel)
            {
                FB.setnS(new newSocket());
            }
            else
            {
                FB.setnS(new newSocket(IpConn.getText()));
            }
            JOptionPane.showMessageDialog(null, "Connessione al Client!", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            sel = FB.init_file();
            
            if(sel) 
            {
                try 
                {
                    FB.recive_json();
                } catch (IOException ex) 
                {
                }
                FB.writeFile();
            }
        }
    }
    
    private void InitMethod()
    {
        this.defConn.addChangeListener(new ChooseConn());
        this.IpConn.addCaretListener(new IpChange());
        this.butCon.addActionListener(new StartConn()); 
    }
}
