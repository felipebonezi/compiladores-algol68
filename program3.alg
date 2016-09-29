BEGIN

    # Declarando variáveis inteiras
    INT a;
    INT b := 1;

    # Declarando variáveis por expressões de soma;
    INT c := 1 - 2;
    INT d := c + b;
    INT e := c - 1 + d - a + b;

    # Declarando variáveis por expressões de multiplicação;
    INT f := 1 * 2;
    INT g := f / g;
    INT h := h / 1 * d / f * b;

    # Declarando variáveis por expressões de soma e multiplicação;
    INT w := h / 1 + d / f * b;
    INT y := (h / 1) + (d / (f * b));

    # Declarando variáveis booleanas
    BOOL i;
    BOOL j := true;
    BOOL k := false;

    # Declarando variáveis booleanas por expressões relacionais;
    BOOL l := g > a;
    BOOL m := e >= b;
    BOOL n := e < g;
    BOOL o := g <= h;
    BOOL p := a != h;
    BOOL q := a = b;

    PROC funcao1 = () VOID :
        BEGIN
            INT a;
            INT b := 1;

            INT c := 1 + 2;
            INT d := c - b;
            INT e := c + 1 - d + a - b;
        END

    PROC funcao2 = (INT param1) VOID :
        BEGIN
            INT f := 1 * 2;
            INT g := f / g;
            INT h := h * 1 / d * f / b;
        END

    PROC funcao3 = (INT param1, INT param2) VOID :
        BEGIN
            BOOL j := true;
            BOOL k := false;

            BOOL l := g > a;
            BOOL m := e >= b;
            BOOL n := e < g;
            BOOL o := g <= h;
            BOOL p := a != h;
            BOOL q := a = b;
        END

    PROC funcao4 = (INT param1, INT param2, INT param3) VOID :
        BEGIN
            IF param1 != param2 THEN
                PRINT param1;
            ELSE
                PRINT param2;
            FI;
        END

    PROC funcao5 = () INT :
        BEGIN
            PRINT a;
        END

    PROC funcao6 = (INT param1) INT :
        BEGIN
            IF param1 > a THEN
                a := param1 * b;
            FI;
        END

    PROC funcao7 = (INT param1, INT param2) INT :
        BEGIN
            IF param1 < param2 THEN
                PRINT param1;
            ELSE
                IF param1 > param2 THEN
                    PRINT param2;
                ELSE
                    PRINT 1;
                FI;
            FI;
        END

    PROC funcao8 = (INT param1, INT param2, INT param3) INT :
        BEGIN
            WHILE param1 != param3
                DO
                    INT a;
                    INT b := 1;

                    INT c := 1 + 2;
                    INT d := c - b;
                    INT e := c + 1 - d + a - b;

                    BOOL j := true;
                    BOOL k := false;

                    BOOL l := g > a;
                    BOOL m := e >= b;
                    BOOL n := e < g;
                    BOOL o := g <= h;
                    BOOL p := a != h;
                    BOOL q := a = b;

                    IF q == TRUE THEN
                        PRINT q;
                    ELSE
                        PRINT FALSE;
                    FI;
                OD;
        END

    PROC funcao9 = () BOOL :
        BEGIN
            WHILE TRUE
                DO
                    WHILE a <= h
                        DO
                            a := a + 1;
                            h := h - 1;

                            IF a < 0 THEN
                                a := 0;
                            ELSE
                                IF h >= 100 THEN
                                    h := 100;
                                FI;
                            FI;
                        OD;
                OD;
        END

    PROC funcao10 = (INT param1) BOOL :
        BEGIN
            WHILE TRUE
                DO

                    IF (h / 2) = 4 THEN
                        RETURN TRUE;
                    FI;

                    IF ((h + 2 * 1) / (a + b + c)) < 20 THEN
                        BREAK;
                    ELSE
                        CONTINUE;
                    FI;
                OD;
        END

    PROC funcao11 = (INT param1, INT param2) BOOL :
        BEGIN
            IF (h / 2) = 4 THEN
                h := 0;
                PRINT h;
                RETURN TRUE;
            FI;

            IF ((h + 2 * 1) / (a + b + c)) < 20 THEN
                h := a + (b + c);
                PRINT a + (b + c);
                BREAK;
            ELSE
                h := (b - (c * d)) / 2;
                PRINT (b - (c * d)) / 2;
                CONTINUE;
            FI;
        END

    PROC funcao12 = (INT param1, INT param2, INT param3) BOOL :
        BEGIN
        END


    PROC funcaox1 = (INT param1, param2, param3) VOID :
        BEGIN
        END

    PROC funcaox2 = (BOOL param1, param2, param3) VOID :
        BEGIN
        END

    PROC funcaox3 = (BOOL param1, INT param2, BOOL param3) VOID :
        BEGIN
        END

    PROC funcaox4 = (INT param1, INT param2, BOOL param3) VOID :
        BEGIN
        END

END