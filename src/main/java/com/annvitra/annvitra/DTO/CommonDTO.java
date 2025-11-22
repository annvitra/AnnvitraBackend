package com.annvitra.annvitra.DTO;


import com.annvitra.annvitra.constants.LocationAccess;
import com.annvitra.annvitra.constants.ProductionType;
import com.annvitra.annvitra.constants.VehicleType;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDTO {
  // common details
  @JsonAlias({ "Role" })
  private String role;
  @JsonAlias({ "OTP", "otp", "Otp" })
  private String OTP;
  private String mobile;

  // delivery partner
  private String name;
  private LocationAccess locationAccess = LocationAccess.DENY;
  private VehicleType vehicleType;
  private String operationArea;
  private String adhaarNumber;
  private String adhaarOTP;
  private String panNumber;

  // farmer
  private String farmName;
  private String ownerName;
  private String farmAddress;
  private ProductionType productionType;
  private String quantityRange;
  private BankDetailsDTO bankDetails;

  // ngo
  private String ngoName;
  private String contactPerson;
  private String ngoAddress;
  private String registrationNumber;
  private String areaOfOperation;
  private String purpose;
  private String adhharNumber;

  // restaurant
  private String restaurantName;
  private String managerName;
  private String restaurantAddress; // serialized address
  private String fssaiLicense;
  private String cuisine;
  private Double operatingHours;
  private String gstNumber;

}
