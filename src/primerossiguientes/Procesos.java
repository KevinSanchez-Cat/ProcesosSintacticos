/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerossiguientes;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.edisoncor.gui.progressBar.ProgressBarRect;

/**
 *
 * @author por_s
 */
public final class Procesos {

    int numGrma = 0;
    org.edisoncor.gui.progressBar.ProgressBarRect pr;
    private String pathGramatica;
    private LDL listaGramatica;
    private String[][] tablaPredictiva;
    private String[] objT;
    private String[] objNT;
    ArrayList<ObjetosNoTeminales> objPyS;
    ArrayList<ConjuntoA> conjuntos;

    public Procesos() {

    }

    public Procesos(String pathGramatica) {
        objPyS = new ArrayList<>();
        conjuntos = new ArrayList<>();
        this.pathGramatica = pathGramatica;
        this.extraerGramatica();
        try {
            this.buscarObjetosNoTeminales();
            this.buscarObjetosTerminales();

        } catch (Exception e) {
            Mensaje.error(null, "Ha ocurrido un error" + e);

        }

    }

    public ProgressBarRect getPr() {
        return pr;
    }

    public void setPr(ProgressBarRect pr) {
        this.pr = pr;
    }

    public ArrayList<ConjuntoA> getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(ArrayList<ConjuntoA> conjuntos) {
        this.conjuntos = conjuntos;
    }

    public ArrayList<ObjetosNoTeminales> getObjPyS() {
        return objPyS;
    }

    public void setObjPyS(ArrayList<ObjetosNoTeminales> objPyS) {
        this.objPyS = objPyS;
    }

    public String getPathGramatica() {
        return pathGramatica;
    }

    public void setPathGramatica(String pathGramatica) {
        this.pathGramatica = pathGramatica;
    }

    public LDL getListaGramatica() {
        return listaGramatica;
    }

    public void setListaGramatica(LDL listaGramatica) {
        this.listaGramatica = listaGramatica;
    }

    public String[][] getTablaPredictiva() {
        return tablaPredictiva;
    }

    public void setTablaPredictiva(String[][] tablaPredictiva) {
        this.tablaPredictiva = tablaPredictiva;
    }

    public String[] getObjT() {
        return objT;
    }

    public void setObjT(String[] objT) {
        this.objT = objT;
    }

    public String[] getObjNT() {
        return objNT;
    }

    public void setObjNT(String[] objNT) {
        this.objNT = objNT;
    }

    public int primeros() {
        int error = 0;

        return error;
    }

    /**
     * Método que extrae la gramatica a partir de un archivo txt
     *
     */
    public void extraerGramatica() {
        String s = ManipulaArchivos.cargarArchivo(getPathGramatica());
        StringTokenizer st = new StringTokenizer(s, " \n\t\r", true);
        String cadena;
        ArrayList<String> palabras = new ArrayList<>();
        while (st.hasMoreElements()) {
            cadena = st.nextToken();
            if (cadena.equals(" ") || cadena.equals("\n") || cadena.equals("\t") || cadena.equals("\r")) {

            } else {
                palabras.add(cadena);
            }
        }
        LDL lisaGramatica = new LDL();
        int cont = 1;
        int i = 0;
        do {
            if ((i + 1) < palabras.size()) {
                if (palabras.get(i + 1).equals("→")) {
                    Nodo n = new Nodo(palabras.get(i), cont);
                    i = i + 2;

                    PilaD pilaG = new PilaD();
                    while (i < palabras.size()) {
                        pilaG.inserta(new Nodo(palabras.get(i).trim(), -1), null);
                        i++;
                        if ((i + 1) < palabras.size()) {
                            if (palabras.get(i + 1).equals("→")) {
                                i--;
                                break;
                            }
                        }
                    }

                    n.setP(pilaG);
                    lisaGramatica.inserta(n, null);
                    cont++;
                    numGrma++;
                }
            }
            i++;
        } while (i < palabras.size());
        setListaGramatica(lisaGramatica);
    }

    private void buscarObjetosNoTeminales() {

        LDL lista = getListaGramatica();
        Nodo n = lista.getR();
        while (n != null) {
            if (n.getS().startsWith("<")) {
                setObjNT(agregarObj(n.getS().trim(), objNT));
            }
            n = n.getSig();
        }
        setObjNT(eliminarRepetidos(objNT));
    }

