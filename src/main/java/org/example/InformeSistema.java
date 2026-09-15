package org.example;

public class InformeSistema {
    static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        long[] reservado = new long[8 * 1024 * 1024]; // 8 M · 8 bytes = 64 MiB

        int procesadores = runtime.availableProcessors();

        long mTotal = runtime.totalMemory();
        long mLibre = runtime.freeMemory();
        long mMax = runtime.maxMemory();
        long mUso = runtime.maxMemory() - runtime.freeMemory();


        System.out.println("PROCESADORES");
        System.out.println("=".repeat(33));
        System.out.println(procesadores);
    }
}