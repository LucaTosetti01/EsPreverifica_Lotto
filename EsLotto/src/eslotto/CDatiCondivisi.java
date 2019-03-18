/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eslotto;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Luca
 */
public class CDatiCondivisi {

    private int Estrazioni;
    private boolean PrimoTrovato;
    private boolean SecondoTrovato;
    private int[] ruota;
    private boolean[] ruoteVincenti;
    private int PrimoNumero;
    private int SecondoNumero;
    private boolean Finito;

    public boolean isFinito() {
        return Finito;
    }

    public void setFinito(boolean Finito) {
        this.Finito = Finito;
    }

    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;
    private Semaphore semFinito;

    public CDatiCondivisi(int estr) {
        Estrazioni = estr;
        sem1 = new Semaphore(2);
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
        semFinito=new Semaphore(0);
        PrimoNumero = 0;
        SecondoNumero = 0;
        ruoteVincenti=new boolean[Estrazioni];
        for(int ii=0;ii<Estrazioni;ii++) {
            ruoteVincenti[ii]=false;
        }
        ruota = new int[5];
        for (int i = 0; i < 5; i++) {
            ruota[i] = 0;
        }
        PrimoTrovato = false;
        SecondoTrovato = false;
    }

    public synchronized int getEstrazioni() {
        return Estrazioni;
    }

    public synchronized void setEstrazioni(int Estrazioni) {
        this.Estrazioni = Estrazioni;
    }

    public synchronized boolean isPrimoTrovato() {
        return PrimoTrovato;
    }

    public synchronized void setPrimoTrovato(boolean PrimoTrovato) {
        this.PrimoTrovato = PrimoTrovato;
    }

    public synchronized boolean isSecondoTrovato() {
        return SecondoTrovato;
    }

    public synchronized void setSecondoTrovato(boolean SecondoTrovato) {
        this.SecondoTrovato = SecondoTrovato;
    }

    public synchronized void AggiungiNumero(int posizione, int numero) {
        ruota[posizione] = numero;
    }

    public synchronized int RestituisciNumero(int posizione) {
        return ruota[posizione];
    }

    public synchronized int[] getRuota() {
        return ruota;
    }

    public synchronized void setRuota(int[] ruota) {
        this.ruota = ruota;
    }

    public synchronized int getPrimoNumero() {
        return PrimoNumero;
    }

    public synchronized void setPrimoNumero(int PrimoNumero) {
        this.PrimoNumero = PrimoNumero;
    }

    public synchronized int getSecondoNumero() {
        return SecondoNumero;
    }

    public synchronized void setSecondoNumero(int SecondoNumero) {
        this.SecondoNumero = SecondoNumero;
    }

    public void WaitSem1() throws InterruptedException {
        sem1.acquire();
    }

    public void SignalSem1() {
        sem1.release();
    }

    public void WaitSem2() throws InterruptedException {
        sem2.acquire();
    }

    public void SignalSem2() {
        sem2.release();
    }

    public void WaitSem3() throws InterruptedException {
        sem3.acquire();
    }

    public void SignalSem3() {
        sem3.release();
    }
    
    
    public void SettaRuotaVincente(int pos) {
        ruoteVincenti[pos]=true;
    }
    
    public boolean RestituisciRuotaVincente(int pos) {
        return ruoteVincenti[pos];
    }
    
    
    public void WaitSemFinito() throws InterruptedException {
        semFinito.acquire();
    }

    public void SignalSemFinito() {
        semFinito.release();
    }
    
}
