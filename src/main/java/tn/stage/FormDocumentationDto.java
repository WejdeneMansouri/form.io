package tn.stage;

import java.util.ArrayList;
import java.util.List;

public class FormDocumentationDto {
    private String formName;
    private List<FormFieldDto> fields = new ArrayList<>();

    public FormDocumentationDto(String formName) {
        this.formName = formName;
    }

    public String getFormName() { return formName; }
    public List<FormFieldDto> getFields() { return fields; }
    public void setFields(List<FormFieldDto> fields) { this.fields = fields; }
}