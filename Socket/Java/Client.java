/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author studente
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
        try (Socket S = new Socket("192.168.1.61", 1998) //connesione al server su porta 667
        ) {
            Scanner input = new Scanner(System.in); //input da tastiera
            String msg, recv;
            InputStreamReader ISR = new InputStreamReader(S.getInputStream()); //cio che ricevo 
            BufferedReader IN = new BufferedReader(ISR);
            OutputStreamWriter OSW = new OutputStreamWriter(S.getOutputStream()); //cio che invio
            BufferedWriter BW = new BufferedWriter(OSW);
            PrintWriter OUT = new PrintWriter(BW, true);
            System.out.println("Client Connesso!");
            do {
                System.out.print("Client : ");
                msg = input.nextLine();
                OUT.println(msg);
                if(msg.compareTo("q") == 0) break;
                recv = IN.readLine();
                Stampa(recv);
                if(recv.compareTo("q") == 0) break;
            } while (true);
            IN.close();
            OUT.close();
        } catch(Exception e) {System.out.println("Errore : " + e.getMessage());}//input da tastiera
        System.exit(0);
    }
    
    public static void Stampa(String msg)
    {
        System.out.print("Server : ");
        for(char c : msg.toCharArray())
        {
            if(c != 0 && c != '�')
                System.out.print(c);
        }
        System.out.println();
    }
}
