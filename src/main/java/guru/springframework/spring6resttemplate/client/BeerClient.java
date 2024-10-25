package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {



    Page<BeerDTO> pageBeers(String beerName, String beerStyle,Integer quantityOnHand, Integer pageNumber, Integer pageSize);

    BeerDTO getBeerById(UUID id);

    BeerDTO createBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(BeerDTO beerDTO);

    void deleteBeerById(UUID id);

}
