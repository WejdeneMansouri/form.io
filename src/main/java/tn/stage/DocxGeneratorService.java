package tn.stage;

import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class DocxGeneratorService {

    public void generateDocxReport(ProcessDocumentationDto context, InputStream templateStream, OutputStream outputStream) {
        DocxStamper<ProcessDocumentationDto> stamper = new DocxStamperConfiguration().build();
        stamper.stamp(templateStream, context, outputStream);
    }
}