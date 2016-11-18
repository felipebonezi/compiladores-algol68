==========================================
=====  		CÓDIGO FONTE EM C 		 =====
==========================================

PROVA 2016.1 [PROVA 2]

==========================================
=====  			ASSEMBLY			 =====
==========================================

SECTION .data
	a: dd (Inteiro) 4 (Quantidade de Bytes)


SECTION .text
	global _WinMain@16

_WinMain@16:
	push ebp 		()
	mov ebp, esp 	(Copia do segundo para o primeiro)
	sub esp, 8 		(Alocar memória para as variáveis b e c)
	push dword [a]		(Empilha o valor da variável a [por isso o colchetes])
	push dword [a]		(Empilha o valor da variável a [por isso o colchetes])
	pop ebx			(O primeiro jogo no registrador ebx)
	pop eax			(O segundo jogo no registrador eax)
	add eax, ebx
	push eax
	pop dword [ebp - 4]
	push dword [ebp - 4]
	push dword 5
	pop ebx
	pop eax
	sub eax, ebx
	push eax
	pop dword [ebp - 8]
	push dword [ebp - 4]
	push dword [ebp - 8]
	call _f
	add esp, 8
	push eax
	pop dword [a]
	mov esp, ebp
	pop ebp
	ret

_f:
	push ebp
	mov ebp, esp
	sub esp, 4
	push dword 0
	pop dword [ebp - 4]

	_while:
		push dword 1
		push dword 1
		pop ebx
		pop eax
		cmp eax, ebx
		jnl _fim_while
		push dword [ebp - 4]
		push dword 0
		pop ebx
		pop eax
		cmp eax, ebx
		jne _else
		push dword [ebp + 12]
		push dword [ebp + 8]
		pop ebx
		pop eax
		sub eax, ebx
		push eax
		pop dword [ebp + 12]
		push dword 1
		pop dword [ebp - 4]
		jmp _fim_else

	_else:
		push dword [ebp + 12]
		push dword 2
		pop ebx
		pop eax
		sub eax, ebx
		push eax
		pop dword [ebp + 12]
		push dword 0
		pop dword [ebp - 4]
	_fim_else:
		push dword [ebp + 12]
		push dword 5
		pop ebx
		pop eax
		cmp eax, ebx
		jge _fim_if
		jmp _fim_while
	_fim_if:
		jmp _while
	_fim_while:
		push dword [ebp + 12]
		pop eax
		mov esp, ebp
		pop ebp
		ret
