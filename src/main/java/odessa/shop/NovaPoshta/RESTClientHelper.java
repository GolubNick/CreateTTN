package odessa.shop.NovaPoshta;

import java.net.URI;

import odessa.shop.LogHelper.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RESTClientHelper {

    public String getTTN(String body) {
        String json = "";
        HttpClient httpclient = HttpClients.createDefault();

        try {
//            URIBuilder builder = new URIBuilder("http://testapi.novaposhta.ua/v2.0/en/save_warehouse/json/");
            URIBuilder builder = new URIBuilder("http://api.novaposhta.ua/v2.0/json/");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");


            StringEntity reqEntity = new StringEntity(body, "utf-8");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                json = EntityUtils.toString(entity);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String ttn = "";
        if (json.contains("IntDocNumber")) {
            ttn = json.replaceAll(".*\\\"IntDocNumber\\\"\\:\\\"(\\d*)\\\".*", "$1");
        }
        else{
            Logger.get().setWriter(json);
        }
        return ttn;
    }
}
