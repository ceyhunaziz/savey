package az.savey.ms.savey.service;

import az.savey.ms.savey.dao.CreditRepository;
import az.savey.ms.savey.dao.model.CreditEntity;
import az.savey.ms.savey.mapper.CreditMapper;
import az.savey.ms.savey.model.request.CreditRequestDto;
import az.savey.ms.savey.model.response.CreditResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public CreditResponseDto createCredit(CreditRequestDto credit) {
        CreditEntity creditEntity = CreditMapper.INSTANCE.creditRequestToEntity(credit);
        creditRepository.save(creditEntity);

        return CreditMapper.INSTANCE.creditEntityToResponse(creditEntity);
    }

    public List<CreditResponseDto> getCredits() {
        List<CreditEntity> credits = creditRepository.findAllByIsActiveIsTrue();
        return CreditMapper.INSTANCE.bulkCreditEntityToResponse(credits);
    }
}
