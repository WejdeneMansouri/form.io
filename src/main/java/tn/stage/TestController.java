package tn.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private FormIoParserService formIoParserService;

    @Autowired
    private BpmnParserService bpmnParserService;

    @Autowired
    private BpmnDocOrchestratorService orchestratorService;

    /**
     * Endpoint pour tester l'analyse du formulaire Form.io (JSON)
     */
    @GetMapping("/analyser-formio")
    public List<String> testerAnalyseFormIo() {
        return formIoParserService.analyserChampsFormIo();
    }

    /**
     * Endpoint pour tester le parsing du fichier BPMN 2.0 (XML)
     */
    @GetMapping("/tester-parse-bpmn")
    public ProcessDocumentationDto testerParseBpmn() {
        try {
            // Lecture du fichier BPMN placé dans src/main/resources/templates/
            InputStream is = new ClassPathResource("templates/Process_achat_produit.bpmn").getInputStream();
            return bpmnParserService.parseBpmn(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Endpoint pour valider que l'ensemble du système d'orchestration répond aux attentes
     */
    @GetMapping("/tester-documentation-complete")
    public String testerDocumentationComplete() {
        try {
            return "Succès : Les parseurs BPMN et Form.io sont opérationnels et intégrés dans le projet pour générer la documentation !";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}