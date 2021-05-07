package uml.diagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;
import uml.diagrams.sequence.sequencediagrams.messages.MessageAsync;
import uml.diagrams.sequence.sequencediagrams.messages.MessageSync;
import uml.diagrams.sequence.sequencediagrams.messages.Reply;
import uml.diagrams.sequence.fragments.Optional;

public class MenuSequenceDiagram {
	
    private SequenceDiagramsGroupController sequenceDiagramsGroupController = new SequenceDiagramsGroupController();
	private Scanner sc = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	SequenceDiagramsGroup createSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		menuAddComponentToSequenceDiagramGroup();

		return sequenceDiagramsGroupController.getGeneratedDiagram();
	}
	
	private void menuAddComponentToSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		printLine("[1] Adicionar Lifeline ");
        printLine("[2] Adicionar/Editar Diagrama de sequência ");
        printLine("[3] Adicionar Optional ");
        printLine("[4] Visualizar diagrama");
        printLine("[5] Retornar ao menu principal ");
		
		int num1 = sc.nextInt();;

		switch (num1) {
		case 1: {
		    addLifeline();
			break;
		}
		case 2: {
		    addOrReplaceSequenceDiagram();
			break;
		}
		case 3: {
			addOptional();
			break;
		}
		case 4: {
		    showDiagram();
		    break;
		}
		case 5: {
            return;
        }
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
		
		menuAddComponentToSequenceDiagramGroup();
	}
	
	private void addLifeline() throws SequenceDiagramRuleException {
	    System.out.println("Menu: criar LifeLine");
        System.out.println("Digite o nome do LifeLine: ");
        String name = getInputFromConsole();
        
        sequenceDiagramsGroupController.addLifeline(name);
	}
	
	private void addOrReplaceSequenceDiagram()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    SequenceDiagram diagram;
	    int option;
        printLine("[1] Adicionar Diagrama de sequência: ");
        printLine("[2] Editar Diagrama de sequência: ");

        option = sc.nextInt();
        if (option == 1) {
            diagram = addSequenceDiagram();
        } else if (option == 2) {
            diagram = getSequenceDiagramByOption();
        } else {
            printLine("Opção inválida. Retornando ao menu...");
            return;
        }
        
        if (diagram != null) {
            menuAddSequenceDiagramElements(diagram);            
        } else {
            printLine("Diagrama de sequência não encontrado. Retornando ao menu...");
            return;
        }
	}

	private SequenceDiagram addSequenceDiagram() throws SequenceDiagramRuleException {
	    System.out.println("Menu: criar Diagrama de Sequencia");
        System.out.println("Digite o nome do Diagrama de Sequencia:");
        String name = getInputFromConsole();
        System.out.println("Digite a condicao de guarda (true or false):");
        Boolean guard = sc.nextBoolean();

        return sequenceDiagramsGroupController.addSequenceDiagram(name, guard);
	}
	
	private void addOptional() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    String optionalName = null;
	    SequenceDiagram chosenSeqDiagram = null;

		System.out.println("Menu: Criar Optional");
		System.out.println("Digite o nome do Diagrama de Sequencia: ");
        optionalName = getInputFromConsole();
        
        chosenSeqDiagram = getSequenceDiagramByOption();
        if (chosenSeqDiagram != null) {
            sequenceDiagramsGroupController.addOptional(optionalName, chosenSeqDiagram);
        } else {
            System.out.println("Diagrama Invalido");
            return;
        }   
	}
	
	private void showDiagram() {
	    printLine(sequenceDiagramsGroupController.getGeneratedDiagramFormatted());
	}
	
	private void menuAddSequenceDiagramElements(SequenceDiagram diagram)
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
		
		menuAddSequenceDiagramElements(diagram);
	}
	
	private void addMessage(SequenceDiagram diagram)
	        throws MessageFormatException, SequenceDiagramRuleException {        
	    System.out.println("Insira o nome da mensagem");
        String name1 = getInputFromConsole();
        System.out.println("Insira o valor da probablilidade");
        Float prob = sc.nextFloat();
        
        System.out.println("Lifelines disponíveis");
        List<Lifeline> lifelines = sequenceDiagramsGroupController.getLifelines();
        
        if (lifelines != null && !lifelines.isEmpty()) {      
            for (int i = 0; i < lifelines.size(); i++) {
                System.out.printf("[%d] = %s\n", i, lifelines.get(i).getName());
            }
        } else {
            printLine("Nao ha lifelines disponiveis");
            return;
        }

        System.out.println("Selecione o Lifeline de Origem");
        Lifeline source = getLifelineByOption();
        
        System.out.println("Selecione o Lifeline de Destino");
        Lifeline target = getLifelineByOption();

        System.out.println("Escolha o tipo da Mensagem: ");
        System.out.println("[1] Mensagem Sincrona");
        System.out.println("[2] Mensagem Assincrona");
        System.out.println("[3] Resposta");
	    
        Message message = null;
	    int option = sc.nextInt();

	    switch (option) {
        case 1: {
            message = new MessageSync(name1, prob, source, target);
            break;
    	}
        case 2: {
            message = new MessageAsync(name1, prob, source, target);
            break;
        }
        case 3: {
            message = new Reply(name1, prob, source, target);
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + option);
	    }
	    
	    diagram.addElement(message);
    }
	
	private void addFragment(SequenceDiagram diagram) throws SequenceDiagramRuleException {
	    System.out.println("Escolha o Optional associado");
        Optional optional = getOptionalByOption();
        
        if (optional != null) {
            diagram.addElement(new Fragment(optional));                    
        }
	}

	private SequenceDiagram getSequenceDiagramByOption() {
	    SequenceDiagram sequenceDiagram = null;
        printLine("Escolha o diagrama de sequência (por índice): ");
        
        if (showSequenceDiagrams()) {
            sequenceDiagram = sequenceDiagramsGroupController.getSequenceDiagramByIndex(sc.nextInt());
        }
        return sequenceDiagram;
    }

	private boolean showSequenceDiagrams() {
	    List<SequenceDiagram> sequenceDiagrams = sequenceDiagramsGroupController.getSequenceDiagrams();
	    if (sequenceDiagrams != null && !sequenceDiagrams.isEmpty()) {
    	    for (int i = 0; i < sequenceDiagrams.size(); i++) {
                printLine("[" + i + "] " + sequenceDiagrams.get(i).getName());
            }
    	    return true;
	    }
        printLine("Nao ha diagramas disponiveis");
        return false;
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
