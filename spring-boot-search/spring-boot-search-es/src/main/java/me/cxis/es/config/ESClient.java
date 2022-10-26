package me.cxis.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Component
public class ESClient {

    private RestClient restClient;

    private ElasticsearchTransport transport;

    private ElasticsearchClient client;

    @PostConstruct
    private void init() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "12345678")
        );

        Path caCertificatePath = Paths.get(this.getClass().getResource("/es/http_ca.crt").toURI());
        CertificateFactory factory =
                CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        try (InputStream is = Files.newInputStream(caCertificatePath)) {
            trustedCa = factory.generateCertificate(is);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null);
        final SSLContext sslContext = sslContextBuilder.build();

        // Create the low-level client
        restClient = RestClient.builder(new HttpHost("localhost", 9200, "https"))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)
                        .setSSLContext(sslContext))
                .build();

        // Create the transport with a Jackson mapper
        transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // Create the API client
        client = new ElasticsearchClient(transport);
    }

    public ElasticsearchClient getClient() {
        return client;
    }

    public boolean indexCreate(String index, String mapping) {
        try {
            CreateIndexResponse response = client.indices().create(
                    c -> c.index(index)
                            .withJson(new ByteArrayInputStream(mapping.getBytes()))
            );
            return response.acknowledged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean indexExists(String index) {
        try {
            BooleanResponse response = client.indices().exists(e -> e.index(index));
            return response.value();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
