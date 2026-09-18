# Tarea 01: Radiografía del sistema

**Alumno:** [Tu Nombre]  
**Curso:** CFGS DAM2 (2026-2027)  
**Módulo:** Programación de Servizos e Procesos

---

## 2. El proceso, desde fuera

### PID, PPID y Proceso Padre
* **PID (Process ID):** Identificador único del proceso en ejecución.
* **PPID (Parent Process ID):** Identificador del proceso padre que lo ha instanciado.
* **Explicación del Proceso Padre:**
  Al ejecutar la aplicación desde la línea de comandos, el **PPID** pertenece al proceso del intérprete de comandos (la shell, como `bash` o `zsh`). La shell es quien invoca la llamada al sistema (`fork` + `exec`) para lanzar la Máquina Virtual de Java (`java`).

### Comparación: Ejecución desde Terminal vs. IDE
* **¿Cambia el PPID?** **Sí**, cambia.
* **Explicación:**
    * **Desde la Terminal:** El padre directo es el PID de la propia shell/terminal.
    * **Desde el IDE (IntelliJ / Eclipse / VS Code):** El proceso padre es el propio proceso del IDE (o del gestor de construcción asociado, como Maven o Gradle). Cada entorno que invoca la JVM se registra como su proceso ejecutor directo.

### Límite de Memoria: `java -Xmx128m InformeSistema`
Al aplicar el parámetro `-Xmx128m`, limitamos el tamaño máximo de la memoria Heap a 128 MiB.

* **Cifras que cambian:**
    * **Memoria Máxima (`maxMemory()`):** Pasa de ser un valor alto dependiente del hardware predeterminado (ej. varios GiB) a fijarse estrictamente en aproximadamente `128 MiB` (~134.217.728 bytes).
* **Cifras que NO cambian (o varían de forma inapreciable):**
    * **Memoria Total reservada (`totalMemory()`):** Permanece similar o ligeramente menor al inicio, ya que representa la memoria inicial que la JVM ha solicitado al Sistema Operativo.
    * **Memoria Libre (`freeMemory()`) y En Uso:** Mantienen comportamientos similares al arranque, pues el consumo de la aplicación al instanciarse no cambia.
* **Justificación:** El parámetro `-Xmx` únicamente limita el techo máximo (*Heap limit*) que la JVM puede solicitar al Sistema Operativo, pero no altera el consumo inicial ni la memoria que la JVM reserva de partida.

### Rutas Multiplataforma
* **Ruta generada en Linux/macOS:**
  `/home/usuario/psp/informe.txt`
* **Ruta generada en Windows:**
  `C:\Users\usuario\psp\informe.txt`
* **Origen de la diferencia:**  
  La diferencia proviene de la propiedad del sistema `file.separator`. En entornos UNIX/Linux se utiliza la barra diagonal (`/`), mientras que en Windows se utiliza la barra invertida (`\`) heredada de MS-DOS. La llamada a `System.getProperty("user.home")` y `System.getProperty("file.separator")` permite construir rutas dinámicas adaptadas al Sistema Operativo ejecutor.

---

## 3. Qué tipo de programación encaja

### a) Un servidor web que atiende 500 peticiones a la vez en una máquina de 8 núcleos.
* **Tipos de programación que encajan:** **Programación Concurrente y Paralela.**
* **Justificación:**
    * **Concurrente:** A nivel de aplicación, se gestionan 500 hilos/peticiones intercalando su tiempo de entrada/salida (I/O) y cómputo.
    * **Paralela:** A nivel del procesador, al disponer de 8 núcleos físicos, se pueden procesar hasta 8 de esas peticiones de forma simultánea y real en el hardware.
* **Inconveniente:**  
  **Condiciones de carrera y sincronización:** Al compartir memoria entre hilos (como sesiones o bases de datos), se pueden producir inconsistencias si dos peticiones modifican el mismo recurso al mismo tiempo.

---

### b) Renderizar una película de animación en un plazo de tres meses.
* **Tipo de programación que encaja:** **Programación Distribuida (Granja de renderizado / Render farm).**
* **Justificación:**  
  Renderizar una película requiere un volumen de cálculo abrumador que no puede completarse a tiempo en una sola máquina. Se divide la película en fotogramas independientes y se distribuyen entre cientos de nodos físicamente separados conectados por red.
* **Inconveniente:**  
  **Latencia y sobrecoste de red:** La coordinación entre nodos y la transferencia de modelos/texturas a través de la red introducen cuellos de botella y fallos parciales si un nodo se desconecta.

---

### c) Una app de móvil que descarga un fichero mientras seguís navegando.
* **Tipo de programación que encaja:** **Programación Concurrente.**
* **Justificación:**  
  Se ejecutan dos tareas en segundo plano (hilo principal para la UI/navegación e hilo secundario para la descarga en red). No requiere paralelismo real para funcionar correctamente; el objetivo principal es evitar que la interfaz de usuario se bloquee mientras se espera por la red.
* **Inconveniente:**  
  **Complejidad en el manejo de hilos de UI:** La modificación de componentes gráficos desde un hilo secundario suele lanzar excepciones; se requiere una sincronización explícita para devolver los resultados al hilo de la interfaz gráfica.

---

### d) Un cálculo que no cabe en la RAM de un solo equipo.
* **Tipo de programación que encaja:** **Programación Distribuida.**
* **Justificación:**  
  Dado que la memoria RAM física de un solo equipo es insuficiente, es imprescindible utilizar un clúster de máquinas (como Apache Spark). El conjunto de datos se fragmenta y la memoria total utilizada es la suma de la RAM de cada uno de los nodos del clúster.
* **Inconveniente:**  
  **Pérdida de rendimiento por comunicación I/O (Paso de mensajes):** El acceso a datos remotos mediante la red es órdenes de magnitud más lento que acceder a la memoria RAM local.