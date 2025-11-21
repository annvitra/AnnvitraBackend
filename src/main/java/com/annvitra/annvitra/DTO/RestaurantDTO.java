package com.annvitra.annvitra.DTO;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String restaurantName;
    private String managerName;
    private String restaurantAddress; // serialized address
    private String fssaiLicense;
    private String cuisine;
    private Double operatingHours;
    private String gstNumber;
    private BankDetailsDTO bankDetails;

 
}
