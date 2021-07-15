/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package based64;
import java.util.Base64;
import java.io.*;
import java.net.*;
import org.json.simple.*;
/**
 *
 * @author Jack
 */
public class Client_socket_file {
    private Socket S;
    private ObjectOutputStream OOS;
    private JSONObject JSO;
    private File file;
    private int ID;
    private static int port = 65535;
    
    /**
    *Creazione socket su localhost e senza file pattern.
    */
    public Client_socket_file()
    {
        try 
        {
            this.S = new Socket(InetAddress.getLocalHost(), getPort());
            this.OOS = new ObjectOutputStream(S.getOutputStream());
            this.JSO = new JSONObject();
            this.file = null;
            this.ID = 0;
        } catch (IOException ex) {System.err.println("Error : " + ex.getMessage());}
    }
    /**
    *Creazione socket su localhost con file pattern.
    * @param Path stringa da cui si preleva il file
    */
    public Client_socket_file(String Path)
    {
        try 
        {
            this.S = new Socket(InetAddress.getLocalHost(), getPort());
            this.OOS = new ObjectOutputStream(S.getOutputStream());
            this.JSO = new JSONObject();
            this.file = new File(Path);
            this.ID = 0;
        } catch (IOException ex) {System.err.println("Error : " + ex.getMessage());}
    }
    
    /**
    *Creazione socket su IP passato pre-selezionata con file pattern.
    * @param IP selezionato
    * @param Path stringa da cui si preleva il file
    */
    public Client_socket_file(String IP, String Path)
    {
        try 
        {
            this.S = new Socket(IP, getPort());
            this.OOS = new ObjectOutputStream(S.getOutputStream());
            this.JSO = new JSONObject();
            this.file = new File(Path);
            this.ID = 0;
        } catch (IOException ex) {System.err.println("Error : " + ex.getMessage());}
    }
    
    public void send_json() throws IOException
    {
        if (!(getJSO().isEmpty()))
            getJSO().clear();
        
        getJSO().put("ID", this.getID());
        getJSO().put("Packages", File_to_Based64(getFile()));
        getOOS().writeObject(getJSO());
        this.setID(this.getID() + 1);
    }

    public JSONObject getJSO() {
        return JSO;
    }
    
    private String File_to_Based64(File file)
    {
        String str_enc = null;
        int size;
        byte[] by_msq, base_str_enc;
        try
        {
            size = (int) file.length();
            by_msq = new byte[size];
            InputStream is = new FileInputStream(file);
            is.read(by_msq);

            String str_bytes = new String(by_msq);

            base_str_enc = Base64.getEncoder().encode(str_bytes.getBytes());
            str_enc = new String(base_str_enc);
        }
        catch(FileNotFoundException ex){System.err.println("Error : Il file non esiste!\n" + ex.getMessage());}
        catch(IOException ex){System.err.println("Error : impossibile leggere il file!\n" + ex.getMessage());}
        
        return str_enc;
    }
    
    public void close_conn() throws IOException
    {
        getOOS().writeObject(new JSONObject());
        getOOS().close();
        getS().close();
    }

    public Socket getS() {
        return S;
    }

    public ObjectOutputStream getOOS() {
        return OOS;
    }
    
    public File getFile() {
        return file;
    }

    public int getID() {
        return ID;
    }
    
    public static int getPort() {
        return port;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public static void setPort(int port) {
        Client_socket_file.port = port;
    }
}