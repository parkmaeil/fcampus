package kr.smhrd.javase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ChatGPT {

    public static void main(String[] args) {
        String apiKey = "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";
        String endpoint = "https://api.openai.com/v1/engines/gpt-3.5-turbo/completions";

        String sourceLanguage = "en"; // Source language code (e.g., "en" for English)
        String targetLanguage = "fr"; // Target language code (e.g., "fr" for French)

        String inputText = "Hello, how are you?"; // Input text to be translated

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(endpoint);

            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            // Prepare the API request
            String requestBody = "{\n" +
                    "    \"prompt\": \"" + inputText + "\",\n" +
                    "    \"temperature\": 0,\n" +
                    "    \"max_tokens\": 100,\n" +
                    "    \"stop\": \"\",\n" +
                    "    \"examples\": [],\n" +
                    "    \"source_language\": \"" + sourceLanguage + "\",\n" +
                    "    \"target_language\": \"" + targetLanguage + "\"\n" +
                    "}";

            httpPost.setEntity(new StringEntity(requestBody));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                // Parse the JSON response and extract the translated text
                // The translated text should be present in the 'choices' field of the response.
                // Handle any errors or additional processing as needed.
                System.out.println("Translation: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
