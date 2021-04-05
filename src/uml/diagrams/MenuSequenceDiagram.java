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
import uml.diagrams.sequence.fragments.Optional;

public class MenuSequenceDiagram {
	
	private SequenceDiagramsGroup sequenceDiagramGroup;
	private Scanner sc = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	SequenceDiagramsGroup createSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		sequenceDiagramGroup = new SequenceDiagramsGroup();

		menuAddComponentToSequenceDiagramGroup();

		return sequenceDiagramGroup;
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
		    menuAddLifeline();
			break;
		}
		case 2: {
		    menuAddOrReplaceSequenceDiagram();
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
	
	private void menuAddLifeline() throws SequenceDiagramRuleException {
	    System.out.println("Menu: criar LifeLine");
        System.out.println("Digite o nome do LifeLine: ");
        String name = getInputFromConsole();         
        Lifeline lifeline = new Lifeline(name);
        sequenceDiagramGroup.addLifeline(lifeline);
	}
	
	private void menuAddOrReplaceSequenceDiagram()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    SequenceDiagram diagram = createDefaultSequenceDiagram();
        printLine("[1] Adicionar Diagrama de sequência: ");
        printLine("[2] Editar Diagrama de sequência: ");
        
        int option = sc.nextInt();

        if (option == 1) {
            System.out.println("Menu: criar Diagrama de Sequencia");
            System.out.println("Digite o nome do Diagrama de Sequencia:");
            String name = getInputFromConsole();
            System.out.println("Digite a condicao de guarda (true or false):");
            String guard = getInputFromConsole();
            
            if(guard.equals("true")) {
                 diagram.setGuardCondition(true);
            } else {
                 diagram.setGuardCondition(false);
            }
    
            diagram.setName(name);
            sequenceDiagramGroup.addSequenceDiagram(diagram);
        } else if (option == 2) {
            printLine("Escolha o diagrama de sequência (por índice): ");
            int i = 0;
            List<SequenceDiagram> sequenceDiagrams = sequenceDiagramGroup.getSequenceDiagrams();
            
            if (sequenceDiagrams != null && !sequenceDiagrams.isEmpty()) {             
                for (SequenceDiagram diag: sequenceDiagramGroup.getSequenceDiagrams()) {
                    printLine("[" + i++ + "] " + diag.getName());
                }
                
                diagram = sequenceDiagramGroup.getSequenceDiagrams().get(sc.nextInt());
            } else {
                printLine("Nao ha diagramas disponiveis");
                return;
            }
        } else {
            printLine("Opção inválida. Retornando ao menu...");
            return;
        }
        
        menuAddSequenceDiagramElements(diagram);
	}

	private SequenceDiagram createDefaultSequenceDiagram() throws SequenceDiagramRuleException {
	    SequenceDiagram diagram = null;
        if (diagram == null) {
            diagram = new SequenceDiagram("default", true);
        }
        return diagram;
    }
	
	private void addOptional() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("Menu: Criar Optional");
		
		System.out.println("Digite o nome do Diagrama de Sequencia: ");
        String optionalName = getInputFromConsole();
		
        System.out.println("Escolha o diagrama de sequencia: ");
		List<SequenceDiagram> sequenceDiagrams = sequenceDiagramGroup.getSequenceDiagrams();
		
		if (sequenceDiagrams != null && !sequenceDiagrams.isEmpty()) {             
		    for(int i = 0; i < sequenceDiagrams.size(); i++) {
	            System.out.printf("[%d] = %s\n", i, sequenceDiagrams.get(i).getName());
	        }
            
		    int seqDiagramOption = sc.nextInt();
	        SequenceDiagram chosenSeqDiagram = null;
	        
	        if (seqDiagramOption >= 0 && seqDiagramOption < sequenceDiagrams.size()) {
	            chosenSeqDiagram = sequenceDiagrams.get(seqDiagramOption);
	        } else {
	            System.out.println("Diagrama Invalido");
	            addOptional();
	        }
	        
	        Optional optional = new Optional(optionalName, chosenSeqDiagram);
	        
	        sequenceDiagramGroup.addOptional(optional);
        } else {
            printLine("Nao ha diagramas disponiveis");
            return;
        }
	}
	
	private void showDiagram() {
	    printLine(this.sequenceDiagramGroup.toString().replaceAll("(?<!\\/)><", ">\n<").replaceAll("/><", "/>\n<"));
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
            System.out.println("Insira o nome da mensagem");
            String name1 = getInputFromConsole();
            System.out.println("Insira o valor da probablilidade");
            Float prob = sc.nextFloat();
            
            System.out.println("Lifelines disponíveis");
            List<Lifeline> lifelines = sequenceDiagramGroup.getLifelines().getLifelines();
            
            if (lifelines != null && !lifelines.isEmpty()) {      
                for (int i = 0; i < lifelines.size(); i++) {
                    System.out.printf("[%d] = %s\n", i, lifelines.get(i).getName());
                }
            } else {
                printLine("Nao ha lifelines disponiveis");
                break;
            }
            
            System.out.println("Selecione o Lifeline de Origem");
            Lifeline source = getLifelineByOption();
            
            System.out.println("Selecione o Lifeline de Destino");
            Lifeline target = getLifelineByOption();
            
            Message message = new Message(name1, prob, source, target);
            diagram.addElement(message);
            
            break;
        }
        case 2: {
            System.out.println("Escolha o Optional associado");
            List<Optional> optionals = sequenceDiagramGroup.getFragments().getOptionals();
            
            if (optionals != null && !optionals.isEmpty()) {                
                for (int i = 0; i < optionals.size(); i++) {
                    System.out.printf("[%d] = %s\n", i, optionals.get(i).getName());
                }
                Optional optional = getOptionalByOption();
                
                diagram.addElement(new Fragment(optional));
            } else {
                printLine("Nao ha optionals disponiveis");
            }

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
	
	private Optional getOptionalByOption() {
        List<Optional> optionals = sequenceDiagramGroup.getFragments().getOptionals();
        int option = sc.nextInt();
        Optional chosenOptional = null;
        
        if (option < 0 || option >= optionals.size()) {
            System.out.printf("Optional invalido");
            chosenOptional = getOptionalByOption();
        } else {
            chosenOptional = optionals.get(option);
        }
        
        return chosenOptional;
    }
	
	private Lifeline getLifelineByOption() {
	    List<Lifeline> lifelines = sequenceDiagramGroup.getLifelines().getLifelines();
	    int option = sc.nextInt();
	    Lifeline chosenLifeline = null;
	    
	    if (option < 0 || option >= lifelines.size()) {
	        System.out.printf("Lifeline invalido");
	        chosenLifeline = getLifelineByOption();
	    } else {
	        chosenLifeline = lifelines.get(option);
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
