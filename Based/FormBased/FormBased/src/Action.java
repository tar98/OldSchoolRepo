/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
/**
 *
 * @author Jack
 */
public class Action 
{
    javax.swing.JTextField IpConn,  Text_File;
    javax.swing.JButton butCon, butSend,  ButtonFile, butReset;
    javax.swing.JCheckBox defConn;
    // End of variables declaration         
    Based_File BF;

    public Action(JButton ButtonFile, JTextField IpConn, JTextField Text_File, JButton butCon, JButton butReset, JButton butSend, JCheckBox defConn) {
        this.ButtonFile = ButtonFile;
        this.IpConn = IpConn;
        this.Text_File = Text_File;
        this.butCon = butCon;
        this.butReset = butReset;
        this.butSend = butSend;
        this.defConn = defConn;
        this.BF = new Based_File();
        Init_Method();
    }


    
    class PickDrop_File implements ActionListener
    {
        JFileChooser JFC = new JFileChooser("C:\\Users\\Jack\\Desktop\\Based");
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (BF.getFile() == null) 
            {
                if (this.JFC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    BF.setFile(this.JFC.getSelectedFile());
                    Text_File.setText(BF.getFile().getPath());
                    ButtonFile.setText("Drop File");
                    butCon.setEnabled(true);
                    BF.File_to_Based64();
                }
            }
            else
            {
                BF.setFile(null);
                ButtonFile.setText("Select File");
                Text_File.setText("None");
                butCon.setEnabled(false);
            }
        }
                
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
                BF.setNewS(new newSocket());
            }
            else
            {
                BF.setNewS(new newSocket(IpConn.getText()));
            }
            
            try 
            {
                boolean bool = BF.send_inf_file();
                if(bool)
                {
                    butReset.setEnabled(true);
                    butSend.setEnabled(true);
                    ButtonFile.setEnabled(false);
                }
            } 
            catch (IOException ex) {} 
            catch (ClassNotFoundException ex) {}
        }
    }
    
    class Reset implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            try 
            {
                BF.getNewS().close_conn();
                ButtonFile.setEnabled(true);
                BF.setFile(null);
                ButtonFile.setText("Select File");
                Text_File.setText("None");
                butCon.setEnabled(false);
            } catch (IOException ex) {}
        }
    }
    
    class Send implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            try 
            {
                BF.send_json();
            } 
            catch (IOException ex) {}
            catch (InterruptedException ex) {}
            
            ButtonFile.setEnabled(true);
            BF.setFile(null);
            ButtonFile.setText("Select File");
            Text_File.setText("None");
            butCon.setEnabled(false);
            butReset.setEnabled(false);
            butSend.setEnabled(false);
        }
    }
    
    private void Init_Method()
    {
        this.ButtonFile.addActionListener(new PickDrop_File());
        this.defConn.addChangeListener(new ChooseConn());
        this.IpConn.addCaretListener(new IpChange());
        this.butCon.addActionListener(new StartConn());
        this.butReset.addActionListener(new Reset());
        this.butSend.addActionListener(new Send());
    }
    
}
