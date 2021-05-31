package az.savey.ms.savey.mapper;

import az.savey.ms.savey.dao.model.CreditEntity;
import az.savey.ms.savey.model.request.CreditRequestDto;
import az.savey.ms.savey.model.response.CreditResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreditMapper {
    public static final CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "paymentLink", source = "paymentLink")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "createdAt", source = "createdAt")
    public abstract CreditResponseDto creditEntityToResponse(CreditEntity credit);

    public List<CreditResponseDto> bulkCreditEntityToResponse(List<CreditEntity> credits) {
        return credits.stream()
                .map(this::creditEntityToResponse)
                .collect(Collectors.toList());
    }

    @Mapping(target = "description", source = "description")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "paymentLink", source = "paymentLink")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "startDate", source = "startDate")
    public abstract CreditEntity creditRequestToEntity(CreditRequestDto credit);
}
