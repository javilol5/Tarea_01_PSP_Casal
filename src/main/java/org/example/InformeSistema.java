package org.example;

public class InformeSistema {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();



        int procesadores = runtime.availableProcessors();

        long mTotal = runtime.totalMemory();
        long mLibre = runtime.freeMemory();
        long mMax = runtime.maxMemory();
        long mUso = runtime.totalMemory() - runtime.freeMemory();
        int mib = 1024 * 1024;
        long mPorciento = mUso * 100 / mTotal;

        System.out.println("PROCESADORES");
        System.out.println("=".repeat(33));
        System.out.println("Disponibles JVM: " + procesadores);
        System.out.println("(son hilos lógicos: con SMT no coinciden con los núcleos físicos)");
        System.out.println();
        System.out.println("MEMORIA · ANTES");
        System.out.println("=".repeat(33));
        System.out.println("Total reservada: " + mTotal/mib + " MiB");
        System.out.println("Libre:           " + mLibre/mib + " MiB");
        System.out.println("En uso:          " + mUso/mib + " MiB (" + mPorciento + " % de la total)");
        System.out.println("Máxima (-Xmx)    " + mMax/mib + " MiB");
        System.out.println();

        long[] reservado = new long[8 * 1024 * 1024]; // 8 M · 8 bytes = 64 MiB

        long m2Total = runtime.totalMemory();
        long m2Libre = runtime.freeMemory();
        long m2Max = runtime.maxMemory();
        long m2Uso = runtime.totalMemory() - runtime.freeMemory();
        long m2Porciento = m2Uso * 100 / m2Total;

        System.out.println("MEMORIA · DESPUÉS DE RESERVAR 64 MIB");
        System.out.println("=".repeat(33));
        System.out.println("Total reservada: " + m2Total/mib + " MiB");
        System.out.println("Libre:           " + m2Libre/mib + " MiB");
        System.out.println("En uso:          " + m2Uso/mib + " MiB (" + m2Porciento + " % de la total)");
        System.out.println("Máxima (-Xmx)    " + m2Max/mib + " MiB");
        System.out.println("Incremento en uso: " + (m2Uso - mUso)/mib +" MiB");
        System.out.println("(el array sigue en memoria: reservado[0] = "+reservado[0]+")");
        System.out.println();

        String sOperativo = System.getProperty("os.name");
        String sSeparador = System.getProperty("file.separator");
        String sRuta = System.getProperty("user.home");

        System.out.println("SISTEMA");
        System.out.println("=".repeat(33));
        System.out.println("os.name:        " + sOperativo);
        System.out.println("file.separator: \"" + sSeparador + "\"");
        System.out.println("Ruta construida con las propiedades:");
        System.out.println(sRuta + sSeparador + "InformeSistema.java");
        System.out.println();

        String javaVersion = System.getProperty("java.version");
        String javaVersionDate = System.getProperty("java.version.date");
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String userCountry = System.getProperty("user.country");
        String userDir = System.getProperty("user.dir");
        String userHome = System.getProperty("user.home");
        String userLanguage = System.getProperty("user.language");
        String userName = System.getProperty("user.name");

        System.out.println("PROPIEDADES QUE EMPIEZAN POR os., user., java.version");
        System.out.println("=".repeat(33));
        System.out.println("java.version = " + javaVersion);
        System.out.println("java.version.date = " + javaVersionDate);
        System.out.println("os.arch = " + osArch);
        System.out.println("os.name = " + osName);
        System.out.println("os.version = " + osVersion);
        System.out.println("user.country = " + userCountry);
        System.out.println("user.dir = " + userDir);
        System.out.println("user.home = " + userHome);
        System.out.println("user.language = " + userLanguage);
        System.out.println("user.name = " + userName);
        System.out.println();

        System.out.println("PROCESO EN ESPERA");
        System.out.println("=".repeat(33));
        System.out.println("Buscame desde otra terminal con:");
        System.out.println("ps -ef | grep InformeSistema");
        System.out.println("Pulsa INTRO para terminar...");
        System.out.println("Fin del programa.");





    }
}