    private void buscarObjetosTerminales() {
        LDL auxLista = getListaGramatica();

        Nodo n = auxLista.getR();

        while (n != null) {

            PilaD pilaAux1 = n.getP();
            PilaD pilaAux2 = new PilaD();
            while (pilaAux1.getTope() != null) {
                Nodo aux = pilaAux1.elimina(null);

                pilaAux2.inserta(aux, null);
            }
            while (pilaAux2.getTope() != null) {
                Nodo aux = pilaAux2.elimina(null);
                if (!aux.getS().startsWith("<")) {
                    setObjT(agregarObj(aux.getS().trim(), objT));
                }
                pilaAux1.inserta(aux, null);
            }

            n = n.getSig();
        }
        setObjT(eliminarRepetidos(objT));

    }

    private String[] agregarObj(String s, String[] obj) {
        String nvo[] = null;

        if (obj == null) {
            nvo = new String[1];
            nvo[0] = s;
        } else {
            nvo = new String[obj.length + 1];
            System.arraycopy(obj, 0, nvo, 0, obj.length);
            nvo[obj.length] = s;

        }

        return nvo;

    }

    private String[] eliminarRepetidos(String[] nvo) {

        for (int i = 0; i < nvo.length; i++) {
            for (int j = 0; j < nvo.length; j++) {
                if (i != j) {
                    if (nvo[i].equals(nvo[j])) {
                        // eliminamos su valor
                        nvo[j] = "";
                    }
                }

                /*if (nvo[j].compareTo(nvo[j + 1]) > 0) {
                    String temp = nvo[j];
                    nvo[j] = nvo[j + 1];
                    nvo[j + 1] = temp;
                }*/
            }
        }

        String s[] = null;
        int cont = 0;
        for (int i = 0; i < nvo.length; i++) {
            if (nvo[i].equals("")) {
                cont++;
            }
        }
        s = new String[nvo.length - cont];

        int h = 0;
        for (int i = 0; i < nvo.length; i++) {

            if (nvo[i] != "") {

                s[h] = nvo[i];
                h++;
            }
        }

        return s;
    }

    private String[] eliminarRepetidos2(String[] nvo) {
        String s[] = null;

        int j = 0;
        int i = 0;
        int cont = 0;

        for (int k = 0; k < nvo.length; k++) {
            if (k + 1 < nvo.length) {
                if (nvo[k].equals(nvo[k + 1])) {
                    cont++;
                }
            }
        }

        s = new String[nvo.length - cont];

        while (i < nvo.length) {

            if ((i + 1) != nvo.length) {
                if (nvo[i].equals(nvo[i + 1])) {

                } else {
                    s[j] = nvo[i];
                    j++;
                }

            }
            if (i + 1 == nvo.length) {

                s[j] = nvo[i];
                j++;

            }
            i++;
        }

        return s;
    }
    int seg = 0;
    int objNa = 0;
    public Thread procesosPrimeros = new Thread() {

        public void run() {
            try {
                objNa = getObjNT().length;
                for (int i = 0; i < getObjNT().length; i++) {
                    ObjetosNoTeminales n = new ObjetosNoTeminales();
                    n.setObjNT(getObjNT()[i]);
                    n.setPrimeros(buscaPrimeros(getObjNT()[i]));
                    objPyS.add(n);

                    seg = ((i) * 100) / objNa;
                    if (seg <= 7.6) {
                        pr.setForeground(new java.awt.Color(153, 0, 0));
                    }
                    if (seg > 7.6 && seg <= 15.2) {
                        pr.setForeground(new java.awt.Color(204, 0, 0));
                    }
                    if (seg > 15.2 && seg <= 22.8) {
                        pr.setForeground(new java.awt.Color(255, 0, 0));
                    }
                    if (seg > 22.8 && seg <= 30.4) {
                        pr.setForeground(new java.awt.Color(255, 51, 0));
                    }
                    if (seg > 30.4 && seg <= 38) {
                        pr.setForeground(new java.awt.Color(255, 102, 0));
                    }
                    if (seg > 38 && seg <= 45.6) {
                        pr.setForeground(new java.awt.Color(255, 153, 0));
                    }
                    if (seg > 45.6 && seg <= 53.2) {
                        pr.setForeground(new java.awt.Color(255, 204, 0));
                    }
                    if (seg > 53.2 && seg <= 60.8) {
                        pr.setForeground(new java.awt.Color(204, 204, 0));
                    }
                    if (seg > 60.8 && seg <= 68.4) {
                        pr.setForeground(new java.awt.Color(102, 250, 0));
                    }
                    if (seg > 68.4 && seg <= 76) {
                        pr.setForeground(new java.awt.Color(51, 204, 0));

                    }
                    if (seg > 76 && seg <= 83.6) {
                        pr.setForeground(new java.awt.Color(102, 204, 0));
                    }
                    if (seg > 83.6 && seg <= 91.2) {
                        pr.setForeground(new java.awt.Color(0, 204, 0));
                    }
                    if (seg > 91.2 && seg <= 100) {
                        pr.setForeground(new java.awt.Color(0, 204, 51));
                    }
                    pr.setValue(seg);
                    procesosPrimeros.sleep((long) 0.001);
                }
                pr.setValue(0);
                seg = 0;
                objNa = 0;
                Mensaje.exito(null, "Proceso realizado con exito");

                for (int i = 0; i < objPyS.size(); i++) {

                    String primeros = objPyS.get(i).getPrimeros();
                    StringTokenizer st = new StringTokenizer(primeros, " ", true);
                    String s1 = "";
                    String[] s2 = null;

                    while (st.hasMoreElements()) {
                        s1 = st.nextToken();
                        s2 = agregarObj(s1, s2);
                    }
                    if (s2 != null) {
                        s2 = eliminarRepetidos(s2);
                        primeros = "";
                        for (int j = 0; j < s2.length; j++) {
                            primeros += " " + s2[j];
                        }

                    }
                    objPyS.get(i).setPrimeros(primeros);
                }
            } catch (InterruptedException e) {
            }
        }
    };

