package com.example.wastemanagement.service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.example.wastemanagement.data.CategoryRepositoryJpa;
import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.domain.Category;
import com.example.wastemanagement.data.RepairRepositoryJpa;
import com.example.wastemanagement.domain.Hub;
import com.example.wastemanagement.web.Form.HubForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Service
public class HubServiceImpl implements HubService {
    private final static String key = "2a84b10eeb0e4a1fb2762de156c8ba9d";
    private final JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(key);
    private final CategoryRepositoryJpa categoryRepositoryJpa;

    private final HubRepositoryJpa hubRepositoryJpa;

    @Autowired
    private RepairRepositoryJpa repairRepositoryJpa;

    @Autowired
    public HubServiceImpl(HubRepositoryJpa hubRepositoryJpa, CategoryRepositoryJpa categoryRepositoryJpa){
        this.hubRepositoryJpa = hubRepositoryJpa;
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    // Converts postcode to latitude and longitude
    public Pair<Double, Double> convertPostcodeToCoords(String postcode){
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(postcode);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        // gets the latitude of the postcode
        double lat = response.getFirstPosition().getLat();
        // gets the longitude of the postcode
        double lng = response.getFirstPosition().getLng();

        // returns a tuple of the latitude and longitude
        return Pair.of(lat, lng);
    }

    @Override
    public List<Hub> getClosestHubsUsingPostcode(String postcode) {
        double lat = 51.586399;
        double lng = -2.992470;

        // In real live application the JOpenCageGeocoder should be a Singleton
        try {
            // stream the lat and lng coordinats from convertPostcodeToCoords
            // into double variable lat and lng
            lat = convertPostcodeToCoords(postcode).getFirst();
            lng = convertPostcodeToCoords(postcode).getSecond();

        } catch (Exception e) {
            System.out.println("Postcode not found" + postcode);
        }
        // default coords of newport town centre
        var hubs = hubRepositoryJpa.findHubsOrderedByClosestLocation(lat, lng);
        return hubs;
    }

    @Override
    public Hub convertHubFormToHub(HubForm hubForm, Integer hubId) {

        System.out.println("HubForm: " + hubForm);
        Hub hub = new Hub();
        hub.setHubId(hubId);
        hub.setHubName(hubForm.getHubName());
        hub.setWhichR(hubForm.getWhichR());
        hub.setCategoriesList(String.join(",",hubForm.getCategoriesList()));
        hub.setLink(hubForm.getLink());
        hub.setAddress(hubForm.getAddress());
        hub.setPostcode(hubForm.getPostcode());
        hub.setPhoneNumber(hubForm.getPhoneNumber());
        String postcode  = hubForm.getPostcode();

        // Convert the postcode to latitude and longitude
        double lat = convertPostcodeToCoords(postcode).getFirst();
        double lng = convertPostcodeToCoords(postcode).getSecond();

        hub.setLatitude(lat);
        hub.setLongitude(lng);
        System.out.println("Hub: " + hub);
        return hub;
    }

    @Override
    public void saveHub(Hub hub) {
        hubRepositoryJpa.save(hub);
    }

    @Override
    public Hub getHubByHubId(int hubId) {
        return hubRepositoryJpa.findHubByHubId(hubId);
    }

    @Override
    public List<Hub> getHubsByR(String r) {
        return hubRepositoryJpa.findHubsByWhichR(r);
    }

    @Override
    public List<Hub> getHubs() {
        return hubRepositoryJpa.findAll();
    }

    @Override
    public List<Hub> getHubByCategoryAndPostcode(String query, String postcode) {
        // Getting hubs by category and postcode
        Double lat = 51.586399;
        Double lng = -2.992470;
        try {
            lat = convertPostcodeToCoords(postcode).getFirst();
            lng = convertPostcodeToCoords(postcode).getSecond();
        } catch (Exception e) {
            System.out.println("Postcode not found" + postcode);
        }

        List<Hub> hubsByCategoryAndPostcode = hubRepositoryJpa.findHubsByCategoryAndPostcode(query, lat, lng);

        return hubsByCategoryAndPostcode;
    }

    @Override
    public void deleteHub(Integer hub) {
        hubRepositoryJpa.deleteById(hub);
    }

    @Override
    public HubForm getHubFormById(int hubId){
        // Gets the current hub
        Hub hub = getHubByHubId(hubId);
        /* Converts the hub's categories into a string array, it removes any spaces using replaceAll, and then splits the
        * string collected into an array using split(",") */
        String[] selectedCategory = (hub.getCategoriesList()).replaceAll(" ", "").split(",");
        // Calls getUnselectedCategoryList to create the FullCategoryStringList
        var FullCategoryStringList = getUnselectedCategoryList(selectedCategory);
        // Returns the new, completed hubForm
        return new HubForm(
                hub.getHubId(),
                hub.getHubName(),
                hub.getLink(),
                hub.getWhichR(),
                selectedCategory,
                FullCategoryStringList,
                hub.getAddress(),
                hub.getPostcode(),
                hub.getPhoneNumber());
    }

    @Override
    public ArrayList<String> getUnselectedCategoryList(String[] selectedCategories){
        // Converts to array list
        ArrayList<String> selectedCategoryList = new ArrayList<>(Arrays.asList(selectedCategories));
        // Returns all the Category objects from the repository as a List
        var FullCategoryList = categoryRepositoryJpa.findAll();
        // Converts the category list collected above streams it/ converts it to an ArrayList, using the Category's name
        ArrayList<String> FullCategoryStringList = FullCategoryList.stream().map(Category::getCategoryName).collect(Collectors.toCollection(ArrayList::new));
        // Removes any matching items resulting from comparing the two ArrayLists from FullCategoryStringList
        FullCategoryStringList.removeAll(selectedCategoryList);

        return FullCategoryStringList;
    }
}
