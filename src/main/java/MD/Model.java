package MD;

import Pizza.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {

    private static final String API_URL = "https://api-y7h2ehweua-od.a.run.app/";
    private static final String AUTH_KEY = "5HYu5Z8zxgqvTihE";
    private static final HttpClient client = HttpClient.newHttpClient();

    private static Model instance = null;

    private Model() {}

    // Méthode statique pour récupérer l'instance unique de Model (singleton)
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private static String getROOT(String ROOT) {
        String result = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + ROOT))
                    .header("Authorization", AUTH_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseCode = response.statusCode();

            if (responseCode == 200) {
                result = response.body();
            } else if (responseCode == 401) {
                result = "Unauthorized request. Invalid API key.";
            } else {
                result = "GET request not worked. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Exception: " + e.getMessage();
        }
        return result;
    }

    public static void deleteROOT(String DATA) {
        String result = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + "stock/" + DATA))
                    .header("Authorization", AUTH_KEY)
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseCode = response.statusCode();

            System.out.println(request.uri().toString());

            if (responseCode == 200) {
                result = response.body();
            } else if (responseCode == 401) {
                result = "Unauthorized request. Invalid API key.";
            } else if (responseCode == 404) {
                result = "Resource not found. Response Code: 404";
            } else {
                result = "DELETE request not worked. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Exception: " + e.getMessage();
        }
    }

    private static String postROOT(String DATA) {
        String result = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + "market/"+DATA+"/buy"))
                    .header("Authorization", AUTH_KEY)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            System.out.println(request.uri().toString());

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseCode = response.statusCode();

            if (responseCode == 200) {
                result = response.body();
            } else if (responseCode == 401) {
                result = "Unauthorized request. Invalid API key.";
            } else {
                result = "POST request not worked. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Exception: " + e.getMessage();
        }
        return result;
    }

    private static String postPIZZAA(String recipeId) {
        String result = "";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("recipeId", recipeId);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + "pizzas"))
                    .header("Authorization", AUTH_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
                    .build();

            System.out.println(request.uri().toString());

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseCode = response.statusCode();

            if (responseCode == 200) {
                result = response.body();
            } else if (responseCode == 401) {
                result = "{\"message\": \"Unauthorized request. Invalid API key.\"}";
            } else {
                result = "{\"message\": \"POST request not worked. Response Code: " + responseCode + "\"}";
                System.out.println(request.headers().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "{\"message\": \"Exception: " + e.getMessage() + "\"}";
        }
        return result;
    }

    public static List<Commande> getOrder() {

        String result = getROOT("orders");
        List<Commande> commandes = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            JSONArray jsonArray = new JSONArray(result);

            List<Recette> rr = getRecette();
            Market market = new Market(getMarket());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String recipe = jsonObject.getString("recipe");
                int price = jsonObject.getInt("price");
                String expiration = jsonObject.getString("expiration");

                for (Recette r : rr) {
                    if (r.getName().equals(recipe)) {
                        commandes.add(new Commande(id, r, price, expiration));
                    }
                }
            }
        }

        return commandes;
    }

    public static List<Recette> getRecette() {

        String result = getROOT("recipies");
        List<Recette> recettes = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                JSONArray ingredients = jsonObject.getJSONArray("ingredients");
                int preparationTime = jsonObject.getInt("preparationTime");

                recettes.add(new Recette(name, ingredients, preparationTime));
            }
        }
        return recettes;
    }

    public static void achatMarket(String ingredient) {
        System.out.println("----- ACHAT -----");
        String result = postROOT(ingredient);
        System.out.println(result);
    }

    public static ArrayList<Ingredient> getStock() {

        String stock = getROOT("stock");
        ArrayList<Ingredient> result = new ArrayList<>();
        if (stock != null && !stock.isEmpty()) {
            JSONArray stockJSON = new JSONArray(stock);

            for (int y = 0; y < stockJSON.length(); y++) {

                JSONObject jsonMARKET = stockJSON.getJSONObject(y);
                String id = jsonMARKET.get("id").toString();
                String name = jsonMARKET.getString("ingredient");
                String expiration = jsonMARKET.getString("expiration");
                String deliveryTime = jsonMARKET.getString("deliveryTime");
                String status = jsonMARKET.getString("status");

                result.add(new Ingredient(id, name, expiration, deliveryTime, status));
            }
        }
        return result;
    }

    public static HashMap<String, Integer> getMarket() {
        System.out.println("----- MARKET -----");

        String market = getROOT("market");
        HashMap<String, Integer> result = new HashMap<>();
        if (market != null && !market.isEmpty()) {
            JSONArray marketJSON = new JSONArray(market);

            for (int y = 0; y < marketJSON.length(); y++) {

                JSONObject jsonMARKET = marketJSON.getJSONObject(y);
                String id = jsonMARKET.getString("id");
                int price = jsonMARKET.getInt("price");
                result.put(id, price);

            }
        }
        return result;
    }

    private static final String BASE_URL = "https://api-y7h2ehweua-od.a.run.app";

    // Méthode générique pour effectuer une requête POST
    private static String sendPostRequest(String endpoint, String requestBody, String authToken) throws IOException, InterruptedException {
        String apiUrl = BASE_URL + endpoint;

        // Création du client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Création de la requête POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", authToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Envoi de la requête et traitement de la réponse
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Vérification du code de réponse
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return response.body(); // Retourne le corps de la réponse de l'API si tout est OK
        } else {
            return "{\"message\": \"POST request not worked. Response Code: " + response.statusCode() + "\"}";
        }
    }

    // Méthode pour fabriquer une pizza à partir d'une commande
    public static String fabriquerPizza(Commande commande) throws IOException, InterruptedException {
        System.out.println("----- FABRIQUE -----");

        // Préparation des données de la requête sous forme JSON
        String requestBody = "{ \"recipeId\": \"" + commande.getRecettes().getName() + "\" }";

        // Appel de la méthode générique pour POST
        String result = sendPostRequest("/pizzas", requestBody, AUTH_KEY);

        System.out.println("Response from POST /pizzas: " + result);

        // Traitement de la réponse
        if (result.equals("{\"message\": \"POST request not worked. Response Code: 422\"}")) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(result);
        String message = jsonObject.getString("message");

        if (jsonObject.has("pizza")) {
            JSONObject pizzaObject = jsonObject.getJSONObject("pizza");
            String pizzaId = pizzaObject.getString("id");
            System.out.println("Created Pizza ID: " + pizzaId);
            return pizzaId;
        } else {
            System.out.println("Error: " + message);
            return null; // or throw an exception as per your error handling strategy
        }
    }

}
