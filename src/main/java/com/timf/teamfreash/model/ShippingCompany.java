package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.ShippingCompanyDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ShippingCompany")
@Getter @Setter
public class ShippingCompany {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "company_register_number", unique = true)
    private String companyRegisterNumber;
    @Column(name = "company_name")
    private String companyName;
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    List<DeliveryDriver> drivers = new ArrayList<>();

    public ShippingCompany() {

    }

    public ShippingCompany(String companyRegisterNumber, String companyName) {
        this.companyRegisterNumber = companyRegisterNumber;
        this.companyName = companyName;
    }

    public static ShippingCompany from(ShippingCompanyDto shippingCompanyDto) {
        ShippingCompany shippingCompany = new ShippingCompany();
        shippingCompany.setCompanyRegisterNumber(shippingCompanyDto.getCompanyRegisterNumber());
        shippingCompany.setCompanyName(shippingCompanyDto.getCompanyName());

        return shippingCompany;
    }
}
