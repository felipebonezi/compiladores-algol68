BEGIN

    PROC funcaox4 = (INT param1, INT param2, BOOL param3) VOID :
        BEGIN
            INT result := funcaox3(param3, param1, param3);
            PRINT result;

            IF (5) > 7 = TRUE THEN
                RETURN TRUE;
            ELSE
                RETURN FALSE;
            FI;

            IF ((TRUE)) THEN
                RETURN FALSE;
            FI;
        END

END