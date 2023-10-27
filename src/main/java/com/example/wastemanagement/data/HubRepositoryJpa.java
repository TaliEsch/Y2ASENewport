package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepositoryJpa extends JpaRepository<Hub, Integer> {
    // Gets by the closest hub to the user
    @Query(value = "SELECT * FROM hub h"
            +" ORDER BY sqrt(Power(ABS( h.latitude - ?1 ), 2)+ Power(ABS( h.longitude - ?2 ),2))",
            nativeQuery = true)
    List<Hub> findHubsOrderedByClosestLocation(double lat, double lng);
    // To test NP19 4RD, should give Tradebe..,Wastersaver recycling, and Wastersaver recyclin
    // ,Wastesaver reuse and newport household waste recycling centre

    List<Hub> findHubsByWhichR(String r);

    Hub findHubByHubId(int hubId);

    // Did it this ways because the database is quicker at searching thing than java,
    // although this is technically repeating code.
    @Query(value = "SELECT * FROM hub h WHERE h.categories_List LIKE %:query% " +
            " ORDER BY sqrt(Power(ABS( h.latitude - :lat ), 2)+ Power(ABS( h.longitude - :lng ),2))",
            nativeQuery = true)
    List<Hub> findHubsByCategoryAndPostcode(@Param("query")String query, @Param("lat") Double lat, @Param("lng") Double lng);

    @Query(value = "SELECT Hub_Name FROM hub h",
            nativeQuery = true)
    List<String> findAllHubs();

    // finds all unqiue whichR's
    @Query(value = "SELECT DISTINCT which_R FROM hub h",
            nativeQuery = true)
    List<String> findAllUniqueWhichR();
}
