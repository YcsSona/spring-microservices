package com.theonewhocode.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Account Number of Eazy Bank Account",
            example = "3454433243"
    )
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits.")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazy Bank Account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty.")
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address",
            example = "123 NewYork"
    )
    @NotEmpty(message = "Branch address can not be null or empty.")
    private String branchAddress;
}
