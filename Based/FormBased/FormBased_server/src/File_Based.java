import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.swing.JOptionPane;
import org.json.simple.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.parser.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class File_Based 
{
    private JSONObject JSO;
    private HashMap<Integer, String> ID_PACK;
    private File F = null;
    private newSocket nS;
    private long size;
    
    public File_Based() 
    {
    }
    
    public void recive_json() throws IOException
    {
        String MSG = null;
        FileOutputStream FSO = null; 
        String json = ",\n";
        File file = null;
        int id;
        file = new File("C:..\\FormBased_server\\File\\ " + getF().getName() + ".json");
        FSO = new FileOutputStream(file);
        if (file.length() == 0) 
        {
            json = "{\n " + '"' + "Array" + '"' + ":[\n";
            FSO.write(json.getBytes());
            json = ",\n";
        }
        try
        { 
            do
            {       
                setJSO((JSONObject) getnS().getOIS().readObject()); // in attesa dell' oggetto JSON
                FSO.write(getJSO().toJSONString().getBytes());
                FSO.write(json.getBytes());
                id = (int) JSO.get("ID");
                MSG = (String) JSO.get("Pack");
                getID_PACK().put(id, MSG);
            }while(true);
        }
        catch(ClassNotFoundException cnf_ex){System.err.println("Error : Classe non convertibile!\n" + cnf_ex.getMessage());}
        catch(IOException io_ex) {System.err.println("Error : Lettura non eseguibile!\n" + io_ex.getMessage());}
        catch(NullPointerException np_ex)
        {
            json = "]\n}";
            FSO.write(json.getBytes());
            FSO.close();
            String Status = (String) getJSO().get("Status");
            if(Status.compareTo("Reset") == 0)
                try 
                {
                    setnS(new newSocket(getnS().resetConn()));
                    file.delete();
                } catch (IOException ex) {}   
            else if(Status.compareTo("Close") == 0)    
                try 
                {
                    getnS().endConn();
                    file.delete();
                }
                catch (IOException ex) {}
            else
                System.err.println("Error: l'oggetto è vuoto o non comprensibile! : " + np_ex.getMessage());
        }
    }
    
    public boolean init_file()
    {
        try 
        {
            setJSO((JSONObject) getnS().getOIS().readObject());
            setSize((long) getJSO().get("Size"));
            setID_PACK((int) getJSO().get("Pack"));
            
            String Name = (String) getJSO().get("Name");          
            String Type = (String) getJSO().get("Type");
            String Path = "C:..\\FormBased_server\\File\\" + Name + "." + Type;
            setF(new File(Path));
            if(!getF().exists())
            {
                getF().createNewFile();
                JOptionPane.showMessageDialog(null, "Il File è stato preparato!", "Avviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                int n = JOptionPane.showConfirmDialog(null, "Il File Esiste gia'!, è da recuperare?", "Attenzione", javax.swing.JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) 
                {
                    rebuildConn(rebuild_file());
                    return true;
                }
                else if (n == JOptionPane.NO_OPTION)
                {
                    rebuildConn(1);
                    getF().delete();
                    getnS().endConn();
                    JOptionPane.showMessageDialog(null, "File eliminato.\n Riavviare la Connesione", "Avviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        catch(ClassNotFoundException cnf_ex){System.err.println("Error : Classe non convertibile!\n" + cnf_ex.getMessage());}
        catch(IOException io_ex) {System.err.println("Error : Lettura non eseguibile!\n" + io_ex.getMessage());}
        
        return true;
    }
    
    private int Json_to_Hash(File file)
    {
        Map<Integer, Map<String, String>> Jsonmaps = null;
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(file));    
            JSONArray jsonArr = (JSONArray) jsonObj.get("Array");
            Jsonmaps = new HashMap<>();
            int i = 0;
            for(Object obj : jsonArr)
            {
               Map<String, String> map = new HashMap<>();
               JSONObject Json = (JSONObject) obj;
               map = mapper.readValue(obj.toString(), new TypeReference<Map<String, String>>(){} );
               Jsonmaps.put(i++, map);
            }
        } 
        catch (IOException e) {}
        catch (org.json.simple.parser.ParseException p_ex){}
        
        System.out.println(Jsonmaps);
        System.out.println(Jsonmaps.values());
        String Pack = Jsonmaps.values().toString();
        String[] Split = Pack.split("\\,");
        System.out.println(Split.toString());
        setHash(Split);
        System.out.println(getID_PACK());
        return Jsonmaps.size();
    }
    
    private void setHash(String[] Split)
    {
        String save;
        String[] Pack;
        for (int j = 1, i = 1; i < Split.length; i+=2 , j++) 
        {
             if (Split.length-1 == i) 
             {
                 save = Split[i].replaceAll("}]", "");
                 Pack = save.split("Pack=");
                 
             }
             else
             {
                 save = Split[i].replaceAll("}", "");
                 Pack = save.split("Pack=");
             }
             getID_PACK().put(j, Pack[1]);
        }
    }
    
    
    private int rebuild_file() throws FileNotFoundException, IOException
    {
        int index;
        String json = "]\n}";
        File Native = new File("C:..\\FormBased_server\\File\\ " + getF().getName() + ".json");
        File Modify = new File("C:..\\FormBased_server\\File\\state.json");
        java.nio.file.Files.copy(Native.toPath(), Modify.toPath(), StandardCopyOption.REPLACE_EXISTING);
        PrintWriter PW = new PrintWriter(new BufferedWriter(new FileWriter(Modify, true))); // to append
        PW.println(json);
        PW.close(); // json completo
        index = Json_to_Hash(Modify);
        System.out.println(Modify.delete());
        return ++index;
    }
    
    
    private void rebuildConn(int index) throws IOException
    {
        ObjectOutputStream OOS = new ObjectOutputStream(getnS().getSock().getOutputStream());
        JSONObject Obj = new JSONObject();
        Obj.put("netStatus", index);
        OOS.writeObject(Obj);
    }
    
    
    public void writeFile()
    {
        try 
        {
            byte[] data = build_file().getBytes();
            BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(getF()));
            BOS.write(data);
            BOS.flush();
            BOS.close();
            JOptionPane.showMessageDialog(null, "File Correttamente Scritto!\n Controllare il percorso FormBased_server->File ", "Avviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {JOptionPane.showMessageDialog(null, "Errore Nella scrittura!\n" + e.getMessage(), "Errore", javax.swing.JOptionPane.ERROR_MESSAGE);}
    }
    
    private String build_file()
    {
        System.out.println(getID_PACK());
        String File_MSG = new String();
        for (int i = 1; i <= getID_PACK().size(); i++) 
        {
            File_MSG += Based64_to_File(getID_PACK().get(i));
        }
        return File_MSG;
    }
    
    private String Based64_to_File(String msg)
    {
        String str_dec = new String(Base64.getDecoder().decode(msg.getBytes()), StandardCharsets.ISO_8859_1);
        return str_dec;
    }

    public HashMap<Integer, String> getID_PACK() {
        return ID_PACK;
    }

    public JSONObject getJSO() {
        return JSO;
    }

    public void setJSO(JSONObject JSO) {
        this.JSO = JSO;
    }

    public File getF() {
        return F;
    }

    public void setF(File F) {
        this.F = F;
    }

    public newSocket getnS() {
        return nS;
    }

    public void setnS(newSocket nS) {
        this.nS = nS;
    }
    
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setID_PACK(int size) {
        this.ID_PACK = new HashMap<>(size);
    }
}
