package az.savey.ms.savey.service;

import az.savey.ms.savey.dao.CreditRepository;
import az.savey.ms.savey.dao.model.CreditEntity;
import az.savey.ms.savey.exception.CreditException;
import az.savey.ms.savey.exception.StatusCode;
import az.savey.ms.savey.logger.DPLogger;
import az.savey.ms.savey.mapper.CreditMapper;
import az.savey.ms.savey.model.BaseResponse;
import az.savey.ms.savey.model.request.CreditRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private static final DPLogger logger = DPLogger.getLogger(CreditService.class);

    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public BaseResponse createCredit(CreditRequestDto credit) {
        logger.info("ActionLog.createCredit.start");
        CreditEntity creditEntity = CreditMapper.INSTANCE.creditRequestToEntity(credit);
        creditRepository.save(creditEntity);

        logger.info("ActionLog.createCredit.success");
        return getResponse(CreditMapper.INSTANCE.creditEntityToResponse(creditEntity));
    }

    public BaseResponse getCredits() {
        logger.info("ActionLog.getCredits.start");
        List<CreditEntity> credits = creditRepository.findAllByIsActiveIsTrue();

        logger.info("ActionLog.getCredits.success");
        return getResponse(CreditMapper.INSTANCE.bulkCreditEntityToResponse(credits));
    }

    public BaseResponse getCreditById(Long id) {
        logger.info("ActionLog.getCreditById.start");
        CreditEntity creditEntity = creditRepository.findByIdAndIsActiveIsTrue(id).orElseThrow(
                () -> {
                    logger.error("ActionLog.getCreditById.error no credit with id: {}", id);
                    return new CreditException("No credit with this id");
                }
        );

        logger.info("ActionLog.getCreditById.success");
        return getResponse(CreditMapper.INSTANCE.creditEntityToResponse(creditEntity));
    }

    private BaseResponse getResponse(Object response) {
        BaseResponse baseResponse = new BaseResponse(StatusCode.SUCCESS);
        baseResponse.setData(response);
        return baseResponse;
    }
}
