package com.showmeyourcode.projects.springmvc.domain.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 60, message = "{login.pass.size}")
    @NotEmpty(message = "{login.pass.empty}")
    private String passwordLogin;

    @NotEmpty(message = "{login.login.empty}")
    private String login;
}
