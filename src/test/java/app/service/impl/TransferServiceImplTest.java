package app.service.impl;

import com.itau.br.app.model.Client;
import com.itau.br.app.model.Transfer;
import com.itau.br.app.service.TransferService;
import com.itau.br.app.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
public class TransferServiceImplTest {

    @InjectMocks
    protected ClientServiceImpl clientService;

    @Mock
    private TransferService transferService;

    @Mock
    private Client client;

    @Before
    public void setup() {
        clientService = Mockito.mock(ClientServiceImpl.class);
        transferService = Mockito.mock(TransferService.class);
        client = Mockito.mock(Client.class);
    }

    @Test
    public void registerCustomer() {

        Client cliente1 = new Client();
        cliente1.setName("Neymar Junior");
        cliente1.setAccount("000123890");
        cliente1.setBalance(1800000);

        client = clientService.registerCustomer(cliente1);

        cliente1 = new Client();
        cliente1.setName("Fátima Bernardes");
        cliente1.setAccount("000234111");
        cliente1.setBalance(51689);

        client = clientService.registerCustomer(cliente1);

        cliente1 = new Client();
        cliente1.setName("Fernando Henrique");
        cliente1.setAccount("000222356");
        cliente1.setBalance(115890);

        client = clientService.registerCustomer(cliente1);

        cliente1 = new Client();
        cliente1.setName("Maria Frazão");
        cliente1.setAccount("000234112");
        cliente1.setBalance(90345);

        client = clientService.registerCustomer(cliente1);

    }

    @Test
    public void listClientsTest() {
        List<Client> clients = clientService.getAllClients();

        for(var ls : clients) {
            Assertions.assertEquals(ls.getName(), "Neymar Junior");
            Assertions.assertEquals(ls.getAccount(), "000123890");
            Assertions.assertEquals(ls.getBalance(), 1800000);

            Assertions.assertEquals(ls.getName(), "Fátima Bernardese");
            Assertions.assertEquals(ls.getAccount(), "000234111");
            Assertions.assertEquals(ls.getBalance(), 51689);

            Assertions.assertEquals(ls.getName(), "Fernando Henrique");
            Assertions.assertEquals(ls.getAccount(), "000222356");
            Assertions.assertEquals(ls.getBalance(), 115890);

            Assertions.assertEquals(ls.getName(), "Maria Frazão");
            Assertions.assertEquals(ls.getAccount(), "000234112");
            Assertions.assertEquals(ls.getBalance(), 90345);
        }
    }


    @Test
    public void listTransfersTest() {

        List<Transfer> transfers = transferService.searchTransfers("000234123");
        Assertions.assertEquals(transfers.size(), 0);

        transfers = transferService.searchTransfers("000654954");
        Assertions.assertEquals(transfers.size(), 0);

        transfers = transferService.searchTransfers("000453989");
        Assertions.assertEquals(transfers.size(), 0);

    }
}