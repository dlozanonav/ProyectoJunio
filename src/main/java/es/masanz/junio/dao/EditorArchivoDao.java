package es.masanz.junio.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

public class EditorArchivoDao {
    public static void main(String[] args) throws IOException {
        EditorArchivoDao dao = new EditorArchivoDao();
        dao.insertarSprite("HOla",2,3,"asfafasffas");
    }
    String archivo = "sprites.txt";

    public void insertarSprite(String nombre, int fila, int columna, String sprite) throws IOException {

        FileWriter fw= new FileWriter(archivo);
        fw.write(nombre+","+fila+","+columna+","+sprite+"\n");
        fw.close();
    }

    //public boolean actualizarSprite(String nombre, int fila, int columna, String sprite){
    //
    //        boolean exito = false;
    //        try {
    //            String sql = "UPDATE editor_mapa SET sprite = ? WHERE nombre like ? AND fila = ? AND columna = ?";
    //            PreparedStatement pst = conexion.prepareStatement(sql) ;
    //            pst.setString(1, sprite);
    //            pst.setString(2, nombre);
    //            pst.setInt(3, fila);
    //            pst.setInt(4, columna);
    //            int numero = pst.executeUpdate();
    //            if(numero>0){
    //                exito = true;
    //            }
    //        } catch (SQLException e) {
    //            throw new RuntimeException(e);
    //        }
    //
    //        return exito;
    //    }
 //   public boolean actualizarSprite(String nombre, int fila, int columna, String sprite) throws IOException {
   //     HashMap<String, List<String>> MapaDatos= new HashMap<>();
     //   FileWriter fw= new FileWriter(archivo);
       // MapaDatos.put();

    //}

}
