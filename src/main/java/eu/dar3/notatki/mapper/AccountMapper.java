package eu.dar3.notatki.mapper;

import eu.dar3.notatki.dto.UserAccountDto;
import eu.dar3.notatki.entity.UserAccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class AccountMapper implements Mapper<UserAccountEntity, UserAccountDto> {

    @Override
    public UserAccountDto mapEntityToDto(UserAccountEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        UserAccountDto target = new UserAccountDto();
        target.setId(source.getId());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setIsAccountNonExpired(source.getIsAccountNonExpired());
        target.setIsAccountNonLocked(source.getIsAccountNonLocked());
        target.setIsCredentialsNonExpired(source.getIsCredentialsNonExpired());
        target.setIsEnabled(source.getIsEnabled());
        target.setAuthorities(source.getAuthorities());
        return target;
    }

    @Override
    public UserAccountEntity mapDtoToEntity(UserAccountDto source) {
        if (isNull(source)) {
            return null;
        }
        UserAccountEntity target = new UserAccountEntity();
        target.setId(source.getId());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setIsAccountNonExpired(source.getIsAccountNonExpired());
        target.setIsAccountNonLocked(source.getIsAccountNonLocked());
        target.setIsCredentialsNonExpired(source.getIsCredentialsNonExpired());
        target.setIsEnabled(source.getIsEnabled());
        target.setAuthorities(source.getAuthorities());
        return target;
    }
}
