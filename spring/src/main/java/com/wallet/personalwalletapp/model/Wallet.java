package com.wallet.personalwalletapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can't be blank.")
    private String name;
    @Size(min = 2,max = 30,message = "You must enter a valid account number.")
    private String accountNumber;
    @Size(max = 100,message = "You must enter max 100 character.")
    private String description;
    @Min(1)
    @Max(3)
    private Integer priority; //1=High;2=Medium;3=Low
    @Min(value = 0,message = "Balance can't be negative!")
    private Double currentBalance;

    @OneToMany(mappedBy = "wallet",orphanRemoval = true)
    private List<Transaction> transactionList;


    public void deposit(double amount){this.currentBalance+=amount;}
    public void withdraw(double amount){this.currentBalance-=amount;}
}