    public void procesoPrimeros() {

        for (int i = 0; i < getObjNT().length; i++) {
            ObjetosNoTeminales n = new ObjetosNoTeminales();
            n.setObjNT(getObjNT()[i]);
            n.setPrimeros(buscaPrimeros(getObjNT()[i]));
            objPyS.add(n);

        }
        for (int i = 0; i < objPyS.size(); i++) {

            String primeros = objPyS.get(i).getPrimeros();
            StringTokenizer st = new StringTokenizer(primeros, " ", true);
            String s1 = "";
            String[] s2 = null;

            while (st.hasMoreElements()) {
                s1 = st.nextToken();
                s2 = agregarObj(s1, s2);
            }
            if (s2 != null) {
                s2 = eliminarRepetidos(s2);
                primeros = "";
                for (int j = 0; j < s2.length; j++) {
                    primeros += " " + s2[j];
                }

            }
            objPyS.get(i).setPrimeros(primeros);
        }
    }

    public String buscaPrimeros(String objNT) {
        String s = "";
        Nodo aux = getListaGramatica().getR();

        while (aux != null) {
            if (aux.getS().equals(objNT)) {
                System.out.println(aux.getS());

                PilaD pilaAux1 = aux.getP();
                PilaD pilaAux2 = new PilaD();
                LDL lista = new LDL();
                Nodo aux1 = null;
                String s2 = "";
                while (pilaAux1.getTope() != null) {
                    aux1 = pilaAux1.elimina(null);
                    pilaAux2.inserta(aux1, null);

                }
                while (pilaAux2.getTope() != null) {
                    Nodo aux2 = pilaAux2.elimina(null);
                    pilaAux1.inserta(aux2, null);
                    lista.inserta(new Nodo(aux2.getS(), -1), null);
                }
                Nodo raton = lista.getR();
                while (raton != null) {

                    s2 = raton.getS();
//                    System.out.println(s2);
                    boolean b = false;
                    if (!s2.startsWith("<")) {
                        s += " " + s2;
                        break;
                    } else {
                        if (!s2.equals(objNT)) {

                            String pa = buscaPrimeros(s2);

                            StringTokenizer st = new StringTokenizer(pa, " λ", true);
                            while (st.hasMoreElements()) {
                                if (st.nextToken().equals("λ")) {
                                    b = true;
                                }
                            }
                            StringTokenizer st2 = new StringTokenizer(pa, " λ", true);
                            pa = "";
                            while (st2.hasMoreElements()) {
                                String sas = st2.nextToken();
                                if (sas.equals("λ")) {

                                } else {
                                    pa += " " + sas + " ";
                                }
                            }
                            s += " " + pa;
                        } else {
                            break;
                        }

                    }

                    if (b == false) {
                        break;
                    }
                    raton = raton.getSig();
                }
            }
            aux = aux.getSig();

        }

        return s;
    }

