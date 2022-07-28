package sample.camunda;

import java.util.Random;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CamundaDelegateExample implements JavaDelegate {


  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {

    String input = (String) delegateExecution.getVariable("userInput");

    delegateExecution.setVariable("cellInput", input);
  }

}
