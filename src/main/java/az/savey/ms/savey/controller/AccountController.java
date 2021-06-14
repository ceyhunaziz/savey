package az.savey.ms.savey.controller;

import az.savey.ms.savey.model.BaseResponse;
import az.savey.ms.savey.model.request.AccountRequestDto;
import az.savey.ms.savey.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PatchMapping("/{id}")
    public BaseResponse getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/")
    public BaseResponse getAllAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/")
    public BaseResponse createAccount(@RequestBody AccountRequestDto account) {
        return accountService.createAccount(account);
    }
}
