/**
 * 
 */
package com.jaymansmann.books.dto.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jay
 *
 */
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}