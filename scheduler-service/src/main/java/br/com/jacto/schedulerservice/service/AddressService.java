package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.configuration.SchedulerServiceConfiguration;
import br.com.jacto.schedulerservice.exceptions.SchedulerException;
import br.com.jacto.schedulerservice.exceptions.ThirdPartyIntegrationException;
import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.ViaCepResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Slf4j
@Service
public class AddressService {

    @Autowired
    SchedulerServiceConfiguration schedulerServiceConfiguration;

    RestTemplate restTemplate;

    @Autowired
    public AddressService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * Return pre-filled AddressModel based on postalCode
     * @param addressModel - AddressModel to be updated based on PostalCode
     * @return AddressModel
     */
    public AddressModel getAddressModel(AddressModel addressModel) throws SchedulerException {
        String url = schedulerServiceConfiguration.getPostalCodeWsUrl().replace("{postalCode}", String.valueOf(addressModel.getPostalCode()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            ResponseEntity<ViaCepResponseModel> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<ViaCepResponseModel>() {});
            if (response.getStatusCode() == HttpStatusCode.valueOf(200)
                    && response.getBody() != null) {
                ViaCepResponseModel model = response.getBody();
                addressModel.setStreet(model.getLogradouro());
                addressModel.setDistrict(model.getBairro());
                addressModel.setCity(model.getLocalidade());
                addressModel.setState(model.getUf());
                return addressModel;
            }
        } catch (Exception e) {
            log.error("Error while fetching address using postal code {}", e.getMessage());
        }
        throw new ThirdPartyIntegrationException();
    }
}
