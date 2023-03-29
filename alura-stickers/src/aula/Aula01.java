package aula;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import util.JsonParse;

public class Aula01 {
    public static void main(String[] args) throws Exception {
        /* Fazer conexão HTTP e buscar os top filmes */
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        /* Extrair os dados que interessam */
        JsonParse parser = new JsonParse();
        List<Map<String, String>> listaFilmes = parser.parse(body);

        /* Exibir os dados */
        for (Map<String, String> filme : listaFilmes) {
            System.out.println(filme.get("fullTitle"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}
