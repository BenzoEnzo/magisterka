package pl.benzo.enzo.magisterka.basejava.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.magisterka.basejava.model.DeregisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.HeartbeatRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterResponse;

@Service
public class RegistryService {

    @Value("${discovery.server.address}")
    private String discoveryServerAddress;

    private final RestTemplate restTemplate;

    public RegistryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RegisterResponse registerService(RegisterRequest registerRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<RegisterResponse> response = restTemplate.postForEntity(
                discoveryServerAddress + "/register",
                request,
                RegisterResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new Exception("Failed to register service");
        }
    }

    public void sendHeartbeat(HeartbeatRequest heartbeatRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HeartbeatRequest> request = new HttpEntity<>(heartbeatRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                discoveryServerAddress + "/heartbeat",
                request,
                String.class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Failed to send heartbeat");
        }
    }

    public void deregisterService(DeregisterRequest deregisterRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DeregisterRequest> request = new HttpEntity<>(deregisterRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                discoveryServerAddress + "/deregister",
                request,
                String.class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Failed to deregister service");
        }
    }
}