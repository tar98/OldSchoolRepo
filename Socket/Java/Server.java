package server;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author studente
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        ServerSocket SS = new ServerSocket(1998,50,InetAddress.getByName("192.168.1.20")); //connesione TCP su porta 667
        Socket client_rec = SS.accept(); // Socket in ascolto
        
        Scanner input = new Scanner(System.in); //input da tastiera
        String msg, recv;
        
        InputStreamReader ISR = new InputStreamReader(client_rec.getInputStream()); //cio che ricevo
        BufferedReader IN = new BufferedReader(ISR);
        OutputStreamWriter OSW = new OutputStreamWriter(client_rec.getOutputStream()); //cio che invio
        BufferedWriter BW = new BufferedWriter(OSW);
        PrintWriter OUT = new PrintWriter(BW, true);
        
        System.out.println("Server Connesso!");
        
        do 
        {
            recv = IN.readLine();
            Stampa(recv);
            if(recv.charAt(0) == 'q') break;
            System.out.print("Server : ");
            msg = input.nextLine();
            OUT.println(msg);
            if(msg.compareTo("q") == 0) break;
        } while (true);
        
        IN.close();
        OUT.close();
        SS.close();
        System.exit(0);
    }
    
    public static void Stampa(String msg)
    {
        System.out.print("Client : ");
        for(char c : msg.toCharArray())
        {
            if(c != 0 && c != '�')
                System.out.print(c);
        }
        System.out.println();
    }
}
