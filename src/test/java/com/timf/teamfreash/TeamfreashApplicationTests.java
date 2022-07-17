package com.timf.teamfreash;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timf.teamfreash.model.*;
import com.timf.teamfreash.model.type.BankType;
import com.timf.teamfreash.model.type.ClientType;
import com.timf.teamfreash.model.type.IssueType;
import com.timf.teamfreash.model.type.SectorType;
import com.timf.teamfreash.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc

class TeamfreashApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(TeamfreashApplicationTests.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShippingCompanyService shippingCompanyService;
    @Autowired
    private DeliveryDriverService deliveryDriverService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private VOCService vocService;
    @Autowired
    private CompensationService compensationService;

    private List<ShippingCompany> shippingCompanyList = new ArrayList<>();
    private List<DeliveryDriver> deliveryDriverList = new ArrayList<>();
    private List<Provider> providerList = new ArrayList<>();
    private List<VOC> vocList = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        // 운송사 생성
        ShippingCompany shippingCompany1 = shippingCompanyService.createShippingCompany(new ShippingCompany("110-81-05034", "CJ대한통운"));
        ShippingCompany shippingCompany2 = shippingCompanyService.createShippingCompany(new ShippingCompany("126-81-38772", "LOGEN"));
        shippingCompanyList.add(shippingCompany1);
        shippingCompanyList.add(shippingCompany2);
        logger.info("운송사 생성");

        // 택배기사 생성
        DeliveryDriver driver1 = deliveryDriverService.createDeliveryDriver(new DeliveryDriver("ghdic", "1234", "김철뜩", "010-5163-0298", "111-1234-1234-11", BankType.카카오뱅크), "CJ대한통운");
        DeliveryDriver driver2 = deliveryDriverService.createDeliveryDriver(new DeliveryDriver("test0078", "0078", "엄준식", "010-1004-2525", "999-000-101010", BankType.케이뱅크), "CJ대한통운");
        DeliveryDriver driver3 = deliveryDriverService.createDeliveryDriver(new DeliveryDriver("abcdef", "happyday11", "이영락", "010-1111-2222", "321-00-987654", BankType.SC제일은행), "LOGEN");
        DeliveryDriver driver4 = deliveryDriverService.createDeliveryDriver(new DeliveryDriver("marinelife", "test0728", "고지식", "010-5000-2626", "526-2232-1156-36", BankType.카카오뱅크), "LOGEN");
        deliveryDriverList.add(driver1);
        deliveryDriverList.add(driver2);
        deliveryDriverList.add(driver3);
        deliveryDriverList.add(driver4);
        logger.info("택배기사 생성");

        // 제품판매자 생성
        Provider provider1 = providerService.createProvider(new Provider("bestprovider", "mypassword", "구준표", "qwerty@gmail.com", "010-2222-4444", SectorType.Fishery));
        Provider provider2 = providerService.createProvider(new Provider("testprovider", "test", "김아랑", "zxcvby@gmail.com", "010-1111-7646", SectorType.Agriculture));
        providerList.add(provider1);
        providerList.add(provider2);
        logger.info("제품판매자 생성");
    }

    @AfterEach
    public void afterEach() {
        for(ShippingCompany company : shippingCompanyList)
            shippingCompanyService.deleteShippingCompanyFromId(company.getId());
        for(Provider provider : providerList)
            providerService.deleteProviderFromId(provider.getId());
        for(VOC voc : vocList)
            vocService.deleteVOCFromId(voc.getId());
        logger.info("DB Clear");
    }

    @Test
    @DisplayName("배상 시나리오 테스트:즉시수락")
    @Transactional
    public void compensation_scenario() throws Exception {
        // given(준비)


        // when(실행)
        // 1. 고객사에서 배상을 청구(기사님 귀책) -> VOC생성
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/voc"));

        // 2. 기사님이 APP에서 확인하시고, 본인의 귀책을 인정하고 사인을 함(이의제기x)

        // 3. 배상 금액만큼 배상 시스템에 포함되고, 그 금액만큼 기사님의 월급 차감

        // then(검증)
        response.andExpect(MockMvcResultMatchers.status().isOk());



    }

    @Test
    @DisplayName("VOC등록")
    public void VOC등록확인() throws Exception {
        // given
        Map<String, String> input = new HashMap<>();
        input.put("complainer_id", Long.toString(providerList.get(0).getId()));
        input.put("complainer_type", "Provider");
        input.put("defendant_id", Long.toString(deliveryDriverList.get(0).getId()));
        input.put("defendant_type", "DeliveryDriver");
        input.put("reason", "배송이 늦게 되어서 음식이 전부 상하였습니다");
        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/voc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)));
        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        List<VOC> remainVocList = vocService.getAllVOC();
        for(VOC voc: remainVocList)
            vocList.add(voc);
    }

    @Test
    @DisplayName("VOC 목록 API")
    public void voc목록API확인() throws Exception {
        // given
        VOC voc1 = vocService.createVOC(new VOC(providerList.get(0).getId(), ClientType.Provider,
                deliveryDriverList.get(0).getId(), ClientType.DeliveryDriver,
                "배송이 늦게 되어서 음식이 전부 상하였습니다"));
        VOC voc2 = vocService.createVOC(new VOC(providerList.get(0).getId(), ClientType.Provider,
                deliveryDriverList.get(1).getId(), ClientType.DeliveryDriver,
                "배송품이 파손이 되었습니다"));
        VOC voc3 = vocService.createVOC(new VOC(providerList.get(1).getId(), ClientType.Provider,
                deliveryDriverList.get(2).getId(), ClientType.DeliveryDriver,
                "배송물을 잘못된 장소로 보내었습니다"));
        VOC voc4 = vocService.createVOC(new VOC(deliveryDriverList.get(3).getId(), ClientType.DeliveryDriver,
                providerList.get(1).getId(), ClientType.Provider,
                "배송물에 누락이 있었습니다"));
        vocList.add(voc1);
        vocList.add(voc2);
        vocList.add(voc3);
        vocList.add(voc4);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/voc"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("배상정보등록")
    public void 배상정보등록확인() {

    }

    @Test
    @DisplayName("배상목록API")
    public void 배상목록API확인() throws Exception {
        // given
        VOC voc1 = vocService.createVOC(new VOC(providerList.get(0).getId(), ClientType.Provider,
                deliveryDriverList.get(0).getId(), ClientType.DeliveryDriver,
                "배송이 늦게 되어서 음식이 전부 상하였습니다"));
        VOC voc2 = vocService.createVOC(new VOC(deliveryDriverList.get(3).getId(), ClientType.DeliveryDriver,
                providerList.get(1).getId(), ClientType.Provider,
                "배송물에 누락이 있었습니다"));
        vocList.add(voc1);
        vocList.add(voc2);

        // 제품 가격이 50000이라 가정
        Compensation compensation1 = compensationService.createCompensation(
                new Compensation(), voc1.getId()); // 왕복배송비 5000
        Compensation compensation2 = compensationService.createCompensation(
                new Compensation(), voc2.getId()); // 제품 가격 50% 배상
        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/compensation"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("패널티확인등록")
    public void 패널티확인등록여부() {

    }

    @Test
    @DisplayName("배송기사패널티확인여부")
    public void 배송기사패널티확인여부등록확인() {

    }


}
