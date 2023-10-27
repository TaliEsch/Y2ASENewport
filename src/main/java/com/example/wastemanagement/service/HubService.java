package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Hub;
import com.example.wastemanagement.web.Form.HubForm;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface HubService {
    List<Hub> getClosestHubsUsingPostcode(String postcode);
    Pair<Double, Double> convertPostcodeToCoords(String postcode);
    Hub convertHubFormToHub(HubForm hubForm, Integer hubId);
    void saveHub(Hub hub);
    Hub getHubByHubId(int hubId);
    List<Hub> getHubsByR(String r);
    List<Hub> getHubs();
    List<Hub> getHubByCategoryAndPostcode(String query, String postcode);
    void deleteHub(Integer hub);
    HubForm getHubFormById(int hubId);
    ArrayList<String> getUnselectedCategoryList(String[] selectedCategories);
}
