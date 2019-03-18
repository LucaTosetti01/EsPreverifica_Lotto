/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eslotto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ThCercaPrimoNumero extends Thread {

    private CDatiCondivisi PtrDati;

    public ThCercaPrimoNumero(CDatiCondivisi dati) {
        PtrDati = dati;
    }

    public void run() {
        try {

            while (!PtrDati.isFinito()) {
                PtrDati.WaitSem2();
                for (int i = 0; i < 5; i++) {
                    if (PtrDati.RestituisciNumero(i) == PtrDati.getPrimoNumero()) {
                        PtrDati.setPrimoTrovato(true);
                    }
                }

                PtrDati.SignalSem1();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThCercaPrimoNumero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
