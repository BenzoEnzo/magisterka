package pl.benzo.enzo.magisterka.basecomponent.domain.account;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AccountMapper {

    public Account mapToEntity(AccountDTO dto){
        return Account.builder()
                .mail(dto.getMail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public AccountDTO mapToDO(Account entity){
        return AccountDTO.builder()
                .id(entity.getId())
                .mail(entity.getMail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}
