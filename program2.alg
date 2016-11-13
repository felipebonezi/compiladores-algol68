BEGIN

    INT x := 11;
    INT y := 300, z := 2100;

    # Somatório das variáveis
    INT sum := x + (y + z);

    PROC cond = (INT a, INT b, INT c) VOID :
        BEGIN
            b := 30;

            IF a <= b THEN
                a := a + 1;
            ELSE
                a := a + b;
            FI;

            IF c < a THEN
                c := b * 2;
            FI;

            IF c != b THEN
                b := b * (c * 2);
            ELSE
                a := 0;
            FI;
        END

    PROC tak = (INT i, j, k) INT:
        BEGIN
            IF i <= j THEN
                RETURN j;
            ELSE
                RETURN tak (tak (i - 1, j, k), tak (j - 1, k, i), tak (k - 1, i, j));
            FI;
        END

    PROC taken = (INT i, j, k) INT:
        BEGIN
            IF i <= j THEN
                i := k * j;
                RETURN i;
            ELSE
                RETURN taken (taken (i - 1, j, k), taken (j - 1, k, i), taken (k - 1, i, j));
            FI;
        END

END