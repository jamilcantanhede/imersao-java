package desafio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import util.JsonParse;

public class DesafioAula01 {
    public static void main(String[] args) throws Exception {
        String urlTopFilmes = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String urlMostPopFilmes = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        String urlTopSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        String urlMostPopSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

        URI endereco = URI.create(urlMostPopFilmes);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        JsonParse parser = new JsonParse();
        List<Map<String, String>> listaFilmes = parser.parse(body);

        System.out.println("\u001b[1m\u001b[33m\uD83C\uDFAC AVALIAÇÃO DE FILMES IMDb \uD83C\uDFAC \u001b[0m");
        for (Map<String, String> filme : listaFilmes) {
            System.out.println("\u001b[1mTítulo: \u001b[0m" + filme.get("fullTitle"));
            System.out.println("\u001b[1mPoster: \u001b[0m" + filme.get("image"));
            System.out.print("\u001b[1mAvaliação: \u001b[0m");

            int rating = Math.round(Float.parseFloat(filme.get("imDbRating")));

            for (int i = 0; i < rating; i++)
                System.out.print("\u2B50");

            if (rating < 8)
                System.out.print(" \uD83D\uDE10");
            else if (rating >= 8 && rating < 9)
                System.out.print(" \uD83D\uDE42");
            else if (rating >= 9 && rating <= 10)
                System.out.print(" \uD83D\uDE04");
            else
                System.out.print(" \uD83D\uDE21");

            System.out.println("\n");
        }
    }
}