    public boolean procesoSiguientes(String inicio) {

        Nodo com = getListaGramatica().getR();
        Nodo aux = getListaGramatica().getR();
        boolean b = false;
        while (com != null) {
            if (com.getS().equals(inicio.trim())) {
                b = true;
            }
            com = com.getSig();
        }
        if (b == false) {
            return false;
        }

        LDL lista = null;
        while (aux != null) {
            PilaD pilaAux1 = aux.getP();
            PilaD pilaAux2 = new PilaD();
            PilaD pilaAux3 = new PilaD();
            lista = new LDL();
            while (pilaAux1.getTope() != null) {
                Nodo aux1 = pilaAux1.elimina(null);
                pilaAux2.inserta(aux1, null);
            }
            while (pilaAux2.getTope() != null) {
                Nodo aux1 = pilaAux2.elimina(null);
                pilaAux1.inserta(new Nodo(aux1.getS(), -1), null);
                pilaAux3.inserta(new Nodo(aux1.getS(), -1), null);
                lista.inserta(new Nodo(aux1.getS(), -1), null);
            }
            aux.setP(pilaAux3);
            Nodo r = lista.getR();
            while (r != null) {
                if (r.getS().startsWith("<")) {
                    ConjuntoA c = new ConjuntoA();
                    c.setA(aux.getS());
                    c.setB(r.getS());
                    if (r.getAnt() == null) {
                        c.setAlpha("λ");
                    } else {
                        c.setAlpha(r.getAnt().getS());
                    }
                    if (r.getSig() != null) {

                        c.setBetha(r.getSig().getS());
                    } else {
                        c.setBetha("λ");
                    }
                    conjuntos.add(c);
                }
                r = r.getSig();
            }

            aux = aux.getSig();

        }
        regla01(inicio);

        return true;
    }

    private String extraePrimeros(String obNT) {
        String s = "";
        for (int i = 0; i < objPyS.size(); i++) {
            if (obNT.equals(objPyS.get(i).getObjNT())) {
                s = objPyS.get(i).getPrimeros();
            }
        }

        return s;
    }

    private String extraeSiguientes(String obNT) {
        String s = "";
        for (int i = 0; i < objPyS.size(); i++) {
            if (obNT.equals(objPyS.get(i).getObjNT())) {
                s = objPyS.get(i).getSiguientes();
            }
        }
        return s;
    }

    private void regla01(String inicio) {
        for (int i = 0; i < objPyS.size(); i++) {
            if (objPyS.get(i).getObjNT().equals(inicio)) {
                objPyS.get(i).setSiguientes("$");
            }
        }
        regla02(inicio);
    }

    private void regla02(String inicio) {
        for (int i = 0; i < conjuntos.size(); i++) {

            String p = "";
            if (!conjuntos.get(i).getBetha().equals("λ")) {

                if (conjuntos.get(i).getBetha().startsWith("<")) {
                    p += extraePrimeros(conjuntos.get(i).getBetha());
                    StringTokenizer st = new StringTokenizer(p, " λ", true);
                    p = "";
                    String s4 = "";
                    boolean centinela = false;
                    while (st.hasMoreElements()) {
                        s4 = st.nextToken();
                        if (s4.equals("λ")) {
                            p += " ";
                            centinela = true;
                        } else {
                            p += " " + s4 + " ";
                        }
                    }
                    if (centinela) {
                        p += buscaElSiguiente(i, conjuntos.get(i).getBetha());
                    }
                } else {
                    p += " " + conjuntos.get(i).getBetha() + " ";
                }

            }

            for (int j = 0; j < objPyS.size(); j++) {
                if (objPyS.get(j).getObjNT().equals(conjuntos.get(i).getB())) {
                    p += objPyS.get(j).getSiguientes();
                    objPyS.get(j).setSiguientes(p);
                }
            }

        }

        regla03(inicio);
    }

    public String buscaElSiguiente(int i, String objNT) {
        String s = "";
        for (int j = i++; j < conjuntos.size(); j++) {
            if (objNT.equals(conjuntos.get(j).getB())) {

                if (conjuntos.get(j).getBetha().equals("λ")) {
                    break;
                } else if (conjuntos.get(j).getBetha().startsWith("<")) {
                    s += extraePrimeros(conjuntos.get(j).getBetha());
                    StringTokenizer st = new StringTokenizer(s, " λ", true);
                    s = "";
                    String s4 = "";
                    boolean centinela = false;
                    while (st.hasMoreElements()) {
                        s4 = st.nextToken();
                        if (s4.equals("λ")) {
                            s += " ";
                            centinela = true;
                        } else {
                            s += " " + s4 + " ";
                        }
                    }
                    if (centinela) {
                        buscaElSiguiente(j, conjuntos.get(j).getBetha());
                    }
                } else {
                    return conjuntos.get(i).getBetha();
                }

            }
        }
        return s;
    }

