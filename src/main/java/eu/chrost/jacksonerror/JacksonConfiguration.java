package eu.chrost.jacksonerror;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.lang.reflect.Type;
import java.util.List;

@Configuration
class JacksonConfiguration {
    private static final List<String> PACKAGES_TO_APPLY_UPPER_CAMEL_CASE = List.of(
            "eu.chrost.jacksonerror.on"
    );

    @Bean
    public MappingJackson2HttpMessageConverter defaultConverter() {
        MappingJackson2HttpMessageConverter converter = new DefaultConverter();
        converter.setObjectMapper(defaultMapper());
        return converter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter upperCameCaseConverter() {
        MappingJackson2HttpMessageConverter converter = new UpperCameCaseConverter();
        converter.setObjectMapper(upperCamelCaseMapper());
        return converter;
    }

    @Bean
    public ObjectMapper defaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean
    public ObjectMapper upperCamelCaseMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UpperCamelCaseStrategy.INSTANCE);
        return mapper;
    }

    private static class DefaultConverter extends MappingJackson2HttpMessageConverter {
        @Override
        public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
            return matches(type) && super.canWrite(type, clazz, mediaType);
        }

        @Override
        public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
            return matches(type) && super.canRead(type, contextClass, mediaType);
        }

        private boolean matches(Type type) {
            for (String pkg : PACKAGES_TO_APPLY_UPPER_CAMEL_CASE) {
                if (type.toString().contains(pkg)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class UpperCameCaseConverter extends MappingJackson2HttpMessageConverter {

        @Override
        public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
            return matches(type) && super.canWrite(type, clazz, mediaType);
        }

        @Override
        public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
            return matches(type) && super.canRead(type, contextClass, mediaType);
        }

        private boolean matches(Type type) {
            for (String pkg : PACKAGES_TO_APPLY_UPPER_CAMEL_CASE) {
                if (type.toString().contains(pkg)) {
                    return true;
                }
            }
            return false;
        }
    }


}
