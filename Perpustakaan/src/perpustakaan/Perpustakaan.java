/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpustakaan;

/**
 *
 * @author Acer
 */
public class Perpustakaan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelPerpustakaan MP=new ModelPerpustakaan();
        ViewPerpustakaan VP=new ViewPerpustakaan();
        ViewUpdateData VU=new ViewUpdateData();
        ControllerPerpustakaan CP=new ControllerPerpustakaan(VP, MP,VU);
    }
    
}
