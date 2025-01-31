package com.example.csvplatform.dtos.viewModels;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginViewModel {

    public String email;
    public String password;

}
