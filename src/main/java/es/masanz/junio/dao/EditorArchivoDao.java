package es.masanz.junio.dao;

import java.io.*;
import java.util.*;

public class EditorArchivoDao {

    private String archivo = "sprites.txt";

    // INSERTAR un nuevo sprite (añadir al archivo)
    public void insertarSprite(String nombre, int fila, int columna, String sprite) throws IOException {
        FileWriter fw = new FileWriter(archivo, true); // modo append
        fw.write(nombre + "," + fila + "," + columna + "," + sprite + "\n");
        fw.close();
    }

    // CONSULTAR un mapa a partir del archivo
    public void consultarMapa(String nombre, String[][] mapa) throws IOException {
        File f = new File(archivo);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        linea = br.readLine();
        while ( linea != null) {
            String[] partes = linea.split(",");
            if (partes.length == 4 && partes[0].equals(nombre)) {
                int fila = Integer.parseInt(partes[1]);
                int columna = Integer.parseInt(partes[2]);
                String sprite = partes[3];
                mapa[fila][columna] = sprite;
            }
        }
        br.close();
    }



    // ACTUALIZAR un sprite si existe (por nombre, fila y columna). Si no existe, se inserta.
    public boolean actualizarSprite(String nombre, int fila, int columna, String nuevoSprite) throws IOException {
        File f = new File(archivo);
        boolean actualizado = false;

        if (!f.exists()) {
            insertarSprite(nombre, fila, columna, nuevoSprite);
            return true;
        }

        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(",");
            if (partes.length == 4 &&
                    partes[0].equals(nombre) &&
                    Integer.parseInt(partes[1]) == fila &&
                    Integer.parseInt(partes[2]) == columna) {

                lineas.add(nombre + "," + fila + "," + columna + "," + nuevoSprite);
                actualizado = true;
            } else {
                lineas.add(linea);
            }
        }
        br.close();

        if (!actualizado) {
            lineas.add(nombre + "," + fila + "," + columna + "," + nuevoSprite);
        }

        FileWriter fw = new FileWriter(archivo, false); // sobrescribir
        for (String l : lineas) {
            fw.write(l + "\n");
        }
        fw.close();

        return true;
    }


    // TEST
    public static void main(String[] args) throws IOException {
        EditorArchivoDao dao = new EditorArchivoDao();

        dao.insertarSprite("Mapa1", 1, 2, "nombreDeSprite");
        dao.insertarSprite("Mapa1", 0, 0, "NombreDeSprite");

        String[][] mapa = new String[5][5];
        dao.consultarMapa("Mapa1", mapa);

        System.out.println("Mapa:");
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print((mapa[i][j] != null ? mapa[i][j] : "·") + " ");
            }
            System.out.println();
        }

        dao.actualizarSprite("Mapa1", 1, 2, "🔥");
    }
}
