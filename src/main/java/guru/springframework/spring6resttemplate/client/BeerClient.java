package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

public interface BeerClient {

    Page<BeerDTO> pageBeers(String beerName, String beerStyle,Integer quantityOnHand, Integer pageNumber, Integer pageSize);

}
