BEGIN

    PROC funcao = (INT param1, INT param2, BOOL param3) VOID :
        BEGIN
        END

    PROC chamarFuncao = (INT param1, INT param2, BOOL param3) BOOL :
        BEGIN
            BOOL x := funcao();
        END

END