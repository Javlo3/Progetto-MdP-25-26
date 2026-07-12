# 📌 RPG JavaFX Game - Progetto Mdp

Questo progetto consiste nello sviluppo di un gioco di ruolo (GDR) testuale e grafico in Java, in cui il giocatore può esplorare un mondo composto da stanze collegate tra loro, affrontare nemici in combattimenti strategici e gestire lo stato della partita. L'applicazione sfrutta un'architettura robusta basata sui principi SOLID e include un sistema completo di persistenza dei dati.

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle

### Istruzioni

```bash
git clone [https://github.com/Javlo3/Progetto-MdP-25-26.git](https://github.com/Javlo3/ProgettoMdP-25-26.git)

### Entra nella cartella del repository

cd ProgettoMdp-25-26

### Build del progetto 

```bash
./gradlew build
```

### Esecuzione

```bash
./gradlew run
```

---

### 🤖 Uso di strumenti di AI

Durante lo sviluppo di questo progetto è stato utilizzato lo strumento di Intelligenza Artificiale Google Gemini come assistente virtuale per il brainstorming e l'ottimizzazione del codice, mantenendo il pieno controllo e la validazione personale su ogni singola riga di codice implementata.

* **Brainstorming, Studio di Fattibilità e Architettura:**
  * Utilizzato nelle fasi embrionali del progetto per analizzare l'idea di base, convalidare se rispondesse effettivamente ai requisiti accademici di un RPG e valutarne la fattibilità tecnica rispetto alle mie attuali competenze, evitando derive fuori portata.
  * Successivamente, impiegato per strutturare un'architettura sensata e convalidare decisioni cruciali a runtime, come la transizione dal sistema a turni asincroni a una gestione dei combattimenti completamente sincrona e polimorfica nel backend.
* **Confronto su Disaccoppiamento e Clean Code:**
  * Utilizzato per un confronto teorico sull'efficacia dei pattern di isolamento tra backend e interfaccia grafica, confermando la mia scelta di implementare i Data Transfer Objects (DTO) per evitare il passaggio diretto delle entità di dominio alla GUI.
  * Impiegato come supporto di revisione per verificare la robustezza della logica di serializzazione e deserializzazione polimorfica in JSON, assicurando che la mia ricostruzione del grafo delle stanze non generasse riferimenti circolari.

Ogni suggerimento concettuale o strutturale fornito dall'AI è stato analizzato, compreso a fondo e modificato manualmente per essere integrato nel contesto dell'applicazione, lasciando la totale paternità logica e del codice allo sviluppatore.
