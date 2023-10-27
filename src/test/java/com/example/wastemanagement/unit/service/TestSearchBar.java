package com.example.wastemanagement.unit.service;

import com.example.wastemanagement.data.BuyRentRepositoryJPA;
import com.example.wastemanagement.domain.buyRent;
import com.example.wastemanagement.service.BuyRentServiceImpl;
import com.example.wastemanagement.service.Dto.buyRentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@SpringBootTest
public class TestSearchBar {
    private final BuyRentRepositoryJPA buyrentrepo;
    private final JavaMailSender javaMailSender;

    @Autowired
    public TestSearchBar(BuyRentRepositoryJPA buyrentrepo, JavaMailSender javaMailSender) {
        this.buyrentrepo = buyrentrepo;
        this.javaMailSender = javaMailSender;
    }

    @Test
    public void testSearchBarWithBasicQuery01() {
        // This test was written with pair programming: Driver: OliverFarmer Navigator: PushpaDebnath
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.save(buyRent1);
        buyrentrepo.save(buyRent2);
        buyrentrepo.save(buyRent3);
        // Given I search for a sofa
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Sofa", "Category Filters",
                "Listing Filters", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        // I should receive a buyRent object with the itemName Sofa
        Assertions.assertThat(buyRentDtoName).isEqualTo("Sofa");
    }

