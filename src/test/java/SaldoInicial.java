import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

import static org.junit.jupiter.api.Assertions.*;

class SaldoInicial {
        @Test
        void deveConfigurarSaldoInicialCorreto() {
            DigitalWallet wallet = new DigitalWallet("Carlos", 200);
            assertTrue(wallet.getBalance() > 0);
        }

        @Test
        void deveLancarExcecaoParaSaldoInicialNegativo() {
            assertThrows(IllegalArgumentException.class, () -> {
                new DigitalWallet("Carlos", -200);
            });
        }
    }