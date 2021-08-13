package com.java.everis.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Document("Customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    String id;

    @NotEmpty
    String name;

    @NotEmpty
    String lastName;

    @Valid
    TypeCustomer typeCustomer;

    @NotNull
    String dni;

    @NotNull
    Integer age;

    @NotNull
    String gender;

}
