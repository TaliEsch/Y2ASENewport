package com.example.wastemanagement.unit.repositories;

import com.example.wastemanagement.data.RepairRepositoryJpa;
import com.example.wastemanagement.domain.Repair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SaveAndGetBooking {

    @Autowired
    private RepairRepositoryJpa repairRepositoryJpa;

    @Test
    public void ShouldUpdateDatabaseWhenBookingIsMade(){
        //Given
        repairRepositoryJpa.deleteAll();
        Repair book1 = Repair.builder().bookingId(null).itemName("Tv")
                .itemDescription("The screen is glitching out").hubId(5).fullName("James Jones")
                .email("JamesJones@gmail.com").address("1 high street,newport").build();

        Repair book2 = Repair.builder().bookingId(null).itemName("Phone")
                .itemDescription("The screen is glitching out").hubId(5).fullName("Kate Jones")
                .email("KateJones@gmail.com").address("2 high street,newport").build();

        Repair book3 = Repair.builder().bookingId(null).itemName("laptop")
                .itemDescription("The screen is glitching out").hubId(5).fullName("Carl Jones")
                .email("CarlJones@gmail.com").address("3 high street,newport").build();

        //When
        repairRepositoryJpa.saveAll(List.of(book1, book2, book3));

        var bookings = repairRepositoryJpa.findAll();

        //Then
        Assertions.assertThat(bookings.get(0)).usingRecursiveComparison().isEqualTo(book1);
        Assertions.assertThat(bookings.get(1)).usingRecursiveComparison().isEqualTo(book2);
        Assertions.assertThat(bookings.get(2)).usingRecursiveComparison().isEqualTo(book3);

        assertEquals(3, bookings.size());
    }
}
