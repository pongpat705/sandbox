package org.sandbox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.sandbox.model.CharactorLocationModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.callingAPI();

    }

    public void callingAPI() {

        String url = "https://rickandmortyapi.com/api/character/161";

        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);

            CharactorLocationModel response = httpclient.execute(httpget, new ResponseHandler<CharactorLocationModel>() {
                @Override
                public CharactorLocationModel handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    InputStream ips = httpResponse.getEntity().getContent();
                    InputStreamReader ipsR = new InputStreamReader(ips);
                    BufferedReader br = new BufferedReader(ipsR);
                    StringBuilder response = new StringBuilder();
                    response.append(br.lines().collect(Collectors.joining()));

                    System.out.println("responseBody = "+response);

                    ObjectMapper mapper = new ObjectMapper();

                    CharactorLocationModel r = mapper.readValue(response.toString(), new TypeReference<CharactorLocationModel>() {});

                    return r;
                }
            });

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
