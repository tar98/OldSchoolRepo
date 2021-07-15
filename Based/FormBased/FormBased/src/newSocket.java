import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class newSocket 
{
    private Socket S;
    private ObjectOutputStream OOS;
    private int port;

    public newSocket() 
    {
        try 
        {
            this.port = 65535;
            this.S = new Socket(InetAddress.getLoopbackAddress(), getPort());
            this.OOS = new ObjectOutputStream(getS().getOutputStream());
        }
        catch (IOException ex) {System.err.println("Error : " + ex.getMessage());}
    }
    
    public newSocket(String IP) 
    {
        try 
        {
            this.port = 65535;
            this.S = new Socket(IP, getPort());
            this.OOS = new ObjectOutputStream(getS().getOutputStream());
        } catch (IOException ex) {System.err.println("Error : " + ex.getMessage());}
    }
    
    public void close_conn() throws IOException
    {
        org.json.simple.JSONObject OBJ = new org.json.simple.JSONObject();
        OBJ.put("Status", "Close");
        getOOS().writeObject(OBJ);
        getOOS().close();
        getS().close();
    }
    
    public void reset_conn() throws IOException
    {
        org.json.simple.JSONObject OBJ = new org.json.simple.JSONObject();
        OBJ.put("Status", "Reset");
        getOOS().writeObject(OBJ);
        getOOS().close();
        getS().close();
    }

    public final Socket getS() {
        return S;
    }

    public ObjectOutputStream getOOS() {
        return OOS;
    }

    public final int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
