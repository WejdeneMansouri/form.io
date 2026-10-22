package tn.stage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormIoParserService {

    public List<String> analyserChampsFormIo() {
        List<String> resumeChamps = new ArrayList<>();
        try {
            // Charger le fichier JSON Form.io depuis les ressources
            InputStream is = new ClassPathResource("templates/inscription.json").getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(is);

            // Parcourir les composants du formulaire (AST)
            JsonNode components = rootNode.get("components");
            if (components != null && components.isArray()) {
                for (JsonNode component : components) {
                    String label = component.path("label").asText();
                    String type = component.path("type").asText();
                    boolean required = component.path("validate").path("required").asBoolean(false);

                    String info = "Champ : " + label + " (Type: " + type + ", Obligatoire: " + required + ")";
                    resumeChamps.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resumeChamps.add("Erreur lors de la lecture du schéma Form.io");
        }
        return resumeChamps;
    }
}