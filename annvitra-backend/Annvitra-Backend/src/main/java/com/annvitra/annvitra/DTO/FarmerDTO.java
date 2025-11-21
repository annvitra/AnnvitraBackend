package com.annvitra.annvitra.DTO;

import com.annvitra.annvitra.constants.LocationAccess;
import com.annvitra.annvitra.constants.ProductionType;

import lombok.Data;

@Data
public class FarmerDTO {
  
    private String farmName;
    private String ownerName;
    private LocationAccess locationAccess;
    private String farmAddress;
    private ProductionType productionType;
    private String quantityRange;
    private BankDetailsDTO bankDetails;
   
}
