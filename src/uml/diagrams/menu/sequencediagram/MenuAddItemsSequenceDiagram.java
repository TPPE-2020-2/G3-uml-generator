package uml.diagrams.menu.sequencediagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import uml.diagrams.sequence.SequenceDiagramsGroupController;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;
import uml.diagrams.sequence.sequencediagrams.messages.MessageAsync;
import uml.diagrams.sequence.sequencediagrams.messages.MessageSync;
import uml.diagrams.sequence.sequencediagrams.messages.Reply;

public class MenuAddItemsSequenceDiagram {
    
    private Scanner sc = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private SequenceDiagramsGroupController sequenceDiagramsGroupController;
    
    MenuAddItemsSequenceDiagram(SequenceDiagramsGroupController sequenceDiagramsGroupController) {
        this.sequenceDiagramsGroupController = sequenceDiagramsGroupController;
    }
    
    void addSequenceDiagramElements(SequenceDiagram diagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
        System.out.println("Menu: criar elementos de Diagrama de Sequencia (diagrama atual ::" + diagram.getName() + ")::");
        System.out.println("[1] Adicionar Mensagens");
        System.out.println("[2] Adicionar Fragmento");
        System.out.println("[3] Retornar ao menu anterior");

        int num1 = sc.nextInt();
        switch (num1) {
        case 1: {
            addMessage(diagram);
            break;
        }
        case 2: {
            addFragment(diagram);
            break;
        }
        case 3: {
            return;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + num1);
        }
        
        addSequenceDiagramElements(diagram);
    }
    
    private void addMessage(SequenceDiagram diagram)
            throws MessageFormatException, SequenceDiagramRuleException {        
        System.out.println("Insira o nome da mensagem");
        String name = getInputFromConsole();
        System.out.println("Insira o valor da probablilidade");
        Float prob = sc.nextFloat();
        System.out.println("Lifelines disponíveis");

        if (!showLifelines())
            return;

        System.out.println("Selecione o Lifeline de Origem");
        Lifeline source = getLifelineByOption();

        System.out.println("Selecione o Lifeline de Destino");
        Lifeline target = getLifelineByOption();
        
        Message message = chooseMessageType(name, prob, source, target);

        diagram.addElement(message);
    }
    
    private void addFragment(SequenceDiagram diagram) throws SequenceDiagramRuleException {
        System.out.println("Escolha o Optional associado");
        Optional optional = getOptionalByOption();
        
        if (optional != null) {
            diagram.addElement(new Fragment(optional));                    
        }
    }
    
    private Message chooseMessageType(String name, Float prob, Lifeline source, Lifeline target)
            throws MessageFormatException, SequenceDiagramRuleException {
        Message message = null;
        int option;

        System.out.println("Escolha o tipo da Mensagem: ");
        System.out.println("[1] Mensagem Sincrona");
        System.out.println("[2] Mensagem Assincrona");
        System.out.println("[3] Resposta");
        
        while (message == null) {          
            option = sc.nextInt();
            switch (option) {
            case 1: {
                message = new MessageSync(name, prob, source, target);
                break;
            }
            case 2: {
                message = new MessageAsync(name, prob, source, target);
                break;
            }
            case 3: {
                message = new Reply(name, prob, source, target);
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + option);
            }
        }
        
        return message;
    }
    
    private Optional getOptionalByOption() {
        Optional chosenOptional = null;
        int option;

        if (showOptionals()) {            
            option = sc.nextInt();
            
            while (chosenOptional == null) {            
                chosenOptional = sequenceDiagramsGroupController.getOptionalByIndex(option);
                
                if (chosenOptional == null) {
                    System.out.printf("Optional invalido");                
                }
            }
        }
        return chosenOptional;
    }
    
    private boolean showOptionals() {
        List<Optional> optionals = sequenceDiagramsGroupController.getOptionals();
        
        if (optionals != null && !optionals.isEmpty()) {
            for (int i = 0; i < optionals.size(); i++) {
                System.out.printf("[%d] = %s\n", i, optionals.get(i).getName());
            }
            return true;
        }
        printLine("Nao ha optionals disponiveis");
        return false;
    }
    
    private Lifeline getLifelineByOption() {
        Lifeline chosenLifeline = null;
        int option;
        
        while (chosenLifeline == null) {
            option = sc.nextInt();
            chosenLifeline = sequenceDiagramsGroupController.getLifelineByIndex(option);
            
            if (chosenLifeline == null) {
                printLine("Lifeline invalido");                
            }
        }
        return chosenLifeline;
    }
    
    private boolean showLifelines() {
        List<Lifeline> lifelines = sequenceDiagramsGroupController.getLifelines();
        
        if (lifelines != null && !lifelines.isEmpty()) {      
            for (int i = 0; i < lifelines.size(); i++) {
                System.out.printf("[%d] = %s\n", i, lifelines.get(i).getName());
            }
            return true;
        }
        printLine("Nao ha lifelines disponiveis");
        return false;
    }
    
    private String getInputFromConsole() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
    
    private void printLine(String text) {
        System.out.println(text);
    }
}
