package narif.poc.spring.microservices.jwt.config.security;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtTokenEnhancer implements TokenEnhancer {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, 
			OAuth2Authentication authentication) {
		Map<String,Object> additionalInfo = new HashMap<>();
		additionalInfo.put("TodaysDate", getTodaysDate());
		additionalInfo.put("OrgId", getOrgId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
	
	private LocalDate getTodaysDate() {
		return LocalDate.now();
	}
	
	private String getOrgId() {
		return "UUID-1234-something-awsome";
	}

}
