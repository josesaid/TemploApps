package com.showmeyourcode.projects.springmvc.domain.model;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterLoginFormDto {

    @Valid
    private AppUserEntity user;
    @Valid
    private UserSignInDto userSignIn;
}
