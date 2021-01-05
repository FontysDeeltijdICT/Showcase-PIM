package com.mitchellton.pim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DatasheetJsonObject extends DatasheetDo {

    public DatasheetJsonObject(
            @JsonProperty("id") UUID id,
            @JsonProperty("partid") UUID partId,
            @JsonProperty("filename") String filename
    ) {
        super(id, partId, filename);
    }

    public DatasheetJsonObject(DatasheetDo datasheetDo) {
        super(datasheetDo);
    }

}
