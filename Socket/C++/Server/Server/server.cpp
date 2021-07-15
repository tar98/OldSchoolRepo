#include <winsock.h>
#include <string.h>
#include <iostream>
using namespace std;

/**Definisce il Massimo dei caratteri che puo' ricevere e inviare il Server*/
#define MAX_BUFFER 50

/**La struct Str contiene tutte le variabili utili all'utilizzo della cominicazione*/
struct Str
{
	/**Il canale logico di comunicazione*/
	SOCKET Server_Socket;
	/**La variabile dedicata alla ricezione del messaggio*/
	string msg_recv;
	/**La variabile dedicata all'invio del messaggio*/
	string msg_send;
};


int __cdecl main(void)
{
	int sin_size, ls_result;
	Str S;
	SOCKET listenSocket;
	SOCKADDR_IN Server_addr, Client_addr;

	WORD wVersionRequested = MAKEWORD(2.0, 2.0);
	WSADATA wsaData;
	int wsastartup = WSAStartup(wVersionRequested, &wsaData);
	if (wsastartup != NO_ERROR)
	{
		printf("Errore WSAStartup() : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	listenSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (listenSocket < 0)
	{
		printf("Server: errore nella creazione della socket. : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	else printf("La Listening Socket e' partita\n");

	short port = 1998;

	Server_addr.sin_family = AF_INET;
	Server_addr.sin_addr.s_addr = inet_addr("192.168.1.97");
	Server_addr.sin_port = htons(port);

	if (bind(listenSocket, (LPSOCKADDR)&Server_addr, sizeof(struct sockaddr)) < 0)
	{
		printf("Server: errore durante la bind : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	ls_result = listen(listenSocket, SOMAXCONN);
	if (ls_result < 0)
	{
		printf("Server: errore durante la listen : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	else printf("La Socket e' in Ascolto\n");

	sin_size = sizeof(struct sockaddr_in);
	S.Server_Socket = accept(listenSocket, (struct sockaddr *)&Client_addr, &sin_size);
	printf("Accettata Connessione con Client: %s\n", inet_ntoa(Client_addr.sin_addr));

	printf("Server\t \t \tClient\n");

	int r;
	char buffer[MAX_BUFFER];
	while(1)
	{
		r = recv(S.Server_Socket, buffer, sizeof(buffer), 0);
		printf("\t \t \t");
		S.msg_recv = string(buffer, r); //stampa pulita
		printf("%s\n", S.msg_recv.c_str());
		if (strcmp(S.msg_recv.c_str(), "q") == 0) break; //sequenza di escape
		std::cin.getline(buffer, MAX_BUFFER);
		strcat_s(buffer, "\r\n");
		S.msg_send = string(buffer, strlen(buffer));
		send(S.Server_Socket, S.msg_send.c_str(), S.msg_send.size(), 0);
		if (strcmp(S.msg_send.c_str() , "q\r\n") == 0) break;
	}

	WSACleanup();
	system("pause");
	return EXIT_SUCCESS;
}


