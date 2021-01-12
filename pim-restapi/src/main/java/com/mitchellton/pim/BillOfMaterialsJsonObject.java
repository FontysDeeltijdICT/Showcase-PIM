package com.mitchellton.pim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class BillOfMaterialsJsonObject extends BillOfMaterialsDo {

    public BillOfMaterialsJsonObject(
            @JsonProperty("id") UUID id,
            @JsonProperty("bomName") String bomName,
            @JsonProperty("projectName") String projectName,
            @JsonProperty("notes") String notes
    ) {
        super(id, bomName, projectName, notes);
    }

    public BillOfMaterialsJsonObject(BillOfMaterialsDo billOfMaterialsDo) {
        super(billOfMaterialsDo);
    }

}
