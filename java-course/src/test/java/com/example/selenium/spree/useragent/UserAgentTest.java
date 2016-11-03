package com.example.selenium.spree.useragent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;

public class UserAgentTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testUserAgent() throws Exception {

        String uas = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";
        Response response = ClientBuilder
                .newClient()
                .target("http://www.useragentstring.com")
                .queryParam("getJSON", "all")
                .queryParam("uas", uas)
                .request()
                .get();

        String json = response.readEntity(String.class);
        UserAgentModel uam = objectMapper.readValue(json, UserAgentModel.class);

        assertEquals(uam.getAgentType(), "Browser");
        assertEquals(uam.getAgentName(), "Chrome");
        assertEquals(uam.getAgentVersion(), "36.0.1985.125");
        assertEquals(uam.getAgentLanguage(), null);
        assertEquals(uam.getAgentLanguageTag(), null);
        assertEquals(uam.getOsType(), "Windows");
        assertEquals(uam.getOsName(), "Windows 7");
        assertEquals(uam.getOsProducer(), null);
        assertEquals(uam.getOsProducerURL(), null);
        assertEquals(uam.getOsVersionName(), null);
        assertEquals(uam.getOsVersionNumber(), null);
        assertEquals(uam.getLinuxDistribution(), null);

    }
}
