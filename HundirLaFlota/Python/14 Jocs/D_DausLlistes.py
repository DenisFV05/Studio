#!/usr/bin/env python3

import os
import platform
import random

"""
Jocs, Daus (amb llistes)

Aquest joc consisteix en:

- Es tiren dos daus cada torn, si la suma és parell, se sumen els punts del dau major i i si són imparells, es resten els del dau menor.

- Es tiren 50 vegades cadascun els daus i el que major puntuació treu, guanya.

- Si guanya l'usuari, s'afegeix el jugador i la seva puntuació al ranking.

- El ranking haurà d’estar ordenat.

- Al final de cada partida, caldrà resetejar el nom de l'usuari.

En aquesta versió el ranking es guarda en una llista d'aquest estil:

[["pepe",150],["Juan",100],["Pablo",50]]

"""

# Aquesta funció neteja la pantalla, no la modifiquis
def clearScreen():
    if os.name == 'nt':     # Si estàs a Windows
        os.system('cls')
    else:                   # Si estàs a Linux o macOS
        os.system('clear')

clearScreen()

def afegir_jugador(ranking, nom_jugador, nova_puntuacio):
    """
    Afegeix un nou jugador al rànquing o actualitza la seva puntuació si és més alta que l'actual.

    Paràmetres:
        ranking (list[list]): Llista que conté el rànquing de jugadors, on cada element és una llista amb el nom del jugador i la seva puntuació.
        nom_jugador (str): Nom del jugador que s'afegirà o actualitzarà al rànquing.
        nova_puntuacio (int): Puntuació del jugador.

    Retorna:
        list[list]: Retorna el rànquing actualitzat.

    Comportament:
        - Comprova si el jugador ja és al rànquing.
        - Si hi és, actualitza la seva puntuació només si la `nova_puntuacio` és més alta que l'actual.
        - Si no hi és, l'afegim amb la `nova_puntuacio`.

    Exemples:
        ranking = [["pepe", 150], ["Juan", 100]]
        afegir_jugador(ranking, "Pablo", 50)
        ranking serà [["pepe", 150], ["Juan", 100], ["Pablo", 50]]

        afegir_jugador(ranking, "Juan", 120)
        ranking serà [["pepe", 150], ["Juan", 120], ["Pablo", 50]]
    """
    pass

def escollir_jugador(ranking):
    """
    Permet a l'usuari escollir un jugador existent del rànquing.

    Paràmetres:
        ranking (list[list]): Llista que conté el rànquing de jugadors, on cada element és una llista amb el nom del jugador i la seva puntuació.

    Retorna:
        str: Retorna el nom del jugador escollit.

    Comportament:
        - Mostra una llista amb els noms dels jugadors disponibles.
        - L'usuari pot escollir un jugador introduint el número corresponent o escrivint el nom del jugador.
        - La funció no surt fins que es selecciona un jugador vàlid.
        - Retorna el nom del jugador seleccionat.

    Exemples:
        ranking = [["pepe", 150], ["Juan", 100]]
        escollir_jugador(ranking)
        "pepe" si l'usuari selecciona la primera opció o escriu "pepe".
    """
    pass


def mostrar_puntuacions(ranking):
    """
    Mostra les puntuacions actuals del rànquing de jugadors de manera ordenada.

    Paràmetres:
        ranking (list[list]): Llista que conté el rànquing de jugadors, on cada element és una llista amb el nom del jugador i la seva puntuació.

    Retorna:
        None: Aquesta funció no retorna res.

    Comportament:
        - Ordena el rànquing segons les puntuacions de manera descendent.
        - Mostra el rànquing amb els noms dels jugadors i les seves puntuacions en format tabular.

    Exemples:
        ranking = [["pepe", 150], ["Juan", 100], ["Pablo", 50]]
        mostrar_puntuacions(ranking)
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
        None: Aquesta funció no retorna res.

    Comportament:
        - Es tiren dos daus per torn, durant 50 torns per al jugador i l'ordinador.
        - Si la suma dels daus és parell, s'afegeixen els punts del dau major.
        - Si la suma dels daus és imparell, es resten els punts del dau menor.
        - El jugador o l'ordinador amb més punts després de 50 tirades guanya.
        - Al final, es mostra qui ha guanyat i amb quina puntuació.
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
        - Es poden escollir les opcions del menú per número [0, 1, 2, 3, 4]
        - Es poden escollir les opcions del menú per text ['afegir', 'escollir', 'jugar', 'puntuacions', 'ranking', 'sortir']

    Textos:
        "\nEscull una opció: ": Per escollir la opció del menú
        "\nIntrodueix el nom del nou jugador: "
        "\nNo hi ha jugadors al rànquing. Afegiu-ne un primer."
        "\nNo s'ha escollit cap jugador."
        "Opció no vàlida."

    Exemples:
        1) Afegir jugador
        X) Escollir jugador
        X) Jugar ()
        4) Mostrar puntuacions
        0) Sortir

        1) Afegir jugador
        2) Escollir jugador
        3) Jugar (Pedrito)
        4) Mostrar puntuacions
        0) Sortir
    """
    pass

# No canvieu això o no passarà el test
if __name__ == "__main__":
    mainRun()