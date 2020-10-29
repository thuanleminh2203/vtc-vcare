package com.venesa.ctvvcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

    private String jwtToken;
    private Collection<? extends GrantedAuthority> role;

}
