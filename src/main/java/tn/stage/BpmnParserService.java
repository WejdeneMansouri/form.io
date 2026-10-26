package tn.stage;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collection;

@Service
public class BpmnParserService {

    public ProcessDocumentationDto parseBpmn(InputStream bpmnInputStream) {
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(bpmnInputStream);
        ProcessDocumentationDto doc = new ProcessDocumentationDto();

        // Utilisation explicite du chemin complet pour éviter l'ambiguïté avec java.lang.Process
        Collection<org.camunda.bpm.model.bpmn.instance.Process> processes =
                modelInstance.getModelElementsByType(org.camunda.bpm.model.bpmn.instance.Process.class);

        if (processes.isEmpty()) {
            throw new IllegalArgumentException("Aucun processus BPMN trouvé.");
        }

        org.camunda.bpm.model.bpmn.instance.Process process = processes.iterator().next();
        doc.setProcessId(process.getId());
        doc.setProcessName(process.getName());
        doc.setExecutable(process.isExecutable());

        // Conversion sécurisée de l'Integer en String pour historyTimeToLive
        doc.setHistoryTimeToLive(
                process.getCamundaHistoryTimeToLive() != null
                        ? String.valueOf(process.getCamundaHistoryTimeToLive())
                        : "N/A"
        );

        Collection<FlowNode> flowNodes = modelInstance.getModelElementsByType(FlowNode.class);
        for (FlowNode node : flowNodes) {
            String id = node.getId();
            String name = node.getName() != null ? node.getName() : id;
            String type = node.getElementType().getTypeName();
            String allowedRoles = extractCamundaProperty(node, "allowedActivityTypes");
            String messageType = extractCamundaProperty(node, "messageType");
            String formKey = (node instanceof UserTask) ? ((UserTask) node).getCamundaFormKey() : "-";

            doc.getSteps().add(new ProcessStepDto(id, name, type, allowedRoles, messageType, formKey));
        }

        Collection<SequenceFlow> flows = modelInstance.getModelElementsByType(SequenceFlow.class);
        for (SequenceFlow flow : flows) {
            doc.getFlows().add(new SequenceFlowDto(
                    flow.getId(),
                    flow.getSource().getId(),
                    flow.getTarget().getId(),
                    flow.getName() != null ? flow.getName() : "-"
            ));
        }

        return doc;
    }

    private String extractCamundaProperty(BaseElement element, String propertyName) {
        ExtensionElements extensionElements = element.getExtensionElements();
        if (extensionElements != null) {
            Collection<CamundaProperty> properties = extensionElements.getElementsQuery()
                    .filterByType(CamundaProperty.class)
                    .list();
            for (CamundaProperty prop : properties) {
                if (propertyName.equals(prop.getCamundaName())) {
                    return prop.getCamundaValue();
                }
            }
        }
        return "N/A";
    }
}