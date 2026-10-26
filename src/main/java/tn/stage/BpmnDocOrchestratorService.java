package tn.stage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BpmnDocOrchestratorService {

    private final BpmnParserService bpmnParserService;
    private final DocxGeneratorService docxGeneratorService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BpmnDocOrchestratorService(BpmnParserService bpmnParserService, DocxGeneratorService docxGeneratorService) {
        this.bpmnParserService = bpmnParserService;
        this.docxGeneratorService = docxGeneratorService;
    }

    public void generateFullDocumentation(InputStream bpmnStream,
                                          Map<String, InputStream> formIoStreams,
                                          InputStream templateStream,
                                          OutputStream outputStream) throws Exception {

        // 1. Parse BPMN
        ProcessDocumentationDto processDoc = bpmnParserService.parseBpmn(bpmnStream);

        // 2. Parse Form.io JSON files dynamically
        for (Map.Entry<String, InputStream> entry : formIoStreams.entrySet()) {
            FormDocumentationDto formDoc = new FormDocumentationDto(entry.getKey());
            JsonNode root = objectMapper.readTree(entry.getValue());
            JsonNode components = root.get("components");

            List<FormFieldDto> fields = new ArrayList<>();
            if (components != null && components.isArray()) {
                for (JsonNode component : components) {
                    String key = component.path("key").asText("-");
                    String type = component.path("type").asText("-");
                    String label = component.path("label").asText("-");
                    boolean required = component.path("validate").path("required").asBoolean(false);

                    String rules = required ? "Obligatoire" : "Optionnel";
                    fields.add(new FormFieldDto(key, type, label, rules));
                }
            }
            formDoc.setFields(fields);
            processDoc.getForms().add(formDoc);
        }

        // 3. Generate Word Document via DocxStamper
        docxGeneratorService.generateDocxReport(processDoc, templateStream, outputStream);
    }
}