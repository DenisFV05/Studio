#!/usr/bin/env python3

import os
import platform
import random

"""
Jocs, Daus

Aquest joc consisteix en:

- Es tiren dos daus cada torn, si la suma és parell, se sumen els punts del dau major i i si són imparells, es resten els del dau menor.

- Es tiren 50 vegades cadascun els daus i el que major puntuació treu, guanya.

- Si guanya l'usuari, s'afegeix el jugador i pa seva puntuació al ranking.

- El ranking haurà d’estar ordenat.

- Al final de cada partida, caldrà resetejar el nom de l'usuari.

En aquesta versió el ranking es guarda en un text d'aquest estil:

"Pedro:1000;Mario:500;Pablo:5;"

No es pot fer servir arrays de cap manera
"""

def clearScreen():
    if os.name == 'nt':
        os.system('cls')
    else:
        os.system('clear')

clearScreen()

def afegir_jugador(ranking_str, nom_jugador, nova_puntuacio):
    """
    Afegeix un nou jugador al rànquing o actualitza la seva puntuació si és més alta que l'actual.

    Paràmetres:
        ranking_str (str): Cadena que conté el rànquing de jugadors en format "nom:puntuació;nom:puntuació;"
        nom_jugador (str): Nom del jugador que s'afegirà o actualitzarà al rànquing.
        nova_puntuacio (int): Puntuació del jugador.

    Retorna:
        str: Retorna el rànquing actualitzat en format de cadena de text.

    Comportament:
        - Comprova si el jugador ja és al rànquing.
        - Si hi és, actualitza la seva puntuació només si la `nova_puntuacio` és més alta que l'actual.
        - Si no hi és, l'afegim amb la `nova_puntuacio`.

    Exemples:
        ranking_str = "pepe:150;Juan:100;"
        afegir_jugador(ranking_str, "Pablo", 50)
        Retorna: "pepe:150;Juan:100;Pablo:50;"

        afegir_jugador(ranking_str, "Juan", 120)
        Retorna: "pepe:150;Juan:120;"
    """
    pass

def escollir_jugador(ranking_str):
    """
    Permet a l'usuari escollir un jugador existent del rànquing.

    Paràmetres:
        ranking_str (str): Cadena que conté el rànquing de jugadors en format "nom:puntuació;nom:puntuació;"

    Retorna:
        str: Retorna el nom del jugador escollit.

    Comportament:
        - Mostra una llista amb els noms dels jugadors disponibles.
        - L'usuari pot escollir un jugador introduint el número corresponent o escrivint el nom del jugador.
        - La funció no surt fins que es selecciona un jugador vàlid.
        - Retorna el nom del jugador seleccionat.

    Exemples:
        ranking_str = "pepe:150;Juan:100;"
        escollir_jugador(ranking_str)
        Retorna: "pepe" si l'usuari selecciona la primera opció o escriu "pepe".
    """
    pass

def mostrar_puntuacions(ranking_str):
    """
    Mostra les puntuacions actuals del rànquing de jugadors de manera ordenada.

    Paràmetres:
        ranking_str (str): Cadena que conté el rànquing de jugadors en format "nom:puntuació;nom:puntuació;"

    Retorna:
        None: Aquesta funció no retorna res.

    Comportament:
        - Ordena el rànquing segons les puntuacions de manera descendent.
        - Mostra el rànquing amb els noms dels jugadors i les seves puntuacions en format tabular.

    Exemples:
        ranking_str = "pepe:150;Juan:100;Pablo:50;"
        mostrar_puntuacions(ranking_str)
        Sortida:
        ················ Ranking ················
        NOM                                 PUNTS
        *****************************************
        pepe                                  150
        Juan                                  100
        Pablo                                  50
    """
    pass

def jugar(jugador):
    """
    Simula una partida entre el jugador i l'ordinador. Es tiren dos daus cada torn, 50 vegades.

    Paràmetres:
        jugador (str): Nom del jugador participant a la partida.

    Retorna:
        int: Puntuació final del jugador, o -1 si ha guanyat l'ordinador.

    Comportament:
        - Es tiren dos daus per torn, durant 50 torns per al jugador i l'ordinador.
        - Si la suma dels daus és parell, s'afegeixen els punts del dau major.
        - Si la suma dels daus és imparell, es resten els punts del dau menor.
        - El jugador o l'ordinador amb més punts després de 50 tirades guanya.
        - Al final, es mostra qui ha guanyat i amb quina puntuació.

    Exemples:
        jugador = "pepe"
        jugar(jugador)
        Retorna: 150 si el jugador guanya, -1 si l'ordinador guanya.
    """
    pass

def mainRun():
    """
    Mostra un menú principal per gestionar el joc de daus i permet interactuar amb les opcions del joc.

    Paràmetres:
        None: Aquesta funció no té paràmetres.

    Retorna:
        None: Aquesta funció no retorna res.

    Comportament:
        - Mostra les opcions disponibles: iniciar una nova partida, afegir un nou jugador, escollir un jugador, jugar, mostrar puntuacions o sortir del joc.
        - Si no hi ha jugadors al rànquing, l'opció d'escollir jugador no està disponible.
        - Si no s'ha escollit un jugador, l'opció de jugar no està disponible.
        - Es poden escollir les opcions del menú per número [0, 1, 2, 3, 4] o per text ['afegir', 'escollir', 'jugar', 'puntuacions', 'ranking', 'sortir'].

    Textos:
        "\nEscull una opció: ": Per escollir la opció del menú.
        "\nIntrodueix el nom del nou jugador: "
        "\nNo hi ha jugadors al rànquing. Afegiu-ne un primer."
        "\nNo s'ha escollit cap jugador."
        "Opció no vàlida."

    Exemples:
        Menú:
        1) Afegir jugador
        X) Escollir jugador
        X) Jugar ()
        4) Mostrar puntuacions
        0) Sortir

        Exemple després d'afegir un jugador:
        1) Afegir jugador
        2) Escollir jugador
        3) Jugar (Pedrito)
        4) Mostrar puntuacions
        0) Sortir
    """
    pass

if __name__ == "__main__":
    mainRun()