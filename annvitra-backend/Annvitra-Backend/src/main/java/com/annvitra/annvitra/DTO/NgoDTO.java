package com.annvitra.annvitra.DTO;

import lombok.Data;

@Data
public class NgoDTO {

    private String ngoName;
    private String contactPerson;
    private String ngoAddress;
    private String registrationNumber;
    private String areaOfOperation;
    private String purpose;
    private String adhharNumber;
    private BankDetailsDTO bankDetails;

 
}
