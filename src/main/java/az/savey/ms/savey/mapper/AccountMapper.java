package az.savey.ms.savey.mapper;

import az.savey.ms.savey.dao.model.AccountEntity;
import az.savey.ms.savey.model.request.AccountRequestDto;
import az.savey.ms.savey.model.response.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper {
    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdAt", source = "createdAt")
    public abstract AccountResponseDto accountEntityToResponse(AccountEntity account);

    public List<AccountResponseDto> bulkAccountEntityToResponse(List<AccountEntity> accounts) {
        return accounts.stream()
                .map(this::accountEntityToResponse)
                .collect(Collectors.toList());
    }

    @Mapping(target = "title", source = "title")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    public abstract AccountEntity accountRequestToEntity(AccountRequestDto account);
}
