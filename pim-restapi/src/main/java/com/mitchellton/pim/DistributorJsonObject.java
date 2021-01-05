package com.mitchellton.pim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DistributorJsonObject extends DistributorDo {

    public DistributorJsonObject(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("url") String url,
            @JsonProperty("address") String address,
            @JsonProperty("phonenumber") String phonenumber,
            @JsonProperty("contactperson") String contactperson
    ) {
        super(id, name, url, address, phonenumber, contactperson);
    }

    public DistributorJsonObject(DistributorDo distributorDo) {
        super(distributorDo);
    }

}
