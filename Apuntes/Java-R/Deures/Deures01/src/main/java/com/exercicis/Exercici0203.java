package com.exercicis;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;

public class Exercici0203 {

    public static Scanner scanner;
    public static Locale defaultLocale;
    
    // Fes anar el 'main' de l'exercici amb:
    // ./run.sh com.exercicis.Exercici0203
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        String url0 = "http://example.com";
        validarURL(url0); 

        String url1 = "https://google";
        validarURL(url1); 
        
        try {
            ArrayList<HashMap<String, Object>> monuments = loadMonuments("./data/monuments.json");
            ArrayList<HashMap<String, Object>> monumentsOrdenats = ordenaMonuments(monuments, "nom");
            ArrayList<HashMap<String, Object>> monumentsFiltrats = filtraMonuments(monuments, "categoria", "Monumental");
            //System.out.println(getMonumentValue(monuments.get(2), "nom"));
            //System.out.println(getMonumentValue(monuments.get(2), "pais"));
            //System.out.println(getMonumentValue(monuments.get(2), "categoria"));
            //System.out.println(getMonumentValue(monuments.get(2), "any"));
            //System.out.println(getMonumentValue(monuments.get(2), "longitud"));

            //System.out.println(getCoordsString((monuments.get(2))));

            taulaMonuments(monumentsOrdenats);
            taulaMonuments(monumentsFiltrats);


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            guardaBaralla("./data/baralla.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /** 
     * Valida una URL a partir d'una cadena de text. 
     * 
     * Cal que:
     * 
     * - No pot ser null ni una cadena de text buida
     * - No pot contenir espais
     * - Ha de començar amb 'http://' o 'https://'
     * - El domini ha de contenir almenys un punt (excepte localhost)
     * - El domini no pot començar ni acabar amb un punt
     * 
     * URLs vàlides: ["http://example.com", "https://www.google.com", "https://sub.domini.cat/pagina", "http://localhost:8080", "http://www.ieti.cat:8080/horaris"]
     * URLs no vàlides: ["", "ftp://example.com", "http:/example", "http:/example.com", "https:// google.com", "https://.example.com", "https://example.", "example.com"]
     * 
     * @param url URL a validar
     * @return 'true' si la URL és vàlida, 'false' si no ho és
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testValidarURL
     */
    public static boolean validarURL(String url) {

        if (url == null || url.isEmpty()) return false;

        // No ha de contenir espais
        if (url.contains(" ")) {
            return false;
        }

        // Ha de començar amb http:// o https://
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return false;
        }

        // Eliminar el protocol i el port per validar el domini
        String senseProtocol = url.substring(url.indexOf("://") + 3);
        String sensePort = senseProtocol.contains(":") ? senseProtocol.split(":", 2)[0] : senseProtocol;
        String domini = sensePort.contains("/") ? sensePort.split("/", 2)[0] : sensePort;
        
        // El domini ha de tenir almenys un punt i no començar/acabar en punt
        if ((!domini.contains(".") && !domini.equals("localhost")) || domini.startsWith(".") || domini.endsWith(".")) {
            return false;
        }

        return true;
    }

    /**
     * Llegeix l'arxiu de 'filePath', retorna un ArrayList amb les dades 
     * dels monuments que són patrimoni de la humanitat.
     * 
     * Ha de generar 'HashMap' amb les dades de cada monument
     * L'atribut que no coincideix, ha d'estar en un 'HashMap' propi anomenat 'altres'
     * que té dues claus:
     * - 'clau': nom de l'atribut
     * - 'valor': valor de l'atribut
     * 
     * @param filePath Ruta de l'arxiu JSON
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IOException Si hi ha algun problema amb l'escriptura de l'arxiu forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testLoadMonuments
     */
    public static ArrayList<HashMap<String, Object>> loadMonuments(String filePath) throws IOException {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray monumentsArray = new JSONObject(content).getJSONArray("monuments");
        for (int i = 0; i < monumentsArray.length(); i++) {
            JSONObject monument = monumentsArray.getJSONObject(i);
            HashMap<String, Object> monumentHM = new HashMap<>();

            for (String key : monument.keySet()) {
                if (key.equals("nom") || key.equals("pais") || key.equals("categoria")) {
                    monumentHM.put(key, monument.get(key));
                } else if (key.equals("detalls")) {
                    JSONObject detalls = monument.getJSONObject(key);
                    HashMap<String, Object> detallsMap = new HashMap<>();
                    HashMap<String, Object> altres = new HashMap<>();
                    for (String detallsKey : detalls.keySet()) {
                        if (detallsKey.equals("coordenades")) {
                            HashMap<String, Object> coordsMap = new HashMap<>();
                            JSONObject coordenadesJSON = detalls.getJSONObject("coordenades");
                            Double lat = coordenadesJSON.getDouble("latitud");
                            Double lon =  coordenadesJSON.getDouble("longitud");
                            coordsMap.put("latitud", lat);
                            coordsMap.put("longitud",lon);
                            detallsMap.put("coordenades", coordsMap);

                        } else if (detallsKey.equals("any_declaracio")) {
                                int any =  detalls.getInt("any_declaracio");
                                detallsMap.put("any_declaracio", any);
                        } else {
                            HashMap<String, Object> altre = new HashMap<>();
                            altre.put("clau", detallsKey);
                            altre.put("valor", detalls.get(detallsKey));
                            altres.put(detallsKey, altre);
                        }
                        
                    }
                    detallsMap.put("altres", altres);
                    monumentHM.put(key, detallsMap);
                }
            }
            rst.add(monumentHM);
        }
        return rst;
    }

    /**
     * Obté el valor d'un monument segons el camp especificat.
     * Es pot utilitzar tant per a ordenació com per a filtratge.
     * 
     * - Si el camp és "nom", retorna el valor directament de la clau "nom".
     * - Si el camp és "pais", retorna el valor directament de la clau "pais".
     * - Si el camp és "categoria", retorna el valor directament de la clau "categoria".
     * - Si el camp és "any", accedeix al valor de "any_declaracio" dins de "detalls".
     * - Si el camp és "latitud" o "longitud", accedeix al valor corresponent dins de "coordenades",
     *   que es troba dins de "detalls".
     * - Si el valor no existeix o la jerarquia de dades no està present, retorna null.
     * 
     * @param monument HashMap amb les dades del monument.
     * @param key Clau pel qual es vol obtenir el valor (pot ser "nom", "any", "latitud" o "longitud").
     * @return Un 'Object' amb el valor corresponent si existeix, en cas contrari retorna null.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGetMonumentValue
     */
    static Object getMonumentValue(HashMap<String,Object> monument, String key) {

        switch (key) {
            case "nom", "pais", "categoria" -> {
            return monument.get(key);
            }
            case "any" -> {
            HashMap<String, Object> detalls = (HashMap<String, Object>) monument.get("detalls");
            return detalls != null ? detalls.get("any_declaracio") : null;
            }
            case "latitud", "longitud" -> {
            HashMap<String, Object> detalls = (HashMap<String, Object>) monument.get("detalls");
            HashMap<String, Object> coordenades = (HashMap<String, Object>) detalls.get("coordenades");
            return coordenades != null ? coordenades.get(key) : null;

            }
        }
        return null;
    }
    
    /**
     * Comprova si un valor es troba dins d'una llista de valors vàlids.
     * 
     * @param value Valor a comprovar.
     * @param validValues Llista de valors permesos.
     * @return True si el valor és dins de la llista, false en cas contrari.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testIsValidValue
     */
    public static boolean isValid(String value, String[] validValues) {
        if (validValues.length == 0) return false;
        return Arrays.asList(validValues).indexOf(value) != -1;
    }

    /**
     * Ordena un ArrayList de monuments per un camp concret.
     * Els camps vàlids són: 'nom', 'any', 'latitud', 'longitud'
     *      * 
     * @param monuments llista dels monuments
     * @param sortKey camp per ordenar
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testOrdenaMonuments
     */
    public static ArrayList<HashMap<String, Object>> ordenaMonuments(ArrayList<HashMap<String, Object>> monuments, String sortKey) throws IllegalArgumentException {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>(monuments);    
        if (!isValid(sortKey, new String[]{"nom", "any", "latitud", "longitud"})) {
            throw new IllegalArgumentException("Columna invalida");
        }
        Collections.sort(rst, (m1, m2) -> {
            Object value1 = getMonumentValue(m1, sortKey);
            Object value2 = getMonumentValue(m2, sortKey);

            if (sortKey.equals("nom")) {
                return ((String) value1).compareTo((String) value2);
            } else if (sortKey.equals("any")) {
                return ((Integer) value1).compareTo((Integer) value2);
            } else {
                return ((Double) value1).compareTo((Double) value2);
            }

        });

        
        return rst;
    }

    /**
     * Filtra un ArrayList de monuments per un camp i un valor
     * Els camps vàlids són: 'nom', 'pais', 'categoria'
     *      * 
     * @param monuments llista dels monuments
     * @param filterKey camp per filtrar
     * @param filterValue valor a filtrar
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid (no força un 'try/catch')
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testFiltraMonuments
     */
    public static ArrayList<HashMap<String, Object>> filtraMonuments(ArrayList<HashMap<String, Object>> monuments, String filterKey, String filterValue) throws IllegalArgumentException {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>();
        if (!isValid(filterKey, new String[]{"nom", "pais", "categoria"})) {
            throw new IllegalArgumentException("Columna invalida");
        }
        for (HashMap<String, Object> monument : monuments) {
            if (getMonumentValue(monument, filterKey).toString().equals(filterValue)) {
                rst.add(monument);
            }
        }
        return rst;
    }

    /**
     * Genera una cadena de text vàlida per formar el marc d'una taula:
     * 
     * Exemple: {2, 5, 3} i { '┌', '┬', '┐' } genera "┌──┬─────┬───┐"
     * Exemple: {4, 3, 6} i { '├', '┼', '┤' } genera "├────┼───┼──────┤"
     * Exemple: {2, 4} i { '└', '┴', '┘' } genera "└──┴────┘"
     * 
     * @param columnWidths array amb els caràcters que ocupa cada columna
     * @param separators array amb els separadors inicial,central i final
     * @return String amb la cadena de text
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGeneraMarcTaula
     */
    public static String generaMarcTaula(int[] columnWidths, char[] separators) {
        StringBuilder rst = new StringBuilder();

        rst.append(separators[0]);
        for (int i = 0; i < columnWidths.length; i++) {
            rst.append(("─").repeat(columnWidths[i]));
            if (i < columnWidths.length - 1) {
                rst.append(separators[1]);
            }
        }
        rst.append(separators[2]);

        return rst.toString();
    }

    /**
     * Formata una fila de la taula amb els valors de cada columna, ajustant l'amplada segons 
     * els valors especificats i afegint marges i separadors.
     *
     * Cada cel·la s'alinea a l'esquerra i es complementa amb espais en blanc si cal 
     * per ajustar-se a l'amplada de la columna.
     *
     * Exemples:
     * formatRow(new String[]{"Nom", "País", "Any"}, new int[]{10, 6, 4});
     * Retorna: "│Nom       │País  │Any │"
     *
     * formatRow(new String[]{"Machu Picchu", "Perú", "1983"}, new int[]{10, 6, 4});
     * Retorna: "│Machu Picc│Perú  │1983│"
     *
     * @param values Array amb els valors de cada columna.
     * @param columnWidths Array amb l'amplada de cada columna.
     * @return Una cadena de text formatejada representant una fila de la taula.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testFormatRow
     */
    public static String formatRow(String[] values, int[] columnWidths) {
        String rst = "";
        for (int i = 0; i < values.length; i = i + 1) { 
            rst += "│";
            String value = values[i];
            if (value.length() > columnWidths[i]) {
                value = value.substring(0, columnWidths[i]);
            }
            rst += value;
            int spaceCount = columnWidths[i] - value.length();
            if (spaceCount > 0) {
                rst += " ".repeat(spaceCount);
            }
            
        }
        rst += "│";
        return rst;
    }
    

    /**
     * Obté una representació en format text de les coordenades d'un monument.
     *
     * Fa servir la funció 'getMonumentValue'
     * 
     * Crida a getCoordsString(monument) → Retorna "40.4,116.5"
     *
     * @param monument HashMap que representa un monument.
     * @return Una cadena de text amb les coordenades en format "latitud,longitud",
     *         o una cadena buida si no es troben les dades.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGetCoordsString
     */
    public static String getCoordsString(HashMap<String, Object> monument) {
        Double latitud = (Double) getMonumentValue(monument, "latitud");
        Double longitud = (Double) getMonumentValue(monument, "longitud");

        if (latitud == null || longitud == null){
            return " ";
        }
        
        return String.format("%.1f,%.1f", latitud, longitud);
    }

    /**
     * Mostra una taula amb la informació d'una llista de monuments.
     * 
     * El format de la taula ha de fer servir els caràcters: "┌", "┬", "┐", "├", "┼", "┤", "└", "┴" i "┘".
     * 
     * Ex.:
     * ┌──────────────┬─────┬────┬────────────┐
     * │Nom           │Pais │Any │Coords      │
     * ├──────────────┼─────┼────┼────────────┤
     * │Gran Muralla X│Xina │1987│40.4,116.6  │
     * │Machu Picchu  │Perú │1983│-13.2,-72.5 │
     * │Catedral de No│Franç│1991│48.9,2.3    │
     * │Parc Nacional │Tanzà│1981│-2.3,34.8   │
     * └──────────────┴─────┴────┴────────────┘
     * 
     * @param monuments llista dels monuments     
     * @param columnaOrdenacio La columna per la qual es vol ordenar ("nom", "radi", "massa", "distància").
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testTaulaMonuments
     */
    public static void taulaMonuments(ArrayList<HashMap<String, Object>> monuments) {
        StringBuilder rst = new StringBuilder();
        
        int [] columnWidths = {15, 10, 5, 10};

        char[] separators = {'┌', '┬', '┐'};
        rst.append(generaMarcTaula(columnWidths, separators)).append("\n");
        
        String[] headers = {"Nom", "Pais", "Any", "Coords"};
        rst.append(formatRow(headers, columnWidths)).append("\n");
        
        char[] separators1 = {'├', '┼', '┤'};
        rst.append(generaMarcTaula(columnWidths, separators1)).append("\n");

        for (int i = 0; i < monuments.size(); i++) {
            HashMap<String, Object> monument = monuments.get(i);
            String nom = (String) getMonumentValue(monument, "nom");
            String pais = (String) getMonumentValue(monument, "pais");
            String any = String.valueOf(getMonumentValue(monument, "any"));
            String coords = getCoordsString(monument);

            String[] rowValues = {nom, pais, any, coords};
            rst.append(formatRow(rowValues, columnWidths)).append("\n");
        }

        separators[0] = '└';
        separators[1] = '┴';
        separators[2] = '┘';
        rst.append(generaMarcTaula(columnWidths, separators)).append("\n");

        System.out.println(rst);
    }

    /**
     * Genera una baralla de cartes espanyola i la retorna en un ArrayList ordenat aleatòriament.
     * 
     * La baralla consta de 48 cartes, amb quatre pals: "oros", "copes", "espases" i "bastos".
     * Cada pal té cartes numerades de l'1 al 12.
     * 
     * Cada carta es representa com un HashMap amb dues claus:
     * - "pal" (String): indica el pal de la carta (ex: "oros").
     * - "numero" (Integer): el número de la carta (ex: 7).
     * 
     * Exemple de sortida d'una carta:
     * { "pal": "copes", "número": 10 }
     * 
     * @return Un ArrayList que conté les 48 cartes de la baralla en ordre aleatori.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGeneraBaralla
     */
    public static ArrayList<HashMap<String, Object>> generaBaralla() {
        ArrayList<HashMap<String, Object>> baralla = new ArrayList<>();
        return baralla;
    }

    /**
     * Guarda la informació d'una baralla espanyola a 'filePath'
     * 
     * @param filePath
     * @throws IOException si hi ha algun error amb l'escriptura de l'arxiu forçant un 'try/catch'
     */
    public static void guardaBaralla(String filePath) throws IOException {

    }
}