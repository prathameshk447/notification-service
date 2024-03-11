package org.dnyanyog.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

}
