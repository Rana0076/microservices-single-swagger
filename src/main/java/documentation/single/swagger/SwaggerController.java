package documentation.single.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class SwaggerController {

    @GetMapping(path = "/swagger")
    public String defaultRedirectToSwaggerUi() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping(path = "/swagger/api/def/{group}")
    public ResponseEntity<Object> getAPIDefination(@PathVariable(value = "group") String group) throws IOException {

        File file = new File(
                this.getClass().getClassLoader().getResource("apis/"+group+".json").getFile()
        );

        ObjectMapper mapper = new ObjectMapper();
        Map apidef = mapper.readValue(file, Map.class);
        return new ResponseEntity<Object>(apidef, HttpStatus.OK);
    }

}
