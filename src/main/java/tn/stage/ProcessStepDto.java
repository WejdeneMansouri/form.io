package tn.stage;

public class ProcessStepDto {
    private String id;
    private String name;
    private String elementType;
    private String allowedRoles;
    private String messageType;
    private String formKey;

    public ProcessStepDto(String id, String name, String elementType, String allowedRoles, String messageType, String formKey) {
        this.id = id;
        this.name = name;
        this.elementType = elementType;
        this.allowedRoles = allowedRoles;
        this.messageType = messageType;
        this.formKey = formKey;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getElementType() { return elementType; }
    public String getAllowedRoles() { return allowedRoles; }
    public String getMessageType() { return messageType; }
    public String getFormKey() { return formKey; }
}