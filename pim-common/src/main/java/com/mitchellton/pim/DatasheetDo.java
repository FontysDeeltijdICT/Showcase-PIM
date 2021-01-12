package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class DatasheetDo implements Do{
    private final UUID id;

    private final UUID partId;
    private PartDo part; // Can be loaded in by the service.

    @NotBlank
    private final String filename;

    public DatasheetDo(UUID id, UUID partId, String filename) {
        this.id = id;
        this.partId = partId;
        this.filename = filename;
    }

    public DatasheetDo(DatasheetDo datasheetDo) {
        this(datasheetDo.id, datasheetDo.partId, datasheetDo.filename);
        this.part = datasheetDo.part;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPartId() {
        return partId;
    }

    public String getFilename() {
        return filename;
    }

    public PartDo getPart() {
        return part;
    }

    public void setPart(PartDo part) {
        this.part = part;
    }
}
