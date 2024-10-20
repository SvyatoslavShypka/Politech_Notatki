package eu.dar3.notatki.service;

import eu.dar3.notatki.dto.UserAccountDto;
import eu.dar3.notatki.entity.UserAccountEntity;
import eu.dar3.notatki.mapper.AccountMapper;
import eu.dar3.notatki.repository.UserAccountRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(UserAccountRepository userAccountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountMapper.mapEntityToDto(userAccountRepository.findUserAccountEntityByUsername(username));
    }

    public List<UserAccountEntity> findAll() {
        return userAccountRepository.findAll();
    }

    public void createUser(UserDetails user) {
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        userAccountRepository.save(userAccountEntity);
    }

    public void updateUser(UserDetails user) {
        UserAccountEntity userAccountEntityByUsername = userAccountRepository.findUserAccountEntityByUsername(user.getUsername());
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        BeanUtils.copyProperties(userAccountEntity, userAccountEntityByUsername);
        userAccountRepository.save(userAccountEntityByUsername);
    }

    public void deleteById(Long id) {
        userAccountRepository.deleteById(id);
    }

    public UserAccountDto getById(Long id) {
        return accountMapper.mapEntityToDto(userAccountRepository.findById(id)
                .orElse(new UserAccountEntity()));
    }

    private void encodePassword(UserAccountEntity userAccountEntity) {
        userAccountEntity.setPassword(passwordEncoder.encode(userAccountEntity.getPassword()));
    }
}
