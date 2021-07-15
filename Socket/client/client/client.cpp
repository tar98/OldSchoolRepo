#include <winsock.h>
#include <string.h>
#include <iostream>
using namespace std;
/**La struct Str contiene tutte le variabili utili all'utilizzo della cominicazione*/
#define MAX_BUFFER 100

/**La struct Str contiene tutte le variabili utili all'utilizzo della cominicazione*/
struct Str
{
	/**Il canale logico di comunicazione*/
	SOCKET Client_socket;
	/**La variabile dedicata alla ricezione del messaggio*/
	string msg_c_recv;
	/**La variabile dedicata all'invio del messaggio*/
	string msg_c_send;
};


int __cdecl main(void)
{
	Str S;
	SOCKADDR_IN addr;

	WORD wVersionRequested = MAKEWORD(2, 2);
	WSADATA wsaData;
	int wsastartup = WSAStartup(wVersionRequested, &wsaData);
	if (wsastartup != NO_ERROR)
	{
		printf("Errore WSAStartup() : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	u_short port = 1998;

	addr.sin_family = AF_INET;
	addr.sin_addr.s_addr = inet_addr("192.168.1.97");
	addr.sin_port = htons(port);

	S.Client_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	int connect_client = connect(S.Client_socket, (LPSOCKADDR)&addr, sizeof(addr));

	if (connect_client < 0)
	{
		printf("Errore nella connessione con il Server : %u\n", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	printf("Connessione con il Server...\n");
	printf("Client\t \t \tServer\n");

	int r;
	char buffer[MAX_BUFFER];

	while(1)
	{
		std::cin.getline(buffer, MAX_BUFFER);
		S.msg_c_send = string(buffer, strlen(buffer));
		send(S.Client_socket, S.msg_c_send.c_str(), S.msg_c_send.size(), 0);
		if (strcmp(S.msg_c_send.c_str(), "q\r\n") == 0) break;
		r = recv(S.Client_socket, buffer, sizeof(buffer), 0);
		printf("\t \t \t");
		S.msg_c_recv = string(buffer, r); //stampa pulita
		printf("%s\n", S.msg_c_recv.c_str());
		if (strcmp(S.msg_c_recv.c_str(), "q") == 0) break;
	}

	WSACleanup();
	system("pause");
	return 0;
}
