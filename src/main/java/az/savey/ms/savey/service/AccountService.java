package az.savey.ms.savey.service;

import az.savey.ms.savey.dao.AccountRepository;
import az.savey.ms.savey.dao.model.AccountEntity;
import az.savey.ms.savey.exception.AccountException;
import az.savey.ms.savey.exception.StatusCode;
import az.savey.ms.savey.logger.DPLogger;
import az.savey.ms.savey.mapper.AccountMapper;
import az.savey.ms.savey.model.BaseResponse;
import az.savey.ms.savey.model.request.AccountRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private static final DPLogger logger = DPLogger.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BaseResponse getAccounts() {
        logger.info("ActionLog.getAccounts.start");
        List<AccountEntity> accounts = accountRepository.findAllByIsActiveIsTrue();

        logger.info("ActionLog.getAccounts.success");
        return getResponse(AccountMapper.INSTANCE.bulkAccountEntityToResponse(accounts));
    }

    public BaseResponse getAccountById(Long id) {
        logger.info("ActionLog.getAccountById.start");
        AccountEntity accountEntity = accountRepository.findByIdAndIsActiveIsTrue(id).orElseThrow(
                () -> {
                    logger.error("ActionLog.getAccountById.error no account with id: {}", id);
                    return new AccountException("No account with this Id");
                }
        );

        logger.info("ActionLog.getAccountById.success");
        return getResponse(AccountMapper.INSTANCE.accountEntityToResponse(accountEntity));
    }

    public BaseResponse createAccount(AccountRequestDto account) {
        logger.info("ActionLog.createAccount.start");
        AccountEntity accountEntity = AccountMapper.INSTANCE.accountRequestToEntity(account);
        accountRepository.save(accountEntity);

        logger.info("ActionLog.createAccount.success");
        return getResponse(AccountMapper.INSTANCE.accountEntityToResponse(accountEntity));
    }

    private BaseResponse getResponse(Object response) {
        BaseResponse baseResponse = new BaseResponse(StatusCode.SUCCESS);
        baseResponse.setData(response);
        return baseResponse;
    }
}
