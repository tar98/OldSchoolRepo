/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_json_based64;
import java.io.*;
import java.net.*;
import org.json.simple.*;
import java.util.Base64;
/**
 *
 * @author Jack
 */
public class Server_sock_file {
    private ServerSocket Server;
    private Socket Sock;
    private ObjectInputStream OIS;
    private JSONObject JSO;
    private static int port = 65535;
    private File F = null;

    public Server_sock_file() 
    {
        try 
        {
            this.Server = new ServerSocket(getPort(), 50, InetAddress.getLocalHost());
            this.Sock = this.Server.accept();
            this.OIS = new ObjectInputStream(this.Sock.getInputStream());
            this.JSO = new JSONObject();
            System.out.println("Connessione con il Client!");
        } catch (IOException e) {System.err.println("Error: Connessione fallita!\n" + e.getMessage());}   
    }
    
    public Server_sock_file(byte[] IP) 
    {
        try 
        {
            this.Server = new ServerSocket(getPort(), 50, InetAddress.getByAddress(IP));
            this.Sock = this.Server.accept();
            this.OIS = new ObjectInputStream(this.Sock.getInputStream());
            this.JSO = new JSONObject();
            System.out.println("Connessione con il Client!");
        } catch (IOException e) {System.err.println("Error: Connessione fallita!\n" + e.getMessage());}
    }
    
    public void recive_json()
    {
        String MSG = null;
        try
        {
            do
            {
                if(getJSO().isEmpty())
                    getJSO().clear();
                
                setJSO((JSONObject) getOIS().readObject()); // in attesa dell' oggetto JSON
                MSG = getJSO().get("Packages").toString(); //TO FIX trovare un modo per salvare gli id
                MSG = Based64_to_File(MSG);
                System.out.println(MSG);
            }while(isClose(MSG)); // Controlla se è un'oggetto 'vuoto'
            endConn();
        }
        catch(ClassNotFoundException cnf_ex){System.err.println("Error : Classe non convertibile!\n" + cnf_ex.getMessage());}
        catch(IOException io_ex){System.err.println("Error : Lettura non eseguibile!\n" + io_ex.getMessage());}
    }
    
    private boolean isClose(String Path)
    {
        boolean b = (Path.compareTo("{}") == 0);
        return b;
    }
    
    private String Based64_to_File(String msg)
    {
        byte[] base_Dec_str = Base64.getDecoder().decode(msg.getBytes());
        String str_dec = new String(base_Dec_str);
        return str_dec;
    }
    
    private void endConn() throws IOException
    {
        getOIS().close();
        getSock().close();
        getServer().close();
    }
    
    public ServerSocket getServer() {
        return Server;
    }

    public Socket getSock() {
        return Sock;
    }
    public JSONObject getJSO() {
        return JSO;
    }

    public ObjectInputStream getOIS() {
        return OIS;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Server_sock_file.port = port;
    }
    
    public void setJSO(JSONObject JSO) {
        this.JSO = JSO;
    }
}
