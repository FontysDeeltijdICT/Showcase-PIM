import com.mitchellton.pim.*;
import com.mitchellton.pim.service.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class PriceCheckServiceTest {

    private final FakeDistributorDataAccessService fakeDistributorDataAccessService;
    private final FakePriceDataAccessService fakePriceDataAccessService;
    private final FakeDatasheetDataAccessService fakeDatasheetDataAccessService;
    private final FakeBillOfMaterialsDataAccessService fakeBillOfMaterialsDataAccessService;
    private final FakeBillOfMaterialsItemDataAccessService fakeBillOfMaterialsItemDataAccessService;
    private final FakePartDataAccessService fakePartDataAccessService;

    private final DistributorService distributorService;
    private final PriceService priceService;
    private final DatasheetService datasheetService;
    private final BillOfMaterialsService billOfMaterialsService;
    private final PartService partService;
    private final PriceCheckService priceCheckService;

    public PriceCheckServiceTest() {
        fakeDistributorDataAccessService = new FakeDistributorDataAccessService();
        fakePriceDataAccessService = new FakePriceDataAccessService();
        fakeDatasheetDataAccessService = new FakeDatasheetDataAccessService();
        fakeBillOfMaterialsDataAccessService = new FakeBillOfMaterialsDataAccessService();
        fakeBillOfMaterialsItemDataAccessService = new FakeBillOfMaterialsItemDataAccessService();
        fakePartDataAccessService = new FakePartDataAccessService();

        distributorService = new DistributorService(fakeDistributorDataAccessService);
        priceService = new PriceService(fakePriceDataAccessService, distributorService);
        datasheetService = new DatasheetService(fakeDatasheetDataAccessService);

        billOfMaterialsService = new BillOfMaterialsService(
                fakeBillOfMaterialsDataAccessService,
                fakeBillOfMaterialsItemDataAccessService
        );

        partService = new PartService(
                fakePartDataAccessService,
                priceService,
                datasheetService
        );

        priceCheckService = new PriceCheckService(
                billOfMaterialsService,
                priceService,
                partService
        );
    }

    @Test
    public void contextLoads() throws Exception {
        assertFalse(distributorService == null);
        assertFalse(priceService == null);
        assertFalse(datasheetService == null);
        assertFalse(billOfMaterialsService == null);
        assertFalse(partService == null);
        assertFalse(priceCheckService == null);
    }

    @Test
    public void CheckIfPriceIsCorrect() throws Exception {
        // Create Part
        UUID partId = UUID.randomUUID();
        PartDo part = new PartDo(partId,"Price check 20", 10, 1, "Price check 20");
        fakePartDataAccessService.insert(partId, part);

        // Create Distributor
        UUID distributorId = UUID.randomUUID();
        DistributorDo distributor = new DistributorDo(distributorId, "Test Destributor", "www.url.com", "idk street", "06666", "not me");
        fakeDistributorDataAccessService.insert(distributorId, distributor);

        // Create Price (min order too high)
        UUID cheapTooManyId = UUID.randomUUID();
        PriceDo cheapTooManyPrice = new PriceDo(cheapTooManyId, partId, distributorId, 100, 0.80);
        fakePriceDataAccessService.insert(cheapTooManyId, cheapTooManyPrice);

        // Create Price (best option)
        UUID cheapId = UUID.randomUUID();
        PriceDo cheapPrice = new PriceDo(cheapId, partId, distributorId, 1, 0.90);
        fakePriceDataAccessService.insert(cheapId, cheapPrice);

        // Create Price (too expensive)
        UUID expensiveId = UUID.randomUUID();
        PriceDo expensivePrice = new PriceDo(expensiveId, partId, distributorId, 1, 1.20);
        fakePriceDataAccessService.insert(expensiveId, expensivePrice);

        UUID bomId = UUID.randomUUID();
        BillOfMaterialsDo billOfMaterials = new BillOfMaterialsDo(bomId, "test bom", "test project", "notes");
        fakeBillOfMaterialsDataAccessService.insert(bomId, billOfMaterials);

        UUID bomItemId = UUID.randomUUID();
        BillOfMaterialsItemDo billOfMaterialsItem = new BillOfMaterialsItemDo(bomItemId, bomId, partId, 10);
        fakeBillOfMaterialsItemDataAccessService.insert(bomItemId, billOfMaterialsItem);

        List<ShoppingListItem> result = priceCheckService.getShoppingList(bomId);

        assertTrue(result.size() > 0);
        assertTrue(result.get(0).getPrices().get(0).getPrice() == 0.9);
    }
}
