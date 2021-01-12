import com.mitchellton.pim.*;
import com.mitchellton.pim.service.DatasheetService;
import com.mitchellton.pim.service.DistributorService;
import com.mitchellton.pim.service.PartService;
import com.mitchellton.pim.service.PriceService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class PartServiceTest {

    private PartService partService;

    public PartServiceTest() {
        DistributorService distributorService = new DistributorService(new FakeDistributorDataAccessService());
        PriceService priceService = new PriceService(new FakePriceDataAccessService(), distributorService);
        DatasheetService datasheetService = new DatasheetService(new FakeDatasheetDataAccessService());
        partService = new PartService(
                new FakePartDataAccessService(),
                priceService,
                datasheetService
        );
    }

    @Test
    public void contextLoads() throws Exception {
        assertFalse(partService == null);
    }

    @Test
    public void insertAddsItem() throws Exception {
        UUID id = UUID.randomUUID();
        int precount = partService.getAll().size();
        partService.add(new PartDo(id, "test", 10, 1, "test comp"));
        int postcount = partService.getAll().size();
        assertTrue(postcount > precount);
    }

    @Test
    public void canReadBackSameItem() throws Exception {
        PartDo item = new PartDo(UUID.randomUUID(),"test 99", 10, 1, "test comp 99");
        partService.add(item);
        for(PartDo partDo : partService.getAll()) {
            if(partDo.getName().equals("test 99")) {
                assertEquals(item.getName(), partDo.getName());
                assertEquals(item.getDescription(), partDo.getDescription());
                assertEquals(item.getValue(), partDo.getValue());
                assertEquals(item.getUnit(), partDo.getUnit());
                return;
            }
        }
        assertTrue(false);
    }
}
