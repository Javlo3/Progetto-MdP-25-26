package it.unicam.cs.mpgc.rpg125585.backend.convertitori;

import com.google.gson.*;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Arma;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Cura;

import java.lang.reflect.Type;

public class ArtefattoDeserializer implements JsonDeserializer<Artefatto> {

    @Override
    public Artefatto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement tipoElement = jsonObject.get("tipo");
        if (tipoElement == null) {
            throw new JsonParseException("Manca il campo 'tipo' nell'artefatto del JSON!");
        }
        String tipo = tipoElement.getAsString().toUpperCase();
        return switch (tipo) {
            case "CURA" -> context.deserialize(json, Cura.class);
            case "ARMA" -> context.deserialize(json, Arma.class);
            default -> throw new JsonParseException("Tipo artefatto sconosciuto nel JSON: " + tipo);
        };
    }
}