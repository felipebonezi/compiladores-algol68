BEGIN

    INT a := 1;
    INT b, c := 2;
    b := 3;

    # Somatório das variáveis #
    INT sum := a + (b + c);

    IF a <= b
    THEN a := a + 1
    ELSE a := a + b
    FI;

    IF c < a
    THEN c := b * 2
    FI;

    IF c != b
    THEN b := b * (c * 2)
    ELSE a := 0
    FI;

    BEGIN
        PROC tak = (INT i, j, k) INT:
            IF i <= j
            THEN j
            ELSE tak (tak (i - 1, j, k), tak (j - 1, k, i), tak (k - 1, i, j))
            FI;
    END

    BEGIN
        PROC taken = (INT i, j, k) VOID:
            IF i <= j
            THEN i := k * j
            ELSE taken (taken (i - 1, j, k), taken (j - 1, k, i), taken (k - 1, i, j))
            FI;
    END

END