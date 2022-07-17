package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Provider;
import com.timf.teamfreash.model.type.SectorType;
import lombok.Data;

@Data
public class ProviderDto {
    private long id;
    private String userId;
    private String password;
    private String managerName;
    private String email;
    private String phoneNumber;
    private SectorType sector;

    public static ProviderDto from(Provider provider) {
        ProviderDto providerDto = new ProviderDto();
        providerDto.setId(provider.getId());
        providerDto.setUserId(provider.getUserId());
        providerDto.setPassword(provider.getPassword());
        providerDto.setManagerName(provider.getMangerName());
        providerDto.setEmail(provider.getEmail());
        providerDto.setPhoneNumber(provider.getPhoneNumber());
        providerDto.setSector(provider.getSector());

        return providerDto;
    }
}
