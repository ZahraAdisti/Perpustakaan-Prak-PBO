/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ModelPerpustakaan {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    static final String USER = "root";
    static final String PASS = ""; 
    public Connection koneksi;
    public Statement statement;
    int jmlData;
    public ModelPerpustakaan() {
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (java.sql.Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
        
    }
    public boolean cekid(int id_buku){
        try{
            String query = "Select COUNT(*) as hitung from databuku WHERE id_buku='"+id_buku+"'";
            statement = koneksi.createStatement();
            ResultSet total=statement.executeQuery(query);
            while(total.next())
            {
                if(total.getString("hitung")!="0")
                {
                    return false;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    void hapusdata(int id_buku){
         try{
            String query = "DELETE from databuku WHERE id_buku='"+id_buku+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Hapus Berhasil");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Hapus Gagal");
        } 
    }
    void inputdata(int id_buku, String nama_buku, String penulis, int tahun_terbit){
        try {
            String query = "INSERT INTO `databuku`(`id_buku`, `nama_buku`,`penulis`,`tahun_terbit`) VALUES ('"+id_buku+"','"+nama_buku+"','"+penulis+"','"+tahun_terbit+"')";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            System.out.println("Insert Berhasil");
            JOptionPane.showMessageDialog(null,"Insert Berhasil !!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
     }
    void updatedata(int id_buku, String nama_buku, String penulis, int tahun_terbit){
         try {
            String query = "UPDATE databuku SET nama_buku='"+nama_buku+"', penulis='"+penulis+"', tahun_terbit='"+tahun_terbit+"' WHERE id_buku='"+id_buku+"'";
            
        
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            System.out.println("Update Berhasil");
            JOptionPane.showMessageDialog(null,"Update Berhasil !!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } 
        
    }
    String[][] readData(){
         String[][] data=new String[jumlahData()][4];
        try{
          int totaldata = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
          String query = "Select * from `databuku`"; //proses pengambilan data
          statement = koneksi.createStatement();
          ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
          while(resultSet.next()){ //konversi tabel ke string
              data[totaldata][0] = resultSet.getString("id_buku"); 
              data[totaldata][1] = resultSet.getString("nama_buku"); 
              data[totaldata][2] = resultSet.getString("penulis");
              data[totaldata][3] = resultSet.getString("tahun_terbit");
              totaldata++; 
          }
      }catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL Error");
      }
      return data;
    }
    int jumlahData()
    {
        try{
            jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "Select * from `databuku`"; //proses pengambilan data
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                jmlData++; 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return jmlData;
      }
}
