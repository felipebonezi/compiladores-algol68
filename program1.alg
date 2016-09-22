BEGIN

    INT a := 1;
    INT b, d, c := 2;

    # Somatório das variáveis #
    INT sum := a + (b + c);

    bool checker := true;

    BEGIN
        PROC test () VOID:
            b := 3;

            IF b = 3
                THEN checker = false
                FI;
    END

END