package com.annvitra.annvitra.ServicesIMPL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.annvitra.annvitra.DTO.CommonDTO;
import com.annvitra.annvitra.DTO.LoginRequestDTO;
import com.annvitra.annvitra.DTO.LoginResponseDTO;
import com.annvitra.annvitra.Entity.BankDetails;
import com.annvitra.annvitra.Entity.DeliveryPartner;
import com.annvitra.annvitra.Entity.Farmer;
import com.annvitra.annvitra.Entity.Ngo;
import com.annvitra.annvitra.Entity.Restaurant;
import com.annvitra.annvitra.Entity.User;
import com.annvitra.annvitra.Repositries.DeliverPartnerRepository;
import com.annvitra.annvitra.Repositries.FarmerRepository;
import com.annvitra.annvitra.Repositries.NGORepository;
import com.annvitra.annvitra.Repositries.RestaurantRepository;
import com.annvitra.annvitra.Repositries.UserRepository;
import com.annvitra.annvitra.Services.AuthService;
import com.annvitra.annvitra.constants.LocationAccess;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceIMPL.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final NGORepository ngoRepository;
    private final FarmerRepository farmerRepository;
    private final DeliverPartnerRepository deliveryPartnerRepository;
    private final RestaurantRepository restaurantRepository;

    public void CommonSignupLogic(CommonDTO commonDTO, User user) {

        try {
            user.setEmail(commonDTO.getEmail());
            // validate password presence
            if (commonDTO.getPassword() == null || commonDTO.getPassword().isEmpty()) {
                logger.error("Password is null or empty during signup for email: " + commonDTO.getEmail());
                throw new IllegalArgumentException("Password must be provided");
            }
            user.setPassword(passwordEncoder.encode(commonDTO.getPassword()));
            // Log name fields to debug mapping issues
            logger.info("Signup incoming names - firstName: {} lastName: {}", commonDTO.getFirstName(),
                    commonDTO.getLastName());
            user.setRole(commonDTO.getRole());
            user.setFirstName(commonDTO.getFirstName());
            user.setLastName(commonDTO.getLastName());
            user.setOTP(commonDTO.getOTP());
            // user.setProfileImage(commonDTO.getProfileImage());
            user.setPhoneNumber(commonDTO.getPhoneNumber());
            user.setAddress(commonDTO.getAddress());
            user.setOTPexpiry(commonDTO.getOTPexpiry());

            userRepository.save(user);
            logger.info("User saved successfully with email: " + user.getEmail());
        } catch (Exception e) {
            logger.error("Failed to save user", e);
            throw new RuntimeException("Failed to save user", e);

        }
    }

    @Override
    public void signup(CommonDTO commonDTO) {

        String role = commonDTO.getRole();
        User user = new User();
        CommonSignupLogic(commonDTO, user);
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

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

            if (auth.isAuthenticated()) {
                // try to extract role from principal if available
                String role = null;
                Object principal = auth.getPrincipal();
                if (principal instanceof User) {
                    role = ((User) principal).getRole();
                } else {
                    // fallback: load from repository
                    User u = userRepository.findByEmail(loginRequestDTO.getEmail()).orElse(null);
                    if (u != null) {
                        role = u.getRole();
                    }
                }
                return new LoginResponseDTO(loginRequestDTO.getEmail(), "Login successful", role);
            } else {
                return new LoginResponseDTO(loginRequestDTO.getEmail(), "Authentication failed", null);
            }
        } catch (AuthenticationException ex) {
            logger.error("Authentication failed for email: " + loginRequestDTO.getEmail(), ex);
            throw ex;
        }
    }

}
