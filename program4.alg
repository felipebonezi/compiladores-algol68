BEGIN

    INT a := 2;
    INT b := 3;

    PROC funcao = (INT param1, INT param2, BOOL param3) INT :
        BEGIN
            WHILE param1 > param2
                DO
                    INT x := 1;
                    RETURN x * 20;
                OD;

            RETURN 1;
        END

    PROC chamarFuncao = (INT param1, INT param2, BOOL param3) BOOL :
        BEGIN
            IF a > b + 2 THEN
                RETURN FALSE;
            ELSE
                a := b * 2;
            FI;
        END

END