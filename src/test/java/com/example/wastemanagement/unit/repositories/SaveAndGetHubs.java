package com.example.wastemanagement.unit.repositories;

import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.domain.Hub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

//@SpringBootTest
@DataJpaTest
public class SaveAndGetHubs {

    private final HubRepositoryJpa hubRepositoryJpa;

    @Autowired
    public SaveAndGetHubs(HubRepositoryJpa hubRepositoryJpa) {
        this.hubRepositoryJpa = hubRepositoryJpa;
    }

    @Test
    public void findHubsOrderedByClosestLocationTest(){
        //Given
//        hubRepositoryJpa.deleteAll();
        String categoriesList = "plastic,metal,wood";

        Hub hub1 = Hub.builder().hubId(null).hubName("Hub1").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub1.com").address("address1").postcode("postcode")
                .latitude(1.0).longitude(1.0).phoneNumber("01164960780").build();

        Hub hub2 = Hub.builder().hubId(null).hubName("Hub2").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub2.com").address("address2").postcode("postcode")
                .latitude(2.0).longitude(2.0).phoneNumber("01154960941").build();

        Hub hub3 = Hub.builder().hubId(null).hubName("Hub3").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub3.com").address("address3").postcode("postcode")
                .latitude(3.0).longitude(3.0).phoneNumber("01184960423").build();

        hubRepositoryJpa.saveAll(List.of(hub1, hub2, hub3));
        //When
        List<Hub> hubs = hubRepositoryJpa.findHubsOrderedByClosestLocation(1.0, 1.0);
        //Then

        Assertions.assertThat(hubs.get(0)).usingRecursiveComparison().isEqualTo(hub1);
        Assertions.assertThat(hubs.get(1)).usingRecursiveComparison().isEqualTo(hub2);
        Assertions.assertThat(hubs.get(2)).usingRecursiveComparison().isEqualTo(hub3);

        System.out.println("hub size :" +hubs.size());
    }

    @Test
    public void findHubsOrderWhenTwoHubsAreEqualInDistance(){
        //Given
//        hubRepositoryJpa.deleteAll();
        String categoriesList = "plastic,metal,wood";
        Hub hub1 = Hub.builder().hubId(1).hubName("Hub1").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub1.com").address("address1").postcode("postcode")
                .latitude(1.0).longitude(1.0).phoneNumber("01164960780").build();

        Hub hub2 = Hub.builder().hubId(2).hubName("Hub2").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub2.com").address("address2").postcode("postcode")
                .latitude(2.0).longitude(2.0).phoneNumber("01154960941").build();

        Hub hub3 = Hub.builder().hubId(3).hubName("Hub3").whichR("recycling").categoriesList(categoriesList)
                .link("www.hub3.com").address("address3").postcode("postcode")
                .latitude(3.0).longitude(3.0).phoneNumber("01184960423").build();

        hubRepositoryJpa.save(hub1);
        hubRepositoryJpa.save(hub2);
        hubRepositoryJpa.save(hub3);

        //When
        List<Hub> hubs = hubRepositoryJpa.findHubsOrderedByClosestLocation(1.5, 1.5);

        //Then
        assertNotEquals(hubs.get(0).getHubName(), hubs.get(1).getHubName());
        Assertions.assertThat(hubs.get(0).getHubName()).isIn("Hub1", "Hub2");
        Assertions.assertThat(hubs.get(1).getHubName()).isIn("Hub1", "Hub2");
        Assertions.assertThat(hubs.get(2).getHubName()).isEqualTo("Hub3");
    }
}
