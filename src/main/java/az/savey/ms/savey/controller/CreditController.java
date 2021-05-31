package az.savey.ms.savey.controller;

import az.savey.ms.savey.model.BaseResponse;
import az.savey.ms.savey.model.request.CreditRequestDto;
import az.savey.ms.savey.service.CreditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/savey")
@RestController
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/")
    public BaseResponse getCredits() {
        return creditService.getCredits();
    }

    @PatchMapping("/{id}")
    public BaseResponse getCreditById(@PathVariable Long id){
        return creditService.getCreditById(id);
    }

    @PostMapping("/")
    public BaseResponse createCredit(@RequestBody CreditRequestDto credit) {
        return creditService.createCredit(credit);
    }

}
