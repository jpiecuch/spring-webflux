package pl.jakubpiecuch.springwebflux.configuration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2AuthenticationDecodedDetails {

    private String subject;

    private Integer userId;

    private String shardId;

    private String companyShortName;

    private String userFirstName;

    private String userLastName;

    private String gender;

    private String locale;

    private String email;

    private Boolean emailIsVerified;
}
