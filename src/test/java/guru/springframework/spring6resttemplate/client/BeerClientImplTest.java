package guru.springframework.spring6resttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}