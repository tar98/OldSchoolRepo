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
    private ServerSocket Server;
    private Socket Sock;
    private ObjectInputStream OIS;
    private int port = 65535;
    
    
    public newSocket() 
    {
        try
        {
            this.Server = new ServerSocket(getPort(), 50, InetAddress.getLoopbackAddress());
            this.Sock = this.Server.accept();
            this.OIS = new ObjectInputStream(this.Sock.getInputStream());
        }catch(IOException io_ex){}
    }
    
    public newSocket(String IP) 
    {
        try
        {
            this.Server = new ServerSocket(getPort(), 50, InetAddress.getByAddress(IP.getBytes()));
            this.Sock = this.Server.accept();
            this.OIS = new ObjectInputStream(this.Sock.getInputStream());
        }catch(IOException io_ex){}
    }
    
    public void endConn() throws IOException
    {
        getOIS().close();
        getSock().close();
        getServer().close();
    }
    
    public String resetConn() throws IOException
    {
        String IP = getServer().getLocalSocketAddress().toString();
        getOIS().close();
        getSock().close();
        getServer().close();
        return IP;
    }
    
    public ServerSocket getServer() {
        return Server;
    }

    public void setServer(ServerSocket Server) {
        this.Server = Server;
    }

    public Socket getSock() {
        return Sock;
    }

    public void setSock(Socket Sock) {
        this.Sock = Sock;
    }

    public ObjectInputStream getOIS() {
        return OIS;
    }

    public void setOIS(ObjectInputStream OIS) {
        this.OIS = OIS;
    }

    public final int getPort() {
        return port;
    }

    public  void setPort(int port) {
        this.port = port;
    }
}
