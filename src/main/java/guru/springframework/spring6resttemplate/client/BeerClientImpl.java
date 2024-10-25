package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private static final String BEER_PATH = "/api/v1/beer";

    private static final String BEER_PATH_UPDATE = "/api/v1/beer/{id}";

    @Override
    public Page<BeerDTO> pageBeers(String beerName,String beerStyle,Integer quantityOnHand, Integer pageNumber, Integer pageSize) {

        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL+ BEER_PATH, String.class);
//
//
//        ResponseEntity<Map> responseEntityMap = restTemplate.getForEntity(BASE_URL + BEER_PATH, Map.class);
//
//        ResponseEntity<JsonNode> responseEntityJson = restTemplate.getForEntity(BASE_URL+BEER_PATH, JsonNode.class);
//
//
//        System.out.println(responseEntityMap.getBody());
//
//        responseEntityJson.getBody().findPath("content").elements().forEachRemaining(jsonNode -> System.out.println(jsonNode.get("beerName").asText()));



        //For query
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(BEER_PATH);

        if (beerName != null) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if (beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }
        if (quantityOnHand != null) {
            uriComponentsBuilder.queryParam("quantityOnHand", quantityOnHand);
        }
        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);
        return null;
    }

    public BeerDTO getBeerById(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(BEER_PATH);

        ResponseEntity<BeerDTO> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString()+"/{id}", BeerDTO.class, id);

        return responseEntity.getBody();


    }

    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(BEER_PATH);

        //ResponseEntity<BeerDTO> responseEntity = restTemplate.postForEntity(uriComponentsBuilder.toUriString(), beerDTO, BeerDTO.class);


        URI uri = restTemplate.postForLocation(uriComponentsBuilder.toUriString(), beerDTO);

        ResponseEntity<BeerDTO> createdDTO = restTemplate.getForEntity(uri.getPath(), BeerDTO.class);
        return createdDTO.getBody();
    }

    @Override
    public BeerDTO updateBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        restTemplate.put(BEER_PATH_UPDATE, beerDTO, beerDTO.getId());

        return getBeerById(beerDTO.getId());

    }

    @Override
    public void deleteBeerById(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(BEER_PATH_UPDATE, id);
    }
}
