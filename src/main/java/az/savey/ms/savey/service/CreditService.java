package az.savey.ms.savey.service;

import az.savey.ms.savey.dao.CreditRepository;
import az.savey.ms.savey.dao.model.CreditEntity;
import az.savey.ms.savey.exception.CreditException;
import az.savey.ms.savey.exception.StatusCode;
import az.savey.ms.savey.mapper.CreditMapper;
import az.savey.ms.savey.model.BaseResponse;
import az.savey.ms.savey.model.request.CreditRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public BaseResponse createCredit(CreditRequestDto credit) {
        CreditEntity creditEntity = CreditMapper.INSTANCE.creditRequestToEntity(credit);
        creditRepository.save(creditEntity);

        return getResponse(CreditMapper.INSTANCE.creditEntityToResponse(creditEntity));
    }

    public BaseResponse getCredits() {
        List<CreditEntity> credits = creditRepository.findAllByIsActiveIsTrue();

        return getResponse(CreditMapper.INSTANCE.bulkCreditEntityToResponse(credits));
    }

    public BaseResponse getCreditById(Long id) {
        CreditEntity creditEntity = creditRepository.findByIdAndIsActiveIsTrue(id).orElseThrow(
                () -> new CreditException("No credit with this id")
        );

        return getResponse(CreditMapper.INSTANCE.creditEntityToResponse(creditEntity));
    }

    private BaseResponse getResponse(Object response) {
        BaseResponse baseResponse = new BaseResponse(StatusCode.SUCCESS);
        baseResponse.setData(response);
        return baseResponse;
    }
}
