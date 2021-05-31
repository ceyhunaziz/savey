package az.savey.ms.savey.controller;

import az.savey.ms.savey.model.request.CreditRequestDto;
import az.savey.ms.savey.model.response.CreditResponseDto;
import az.savey.ms.savey.service.CreditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/savey")
@RestController
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/")
    public List<CreditResponseDto> getCredits() {
        return creditService.getCredits();
    }

    @PostMapping("/")
    public CreditResponseDto createCredit(@RequestBody CreditRequestDto credit) {
        return creditService.createCredit(credit);
    }

}
