
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package based64;

import java.io.IOException;

/**
 *
 * @author Jack
 */
public class Based64_json {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        String Path1 = "C:\\Users\\Jack\\Desktop\\Based.txt";
        String Path2 = "C:\\Users\\Jack\\Desktop\\Based.odt";
        Client_socket_file CSF = new Client_socket_file(Path1);
        CSF.send_json();
        CSF.close_conn();
        
        /*File F = new File(Path1);
        File F2 = new File(Path2);
        System.out.println(F.exists());
        FileReader FR = new FileReader(F);
        int size = (int) F.length(); //lunghezza del file;
        char[] in = new char[size];
        FR.read(in); //inserimento del file testuale in un array di char
        
        System.out.println(size);
        System.out.println(in);
        Based64_and_string(in);
        Based64_and_File(F2);*/
        
    }
    
    /*public static void Based64_and_string(char[] in)
    {
        String reading = new String(in);
        
        byte[] str = reading.getBytes();
        
        byte[] base_enc_str = Base64.getEncoder().encode(str);
        String str_enc = new String(base_enc_str);
        System.out.println(str_enc);
        
        byte[] base_Dec_str = Base64.getDecoder().decode(str_enc.getBytes());
        String str_dec = new String(base_Dec_str);
        System.out.println(str_dec);
    }
    
    public static void Based64_and_File(File file) throws FileNotFoundException, IOException
    {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        InputStream is = new FileInputStream(file);
        is.read(bytes);
        
        String str_bytes = new String(bytes);
        
        byte[] base_str_enc = Base64.getEncoder().encode(str_bytes.getBytes());
        String str_enc = new String(base_str_enc);
        System.err.println(str_enc);
        
        byte[] base_str_dec = Base64.getDecoder().decode(str_enc.getBytes());
        String str_dec = new String(base_str_dec);
        System.out.println(str_dec);
    }
    */
}
