class Proceso implements Comparable<Proceso> {
    private String nombre;
    private String usuario;
    private int nice;
    private int priority;

    public Proceso(String nombre, String usuario, int nice, int priority) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.nice = nice;
        this.priority = priority;
    }

    @Override
    public int compareTo(Proceso otro) {
        return Integer.compare(this.priority, otro.priority);
    }

    @Override
    public String toString() {
        return nombre + "," + usuario + "," + nice + ",PR=" + priority;
    }
}
