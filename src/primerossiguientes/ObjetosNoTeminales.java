/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerossiguientes;

/**
 *
 * @author por_s
 */
public class ObjetosNoTeminales {

    private String objNT = "";
    private String primeros = "";
    private String siguientes = "";
    private boolean ambiguedad = false;

    public String getObjNT() {
        return objNT;
    }

    public void setObjNT(String objNT) {
        this.objNT = objNT;
    }

    public String getPrimeros() {
        return primeros;
    }

    public void setPrimeros(String primeros) {
        this.primeros = primeros;
    }

    public String getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(String siguientes) {
        this.siguientes = siguientes;
    }

    public boolean isAmbiguedad() {
        return ambiguedad;
    }

    public void setAmbiguedad(boolean ambiguedad) {
        this.ambiguedad = ambiguedad;
    }

}
