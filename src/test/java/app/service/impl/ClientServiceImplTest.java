package app.service.impl;

import com.itau.br.app.model.Client;
import com.itau.br.app.repository.ClientRepository;
import com.itau.br.app.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @InjectMocks
    protected ClientServiceImpl clienteService;

    @Mock
    protected ClientRepository clientRepository;

    protected static final String targetAccount = "Conta destino";
    protected static final String sourceAccount = "Conta origem";
    protected static final String account2 = "000234123";
    protected static final String account1 = "000342341";

    @Before
    public void setup() {
        clienteService = Mockito.mock(ClientServiceImpl.class);
        clientRepository = Mockito.mock(ClientRepository.class);
    }

    @Test
    public void listCustomer() {
        configFindMock(Optional.of(getClient(1, sourceAccount, account1)), account1);

        Client client = clienteService.getClientByAccountNumber(String.valueOf(account1));
        assertEquals(1, client.getId());
        assertEquals(sourceAccount, client.getName());
        assertEquals(account1, client.getAccount());

        configFindMock(Optional.of(getClient(2, targetAccount, account2)), account2);
        client = clienteService.getClientByAccountNumber(account2);
        assertEquals(2, client.getId());
        assertEquals(targetAccount, client.getName());
        assertEquals(account2, client.getAccount());
    }

    protected void configFindMock(Optional<Client> client, String account) {

        BDDMockito.when(clientRepository.findCustomerByAccount(BDDMockito.eq(account))).thenReturn(client);
    }

    protected Client getClient(int id, String nome, String account) {
        Client c = new Client();
        c.setId(id);
        c.setBalance(800);
        c.setAccount(account);
        c.setName(nome);
        return c;
    }
}
