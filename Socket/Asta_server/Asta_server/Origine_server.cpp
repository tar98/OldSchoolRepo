#include <winsock.h>
#include <process.h>
#include <string.h>
#include <iostream>
using namespace std;

#define MAXOFF 5 // MAX Offerte
#define FILE_NAME "asta.txt" 

struct Str
{
	SOCKET Server_Socket;
	char Nome_V[50], data[100], recver[100];
	int imp_v;
	int Num_OFF = 0;
};

unsigned __stdcall Invio_MSG(void* param);

unsigned __stdcall Apertura_Asta(void* param);

unsigned __stdcall Ricezione_MSG(void* param);

void NewSocket(SOCKET &listenSocket, SOCKADDR_IN &Server_addr);

int main(int argc, char *argv[])
{
	unsigned idth;
	Str S;
	SOCKET listenSocket;
	SOCKADDR_IN Server_addr, Client_addr;
	HANDLE TH;

	WORD wVersionRequested = MAKEWORD(2, 2);
	WSADATA wsaData;
	int wsastartup = WSAStartup(wVersionRequested, &wsaData);
	if (wsastartup != NO_ERROR)
	{
		printf("Errore WSAStartup() : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	NewSocket(listenSocket, Server_addr);

	int sin_size, ls_result;

	ls_result = listen(listenSocket, SOMAXCONN);
	if (ls_result < 0)
	{
		printf("Server: errore durante la listen : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	else printf("La Socket è in Ascolto\n");

	sin_size = sizeof(struct sockaddr_in);
	S.Server_Socket = accept(listenSocket, (struct sockaddr *)&Client_addr, &sin_size);
	printf("Accettata Connessione con Client: %s\n", inet_ntoa(Client_addr.sin_addr));

	TH = (HANDLE)_beginthreadex(NULL, 0, &Apertura_Asta, &S, 0, &idth);

	DWORD Await = WaitForSingleObject(TH ,INFINITE);

	CloseHandle(TH);
		
	TH = (HANDLE)_beginthreadex(NULL, 0, &Ricezione_MSG, &S, 0, &idth);
	WaitForSingleObject(TH, INFINITE);

	return EXIT_SUCCESS;
}


void NewSocket(SOCKET &listenSocket, SOCKADDR_IN &Server_addr)
{
	listenSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (listenSocket < 0)
	{
		printf("Server: errore nella creazione della socket. : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	else printf("La Listening Socket è partita\n");

	int port = 4000;

	Server_addr.sin_family = AF_INET;
	Server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	Server_addr.sin_port = htons(port);

	if (bind(listenSocket, (LPSOCKADDR)&Server_addr, sizeof(struct sockaddr)) < 0)
	{
		printf("Server: errore durante la bind : %u \n ", WSAGetLastError());
		exit(EXIT_FAILURE);
	}
}

unsigned __stdcall Ricezione_MSG(void* param)
{
	Str* S = (Str*)param;
	char new_off[50];
	int new_imp;
	HANDLE TH;
	while (1)
	{
		if (recv(S->Server_Socket, S->recver, sizeof(S->recver), 0) > 0)
		{
			sscanf_s(S->recver, "%s %d", new_off, 50, &new_imp);
			if (new_imp <= 0)
			{
				strcpy_s(S->data, "Offerta non valida!\n");
				printf(S->data);
			}
			else
			{
				S->Num_OFF++;
				printf("Nuova Offerta # %d di : %d\n", S->Num_OFF, new_imp);
				if (S->imp_v < new_imp)
				{
					S->imp_v = new_imp;
					strcpy_s(S->Nome_V, new_off);
					strcpy_s(S->data, "Offerta conclusa!\n Il miglior offerente e' : ");
					sprintf_s(S->data, "%s %s %d\n", S->data, S->Nome_V, S->imp_v);
					printf(S->data);
					TH = (HANDLE)_beginthreadex(NULL, 0, &Invio_MSG, &S, 0, 0);
				}
			}
			if (S->Num_OFF > MAXOFF)
			{
				strcpy_s(S->data, "Asta conclusa!\n Il miglior offerente e' : ");
				sprintf_s(S->data, "%s %s %d\n", S->data, S->Nome_V, S->imp_v);
				printf(S->data);
				TH = (HANDLE)_beginthreadex(NULL, 0, &Invio_MSG, &S, 0, 0);
				Sleep(2000);
				_endthreadex(0);
				return 0;
			}
			CloseHandle(TH);
		}
		Sleep(1000);
	}
}

unsigned __stdcall Apertura_Asta(void* param)
{
	Str* S = (Str*)param;
	S->Nome_V[0] = '\0';
	S->imp_v = 0;
	strcpy_s(S->data, "L'asta e' aperta!\n");
	printf(S->data);
	HANDLE TH = (HANDLE)_beginthreadex(NULL, 0, &Invio_MSG, &S, 0, 0);
	Sleep(2000);
	_endthreadex(0);
	return 0;
}

unsigned __stdcall Invio_MSG(void* param)
{
	Str* S = (Str*)param;
	send(S->Server_Socket, S->data, sizeof(S->data), 0);
	_endthreadex(0);
	return 0;
}