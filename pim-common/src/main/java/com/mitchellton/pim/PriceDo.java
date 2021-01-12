package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class PriceDo implements Do {

    private final UUID id;

    private final UUID partId;

    private final UUID distributorId;
    private DistributorDo distributor; // Can be loaded in by the service.

    @NotBlank
    private final int minOderAmount;
    @NotBlank
    private final double price;

    public PriceDo(UUID id, UUID partId, UUID distributorId, int minOderAmount, double price) {
        this.id = id;
        this.partId = partId;
        this.distributorId = distributorId;
        this.minOderAmount = minOderAmount;
        this.price = price;
    }

    public PriceDo(PriceDo priceDo) {
        this(priceDo.id, priceDo.partId, priceDo.distributorId, priceDo.minOderAmount, priceDo.price);
        this.distributor = priceDo.distributor;
    }

    public UUID getId() { return id; }

    public UUID getPartId() {
        return partId;
    }

    public UUID getDistributorId() {
        return distributorId;
    }

    public int getMinOderAmount() {
        return minOderAmount;
    }

    public double getPrice() {
        return price;
    }

    public DistributorDo getDistributor() {
        return distributor;
    }

    public void setDistributor(DistributorDo distributor) {
        this.distributor = distributor;
    }
}
