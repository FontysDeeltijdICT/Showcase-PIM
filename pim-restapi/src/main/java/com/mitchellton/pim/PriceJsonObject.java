package com.mitchellton.pim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PriceJsonObject extends PriceDo {

    public PriceJsonObject(
            @JsonProperty("id") UUID id,
            @JsonProperty("partId") UUID partId,
            @JsonProperty("distributorId") UUID distributorId,
            @JsonProperty("minOderAmount") int minOderAmount,
            @JsonProperty("price") double price
    ) {
        super(id, partId, distributorId, minOderAmount, price);
    }

    public PriceJsonObject(PriceDo priceDo) {
        super(priceDo);
    }

}
