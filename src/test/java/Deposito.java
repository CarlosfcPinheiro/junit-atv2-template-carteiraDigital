import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;


class Deposito {

        @ParameterizedTest
        @ValueSource(doubles = {200, 500, 1, 2, 1.5})
        void deveDepositarValoresValidos(double amount) {
            DigitalWallet wallet = new DigitalWallet("Carlos", 200);

            double postDeposit = wallet.getBalance() + amount;
            wallet.deposit(amount);

            assertEquals(postDeposit, wallet.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles = {-200, 0, -2, -1.5})
        void deveLancarExcecaoParaDepositoInvalido(double amount) {
            DigitalWallet wallet = new DigitalWallet("Carlos", 200);

            assertThrows(IllegalArgumentException.class, () -> {
                wallet.deposit(amount);
            });
        }
    }