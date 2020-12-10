package app;

import com.itau.br.app.TransferApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
public class TransferApplicationTest {

    @Test
    public void mainTest() {

        TransferApplication.main(new String[] {});
    }
}
