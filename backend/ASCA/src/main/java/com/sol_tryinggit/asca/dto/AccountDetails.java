package com.sol_tryinggit.asca.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    private String username;
    private String password;
    private List<String> services;
}
