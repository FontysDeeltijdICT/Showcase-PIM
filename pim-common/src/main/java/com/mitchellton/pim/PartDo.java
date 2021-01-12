package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public class PartDo implements Do {
    private final UUID id;

    @NotBlank
    private final String name;
    private final int value;
    private final int unit;
    private final String description;

    private List<PriceDo> prices; // Can be loaded in by the service.

    private List<DatasheetDo> datasheets; // Can be loaded in by the service.

    public PartDo(UUID id, String name, int value, int unit, String description) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.description = description;
    }

    public PartDo(PartDo partDo) {
        this(partDo.id, partDo.name, partDo.value, partDo.unit, partDo.description);
        this.prices = partDo.prices;
        this.datasheets = partDo.datasheets;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }

    public List<PriceDo> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceDo> prices) {
        this.prices = prices;
    }

    public List<DatasheetDo> getDatasheets() {
        return datasheets;
    }

    public void setDatasheets(List<DatasheetDo> datasheets) {
        this.datasheets = datasheets;
    }
}
