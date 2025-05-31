package com.examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static Scanner scanner;
    public static Locale defaultLocale;
    
    // Fes anar el 'main' de l'examen amb:
    // ./run.sh com.examen.ResoltMain

    // Calcula la nota de l'examen amb:
    // ./runTest.sh com.examen.TestMain
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        System.out.println("1r DNI (s'espera true): " + validarDNI("12868093F"));
        System.out.println("2n DNI (s'espera true): " + validarDNI("56442478H"));
        System.out.println("3r DNI (s'espera false): " + validarDNI("56865736A"));

        try {
            @SuppressWarnings("unused")
            ArrayList<HashMap<String, Object>> alumnesFail =  loadAlumnes("res.json");
        } catch (IOException e) {
            System.out.println("L'arxiu \"res.json\" no existeix (correcte)");
        }

        ArrayList<HashMap<String, Object>> alumnes = new ArrayList<>();
        try {
            alumnes =  loadAlumnes("./data/alumnes.json");
        } catch (IOException e) {
            System.out.println("L'arxiu \"./data/alumnes.json\" no existeix (error)");
        } 

        System.out.println("1r isValid (s'espera true): " + isValid("hola", new String[] {"adeu", "fins aviat", "hola", "a reveure"}));
        System.out.println("2n isValid (s'espera false): " + isValid("hola", new String[] {"adeu", "fins aviat"}));

        try {
            @SuppressWarnings("unused")
            Object valorFail =  getAlumneValue(alumnes.get(0), "clauErronia");
        } catch (IllegalArgumentException e) {
            System.out.println("La clau \"clauErronia\" no existeix (correcte)");
        }

        try {
            System.out.println("1r getAlumneValue (s'espera \"Maria\"): " + getAlumneValue(alumnes.get(0), "nom"));
            System.out.println("2n getAlumneValue (s'espera \"19\"): " + getAlumneValue(alumnes.get(1), "edat"));
            System.out.println("3r getAlumneValue (s'espera \"56865736A\"): " + getAlumneValue(alumnes.get(2), "dni"));
            System.out.println("4t getAlumneValue (s'espera \"[Futbol, Basquet]\"): " + getAlumneValue(alumnes.get(0), "aficions"));
        } catch (IllegalArgumentException e) {
            System.out.println("No ha trobat les dades (error)");
        }

        try {
            @SuppressWarnings("unused")
            ArrayList<HashMap<String, Object>> ordenaFail =  ordenaAlumnes(alumnes, "clauOrdenaErronia");
        } catch (IllegalArgumentException e) {
            System.out.println("La clau \"clauOrdenaErronia\" no existeix (correcte)");
        }

        ArrayList<HashMap<String, Object>> ordenatsNom = new ArrayList<>();
        ArrayList<HashMap<String, Object>> ordenatsEdat = new ArrayList<>();
        try {
            ordenatsNom =  ordenaAlumnes(alumnes, "nom");
            ordenatsEdat =  ordenaAlumnes(alumnes, "edat");
        } catch (IllegalArgumentException e) {
            System.out.println("La clau \"clauOrdenaErronia\" no existeix (correcte)");
        }

        taulaAlumnes(ordenatsNom);
        taulaAlumnes(ordenatsEdat);

        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /**
     * Valida un DNI espanyol.
     *
     * El DNI ha de consistir en 8 dígits seguits d'una lletra. La lletra es determina
     * dividint el nombre per 23 i utilitzant el residu com a índex en la cadena:
     * "TRWAGMYFPDXBNJZSQVHLCKE".
     * 
     * Si "dni" és null o no té longitud 9 retorna false
     * S'ha d'obtenir el número enter i fer la següent operació:
     * 
     * int num = obtenir el número enter del DNI
     * String lletres = "TRWAGMYFPDXBNJZSQVHLCKE";
     * char lletraCorrecta = lletres.charAt(num % 23);
     * 
     * Si escriuen una lletra en minúscula i és vàlida, també s'accepta
     * Si es produeix un error obtenint el número retorna false
     *
     * @param dni el DNI a validar.
     * @return true si el DNI és vàlid
     * 
     * @test ./runTest.sh com.examen.TestMain#testValidarDNI
     */
    public static boolean validarDNI(String dni) {
        if (dni.isEmpty() || dni.contains(" ") || dni.length() != 9 ) return false;
        int num = Integer.parseInt(dni.substring(0, 7));
        String lletres = "TRWAGMYFPDXBNJZSQVHLCKE";
        char lletraCorrecta = lletres.charAt(num % 23);
        
        // La lletra correcta me la calcula mal a pesar que el numero si coincide, con esta funcion de abajo me salen o todas true o todas false
        if (!dni.contains(Character.toString(lletraCorrecta))) return false;


        return true;
        
    }

    /**
     * Llegeix l'arxiu de 'filePath', retorna un ArrayList
     * amb les dades dels alumnes.
     * 
     * Ha de generar 'HashMap' amb les dades de cada alumne. 
     * "nom" i "dni" són de tipus String
     * "edat" és de tipus int
     * "aficions" és de tipus ArrayList<String>
     * 
     * @param filePath Ruta de l'arxiu JSON
     * @return ArrayList amb les dades dels alumnes
     * 
     * @throws IOException Si hi ha algun problema amb l'escriptura de l'arxiu forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.examen.TestMain#testLoadAlumnes
     */
    public static ArrayList<HashMap<String, Object>> loadAlumnes(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        ArrayList<HashMap<String, Object>> alumnes = new ArrayList<>();
        
        JSONArray aray = new JSONArray(content);
            for (int i = 0; i < aray.length(); i++) {
                JSONObject alumne = aray.getJSONObject(i);
                HashMap<String, Object> alumneHM = new HashMap<>();

            for (String key : alumne.keySet()) {
                if (key.equals("nom") || key.equals("dni") || key.equals("edat") || key.equals("aficions")) {
                    alumneHM.put(key, alumne.get(key));}
            alumnes.add(alumneHM);
        }}



        return alumnes;
    }

    /**
     * Comprova si un valor es troba dins d'una llista de valors vàlids.
     * 
     * @param value Valor a comprovar.
     * @param validValues Llista de valors permesos.
     * @return true si el valor és dins de la llista, false en cas contrari.
     * 
     * @test ./runTest.sh com.examen.TestMain#testIsValidValue
     */
    public static boolean isValid(String value, String[] validValues) {
        for (String valid : validValues) {
            if (valid.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obté el valor d'un alumne segons el camp especificat.
     *
     * - Si el camp és "nom", "edat", "dni" o "aficions", 
     * retorna el valor directament de la clau corresponent.
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid forçant un 'try/catch'
     * amb el text "Clau no valida"
     *
     * @param alumne HashMap amb les dades de l'alumne.
     * @param key Clau pel qual es vol obtenir el valor (pot ser "nom", "edat", "dni" o "aficions").
     * @return Un 'Object' amb el valor corresponent si existeix, en cas contrari retorna null.
     * 
     * @test ./runTest.sh com.examen.TestMain#testGetAlumneValue
     */
    public static Object getAlumneValue(HashMap<String, Object> alumne, String key) throws IllegalArgumentException {
        switch (key) {
            case "nom", "edat", "dni", "aficions" -> {
            return alumne.get(key);
            }
        }
        return null;
    }

    /**
     * Ordena un ArrayList d'alumnes per un camp concret.
     * Els camps vàlids són: "nom", "edat" i "dni".
     *
     * @param alumnes llista dels alumnes.
     * @param sortKey camp per ordenar.
     * @return ArrayList amb les dades dels alumnes ordenats.
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid.
     * 
     * @test ./runTest.sh com.examen.TestMain#testOrdenaAlumnes
     */
    public static ArrayList<HashMap<String, Object>> ordenaAlumnes(ArrayList<HashMap<String, Object>> alumnes, String sortKey) throws IllegalArgumentException {
        // Validació dels camps
        String[] validKeys = {"nom", "edat", "dni"};
        if (!isValid(sortKey, validKeys)) {
            throw new IllegalArgumentException("Invalid sort key: " + sortKey);
        }
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>(alumnes);
        rst.sort((m1, m2) -> {
            Object val1 = getAlumneValue(m1, sortKey);
            Object val2 = getAlumneValue(m2, sortKey);
    
            if (val1 == null) return 1;
            if (val2 == null) return -1;
    
            if (val1 instanceof String && val2 instanceof String) {
                return ((String) val1).compareToIgnoreCase((String) val2);
            } else if (val1 instanceof Number && val2 instanceof Number) {
                return Double.compare(((Number) val1).doubleValue(), ((Number) val2).doubleValue());
            }
    
            return 0;
        });
    
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
     * @test ./runTest.sh com.examen.TestMain#testGeneraMarcTaula
     */
    public static String generaMarcTaula(int[] columnWidths, char[] separators) {
        StringBuilder result = new StringBuilder();
        result.append(separators[0]); 
    
        for (int i = 0; i < columnWidths.length; i++) {
            result.append("─".repeat(columnWidths[i])); 
            if (i < columnWidths.length - 1) {
                result.append(separators[1]); 
            }
        }
    
        result.append(separators[2]); 
    
        return result.toString();
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
     * @test ./runTest.sh com.examen.TestMain#testFormatRow
     */
    public static String formatRow(String[] values, int[] columnWidths) {
        StringBuilder row = new StringBuilder("│");
        for (int i = 0; i < values.length; i++) {
            String value = values[i] == null ? "" : values[i];
            if (value.length() > columnWidths[i]) {
                value = value.substring(0, columnWidths[i]);
            }
            row.append(String.format("%-" + columnWidths[i] + "s", value)).append("│");
        }
        return row.toString();
    }
    
    /**
     * Dibuixa una taula amb la informació d'un ArrayList d'alumnes.
     *
     * El format de la taula utilitza els caràcters: "┌", "┬", "┐", "├", "┼", "┤", "└", "┴" i "┘".
     * 
     * La taula mostra les columnes "Nom", "Edat", "DNI" i "Aficions"
     * amb els amples de columna: {15, 5, 10, 15}
     * 
     * ┌───────────────┬─────┬──────────┬───────────────┐
     * │Nom            │Edat │DNI       │Aficions       │
     * ├───────────────┼─────┼──────────┼───────────────┤
     * │Alex           │17   │56865736A │Esqui, Futbol  │
     * │Arnau          │19   │56442478H │Natacio, Basque│
     * │Maria          │18   │12868093F │Futbol, Basquet│
     * └───────────────┴─────┴──────────┴───────────────┘
     *
     * @param alumnes llista d'alumnes representats com a HashMap.
     * 
     * @test ./runTest.sh com.examen.TestMain#testTaulaAlumnes
     */
    public static void taulaAlumnes(ArrayList<HashMap<String, Object>> alumnes) {
    // Definició de les columnes
    String[] columnTitles = {"Nom", "Edat", "DNI", "Aficions"};
    int[] columnWidths = {15, 5, 10, 20};

    // Crear i mostrar la taula
    System.out.println(generaMarcTaula(columnWidths, new char[]{'┌', '┬', '┐'}));
    System.out.println(formatRow(columnTitles, columnWidths));
    System.out.println(generaMarcTaula(columnWidths, new char[]{'├', '┼', '┤'}));
    
    for (HashMap<String, Object> alumne : alumnes) {
        String[] row = {
            getAlumneValue(alumne, "nom").toString(),
            getAlumneValue(alumne, "edat").toString(),
            getAlumneValue(alumne, "dni").toString(),
            getAlumneValue(alumne, "aficions").toString()
        };
        System.out.println(formatRow(row, columnWidths));
    }

    System.out.println(generaMarcTaula(columnWidths, new char[]{'└', '┴', '┘'}));
}
}