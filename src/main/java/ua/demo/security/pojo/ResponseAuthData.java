package ua.demo.security.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseAuthData {
    private String access_token;
    private String token_type;
    private long expires_in;
    private String scope;
    private String phone;
    private String email;
    private String jti;
}
