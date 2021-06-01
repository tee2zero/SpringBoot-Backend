package com.bedtaletshop.springbootbackend.controller.request;

import com.bedtaletshop.springbootbackend.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    @Email
    @NotEmpty
    private String email;


    private String telephone;

    private List<Product> products;
}
