/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_json_based64;

/**
 *
 * @author Jack
 */
public class Server_json_based64 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Server_sock_file SSF = new Server_sock_file();
        SSF.recive_json();
    }
}