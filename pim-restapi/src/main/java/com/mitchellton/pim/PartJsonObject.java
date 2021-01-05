package com.mitchellton.pim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PartJsonObject extends PartDo {

    public PartJsonObject(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("value") int value,
            @JsonProperty("unit") int unit,
            @JsonProperty("description") String description
    ) {
        super(id, name, value, unit, description);
    }

    public PartJsonObject(PartDo partDo) {
        super(partDo);
    }

}
