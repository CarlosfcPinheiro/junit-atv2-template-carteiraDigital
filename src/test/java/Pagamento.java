import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;


public class Pagamento {

    @ParameterizedTest
    @CsvSource({"200, 100, true", "100, 200, false"})
    void pagamentoComCarteiraVerificadaENaoBloqueada(double inicial, double valor, boolean esperado) {
        DigitalWallet wallet = new DigitalWallet("Carlos", inicial);
        wallet.verify();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());
        boolean payed = wallet.pay(valor);

        assertEquals(payed, esperado);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -10.50, -200})
    void deveLancarExcecaoParaPagamentoInvalido(double valor) {
        DigitalWallet wallet = new DigitalWallet("Carlos", 200);
        wallet.verify();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        assertThrows(IllegalArgumentException.class, () -> wallet.pay(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet wallet = new DigitalWallet("Carlos", 200);

        assertThrows(IllegalStateException.class, () -> wallet.pay(100));
    }
}
