package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class BillOfMaterialsDo implements Do {

    private final UUID id;

    @NotBlank
    private final String bomName;
    @NotBlank
    private final String projectName;

    private final String notes;

    private List<BillOfMaterialsItemDo> bomItems;

    public BillOfMaterialsDo(UUID id, String bomName, String projectName, String notes) {
        this.id = id;
        this.bomName = bomName;
        this.projectName = projectName;
        this.notes = notes;
    }

    public BillOfMaterialsDo(BillOfMaterialsDo billOfMaterialsDo) {
        this(billOfMaterialsDo.id, billOfMaterialsDo.bomName, billOfMaterialsDo.projectName, billOfMaterialsDo.notes);
        this.bomItems = billOfMaterialsDo.bomItems;
    }

    public UUID getId() { return id; }

    public String getBomName() {
        return bomName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getNotes() {
        return notes;
    }

    public List<BillOfMaterialsItemDo> getBomItems() {
        return bomItems;
    }

    public void setBomItems(List<BillOfMaterialsItemDo> bomItems) {
        this.bomItems = bomItems;
    }

}
