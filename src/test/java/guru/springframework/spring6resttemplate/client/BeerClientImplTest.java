package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void getBeers() {
        beerClient.pageBeers(null, null,null, null, null);
    }

    @Test
    void getBeersALE() {
        beerClient.pageBeers("ALE",null, null,null, null);
    }

    @Test
    void getBeerStylePorter() {
        beerClient.pageBeers(null,"PORTER",null, null, null);
    }

    @Test
    void getQuantityOnHandShow() {
        beerClient.pageBeers(null,null,1, null, null);
    }

    @Test
    void getBeerById() {
        beerClient.getBeerById(UUID.fromString("45c714e2-6ebe-452d-864a-209968078b04"));
    }

    @Test
    void createBeer() {
        BeerDTO newBeerDTO = BeerDTO.builder()
                .beerName("Joseba´s Ale")
                .beerStyle(BeerStyle.IPA)
                .price(BigDecimal.valueOf(150.5))
                .quantityOnHand(200)
                .upc("Upc")
                .build();
        BeerDTO createdDTO = beerClient.createBeer(newBeerDTO);
        assertNotNull(createdDTO);
    }

    @Test
    void updateBeer() {
        BeerDTO newBeerDTO = BeerDTO.builder()
                .beerName("Joseba´s Ale")
                .beerStyle(BeerStyle.IPA)
                .price(BigDecimal.valueOf(150.5))
                .quantityOnHand(200)
                .upc("Upc")
                .build();
        BeerDTO createdDTO = beerClient.createBeer(newBeerDTO);
        //Now, we change some value
        createdDTO.setBeerStyle(BeerStyle.ALE);
        BeerDTO updateDTO = beerClient.updateBeer(createdDTO);

        assertNotEquals(updateDTO,createdDTO);
    }

    @Test
    void deleteBeer() {
        BeerDTO newBeerDTO = BeerDTO.builder()
                .beerName("Joseba´s Ale")
                .beerStyle(BeerStyle.IPA)
                .price(BigDecimal.valueOf(150.5))
                .quantityOnHand(200)
                .upc("Upc")
                .build();
        BeerDTO createdDTO = beerClient.createBeer(newBeerDTO);

        beerClient.deleteBeerById(createdDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> beerClient.getBeerById(createdDTO.getId()));


    }



}