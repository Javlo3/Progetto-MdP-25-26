package it.unicam.cs.mpgc.rpg125585.backend.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.convertitori.ArtefattoDeserializer;
import it.unicam.cs.mpgc.rpg125585.dto.StanzaDTO;
import it.unicam.cs.mpgc.rpg125585.dto.SalvataggioDTO;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GestoreFile {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Artefatto.class, new ArtefattoDeserializer())
            .create();
    private final Gson gsonPretty = new GsonBuilder()
            .registerTypeAdapter(Artefatto.class, new ArtefattoDeserializer())
            .setPrettyPrinting()
            .create();

    // 1. Legge mappabase.json e sputa fuori la lista di moduli DTO
    public List<StanzaDTO> caricaMappaBase(String nomeFileNelleRisorse) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(nomeFileNelleRisorse)) {
            if (is == null) {
                System.err.println("Il file non è stato trovato tra le risorse: " + nomeFileNelleRisorse);
                return null;
            }
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Type tipoLista = new TypeToken<List<StanzaDTO>>() {}.getType();
                return gson.fromJson(reader, tipoLista);
            }
        } catch (IOException e) {
            throw  new RuntimeException("Errore nel caricamento delle risorse di gioco", e);
        }
    }

    // 2. Legge salvataggio.json e sputa fuori il modulo del salvataggio
    public SalvataggioDTO caricaPartitaSalvata(String percorsoFile) {
        Path path = Paths.get(percorsoFile);
        if (!Files.exists(path)) {
            return null;
        }
        try (FileReader reader = new FileReader(path.toFile())) {
            return gson.fromJson(reader, SalvataggioDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento del salvataggio" + e);
        }
    }

    // 3. Prende un modulo di salvataggio pieno e lo scrive su salvataggio.json
    public void salvaPartita(String percorsoFile, SalvataggioDTO datiSalvataggio) {
        Path path = Paths.get(percorsoFile);
        controllaEsistenzaCartella(path);
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gsonPretty.toJson(datiSalvataggio, writer);
        } catch (IOException e) {
            throw new RuntimeException("Errore critico durante il salvataggio della partita", e);
        }
    }

    private void controllaEsistenzaCartella(Path path) {
        if(path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new RuntimeException("Impossibile creare la cartella di destinazione", e);
            }
        }
    }

    public boolean esisteSalvataggio(String percorsoSalvataggio) {
        File file = new File(percorsoSalvataggio);
        return file.exists() && !file.isDirectory();
    }
}