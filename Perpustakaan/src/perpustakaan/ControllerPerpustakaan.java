/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class ControllerPerpustakaan {
    ViewPerpustakaan VP = new ViewPerpustakaan();
    ModelPerpustakaan MP = new ModelPerpustakaan();
    ViewUpdateData VU=new ViewUpdateData();
    public ControllerPerpustakaan(ViewPerpustakaan VP, ModelPerpustakaan MP, ViewUpdateData VU){
        this.VP=VP;
        this.MP=MP;
        VP.setVisible(true);
        showData();
        this.VU=VU;
        VP.JButtonInput.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int id_buku=VP.getIdBuku();
                String nama_buku=VP.getNamaBuku();
                String penulis=VP.getPenulis();
                int tahun_terbit=VP.getTahunTerbit();
                boolean id=MP.cekid(VP.getIdBuku());
                if(!id){
                    MP.inputdata(id_buku, nama_buku, penulis, tahun_terbit);
                }else{
                    JOptionPane.showMessageDialog(null, "Id Sudah Terpakai!");
                }
            }
        });
        VP.ButtonLoad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showData();
                ResetData();
            }
        });
        VP.TabelPerpustakaan.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                super.mouseClicked(e);
                int row = VP.TabelPerpustakaan.getSelectedRow();
                String id = VP.TabelPerpustakaan.getValueAt(row, 0).toString();
                String nama = VP.TabelPerpustakaan.getValueAt(row, 1).toString();
                String penulisbuku = VP.TabelPerpustakaan.getValueAt(row, 2).toString();
                String tahun = VP.TabelPerpustakaan.getValueAt(row, 3).toString();
                int input = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu mau hapus buku dengan id buku = " +id+ "?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                
                if(input==0){
                    MP.hapusdata(Integer.parseInt(id));
                    showData();
                }
                else{
                    int input1 = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu mau update buku dengan id buku = "+ id + "?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                
                    if(input1==0){
                        VU.setVisible(true);
                        VU.TFIdBuku.setText(id);
                        VU.TFIdBuku.setEditable(false);
                        VU.TFNamaBuku.setText(nama);
                        VU.TFPenulis.setText(penulisbuku);
                        VU.TFTahun.setText(tahun);
                    }
                }
            }
        });
        VU.jButtonSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = VU.getIdBuku();
                String nama = VU.getNamaBuku();
                String penulisbuku = VU.getPenulis();
                int tahun = VU.getTahunTerbit();
                MP.updatedata(id, nama, penulisbuku, tahun);
                VU.setVisible(false);
            }
            
        });
    }
    void showData(){
        String[][] datatabel=MP.readData();
        String[] namakolom={"Id Buku","Nama Buku","Penulis","Tahun Terbit"};
        VP.TabelPerpustakaan.setModel((new JTable(datatabel,namakolom)).getModel());
    }
    void ResetData(){
        VP.TFIdBuku.setText("");
        VP.TFNamaBuku.setText("");
        VP.TFPenulis.setText("");
        VP.TFTahun.setText("");     
    }
    
}
