package uml.diagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.ISequenceDiagramElement;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;
import uml.diagrams.sequence.fragments.Optional;

public class MenuSequenceDiagram {
	
	private SequenceDiagramsGroup sequenceDiagramGroup;
	private Scanner sc = new Scanner(System.in);	
	
	SequenceDiagramsGroup createSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		sequenceDiagramGroup = new SequenceDiagramsGroup();

		menuAddComponentToSequenceDiagramGroup();

		return sequenceDiagramGroup;
	}
	
	private void menuAddComponentToSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("[1] Adicionar Lifeline: ");
		System.out.println("[2] Adicionar Diagrama de sequencia: ");
		System.out.println("[3] Adicionar Fragmento: ");
		System.out.println("[4] Retornar ao menu principal: ");
		
		int num1 = Integer.parseInt(sc.nextLine());

		switch (num1) {
		case 1: {
		    menuAddLifeline();
			break;
		}
		case 2: {
		    menuAddSequenceDiagram();
			break;
		}
		case 3: {
			addOptional();
			break;
		}
		case 4: {
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
        String name = sc.nextLine();         
        Lifeline lifeline = new Lifeline(name);
        sequenceDiagramGroup.addLifeline(lifeline);
	}
	
	private void menuAddSequenceDiagram()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    SequenceDiagram diagram = createDefaultSequenceDiagram();
        System.out.println("Menu: criar Diagrama de Sequencia");
        System.out.println("Digite o nome do Diagrama de Sequencia:");
        String name = sc.nextLine();
        System.out.println("Digite a condicao de guarda (true or false):");
        String guard = sc.nextLine();
        
        if(guard.equals("true")) {
             diagram.setGuardCondition(true);
        }
        else {
             diagram.setGuardCondition(false);
        }

        diagram.setName(name);
        sequenceDiagramGroup.addSequenceDiagram(diagram);
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
		System.out.println("Escolha o diagrama de sequencia");
		
		List<SequenceDiagram> sequenceDiagrams = sequenceDiagramGroup.getSequenceDiagrams();
		for(int i = 0; i < sequenceDiagrams.size(); i++) {
            System.out.printf("[%d] = %s\n", i, sequenceDiagrams.get(i).getName());
        }
		
		int seqDiagramOption = Integer.parseInt(sc.nextLine());
		SequenceDiagram chosenSeqDiagram = null;
		
		if (seqDiagramOption >= 0 && seqDiagramOption < sequenceDiagrams.size()) {
		    chosenSeqDiagram = sequenceDiagrams.get(seqDiagramOption);
		} else {
		    System.out.println("Diagrama Invalido");
		    addOptional();
		}

		System.out.println("Escolha o fragmento associado");
		
		List<Fragment> fragments = new ArrayList<>();
		
		for(ISequenceDiagramElement element : chosenSeqDiagram.getElements()) {
          if(element instanceof Fragment) {
              fragments.add((Fragment) element);
          }
		}
		
		for(int i = 0; i < fragments.size(); i++) {
		    System.out.printf("[%d] = %s\n", i, fragments.get(i).getName());
	    }
		
		int seqFragmentOption = Integer.parseInt(sc.nextLine());
		Fragment chosenFragment = null;

		if (seqFragmentOption >= 0 && seqFragmentOption < fragments.size()) {
		    chosenFragment = fragments.get(seqFragmentOption);
        } else {
            System.out.println("Diagrama Invalido");
            addOptional();
        }
		
		Optional optional = new Optional(chosenFragment, chosenSeqDiagram);
		
		sequenceDiagramGroup.addOptional(optional);
	}
	
	private void menuAddSequenceDiagramElements(SequenceDiagram diagram)
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    System.out.println("Menu: criar elementos do Diagrama de Sequencia");
		System.out.println("[1] Adicionar Mensagens");
		System.out.println("[2] Adicionar Fragmento");
		System.out.println("[3] Retornar");
		
		Fragment fragment = new Fragment("default");
		
		int num1 = Integer.parseInt(sc.nextLine());
		switch (num1) {
        case 1: {
            System.out.println("Insira o nome da mensagem");
            String name1 = sc.nextLine();
            System.out.println("Insira o valor da probablilidade");
            Float prob = Float.parseFloat(sc.nextLine());
            
            System.out.println("Lifelines disponíveis");
            showLifelines();
            
            System.out.println("Selecione o Lifeline de Origem");
            Lifeline source = getLifelineByOption();
            sequenceDiagramGroup.addLifeline(source);
            
            System.out.println("Selecione o Lifeline de Destino");
            Lifeline target = getLifelineByOption();
            sequenceDiagramGroup.addLifeline(target);
            
            Message message = new Message(name1, prob, source, target);
            diagram.addElement(message);
            
            break;
        }
        case 2: {
            System.out.println("Insira o nome do Fragmento");
            String name1 = sc.nextLine();
            fragment.setName(name1);
            diagram.addElement(fragment);
            
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
	
	private void showLifelines() {
	    List<Lifeline> lifelines = sequenceDiagramGroup.getLifelines().getLifelines();
	    for (int i = 0; i < lifelines.size(); i++) {
	        System.out.printf("[%d] = %s\n", i, lifelines.get(i).getName());
	    }
	}
	
	private Lifeline getLifelineByOption() {
	    List<Lifeline> lifelines = sequenceDiagramGroup.getLifelines().getLifelines();
	    int option = Integer.parseInt(sc.nextLine());
	    
	    if (option < 0 || option >= lifelines.size()) {
	        System.out.printf("Lifeline invalido");
	        getLifelineByOption();
	    }
        return lifelines.get(option);
	}
}
