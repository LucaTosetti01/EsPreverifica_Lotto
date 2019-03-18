/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eslotto;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class EsLotto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        String ruoteVincenti="(";
        int contVincite=0;
        
        System.out.println("Inserisca il numero di estrazioni che si intende effettuare");
        int estrazioni=input.nextInt();
        
        System.out.println("Inserisca i 2 numero su cui intende puntare:");
        System.out.print("1)");
        int Numero1=input.nextInt();
        
        System.out.print("2)");
        int Numero2=input.nextInt();
        
        CDatiCondivisi dati=new CDatiCondivisi(estrazioni);
        
        dati.setPrimoNumero(Numero1);
        dati.setSecondoNumero(Numero2);
        
        ThGeneraNumeri th1=new ThGeneraNumeri(dati);
        ThCercaPrimoNumero th2=new ThCercaPrimoNumero(dati);
        ThCercaSecondoNumero th3=new ThCercaSecondoNumero(dati);
        
        th1.start();
        th2.start();
        th3.start();
        
        try {
            dati.WaitSemFinito();
            
            for(int i=0;i<estrazioni;i++) {
                if(dati.RestituisciRuotaVincente(i)) {
                    contVincite++;
                    ruoteVincenti=ruoteVincenti+(i+1)+" ";
                }
            }
            ruoteVincenti=ruoteVincenti.trim();
            ruoteVincenti=ruoteVincenti+")";
            
            if(contVincite==0) {
                System.out.println("Mi dispiace non ha vinto su nessuna ruota");
            }
            else {
                System.out.println("Complimenti ha vinto su: "+contVincite+" ruota/e"+ruoteVincenti);
            }
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(EsLotto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
