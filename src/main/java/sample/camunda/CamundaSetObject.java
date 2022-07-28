package sample.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CamundaSetObject implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {

    String input = (String) delegateExecution.getVariable("userInput");

    delegateExecution.setVariable("cellInput", input);
  }

}
