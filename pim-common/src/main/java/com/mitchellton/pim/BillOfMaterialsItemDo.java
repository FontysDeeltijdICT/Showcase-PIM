package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class BillOfMaterialsItemDo implements Do {

    private final UUID id;

    @NotBlank
    private final UUID bomId;
    @NotBlank
    private final UUID partId;

    private PartDo part; // Can be loaded in by the service.

    private final int amount;

    public BillOfMaterialsItemDo(UUID id, UUID bomId, UUID partId, int amount) {
        this.id = id;
        this.bomId = bomId;
        this.partId = partId;
        this.amount = amount;
    }

    public BillOfMaterialsItemDo(BillOfMaterialsItemDo priceDo) {
        this(priceDo.id, priceDo.bomId, priceDo.partId, priceDo.amount);
    }

    public UUID getId() { return id; }

    public UUID getBomId() {
        return bomId;
    }

    public UUID getPartId() {
        return partId;
    }

    public int getAmount() {
        return amount;
    }

    public PartDo getPart() {
        return part;
    }

    public void setPart(PartDo part) {
        this.part = part;
    }

}
