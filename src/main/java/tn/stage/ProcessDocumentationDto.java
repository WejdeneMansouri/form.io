package tn.stage;

import java.util.ArrayList;
import java.util.List;

public class ProcessDocumentationDto {
    private String processId;
    private String processName;
    private Boolean executable;
    private String historyTimeToLive;
    private List<ProcessStepDto> steps = new ArrayList<>();
    private List<SequenceFlowDto> flows = new ArrayList<>();
    private List<FormDocumentationDto> forms = new ArrayList<>();

    // Getters et Setters
    public String getProcessId() { return processId; }
    public void setProcessId(String processId) { this.processId = processId; }

    public String getProcessName() { return processName; }
    public void setProcessName(String processName) { this.processName = processName; }

    public Boolean getExecutable() { return executable; }
    public void setExecutable(Boolean executable) { this.executable = executable; }

    public String getHistoryTimeToLive() { return historyTimeToLive; }
    public void setHistoryTimeToLive(String historyTimeToLive) { this.historyTimeToLive = historyTimeToLive; }

    public List<ProcessStepDto> getSteps() { return steps; }
    public void setSteps(List<ProcessStepDto> steps) { this.steps = steps; }

    public List<SequenceFlowDto> getFlows() { return flows; }
    public void setFlows(List<SequenceFlowDto> flows) { this.flows = flows; }

    public List<FormDocumentationDto> getForms() { return forms; }
    public void setForms(List<FormDocumentationDto> forms) { this.forms = forms; }
}