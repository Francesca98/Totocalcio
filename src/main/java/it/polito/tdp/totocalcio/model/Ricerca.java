package it.polito.tdp.totocalcio.model;

import java.util.LinkedList;
import java.util.List;

public class Ricerca {
	
	//variabili di classe che sono necessarie xk altrimenti alla funzione ricorsiva dovresti ogni volta passar 
	//gli stessi valori 
	private int N ;
	private Pronostico pronostico;
	List<Risultato> soluzione;  

	public List<Risultato> cerca(Pronostico pronostico) {
		this.pronostico=pronostico;
		this.N=pronostico.size();
		int livello = 0;
		List<RisultatoPartita> parziale  = new LinkedList<>();
		this.soluzione  = new LinkedList<>();
		ricorsiva(parziale,livello);
		

		return this.soluzione;
	}

	

//RICERCA DI TUTTI I RISULTATI COMPATIBILI
		/* Io ho questo pronostico  ["2X","1","1X2","12"]
		  e i risultati possibili sarebbero 2 1 1 1 
		                                    2 1 1 2
		                                    2 1 X 2
		                                    e cosi via con tutte le combinazioni possibili
		 VIENE FATTA UNA DIVISIONE IN SOTTOPROBLEMI  ["2X","1","1X2","12"]
		  ["2X"] + ["1","1X2","12"]
		            ["1","1X2","12"]
		            ["1"] + ["1X2","12"] ECC*/

/*IL LIVELLO DEòLA RICORSIONE INDICA IL NUMERO DI PARTITE CHE STO CONSIDERANDO
 *le partite da a livello-1 sono già state decise
 *la partita di indice livello la devo decidere io 
 *le partite da livello+1 in poi le decideranno le procedure ricorsive sottostanti 
 *
 *SOLUZIONE PARZIALE : un insieme di RisultatoPartita di lunghezza pari al livello
 *List<RisultatoPartita> parziale vuol dire che se tu hai ricevuto una lista di 3 elementi i primi 3 sono già 
 *stati decisi e devi decidere il 4
 *
 *SOLUZIONE TOTALE : ho N risultati
 *
 *CONDIZIONE DI TERMINAZIONE : livello == N
 *
 *GENERAZIONE DELLE SOLUZIONI DEL LIVELLO : provando tutti i pronostici definiti per quel livello
 * */

private void ricorsiva (List<RisultatoPartita> parziale , int livello)
{ //CASO TERMINALE
	if(livello == N)
	{
		/*se arrivi a questo punto vuol dire che la soluzione parziale è completa*/
		System.out.println(parziale); //restiduire al chiamante 
		this.soluzione.add(new  Risultato(parziale));
		
	}
	//CASO GENERALE
	else {
		
		//[parziale da 0 a livello-1][livello] [livello +1 in poi]
		PronosticoPartita pp = pronostico.get(livello);
		//pp sono i sottoproblemi da provare 
		
		for ( RisultatoPartita ris : pp.getRisultati())
		{
			//provo a mettere ris nella posizione livello
			//della soluzione parziale
			
			//COSTRUZIONE DELLA SOLUZIONE PARZIALE(SOTTOPROBLEMA)
			parziale.add(ris);
			//CHIAMATA RICORSIVA
			ricorsiva(parziale, livello+1);
			//BACKTRACKING
			parziale.remove(parziale.size()-1);
		}
		
	}
	
}
}
 