package com.example.wastemanagement.unit.service;

import com.example.wastemanagement.data.CategoryRepositoryJpa;
import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.domain.Hub;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.HubServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DataJpaTest
class GetHubs {
    private final HubRepositoryJpa hubRepositoryJpa;
    private final CategoryRepositoryJpa categoryRepositoryJpa;

    @Autowired
    public GetHubs(HubRepositoryJpa hubRepositoryJpa, CategoryRepositoryJpa categoryRepositoryJpa) {
        this.hubRepositoryJpa = hubRepositoryJpa;
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    // Testing the useService, returns the correct number of uses for a category
    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldGetHubClosestToPostcode(){
        // given
        // empty hubs in database
//        hubRepositoryJpa.deleteAll();
        String categoriesList = "plastic,metal,wood";
        Hub hub = Hub.builder().hubId(null).hubName("Hub1").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub1.com").address("address1").postcode("postcode")
                .latitude(1.0).longitude(1.0).phoneNumber("01164960780").build();

        Hub hub2 = Hub.builder().hubId(null).hubName("Hub2").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub2.com").address("address2").postcode("postcode")
                .latitude(2.0).longitude(2.0).phoneNumber("01154960941").build();

        Hub hub3 = Hub.builder().hubId(null).hubName("Hub3").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub3.com").address("address3").postcode("postcode")
                .latitude(3.0).longitude(3.0).phoneNumber("01184960423").build();

        Integer length = hubRepositoryJpa.findAll().size();

        hubRepositoryJpa.save(hub);
        hubRepositoryJpa.save(hub2);
        hubRepositoryJpa.save(hub3);

        HubServiceImpl hubService = new HubServiceImpl(hubRepositoryJpa, categoryRepositoryJpa);
        // when
        List<Hub> hubs = hubService.getClosestHubsUsingPostcode("NP19 4RD");
        // then
        Assertions.assertThat(hubs.get(length)).usingRecursiveComparison().isEqualTo(hub3);
        Assertions.assertThat(hubs.get(length+1)).usingRecursiveComparison().isEqualTo(hub2);
        Assertions.assertThat(hubs.get(length+2)).usingRecursiveComparison().isEqualTo(hub);

    }

    @Test
    public void shouldGetHubClosestToTownWhenNothingIsInputted(){
        // given
        // empty hubs in database
//        hubRepositoryJpa.deleteAll();
        String categoriesList = "plastic,metal,wood";
        Hub hub = Hub.builder().hubId(null).hubName("Hub1").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub1.com").address("address1").postcode("postcode")
                .latitude(50.0).longitude(1.0).phoneNumber("01164960780").build();

        Hub hub2 = Hub.builder().hubId(null).hubName("Hub2").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub2.com").address("address2").postcode("postcode")
                .latitude(55.0).longitude(-1.0).phoneNumber("01154960941").build();

        Hub hub3 = Hub.builder().hubId(null).hubName("Hub3").whichR("rerecyclingcyling").categoriesList(categoriesList)
                .link("www.hub3.com").address("address3").postcode("postcode")
                .latitude(48.0).longitude(3.0).phoneNumber("01184960423").build();

        Integer length = hubRepositoryJpa.findAll().size();

        hubRepositoryJpa.save(hub);
        hubRepositoryJpa.save(hub2);
        hubRepositoryJpa.save(hub3);

        HubServiceImpl hubService = new HubServiceImpl(hubRepositoryJpa, categoryRepositoryJpa);
        // when
        // 51.586399, -2.992470 = town of Newport
        List<Hub> hubs = hubService.getClosestHubsUsingPostcode("");
        // then
        Assertions.assertThat(hubs.get(length)).usingRecursiveComparison().isEqualTo(hub2);
        Assertions.assertThat(hubs.get(length+1)).usingRecursiveComparison().isEqualTo(hub);
        Assertions.assertThat(hubs.get(length+2)).usingRecursiveComparison().isEqualTo(hub3);

        // when
        // 51.586399, -2.992470 = town of Newport
        List<Hub> hubs1 = hubService.getClosestHubsUsingPostcode(" ");
        // then
        Assertions.assertThat(hubs1.get(length)).usingRecursiveComparison().isEqualTo(hub2);
        Assertions.assertThat(hubs1.get(length+1)).usingRecursiveComparison().isEqualTo(hub);
        Assertions.assertThat(hubs1.get(length+2)).usingRecursiveComparison().isEqualTo(hub3);

        // when
        // 51.586399, -2.992470 = town of Newport
        List<Hub> hubs2 = hubService.getClosestHubsUsingPostcode(null);
        // then
        Assertions.assertThat(hubs2.get(length)).usingRecursiveComparison().isEqualTo(hub2);
        Assertions.assertThat(hubs2.get(length+1)).usingRecursiveComparison().isEqualTo(hub);
        Assertions.assertThat(hubs2.get(length+2)).usingRecursiveComparison().isEqualTo(hub3);
    }
}