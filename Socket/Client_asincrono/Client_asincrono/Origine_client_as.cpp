// @file Origine_client_as.cpp
// @version 1.3.5
// @author tarchi.giacomo

/**
* @mainpage
* Il programma 'Client_asincrono' tramite un Socket creato nel main, con la funzione 'WSAStartup' e 'socket',
* poi abbiamo assegnato un porta al canale della Socket tramite la funzione 'connect'.
* Da li' si avra' una connessione bidirezionale asincrona tra Server e Client, terminabile tramite una sequenza di escape.
* In caso di errore nella creazione o connesione del Socket il programma terminera' rilasciando un determinato codice d'errore.
* Inoltre nel progetto utlizzeremo una libreria non citata esplicitamente nel codice,
* ma individuabile nelle propieta'del progetto, la libreria "Ws2_32.lib".
*/

#include <winsock.h>
#include <process.h>
#include <string.h>
#include <iostream>

/**La struct Str contiene tutte le variabili utili all'utilizzo della cominicazione*/
#define MAX_BUFFER 100

/**La struct Str contiene tutte le variabili utili all'utilizzo della cominicazione*/
struct Str
{
	/**Il canale logico di comunicazione*/
	SOCKET Client_socket;
	/**La variabile dedicata alla ricezione del messaggio*/
	char msg_c_recv[MAX_BUFFER];
	/**La variabile dedicata all'invio del messaggio*/
	char msg_c_send[MAX_BUFFER];
};

unsigned __stdcall Recv(void* param);

unsigned __stdcall Send(void* param);

/**
* @fn int __cdecl main(void)
* @brief Inizializziamo il Socket e li diamo un MAKEWORD(2.0, 2.0) per indicare la versione di
* Windows che il Socket andra' ad utilizzare, tutt'ora la versione piu' recente e' la "2.2".
* Il nostro Socket di "connesione" 'clientsocket' e' un IPv4, con connessione sicura, e protocollo TCP.
* Il main dopo aver fatto partire i relativi Thread si blocca alla linea '97' tramite un 'WaitForMultipleObject'
* dove attende la fine o da parte del Server o del Client tramite una sequenza di escape uguale alla lettera 'q'.
* In caso di errore nella creazione o connesione del Socket il programma terminera' non prima di lasciare,
* in output, un codice relativo all'errore tramite la funzione 'WSAGetLastError()'.
*
* @code
*	...
*	TH[0] = (HANDLE)_beginthreadex(NULL, 0, &Recv, &S, 0, 0);
*	TH[1] = (HANDLE)_beginthreadex(NULL, 0, &Send, &S, 0, 0);
*
*	printf("Connessione con il Server...\n");
*	printf("Client\t \t \tServer\n");
*
*	WaitForMultipleObjects(2, TH, TRUE, INFINITE);
*	...
* @endcode
* @author tarchi.giacomo
*/
int __cdecl main(void)
{
	Str S;
	SOCKADDR_IN addr;
	HANDLE TH[2];

	WORD wVersionRequested = MAKEWORD(2, 2);
	WSADATA wsaData;
	int wsastartup = WSAStartup(wVersionRequested, &wsaData);
	if (wsastartup != NO_ERROR)
	{
		printf("Errore WSAStartup() : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	int port = 4000;

	S.Client_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	addr.sin_family = AF_INET;
	addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	addr.sin_port = htons(port);

	

	int connect_client = connect(S.Client_socket, (LPSOCKADDR)&addr, sizeof(addr));

	if (connect_client < 0)
	{
		printf("Errore nella connessione con il Server : %u\n", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	TH[0] = (HANDLE)_beginthreadex(NULL, 0, &Recv, &S, 0, 0);
	TH[1] = (HANDLE)_beginthreadex(NULL, 0, &Send, &S, 0, 0);

	printf("Connessione con il Server...\n");
	printf("Client\t \t \tServer\n");

	WaitForMultipleObjects(2, TH, TRUE, INFINITE);

	WSACleanup();
	CloseHandle(TH[0]);
	CloseHandle(TH[1]);
	system("pause");
	return 0;
}

/**
* @fn unsigned __stdcall Recv(void* param)
* @brief riceve il messaggio dal Server e lo mostra in output.
*
* @param void* data: Contiene la struttura Str
* @return unsigned: zero.
* @code
*	{
*		Str* S = (Str*)param;
*
*		while (1)
*		{
*			if (recv(S->Client_socket, S->msg_c_recv, sizeof(S->msg_c_recv), 0) > 0)
*			{
*				printf("\t \t \t%s\n", S->msg_c_recv);
*				if (strcmp(S->msg_c_recv, "q") == 0 || strcmp(S->msg_c_send, "q") == 0) break; //sequenza di escape
*			}
*		}
*		_endthreadex(0);
*		return 0;
*	}
* @endcode
* @author tarchi.giacomo
*/
unsigned __stdcall Recv(void * param)
{
	Str* S = (Str*)param;

	while (1)
	{
		if (recv(S->Client_socket, S->msg_c_recv, sizeof(S->msg_c_recv), 0) > 0)
		{
			printf("\t \t \t%s\n", S->msg_c_recv);
			if (strcmp(S->msg_c_recv, "q") == 0 || strcmp(S->msg_c_send, "q") == 0) break;
		}
	}
	_endthreadex(0);
	return 0;
}

/**
* @fn unsigned __stdcall Send(void * param)
* @brief Invia il messaggio al Server.
*
* @param void* data: Contiene la struttura Str
* @return unsigned: zero.
* @code
*	{
*		Str* S = (Str*)param;
*
*		while (1)
*		{
*			std::cin.getline(S->msg_c_send, MAX_BUFFER);
*			send(S->Client_socket, S->msg_c_send, sizeof(S->msg_c_send), 0);
*			if (strcmp(S->msg_c_recv, "q") == 0 || strcmp(S->msg_c_send, "q") == 0) break; //sequenza di escape
*		}
*		_endthreadex(0);
*		return 0;
*	}
* @endcode
* @author tarchi.giacomo
*/
unsigned __stdcall Send(void * param)
{
	Str* S = (Str*)param;

	while (1)
	{
		std::cin.getline(S->msg_c_send, MAX_BUFFER);
		send(S->Client_socket, S->msg_c_send, sizeof(S->msg_c_send), 0);
		if (strcmp(S->msg_c_recv, "q") == 0 || strcmp(S->msg_c_send, "q") == 0) break;
	}
	_endthreadex(0);
	return 0;
}
