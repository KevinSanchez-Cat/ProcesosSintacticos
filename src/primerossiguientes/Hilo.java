package primerossiguientes;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.edisoncor.gui.progressBar.ProgressBarRect;

public class Hilo implements Runnable {

    org.edisoncor.gui.progressBar.ProgressBarRect pr;
    int pidos;
    boolean repe;
    int porcent=0;
    int numVariable;

    public Hilo() {
        pidos = 1000;
        repe = true;
        porcent = 0;
    }

    public Hilo(int pidos) {
        this.pidos = pidos;
        repe = true;
        porcent = 0;
    }

    public Hilo(int pido, boolean repe) {
        this.pidos = pido;
        this.repe = repe;
    }

    public ProgressBarRect getPr() {
        return pr;
    }

    public void setPr(ProgressBarRect pr) {
        this.pr = pr;
    }

    public int getPidos() {
        return pidos;
    }

    public void setPidos(int pidos) {
        this.pidos = pidos;
    }

    public boolean isRepe() {
        return repe;
    }

    public void setRepe(boolean repe) {
        this.repe = repe;
    }

    public int getPorcent() {
        return porcent;
    }

    public void setPorcent(int porcent) {
        this.porcent = porcent;
    }

    public int getNumVariable() {
        return numVariable;
    }

    public void setNumVariable(int numVariable) {
        this.numVariable = numVariable;
    }
    boolean b=true;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
    
    @Override
    public void run() {
        int progreso = 0;
        while(b) {
            progreso = ((porcent) * 100) / numVariable;
            if (progreso <= 7.6) {
                pr.setForeground(new java.awt.Color(153, 0, 0));
            }
            if (progreso > 7.6 && progreso <= 15.2) {
                pr.setForeground(new java.awt.Color(204, 0, 0));
            }
            if (progreso > 15.2 && progreso <= 22.8) {
                pr.setForeground(new java.awt.Color(255, 0, 0));
            }
            if (progreso > 22.8 && progreso <= 30.4) {
                pr.setForeground(new java.awt.Color(255, 51, 0));
            }
            if (progreso > 30.4 && progreso <= 38) {
                pr.setForeground(new java.awt.Color(255, 102, 0));
            }
            if (progreso > 38 && progreso <= 45.6) {
                pr.setForeground(new java.awt.Color(255, 153, 0));
            }
            if (progreso > 45.6 && progreso <= 53.2) {
                pr.setForeground(new java.awt.Color(255, 204, 0));
            }
            if (progreso > 53.2 && progreso <= 60.8) {
                pr.setForeground(new java.awt.Color(204, 204, 0));
            }
            if (progreso > 60.8 && progreso <= 68.4) {
                pr.setForeground(new java.awt.Color(102, 250, 0));
            }
            if (progreso > 68.4 && progreso <= 76) {
                pr.setForeground(new java.awt.Color(51, 204, 0));

            }
            if (progreso > 76 && progreso <= 83.6) {
                pr.setForeground(new java.awt.Color(102, 204, 0));
            }
            if (progreso > 83.6 && progreso <= 91.2) {
                pr.setForeground(new java.awt.Color(0, 204, 0));
            }
            if (progreso > 91.2 && progreso <= 100) {
                pr.setForeground(new java.awt.Color(0, 204, 51));
            }
            pr.setValue(progreso);
            try {
                Thread.sleep((long) 0.001);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void recibeBarra(org.edisoncor.gui.progressBar.ProgressBarRect pr) {
        this.pr = pr;
    }

}
