package com.annvitra.annvitra.DTO;

import lombok.Data;

@Data
public class BankDetailsDTO {
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String branchName;
}
