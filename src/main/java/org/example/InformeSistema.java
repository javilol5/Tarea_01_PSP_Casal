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


    }
}