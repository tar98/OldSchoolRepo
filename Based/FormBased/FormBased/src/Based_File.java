import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.json.simple.*;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class Based_File 
{
    private long size;
    private int netStatus;
    private HashMap<Integer, String> ID_PACK;
    private File file;
    private newSocket newS;

    public Based_File() 
    {
        this.ID_PACK = new HashMap<>();
        this.size = 512;
        this.netStatus = 1;
    }
    
    public boolean send_inf_file() throws IOException, ClassNotFoundException
    {   
        try
        {
            JSONObject Obj = new JSONObject();
        
            setSize(getFile());
            
            String[] Path = getFile().getName().split("\\."); // trust me, it works.
            String Name = Path[0];
            String Type = Path[1];
            
            Obj.put("Name", Name);
            Obj.put("Type", Type);
            Obj.put("Size", getSize());
            Obj.put("Pack", getID_PACK().size());
            getNewS().getOOS().writeObject(Obj);
            StatusFail(getFile());
            return true;
        }
        catch (NullPointerException n_ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Nessun server in ascolto!\n Impossibile preparare il file.", "Errore", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void StatusFail(File file) throws IOException, ClassNotFoundException
    {
        File Log = new File("C:..\\FormBased\\File\\StatusFail" + getFile().getName());
        if(Log.exists())
        {
            ObjectInputStream OIS = new ObjectInputStream(getNewS().getS().getInputStream());
            JSONObject obj = new JSONObject();
            obj = (JSONObject) OIS.readObject();
            int net = (int) obj.get("netStatus"); // net sarà 1 nel caso il server decida di non recuperare il file 'perso' altrimenti sarà un numero specifico.
            setNetStatus(net);
            Log.delete();
        }
        else
            setNetStatus(1);
    }
    public void File_to_Based64()
    {
        String str_enc = null, str_bytes = null;
        long file_size = getFile().length();
        int static_size = (int) getSize();
        try
        {
            InputStream is = new FileInputStream(getFile());
            for (int i = 0, j = 1; i <= file_size; i += static_size, j++)
            {
                byte[] bytes = new byte[static_size];
                is.read(bytes, 0, static_size); 
                str_enc = Base64.getEncoder().encodeToString(bytes);
                getID_PACK().put(j, str_enc);
            }
        }
        catch(FileNotFoundException ex){System.err.println("Error : Il file non esiste!\n" + ex.getMessage());}
        catch(IOException ex){System.err.println("Error : impossibile leggere il file!\n" + ex.getMessage());}
    }
    
    public void send_json() throws IOException, InterruptedException
    {
        try 
        {
            String str = new String();
            JSONObject Obj;
            for (int i = getNetStatus(); i <= getID_PACK().size(); i++) 
            {
                Obj = new JSONObject();
                Obj.put("ID", i);
                str = getID_PACK().get(i);
                Obj.put("Pack", str);
                getNewS().getOOS().writeObject(Obj);
                System.out.println(Obj.toJSONString());
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (java.net.SocketException s_ex)
        {
            JOptionPane.showMessageDialog(null, "Il Server si è disconnesso! : " + s_ex.getMessage(), "Attenzione!", JOptionPane.WARNING_MESSAGE);
            File Log = new File("C:..\\FormBased\\File\\StatusFail" + getFile().getName());
            Log.createNewFile();
        }
        getNewS().close_conn();
    }
    
    public void setFile(File file) 
    {
        this.file = file;
    }

    public long getSize() {
        return size;
    }

    public HashMap<Integer, String> getID_PACK() {
        return ID_PACK;
    }

    public File getFile() {
        return file;
    }

    public newSocket getNewS() {
        return newS;
    }

    public void setNewS(newSocket newS) {
        this.newS = newS;
    }
    
    public void setSize(File file)
    {
        long a = file.length();
        if (a > Math.pow(2, 14)) //16384 bytes
        {
            this.size = 16384; // 16 kilobyte
        }
        else if (a > Math.pow(2, 13)) //8192 bytes
        {
            this.size = 8192; // 8 kilobyte
        }
        else if (a > Math.pow(2, 12)) //4096 bytes
        {
            this.size = 4096; // 4 kilobyte
        }
        else if (a > Math.pow(2, 11)) //2048 byte
        {
            this.size = 2048; // 2 kilobyte
        }
        else if(a > Math.pow(2, 10)) // 1024 byte (se e' maggiore di....)
        {
            this.size = 1024; // 1 kilobyte
        }
    }
    
    public int getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(int netStatus) {
        this.netStatus = netStatus;
    }
}
