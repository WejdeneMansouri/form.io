package tn.stage;

public class FormFieldDto {
    private String key;
    private String type;
    private String label;
    private String rules;

    public FormFieldDto(String key, String type, String label, String rules) {
        this.key = key;
        this.type = type;
        this.label = label;
        this.rules = rules;
    }

    public String getKey() { return key; }
    public String getType() { return type; }
    public String getLabel() { return label; }
    public String getRules() { return rules; }
}