package com.mitchellton.pim;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class DistributorDo implements Do{
    private final UUID id;

    @NotBlank
    private final String name;

    private final String url;
    private final String address;
    private final String phonenumber;
    private final String contactperson;

    public DistributorDo(UUID id, String name, String url, String address,String phonenumber, String contactperson) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.address = address;
        this.phonenumber = phonenumber;
        this.contactperson = contactperson;
    }

    public DistributorDo(DistributorDo Do) {
        this(Do.id, Do.name, Do.url, Do.address, Do.phonenumber, Do.contactperson);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getContactperson() {
        return contactperson;
    }
}
