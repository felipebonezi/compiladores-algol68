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
            RETURN 1;
        END

    PROC funcao6 = (INT param1) INT :
        BEGIN
            IF param1 > a THEN
                a := param1 * b;
            FI;

            RETURN 1;
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

            RETURN 1;
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

                    BOOL g := g > a;
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

            RETURN 1;
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

            RETURN TRUE;
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

            RETURN FALSE;
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
                RETURN FALSE;
            FI;

            RETURN TRUE;
        END

    PROC funcao12 = (INT param1, INT param2, INT param3) BOOL :
        BEGIN
            IF (h / 2) = 4 THEN
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
                            CONTINUE;
                        ELSE
                            PRINT FALSE;
                        FI;
                    OD;
                RETURN TRUE;
            FI;

            IF ((h + 2 * 1) / (a + b + c)) < 20 THEN
                WHILE param1 != param3
                    DO
                        IF q == 1 THEN
                            PRINT TRUE;
                        ELSE
                            PRINT FALSE;
                        FI;
                    OD;
                PRINT a + (b + c);
                BREAK;
            ELSE
                h := (b - (c * d)) / 2;
                PRINT (b - (c * d)) / 2;
            FI;

            RETURN FALSE;
        END


    PROC funcaox1 = (INT param1, param2, param3) INT :
        BEGIN
            IF ((h + 2 * 1) / (a + (b + c))) < 20 THEN
                RETURN funcao8(1, h, (1 + 1));
            ELSE
                h := (b - (c * d)) / 2;
                PRINT (b - (c * d)) / 2;
                RETURN 0;
            FI;
        END

    PROC funcaox2 = (BOOL param1, param2, param3) VOID :
        BEGIN
            INT result := funcao8(1, (a * h +(3 - 1)), 0);
            PRINT result;
        END

    PROC funcaox3 = (BOOL param1, INT param2, BOOL param3) VOID :
        BEGIN
            INT result := funcao8(param2, (a * h +(3 - 1)), param2);
            PRINT result;

            WHILE (5 > (7)) = FALSE
                DO
                    PRINT result;
                OD;
        END

    PROC funcaox4 = (INT param1, INT param2, BOOL param3) BOOL :
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