package com.wallet.personalwalletapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1,message = "Invalid amount!")
    @NotNull(message = "Amount can't be null")
    private Double amount;
    private String description;
    @Min(1)
    @Max(2)
    private int type; //1 for income, 2 for expense
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "wallet_id",nullable = false,referencedColumnName = "id")
    @JsonIgnore
    private Wallet wallet;


}
