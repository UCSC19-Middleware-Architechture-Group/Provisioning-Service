package com.sritel.provisioning.config;

import com.sritel.provisioning.client.AccountClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final DiscoveryClient discoveryClient;
    private final ObservationRegistry observationRegistry;

    @Bean
    public AccountClient accountClient() {
        String accountServiceUrl = getAccountServiceUrl();

        RestClient restClient = RestClient.builder()
                .baseUrl(accountServiceUrl)
                .requestFactory(getClientRequestFactory())
                .observationRegistry(observationRegistry)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(AccountClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(30))
                .withReadTimeout(Duration.ofSeconds(30));

        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }

    private String getAccountServiceUrl() {
        List<ServiceInstance> instances = discoveryClient.getInstances("account-service");
        if (instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            return serviceInstance.getUri().toString();
        } else {
            throw new IllegalStateException("No instances of service: account-service available");
        }
    }
}