    @Test
    public void testSearchBarWithBasicQuery02() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.save(buyRent1);
        buyrentrepo.save(buyRent2);
        buyrentrepo.save(buyRent3);
        // Given I search for a seat
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Seat", "Category Filters",
                "Listing Filters", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        // I should receive a buyRent object with the itemName Seat
        Assertions.assertThat(buyRentDtoName).isEqualTo("Seat");
    }

    @Test
    public void testSearchBarWithQueryAndCategory() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Chairs", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Furniture", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search for a Chair, with the filter Homeware
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Chair", "Homeware",
                "Listing Filters", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        // I should receive a buyRent object with the name Chair
        Assertions.assertThat(buyRentDtoName).isEqualTo("Chair");
    }

    @Test
    public void testSearchBarWithSaleType() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Seat Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search for a seat with the filter To Sell
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Seat", "Category Filters",
                "To Sell", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        // I should only be returned that seat
        Assertions.assertThat(buyRentDtoName).isEqualTo("Seat");
    }

    @Test
    public void testSearchBarWithQueryCategorySaleType() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Chaire", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search for a Chair with the filter To Buy
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Chair", "Homeware",
                "To Buy", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        // I should receive a Chair
        Assertions.assertThat(buyRentDtoName).isEqualTo("Chair");
    }

    @Test
    public void testSearchBarWithPriceFilterAsc() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Sofa", "Johhny", "jer@email.com",
                "used, needs repair","20.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Sofa", "Derrick", "jer@email.com",
                "used, needs repair","30.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search for a Chair, with the filter Price Ascending
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Sofa", "Category Filters",
                "Listing Filters", "Price Ascending");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var firstBuyRentDtoName = firstSearchResult.getUsername();
        var secondSearchResult = searchOutput.get(1);
        var secondBuyRentDtoName = secondSearchResult.getUsername();
        var thirdSearchResult = searchOutput.get(2);
        var thirdBuyRentDtoName = thirdSearchResult.getUsername();
        // I should recieve all objects with the itemName Chair in the order of Price Ascending
        Assertions.assertThat(firstBuyRentDtoName).isEqualTo("Jeremy");
        Assertions.assertThat(secondBuyRentDtoName).isEqualTo("Johhny");
        Assertions.assertThat(thirdBuyRentDtoName).isEqualTo("Derrick");
    }

    @Test
    public void testSearchBarWithPriceFilterDesc() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Sofa", "Johhny", "jer@email.com",
                "used, needs repair","20.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Sofa", "Derrick", "jer@email.com",
                "used, needs repair","30.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search for a Sofa with the price filter Price Descending
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("Sofa", "Category Filters",
                "Listing Filters", "Price Descending");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var firstBuyRentDtoName = firstSearchResult.getUsername();
        var secondSearchResult = searchOutput.get(1);
        var secondBuyRentDtoName = secondSearchResult.getUsername();
        var thirdSearchResult = searchOutput.get(2);
        var thirdBuyRentDtoName = thirdSearchResult.getUsername();
        // I should receive all objects with the itemName Sofa, in the order of price highest to lowest
        Assertions.assertThat(firstBuyRentDtoName).isEqualTo("Derrick");
        Assertions.assertThat(secondBuyRentDtoName).isEqualTo("Johhny");
        Assertions.assertThat(thirdBuyRentDtoName).isEqualTo("Jeremy");
    }

    @Test
    public void testPartialSearch01() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search with an incomplete search (so)
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("So", "Category Filters",
                "To Sell", "Price Filters");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var firstBuyRentDtoName = firstSearchResult.getItemName();
        // I should receive any + all results with "So" in the itemName
        Assertions.assertThat(firstBuyRentDtoName).isEqualTo("Sofa");
        Assertions.assertThat(searchOutput.size()).isEqualTo(1);
    }

    @Test
    public void testPartialSearch02() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "1", null);
        buyRent buyRent2 = new buyRent(null, "Chair", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Seat", "Jeremy", "jer@email.com",
                "used, needs repair","15.00", "Homeware", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3));
        // Given I search with an incomplete search (S)
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("S", "Category Filters",
                "To Sell", "Price Ascending");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var buyRentDtoName = firstSearchResult.getItemName();
        var secondSearchResult = searchOutput.get(1);
        var secondBuyRentDtoName = secondSearchResult.getItemName();
        // I should receive any + all results with "S" in the itemName
        Assertions.assertThat(buyRentDtoName).isEqualTo("Sofa");
        Assertions.assertThat(secondBuyRentDtoName).isEqualTo("Seat");
        Assertions.assertThat(searchOutput.size()).isEqualTo(2);
    }

    @Test
    public void testFullSearchCaseWithPartial() {
        buyrentrepo.deleteAll();
        BuyRentServiceImpl buyRentServiceImpl = new BuyRentServiceImpl(buyrentrepo, javaMailSender);

        buyRent buyRent1 = new buyRent(null, "Sofa", "Jeremy", "jer@email.com",
                "used, needs repair","10.00", "Furniture", "1", null);
        buyRent buyRent2 = new buyRent(null, "Safari Suit", "Johhny", "jer@email.com",
                "used, needs repair","20.00", "Homeware", "0", null);
        buyRent buyRent3 = new buyRent(null, "Sofa", "Derrick", "jer@email.com",
                "used, needs repair","30.00", "Furniture", "1", null);
        buyRent buyRent4 = new buyRent(null, "Settee", "Dave", "jer@email.com",
                "used, needs repair","40.00", "Homeware", "1", null);
        buyRent buyRent5 = new buyRent(null, "Sofa", "Terry", "jer@email.com",
                "used, needs repair","50.00", "Furniture", "1", null);

        buyrentrepo.saveAll(List.of(buyRent1, buyRent2, buyRent3, buyRent4, buyRent5));
        // Given I search with an incomplete search (S) along with all filters
        List<buyRentDto> searchOutput = buyRentServiceImpl.getSortedBuyRent("S", "Furniture",
                "To Sell", "Price Descending");
        // When the search result is returned
        var firstSearchResult = searchOutput.get(0);
        var firstBuyRentDtoName = firstSearchResult.getUsername();
        var secondSearchResult = searchOutput.get(1);
        var secondBuyRentDtoName = secondSearchResult.getUsername();
        var thirdSearchResult = searchOutput.get(2);
        var thirdBuyRentDtoName = thirdSearchResult.getUsername();
        // I should only receive objects with fields meeting the query + should receive ALL meeting that query
        Assertions.assertThat(firstBuyRentDtoName).isEqualTo("Terry");
        Assertions.assertThat(secondBuyRentDtoName).isEqualTo("Derrick");
        Assertions.assertThat(thirdBuyRentDtoName).isEqualTo("Jeremy");
        Assertions.assertThat(searchOutput.size()).isEqualTo(3);
    }

}
