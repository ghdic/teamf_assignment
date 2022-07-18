package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.ProviderDto;
import com.timf.teamfreash.model.type.SectorType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Provider")
@Getter @Setter
public class Provider {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_id", unique = true)
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "manager_name")
    private String mangerName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "sector")
    @Enumerated(value = EnumType.STRING)
    private SectorType sector;

    public Provider() {
    }

    public Provider(String userId, String password, String mangerName, String email, String phoneNumber, SectorType sector) {
        this.userId = userId;
        this.password = password;
        this.mangerName = mangerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sector = sector;
    }

    public static Provider from(ProviderDto providerDto) {
        Provider provider = new Provider();
        provider.setUserId(providerDto.getUserId());
        provider.setPassword(providerDto.getPassword());
        provider.setMangerName(providerDto.getManagerName());
        provider.setEmail(providerDto.getEmail());
        provider.setPhoneNumber(providerDto.getPhoneNumber());
        provider.setSector(providerDto.getSector());

        return provider;
    }
}
