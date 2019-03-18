/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eslotto;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ThGeneraNumeri extends Thread {

    private int contRuote=0;
    private CDatiCondivisi PtrDati;

    public ThGeneraNumeri(CDatiCondivisi dati) {
        PtrDati = dati;
    }

    public void run() {
        try {
            Random rand = new Random();
            while (!PtrDati.isFinito()) {

                
                for (int i = 0; i < PtrDati.getEstrazioni(); i++) {
                    PtrDati.WaitSem1();
                    PtrDati.WaitSem1();
                    
                    if((PtrDati.isPrimoTrovato()) && (PtrDati.isSecondoTrovato())) {
                        PtrDati.SettaRuotaVincente(i-1);
                    }
                    
                    
                    PtrDati.setPrimoTrovato(false);
                    PtrDati.setSecondoTrovato(false);
                    
                    
                    for (int ii = 0; ii < 5; ii++) {
                        int numeroRand = rand.nextInt(100);
                        
                        
                        PtrDati.AggiungiNumero(ii, numeroRand);
                    }
                    contRuote++;
                    System.out.println(Arrays.toString(PtrDati.getRuota())+" Ruota N."+contRuote);
                    System.out.println("------------------------");
                    PtrDati.SignalSem2();
                    PtrDati.SignalSem3();
                }
                
                PtrDati.setFinito(true);
                PtrDati.SignalSemFinito();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThGeneraNumeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
