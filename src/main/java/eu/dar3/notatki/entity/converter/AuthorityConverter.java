package eu.dar3.notatki.entity.converter;

import jakarta.persistence.AttributeConverter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorityConverter implements AttributeConverter<List<SimpleGrantedAuthority>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<SimpleGrantedAuthority> attribute) {
        return Optional.ofNullable(attribute)
                .map(authorities -> authorities.stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .collect(Collectors.joining(DELIMITER))
                ).orElse(null);
    }

    @Override
    public List<SimpleGrantedAuthority> convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
                .map(authorities -> Arrays.stream(authorities.split(DELIMITER))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}
