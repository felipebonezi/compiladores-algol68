BEGIN

    PROC funcao = (INT param1, INT param2, BOOL param3) INT :
        BEGIN
            RETURN 1 + 2;
        END

    PROC chamarFuncao = (INT param1, INT param2, BOOL param3) BOOL :
        BEGIN
            BOOL x := funcao(1, 2, 3) >= 1;
        END

END