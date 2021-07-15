#include <iostream>
#include <winsock.h>
#include <process.h>
#include <string.h>
using namespace std;

#define MAXOFFER 5

struct Str
{
	SOCKET	clientsocket;
	char buffer[100];
	char reciver[100];
	bool closeConnection;
};

unsigned __stdcall ReceiveMsgClient(void* data)
{
	Str* S = (Str*)data;

	while (MAXOFFER != 6)
	{
		recv(S->clientsocket, S->reciver, sizeof(S->reciver), 0);
		printf(S->reciver);
	}
	_endthreadex(0);
	return 0;
}

int main(void)
{
	Str S;
	unsigned th_id_;
	SOCKADDR_IN addr;
	short port, count;

	WORD wVersionRequested = MAKEWORD(2.0, 2.0);
	WSADATA wsaData;
	int wsastartup = WSAStartup(wVersionRequested, &wsaData);
	if (wsastartup != NO_ERROR)
	{
		printf("Errore WSAStartup() : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	port = 4000;

	addr.sin_family = AF_INET;
	addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	addr.sin_port = htons(port);
	S.clientsocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	int connect_client = connect(S.clientsocket, (LPSOCKADDR)&addr, sizeof(addr));

	if (connect_client < 0)
	{
		printf("Errore nella connessione con il Server : %u\n", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	HANDLE receivetThread = (HANDLE)_beginthreadex(NULL, 0, &ReceiveMsgClient, &S, 0, &th_id_);

	count = 0;

	while (MAXOFFER+1 != count)
	{
		do
		{
			printf("Inserisci nome e importo! : ");
			cin.getline(S.buffer, 100);
			send(S.clientsocket, S.buffer, sizeof(S.buffer), 0);
			count++;
		} while (!S.closeConnection);
	}

	printf("Chiudo il client");
	SOCKET close(S.clientsocket);
	WSACleanup();
	CloseHandle(receivetThread);
	system("pause");
	return 0;
}