package pl.benzo.enzo.magisterka.basecomponent.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO createAccount(AccountDTO request){
        Account account = accountMapper.mapToEntity(request);
        return accountMapper.mapToDO(accountRepository.save(account));
    }
}
