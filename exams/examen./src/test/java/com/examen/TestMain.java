package com.examen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TestMain {

    private Locale defaultLocale;
    private static final String PATH_MONUMENTS = "./data/monuments.json";

    static int obtainedPoints = 0;
    private static final HashMap<String, Integer> testPointsMap = new HashMap<>();
    static {
        testPointsMap.put("testValidarDNI", 5);
        testPointsMap.put("testLoadAlumnes", 5);
        testPointsMap.put("testIsValidValue", 5);
        testPointsMap.put("testGetAlumneValue", 5);
        testPointsMap.put("testOrdenaAlumnes", 5);
        testPointsMap.put("testGeneraMarcTaula", 5);
        testPointsMap.put("testFormatRow", 5);
        testPointsMap.put("testTaulaAlumnes", 5);
    }

    @BeforeEach
    public void setUp() {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
    }

    @AfterEach
    public void tearDown() {
        Locale.setDefault(defaultLocale);
    }

    @AfterAll
    static void showFinalGrade() {
    int total = testPointsMap.values().stream().mapToInt(Integer::intValue).sum();

    double rawScore = (double) obtainedPoints / total * 10;
    double truncated = Math.floor(rawScore);
    double fraction = rawScore - truncated;
    fraction = fraction < 0.5 ? 0.0 : 0.5;
    double finalScore = truncated + fraction;

    String formulaDecimal = String.format("(%d / %d) * 10 = %.2f", obtainedPoints, total, rawScore);
    String formulaReal = String.format("(%d / %d) * 10 = %.1f", obtainedPoints, total, finalScore);
    System.out.println("""
        **************************************************
        Nota decimal: %s
        Nota real:    %s
        **************************************************
        """.formatted(formulaDecimal, formulaReal));
    }

    @Test
    void testValidarDNI(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            // DNIs vàlids
            assertTrue(Main.validarDNI("00000001R"), "El DNI 00000001R ha de ser vàlid");
            assertTrue(Main.validarDNI("12345678Z"), "El DNI 12345678Z ha de ser vàlid");
            // DNIs invàlids
            assertFalse(Main.validarDNI("1234567A"), "El DNI 1234567A amb 8 caràcters ha de ser invàlid");
            assertFalse(Main.validarDNI("123456789A"), "El DNI 123456789A amb 10 caràcters ha de ser invàlid");
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            e.printStackTrace();
            fail(e);
        }
    }
    
    @Test
    void testLoadAlumnes(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            ArrayList<HashMap<String, Object>> alumnes = Main.loadAlumnes("./data/alumnes.json");
            assertEquals(3, alumnes.size());
    
            HashMap<String, Object> a1 = alumnes.get(0);
            assertEquals("Maria", a1.get("nom"));
            assertEquals(18, a1.get("edat"));
            assertEquals("12868093F", a1.get("dni"));
            @SuppressWarnings("unchecked")
            ArrayList<String> aficions1 = (ArrayList<String>) a1.get("aficions");
            assertEquals(2, aficions1.size());
            assertTrue(aficions1.contains("Futbol"));
            assertTrue(aficions1.contains("Basquet"));
    
            HashMap<String, Object> a2 = alumnes.get(1);
            assertEquals("Arnau", a2.get("nom"));
            assertEquals(19, a2.get("edat"));
            assertEquals("56442478H", a2.get("dni"));
            @SuppressWarnings("unchecked")
            ArrayList<String> aficions2 = (ArrayList<String>) a2.get("aficions");
            assertEquals(2, aficions2.size());
            assertTrue(aficions2.contains("Natacio"));
            assertTrue(aficions2.contains("Basquet"));
    
            HashMap<String, Object> a3 = alumnes.get(2);
            assertEquals("Alex", a3.get("nom"));
            assertEquals(17, a3.get("edat"));
            assertEquals("56865736A", a3.get("dni"));
            @SuppressWarnings("unchecked")
            ArrayList<String> aficions3 = (ArrayList<String>) a3.get("aficions");
            assertEquals(2, aficions3.size());
            assertTrue(aficions3.contains("Esqui"));
            assertTrue(aficions3.contains("Futbol"));
    
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    void testIsValidValue(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            Class<?> clazz = Main.class;
            java.lang.reflect.Method method = clazz.getDeclaredMethod("isValid", String.class, String[].class);
            method.setAccessible(true);
            String[] validValues = {"A", "Ba", "C"};
            assertTrue((Boolean) method.invoke(null, "A", validValues));
            assertTrue((Boolean) method.invoke(null, "Ba", validValues));
            assertFalse((Boolean) method.invoke(null, "D", validValues));
            assertFalse((Boolean) method.invoke(null, "", validValues));
            assertFalse((Boolean) method.invoke(null, null, validValues));
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    void testGetAlumneValue(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            Class<?> clazz = Main.class;
            java.lang.reflect.Method method = clazz.getDeclaredMethod("getAlumneValue", HashMap.class, String.class);
            method.setAccessible(true);
            
            HashMap<String, Object> alumne = new HashMap<>();
            alumne.put("nom", "Test Alumne");
            alumne.put("edat", 20);
            alumne.put("dni", "12345678Z");
            ArrayList<String> aficions = new ArrayList<>();
            aficions.add("Futbol");
            aficions.add("Basquet");
            alumne.put("aficions", aficions);
            
            Object nom = method.invoke(null, alumne, "nom");
            Object edat = method.invoke(null, alumne, "edat");
            Object dni = method.invoke(null, alumne, "dni");
            Object aficionsObj = method.invoke(null, alumne, "aficions");
            
            assertEquals("Test Alumne", nom);
            assertEquals(20, edat);
            assertEquals("12345678Z", dni);
            assertEquals(aficions, aficionsObj);
            
            try {
                method.invoke(null, alumne, "invalid");
                fail("Expected IllegalArgumentException for invalid key");
            } catch (java.lang.reflect.InvocationTargetException e) {
                assertTrue(e.getCause() instanceof IllegalArgumentException);
            }
            
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    void testOrdenaAlumnes(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            ArrayList<HashMap<String, Object>> alumnes = new ArrayList<>();
            
            HashMap<String, Object> a1 = new HashMap<>();
            a1.put("nom", "Arnau");
            a1.put("edat", 19);
            a1.put("dni", "56442478H");
            ArrayList<String> aficions1 = new ArrayList<>();
            aficions1.add("Natacio");
            aficions1.add("Basquet");
            a1.put("aficions", aficions1);
            alumnes.add(a1);
            
            HashMap<String, Object> a2 = new HashMap<>();
            a2.put("nom", "Alex");
            a2.put("edat", 17);
            a2.put("dni", "56865736A");
            ArrayList<String> aficions2 = new ArrayList<>();
            aficions2.add("Esqui");
            aficions2.add("Futbol");
            a2.put("aficions", aficions2);
            alumnes.add(a2);
            
            HashMap<String, Object> a3 = new HashMap<>();
            a3.put("nom", "Maria");
            a3.put("edat", 18);
            a3.put("dni", "12868093F");
            ArrayList<String> aficions3 = new ArrayList<>();
            aficions3.add("Futbol");
            aficions3.add("Basquet");
            a3.put("aficions", aficions3);
            alumnes.add(a3);
            
            ArrayList<HashMap<String, Object>> sortedNom = Main.ordenaAlumnes(alumnes, "nom");
            assertEquals("Alex", sortedNom.get(0).get("nom"));
            assertEquals("Arnau", sortedNom.get(1).get("nom"));
            assertEquals("Maria", sortedNom.get(2).get("nom"));
            
            ArrayList<HashMap<String, Object>> sortedEdat = Main.ordenaAlumnes(alumnes, "edat");
            assertEquals(17, sortedEdat.get(0).get("edat"));
            assertEquals(18, sortedEdat.get(1).get("edat"));
            assertEquals(19, sortedEdat.get(2).get("edat"));
            
            ArrayList<HashMap<String, Object>> sortedDni = Main.ordenaAlumnes(alumnes, "dni");
            assertEquals("12868093F", sortedDni.get(0).get("dni"));
            assertEquals("56442478H", sortedDni.get(1).get("dni"));
            assertEquals("56865736A", sortedDni.get(2).get("dni"));
            
            try {
                Main.ordenaAlumnes(alumnes, "invalid");
                fail("Expected IllegalArgumentException for invalid sort key");
            } catch (IllegalArgumentException e) {}
            
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }
    
    @Test
    void testGeneraMarcTaula(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            String result1 = Main.generaMarcTaula(new int[]{2, 5, 3}, new char[]{'┌', '┬', '┐'});
            assertEquals("┌──┬─────┬───┐", result1);
            
            String result2 = Main.generaMarcTaula(new int[]{4, 3, 6}, new char[]{'├', '┼', '┤'});
            assertEquals("├────┼───┼──────┤", result2);
            
            String result3 = Main.generaMarcTaula(new int[]{2, 4}, new char[]{'└', '┴', '┘'});
            assertEquals("└──┴────┘", result3);
            
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    void testFormatRow(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            Class<?> clazz = Main.class;
            java.lang.reflect.Method method = clazz.getDeclaredMethod("formatRow", String[].class, int[].class);
            method.setAccessible(true);
            
            String[] values1 = {"Nom", "País", "Any"};
            int[] widths1 = {10, 6, 4};
            String expected1 = "│Nom       │País  │Any │";
            String result1 = (String) method.invoke(null, values1, widths1);
            assertEquals(expected1, result1);
            
            String[] values2 = {"Machu Picchu", "Perú", "1983"};
            int[] widths2 = {10, 6, 4};
            String expected2 = "│Machu Picc│Perú  │1983│";
            String result2 = (String) method.invoke(null, values2, widths2);
            assertEquals(expected2, result2);
            
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            System.out.println("Test encountered an error: " + testInfo.getDisplayName());
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    void testTaulaAlumnes(TestInfo testInfo) {
        int testPoints = testPointsMap.getOrDefault(testInfo.getTestMethod().get().getName(), 1);
        try {
            ArrayList<HashMap<String, Object>> alumnes = new ArrayList<>();
            
            HashMap<String, Object> a1 = new HashMap<>();
            a1.put("nom", "Alex");
            a1.put("edat", 17);
            a1.put("dni", "56865736A");
            ArrayList<String> aficions1 = new ArrayList<>();
            aficions1.add("Esqui");
            aficions1.add("Futbol");
            a1.put("aficions", aficions1);
            alumnes.add(a1);
            
            HashMap<String, Object> a2 = new HashMap<>();
            a2.put("nom", "Arnau");
            a2.put("edat", 19);
            a2.put("dni", "56442478H");
            ArrayList<String> aficions2 = new ArrayList<>();
            aficions2.add("Natacio");
            aficions2.add("Basquet");
            a2.put("aficions", aficions2);
            alumnes.add(a2);
            
            HashMap<String, Object> a3 = new HashMap<>();
            a3.put("nom", "Maria");
            a3.put("edat", 18);
            a3.put("dni", "12868093F");
            ArrayList<String> aficions3 = new ArrayList<>();
            aficions3.add("Futbol");
            aficions3.add("Basquet");
            a3.put("aficions", aficions3);
            alumnes.add(a3);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(baos));
            
            Main.taulaAlumnes(alumnes);
            
            System.setOut(originalOut);
            String output = baos.toString().replace("\r\n", "\n");
            String nl = "\n";
            String expected = "┌───────────────┬─────┬──────────┬───────────────┐" + nl +
                              "│Nom            │Edat │DNI       │Aficions       │" + nl +
                              "├───────────────┼─────┼──────────┼───────────────┤" + nl +
                              "│Alex           │17   │56865736A │Esqui, Futbol  │" + nl +
                              "│Arnau          │19   │56442478H │Natacio, Basque│" + nl +
                              "│Maria          │18   │12868093F │Futbol, Basquet│" + nl +
                              "└───────────────┴─────┴──────────┴───────────────┘" + nl;
            assertEquals(expected.trim(), output.trim(), "La taula generada no coincideix amb l'esperada");
            System.out.println("Test passed, succeeded!");
            obtainedPoints += testPoints;
        } catch (Exception e) {
            e.printStackTrace();
            fail(e);
        }
    }
}
