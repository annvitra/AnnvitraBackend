package com.annvitra.annvitra.DTO;

import com.annvitra.annvitra.constants.LocationAccess;
import com.annvitra.annvitra.constants.VehicleType;

import lombok.Data;

@Data
public class DeliveryPartnerDTO {
    

    private LocationAccess locationAccess;
    private VehicleType vehicleType;
    private String operationArea;
    private String adhaarNumber;
    private String adhaarOTP;
    private String panNumber;
 
}
