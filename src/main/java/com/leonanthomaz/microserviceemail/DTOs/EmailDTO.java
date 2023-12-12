package com.leonanthomaz.microserviceemail.DTOs;

import com.leonanthomaz.microserviceemail.enums.StatusEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailDTO {

    @NotBlank
    private String ownerRef;
    @NotBlank
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
