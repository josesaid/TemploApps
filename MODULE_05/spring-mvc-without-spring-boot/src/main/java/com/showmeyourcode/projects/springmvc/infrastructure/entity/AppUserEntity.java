package com.showmeyourcode.projects.springmvc.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email", name = "app_user_email_uk"),
                @UniqueConstraint(columnNames = "nick", name = "app_user_nick_uk")
        }
)
public class AppUserEntity implements Serializable {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "{register.email.pattern}")
    @Size(max = 50, message = "{register.email.size}")
    @NotEmpty(message = "{register.email.empty}")
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 60, message = "{register.pass.size}")
    @NotEmpty(message = "{register.pass.empty}")
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Size(min = 3, max = 20, message = "{register.login.size}")
    @Pattern(regexp = "[^0-9]{1}[a-zA-Z|0-9]*", message = "{register.login.pattern}")
    @NotEmpty(message = "{register.login.empty}")
    @Column(name = "nick", nullable = false, length = 20)
    private String nick;

    @Column(name = "is_admin", columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column(name = "create_date", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createDate;

    @Transient
    private String confirmPassword;
}
