package com.assignment.requip.dto;

import com.assignment.requip.entity.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    private Gender gender;

    @NotBlank(message = "Primary mobile is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String primaryMobile;

    private String secondaryMobile;

    @Pattern(
            regexp = "^\\d{12}$",
            message = "Aadhaar must be 12 digits"
    )
    private String aadhaar;

    @Pattern(
            regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "Invalid PAN format"
    )
        private String pan;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String currentAddress;

    private String permanentAddress;
}