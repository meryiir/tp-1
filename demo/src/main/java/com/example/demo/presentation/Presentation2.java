package com.example.demo.presentation;

import com.example.demo.dao.IDao;
import com.example.demo.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        // Lecture des noms des classes depuis le fichier de configuration
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();

        // Chargement de la classe DAO avec réflexion
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // Lecture du nom de la classe Métier
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // Injection de la DAO dans le Métier
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        // Calcul et affichage
        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}