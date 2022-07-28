package sample.camunda;

import java.util.HashMap;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamundaInputController {

  private RuntimeService runtimeService;
  private CamundaInput camundaInput = new CamundaInput();

  @Autowired
  public void PayloadRequest(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  @PostMapping("/test")
  public Boolean getBooleanFromInput(@RequestBody String userInput) {

    /*TODO: Going to be passing a seralized object through our webservice, specific thing that ratings is sending
    Serialized version of Rulefact class, gonna have to map that class to what camunda expects
    call this endpoint, transform the object, call some sort of execute method to shoot off the process in camunda
    assemble it in a way camunda understands and then send it
    Map the resulting bool to the object
    Step 1: How to fire off camunda from here - check, done
    Step 2: How to map results from camunda in here
    */

    camundaInput.setUserInput(userInput);

    Map<String, Object> variables = createVariables(camundaInput);

    runtimeService.startProcessInstanceByKey("sample-camunda-process", variables);


    return true;

  }

  private Map<String, Object> createVariables(CamundaInput input) {
    Map<String, Object> variables = new HashMap<>();
    variables.put("userInput", input.getUserInput());

    //creates a null boolean object, want it to get set to whatever the camunda process sends back
    variables.put("bool", input.getBool());

    return variables;
  }
}
