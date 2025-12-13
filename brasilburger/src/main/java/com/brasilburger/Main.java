package com.brasilburger;

import com.brasilburger.utils.InputHelper;
import com.brasilburger.views.MenuView;
import com.brasilburger.views.ComplementView;
import com.brasilburger.views.BurgerView;

public class Main {

    public static void main(String[] args) {

        MenuView menuView = new MenuView();
        BurgerView burgerView = new BurgerView();
        ComplementView complementView = new ComplementView();

        int choix;

        do {
            InputHelper.clearConsole();
            InputHelper.afficherSeparateur("🍔 BRASIL BURGER - MENU PRINCIPAL");

            System.out.println("1. Gestion des menus");
            System.out.println("2. Gestion des burgers");
            System.out.println("3. Gestion des compléments");
            System.out.println("0. Quitter");
            System.out.println("═══════════════════════════════════════════════════");

            choix = InputHelper.lireEntier("Votre choix : ", 0, 3);

            switch (choix) {
                case 1 -> menuView.afficherMenu();
                case 2 -> burgerView.afficherMenu();
                case 3 -> complementView.afficherMenu();
                case 0 -> InputHelper.afficherInfo("Au revoir 👋");
            }

        } while (choix != 0);
    }
}
