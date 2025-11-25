package com.annvitra.annvitra.ServicesIMPL;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.annvitra.annvitra.Configuration.SMSservice;
import com.annvitra.annvitra.DTO.CommonDTO;
import com.annvitra.annvitra.Entity.BankDetails;
import com.annvitra.annvitra.Entity.DeliveryPartner;
import com.annvitra.annvitra.Entity.Farmer;
import com.annvitra.annvitra.Entity.Ngo;
import com.annvitra.annvitra.Entity.Restaurant;
import com.annvitra.annvitra.Entity.User;
import com.annvitra.annvitra.Exceptions.Exceptions;
import com.annvitra.annvitra.Repositries.DeliverPartnerRepository;
import com.annvitra.annvitra.Repositries.FarmerRepository;
import com.annvitra.annvitra.Repositries.NGORepository;
import com.annvitra.annvitra.Repositries.RestaurantRepository;
import com.annvitra.annvitra.Repositries.UserRepository;
import com.annvitra.annvitra.Services.AuthService;
import com.annvitra.annvitra.Util.CommonMethods;
import com.annvitra.annvitra.constants.LocationAccess;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceIMPL.class);
    private final UserRepository userRepository;

    private final NGORepository ngoRepository;
    private final FarmerRepository farmerRepository;
    private final DeliverPartnerRepository deliveryPartnerRepository;
    private final RestaurantRepository restaurantRepository;
    private final CommonMethods commonMethods;
    private final SMSservice smsservice;

    @Override
    public void signup(CommonDTO commonDTO) {

        String role = commonDTO.getRole();
        String mobile = commonDTO.getMobile();
        User user = userRepository.findByMobile(mobile);
        if (user == null) {
            logger.error("No user found with mobile number: " + mobile);
            throw new IllegalArgumentException(new Exceptions.UserNotFoundException());
        }

        if (role == null || role.isEmpty()) {
            logger.error("Role not provided during signup");
            throw new IllegalArgumentException("Role must be provided for signup");
        } else if (role.equalsIgnoreCase("NGO")) {

            try {
                Ngo ngo = new Ngo();
                ngo.setNgoAddress(commonDTO.getNgoAddress());
                ngo.setNgoName(commonDTO.getNgoName());
                ngo.setContactPerson(commonDTO.getContactPerson());
                ngo.setRegistrationNumber(commonDTO.getRegistrationNumber());
                ngo.setAreaOfOperation(commonDTO.getAreaOfOperation());
                ngo.setAdhharNumber(commonDTO.getAdhharNumber());
                ngo.setPurpose(commonDTO.getPurpose());
                // ngo.setNgoLogo(commonDTO.getNgoLogo());
                BankDetails bankDetails = new BankDetails();
                if (commonDTO.getBankDetails() != null) {
                    bankDetails.setAccountNumber(commonDTO.getBankDetails().getAccountNumber());
                    bankDetails.setIfscCode(commonDTO.getBankDetails().getIfscCode());
                    bankDetails.setBankName(commonDTO.getBankDetails().getBankName());
                    bankDetails.setAccountHolderName(commonDTO.getBankDetails().getAccountHolderName());
                    bankDetails.setBranchName(commonDTO.getBankDetails().getBranchName());
                }
                ngo.setBankDetails(bankDetails);

                ngo.setUser(user);
                ngoRepository.save(ngo);
                logger.info("NGO saved successfully with name: " + ngo.getNgoName());
            } catch (Exception e) {
                logger.error("Failed to save NGO", e);
                throw new RuntimeException("Failed to save NGO", e);
            }

        } else if (role.equalsIgnoreCase("FARMER")) {

            try { // farmer signup logic to be implemented
                Farmer farmer = new Farmer();
                farmer.setFarmName(commonDTO.getFarmName());
                farmer.setOwnerName(commonDTO.getOwnerName());
                farmer.setFarmAddress(commonDTO.getFarmAddress());
                farmer.setProductionType(commonDTO.getProductionType());
                farmer.setQuantityRange(commonDTO.getQuantityRange());
                BankDetails bankDetails = new BankDetails();
                if (commonDTO.getBankDetails() != null) {
                    bankDetails.setAccountNumber(commonDTO.getBankDetails().getAccountNumber());
                    bankDetails.setIfscCode(commonDTO.getBankDetails().getIfscCode());
                    bankDetails.setBankName(commonDTO.getBankDetails().getBankName());
                    bankDetails.setAccountHolderName(commonDTO.getBankDetails().getAccountHolderName());
                    bankDetails.setBranchName(commonDTO.getBankDetails().getBranchName());
                }
                farmer.setBankDetails(bankDetails);

                farmer.setUser(user);
                farmerRepository.save(farmer);
                logger.info("Farmer saved successfully with Farm Name: " + farmer.getFarmName());
            } catch (Exception e) {
                logger.error("Failed to save farmer", e);
                throw new RuntimeException("Failed to save farmer", e);
            }

        } else if (role.equalsIgnoreCase("DELIVERY_PARTNER")) {

            try {
                DeliveryPartner deliveryPartner = new DeliveryPartner();
                deliveryPartner.setName(commonDTO.getName());
                deliveryPartner.setLocationAccess(LocationAccess.ALLOW);
                deliveryPartner.setVehicleType(commonDTO.getVehicleType());
                deliveryPartner.setOperationArea(commonDTO.getOperationArea());
                deliveryPartner.setAdhaarNumber(commonDTO.getAdhaarNumber());
                deliveryPartner.setAdhaarOTP(commonDTO.getAdhaarOTP());
                deliveryPartner.setPANNumber(commonDTO.getPanNumber());

                deliveryPartner.setUser(user);
                deliveryPartnerRepository.save(deliveryPartner);
                logger.info(
                        "Delivery Partner saved successfully with Adhaar Number: " + deliveryPartner.getAdhaarNumber());
            } catch (Exception e) {
                logger.error("Failed to save delivery partner", e);
                throw new RuntimeException("Failed to save delivery partner", e);
            }

        } else if (role.equalsIgnoreCase("RESTAURANT")) {

            try {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantName(commonDTO.getRestaurantName());
                restaurant.setManagerName(commonDTO.getManagerName());
                restaurant.setRestaurantAddress(commonDTO.getRestaurantAddress());
                restaurant.setFssaiLicense(commonDTO.getFssaiLicense());
                restaurant.setCuisine(commonDTO.getCuisine());
                restaurant.setOperatingHours(commonDTO.getOperatingHours());
                restaurant.setGstNumber(commonDTO.getGstNumber());
                // restaurant.setRestaurantLogo(commonDTO.getRestaurantLogo());
                BankDetails bankDetails = new BankDetails();
                if (commonDTO.getBankDetails() != null) {
                    bankDetails.setAccountNumber(commonDTO.getBankDetails().getAccountNumber());
                    bankDetails.setIfscCode(commonDTO.getBankDetails().getIfscCode());
                    bankDetails.setBankName(commonDTO.getBankDetails().getBankName());
                    bankDetails.setAccountHolderName(commonDTO.getBankDetails().getAccountHolderName());
                    bankDetails.setBranchName(commonDTO.getBankDetails().getBranchName());
                }

                restaurant.setBankDetails(bankDetails);
                restaurant.setUser(user);
                restaurantRepository.save(restaurant);
                logger.info("Restaurant saved successfully with name: " + restaurant.getRestaurantName());
            } catch (Exception e) {
                logger.error("Failed to save restaurant", e);
                throw new RuntimeException("Failed to save restaurant", e);
            }

        } else {
            logger.error("Invalid role provided during signup: " + role);
            throw new IllegalArgumentException("Invalid role: " + role);
        }

    }

    public void registerUser(CommonDTO commonDTO, String generatedOTP) {

        User user = new User();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);
        user.setMobile(commonDTO.getMobile());
        user.setOTPexpiry(expiry);
        user.setRole(commonDTO.getRole());
        user.setOTP(generatedOTP);
        userRepository.save(user);
    }

    @Override
    public void verifyMobile(CommonDTO commonDTO) {
        String role = commonDTO.getRole();
        if (role.equalsIgnoreCase("NGO")) {
            Ngo existingNgo = ngoRepository.findByUser_Mobile(commonDTO.getMobile());
            if (existingNgo != null) {
                // Mobile number already exists
                logger.info("Mobile number already registered for NGO: " + commonDTO.getMobile());
                throw new IllegalArgumentException("Mobile number already registered for NGO");

            } else {
                // generate and send OTP
               String generatedOTP = smsservice.sendOTP(commonDTO.getMobile());
                registerUser(commonDTO, generatedOTP);
            }

        } else if (role.equalsIgnoreCase("FARMER")) {
            Farmer existingFarmer = farmerRepository.findByUser_Mobile(commonDTO.getMobile());
            if (existingFarmer != null) {
                // Mobile number already exists
                logger.info("Mobile number already registered for Farmer: " + commonDTO.getMobile());
                throw new IllegalArgumentException("Mobile number already registered for Farmer");
            } else {
                // generate and send OTP
               String generatedOTP = smsservice.sendOTP(commonDTO.getMobile());
                registerUser(commonDTO, generatedOTP);
            }

        } else if (role.equalsIgnoreCase("DELIVERY_PARTNER")) {
            DeliveryPartner existingDeliveryPartner = deliveryPartnerRepository
                    .findByUser_Mobile(commonDTO.getMobile());
            if (existingDeliveryPartner != null) {
                // Mobile number already exists
                logger.info("Mobile number already registered for Delivery Partner: " + commonDTO.getMobile());
                throw new IllegalArgumentException("Mobile number already registered for Delivery Partner");
            } else {
                // generate and send OTP
               String generatedOTP = smsservice.sendOTP(commonDTO.getMobile());
                registerUser(commonDTO, generatedOTP);
            }
        } else if (role.equalsIgnoreCase("RESTAURANT")) {
            Restaurant existingRestaurant = restaurantRepository.findByUser_Mobile(commonDTO.getMobile());
            if (existingRestaurant != null) {
                // Mobile number already exists
                logger.info("Mobile number already registered for Restaurant: " + commonDTO.getMobile());
                throw new IllegalArgumentException("Mobile number already registered for Restaurant");
            } else {
                // generate and send OTP
                String generatedOTP = smsservice.sendOTP(commonDTO.getMobile());
                registerUser(commonDTO, generatedOTP);
            }
        } else {
            logger.error("Invalid role provided during mobile verification: " + role);
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    @Override
    public void verifyOTP(CommonDTO commonDTO) {
        String mobile = commonDTO.getMobile();
        String role = commonDTO.getRole();
        String inputOTP = commonDTO.getOTP();
        logger.info("verifyOTP requested for mobile={}, role={}, inputOTP={}", mobile, role, inputOTP);

        User user = userRepository.findByMobile(mobile);
        if (user != null) {
            logger.info("Found user for mobile={}, storedOTP={}, otpExpiry={}", user.getMobile(), user.getOTP(),
                    user.getOTPexpiry());
        } else {
            logger.info("No user found for mobile={}", mobile);
        }
        if (user == null) {
            logger.error("No user found with mobile number: " + mobile);
            throw new IllegalArgumentException(new Exceptions.UserNotFoundException());
        }

        // Debug: log exact stored vs provided OTP before any role-specific checks
        logger.info("Comparing storedOTP={} with provided inputOTP={} for mobile={}", user.getOTP(), inputOTP, mobile);

        if (role.equalsIgnoreCase("NGO")) {

            if (commonMethods.isOTPExpired(user.getOTPexpiry())) {
                logger.error("OTP has expired for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException("OTP has expired"));
            }

            if (commonMethods.verifyOTP(user.getOTP(), inputOTP)) {
                logger.info("OTP verified successfully for mobile number: " + mobile);
                // OTP verified successfully, proceed with further logic if needed
            } else {
                logger.error("Invalid OTP provided for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException());
            }

        } else if (role.equalsIgnoreCase("FARMER")) {

            if (commonMethods.isOTPExpired(user.getOTPexpiry())) {
                logger.error("OTP has expired for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException("OTP has expired"));
            }

            if (commonMethods.verifyOTP(user.getOTP(), inputOTP)) {
                logger.info("OTP verified successfully for mobile number: " + mobile);
                // OTP verified successfully, proceed with further logic if needed
            } else {
                logger.error("Invalid OTP provided for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException());
            }

        } else if (role.equalsIgnoreCase("DELIVERY_PARTNER")) {

            if (commonMethods.isOTPExpired(user.getOTPexpiry())) {
                logger.error("OTP has expired for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException("OTP has expired"));
            }

            if (commonMethods.verifyOTP(user.getOTP(), inputOTP)) {
                logger.info("OTP verified successfully for mobile number: " + mobile);
                // OTP verified successfully, proceed with further logic if needed
            } else {
                logger.error("Invalid OTP provided for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException());
            }

        } else if (role.equalsIgnoreCase("RESTAURANT")) {

            if (commonMethods.isOTPExpired(user.getOTPexpiry())) {
                logger.error("OTP has expired for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException("OTP has expired"));
            }

            if (commonMethods.verifyOTP(user.getOTP(), inputOTP)) {
                logger.info("OTP verified successfully for mobile number: " + mobile);
                // OTP verified successfully, proceed with further logic if needed
            } else {
                logger.error("Invalid OTP provided for mobile number: " + mobile);
                throw new IllegalArgumentException(new Exceptions.InvalidOTPException());
            }

        } else {
            logger.error("Invalid role provided during mobile verification: " + role);
            throw new IllegalArgumentException(new Exceptions.RoleNotProvidedException("Invalid role: " + role));
        }
    }

}
