package it.unicam.cs.mpgc.rpg125585.backEnd.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg125585.dto.StanzaDTO;
import it.unicam.cs.mpgc.rpg125585.dto.SalvataggioDTO;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GestoreFile {
    private final Gson gson = new Gson();
    private final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

    // 1. Legge mappabase.json e sputa fuori la lista di moduli DTO
    public List<StanzaDTO> caricaMappaBase(String percorsoFile) {
        try (FileReader reader = new FileReader(percorsoFile)) {
            Type tipoLista = new TypeToken<List<StanzaDTO>>(){}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 2. Legge salvataggio.json e sputa fuori il modulo del salvataggio
    public SalvataggioDTO caricaPartitaSalvata(String percorsoFile) {
        try (FileReader reader = new FileReader(percorsoFile)) {
            return gson.fromJson(reader, SalvataggioDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 3. Prende un modulo di salvataggio pieno e lo scrive su salvataggio.json
    public void salvaPartita(String percorsoFile, SalvataggioDTO datiSalvataggio) {
        try (FileWriter writer = new FileWriter(percorsoFile)) {
            gsonPretty.toJson(datiSalvataggio, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}