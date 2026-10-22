package tn.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private FormIoParserService formIoParserService;

    @GetMapping("/analyser-formio")
    public List<String> testerAnalyseFormIo() {
        // Appelle le service qui lit votre fichier JSON Form.io
        return formIoParserService.analyserChampsFormIo();
    }
}