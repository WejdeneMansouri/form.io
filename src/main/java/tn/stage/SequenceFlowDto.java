package tn.stage;

public class SequenceFlowDto {
    private String id;
    private String sourceRef;
    private String targetRef;
    private String name;

    public SequenceFlowDto(String id, String sourceRef, String targetRef, String name) {
        this.id = id;
        this.sourceRef = sourceRef;
        this.targetRef = targetRef;
        this.name = name;
    }

    public String getId() { return id; }
    public String getSourceRef() { return sourceRef; }
    public String getTargetRef() { return targetRef; }
    public String getName() { return name; }
}