    private void regla03(String inicio) {
        for (int i = 0; i < conjuntos.size(); i++) {
            String p = "";
            if (conjuntos.get(i).getBetha().equals("λ")) {

                p += extraeSiguientes(conjuntos.get(i).getA());

                for (int j = 0; j < objPyS.size(); j++) {
                    if (objPyS.get(j).getObjNT().equals(conjuntos.get(i).getB())) {

                        p += objPyS.get(j).getSiguientes();
                        objPyS.get(j).setSiguientes(p);
                    }
                }

            } else {

                p = extraePrimeros(conjuntos.get(i).getBetha());
                StringTokenizer st = new StringTokenizer(p, " λ", true);
                boolean b = false;
                while (st.hasMoreElements()) {
                    p = st.nextToken();
                    if (p.equals("λ")) {
                        b = true;
                    }

                }
                p = "";
                if (b) {
                    p += extraeSiguientes(conjuntos.get(i).getA());

                    for (int j = 0; j < objPyS.size(); j++) {
                        if (objPyS.get(j).getObjNT().equals(conjuntos.get(i).getB())) {
                            p += objPyS.get(j).getSiguientes();
                            objPyS.get(j).setSiguientes(p);
                        }
                    }

                }

            }

        }
        for (int i = conjuntos.size() - 1; i >= 0; i--) {
            String p = "";
            if (conjuntos.get(i).getBetha().equals("λ")) {

                p += extraeSiguientes(conjuntos.get(i).getA());

                for (int j = 0; j < objPyS.size(); j++) {
                    if (objPyS.get(j).getObjNT().equals(conjuntos.get(i).getB())) {

                        p += objPyS.get(j).getSiguientes();
                        objPyS.get(j).setSiguientes(p);
                    }
                }

            } else {

                p = extraePrimeros(conjuntos.get(i).getBetha());
                StringTokenizer st = new StringTokenizer(p, " λ", true);
                boolean b = false;
                while (st.hasMoreElements()) {
                    p = st.nextToken();
                    if (p.equals("λ")) {
                        b = true;
                    }

                }
                p = "";
                if (b) {
                    p += extraeSiguientes(conjuntos.get(i).getA());

                    for (int j = 0; j < objPyS.size(); j++) {
                        if (objPyS.get(j).getObjNT().equals(conjuntos.get(i).getB())) {
                            p += objPyS.get(j).getSiguientes();
                            objPyS.get(j).setSiguientes(p);
                        }
                    }

                }

            }

        }
        for (int i = 0; i < objPyS.size(); i++) {

            String sigiente = objPyS.get(i).getSiguientes();
            StringTokenizer st = new StringTokenizer(sigiente, " $", true);
            String s1 = "";
            String[] s2 = null;

            while (st.hasMoreElements()) {
                s1 = st.nextToken();
                s2 = agregarObj(s1, s2);
            }
            if (s2 != null) {
                s2 = eliminarRepetidos(s2);
                sigiente = "";
                for (int j = 0; j < s2.length; j++) {
                    sigiente += " " + s2[j];
                }

            }
            objPyS.get(i).setSiguientes(sigiente);
        }

    }

    public void procesoAmbieguedad() {
        for (int i = 0; i < objPyS.size(); i++) {
            ArrayList<String> primero = new ArrayList<>();
            ArrayList<String> siguiente = new ArrayList<>();
            StringTokenizer stP = new StringTokenizer(objPyS.get(i).getPrimeros(), " ", true);
            String sP = "";

            while (stP.hasMoreElements()) {

                sP = stP.nextToken();
                if (" ".equals(sP)) {

                } else {
                    primero.add(sP);
                }
            }
            StringTokenizer stS = new StringTokenizer(objPyS.get(i).getSiguientes(), " ", true);
            String sS = "";

            while (stS.hasMoreElements()) {
                sS = stS.nextToken();
                if (sS.equals(" ")) {

                } else {
                    siguiente.add(sS);
                }
            }
            for (int j = 0; j < primero.size(); j++) {
               
                for (int k = 0; k < siguiente.size(); k++) {

                    if (primero.get(j).equals(siguiente.get(k))) {
                      
                            objPyS.get(i).setAmbiguedad(true);
                        

                    }
                }
            }

        }
    }

}
