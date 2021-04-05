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
import uml.diagrams.sequence.sequencediagrams.fragments.FragmentParent;
import uml.diagrams.sequence.sequencediagrams.messages.Message;
import uml.diagrams.sequence.fragments.Fragments;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.sequencediagrams.messages.*;

public class MenuSequenceDiagram {
	
	//private ActivityDiagram activityDiagram;
	public  static SequenceDiagramsGroup sequenceDiagramGroup;
	public static Scanner sc = new Scanner(System.in);	
	public static Fragments fragmentos = new Fragments();
	public static SequenceDiagram diagram;

	
	public static void main(String[] args) throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException  {	
		createSequenceDiagram();
		menuInicial();
	}
	
	private static void createSequenceDiagramGroup() {
		if (sequenceDiagramGroup == null) {
			sequenceDiagramGroup = new SequenceDiagramsGroup();
		}
		else {
			System.out.println("Ja existe um Grupo de Diagrama de Sequencia");
		}
	}
	
	private static void createSequenceDiagram() throws SequenceDiagramRuleException {
		if (diagram == null) {
			diagram = new SequenceDiagram("default", true);
		}
	}
	
	public static void addElementToSequenceDiagram() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("[1] Adicionar Lifelines: ");
		System.out.println("[2] Adicionar Diagrama de sequ�ncia: ");
		System.out.println("[3] Adicionar Fragments: ");
		
		int num1 = Integer.parseInt(sc.nextLine());

		switch (num1) {
		case 1: {
			System.out.println("Menu: criar LifeLine");
			System.out.println("Digite o nome do LifeLine: ");
			String name = sc.nextLine();			
			Lifeline lifeline = new Lifeline(name);
			sequenceDiagramGroup.addLifeline(lifeline);
			menuInicial();
			break;
		}
		case 2: {
			createSequenceDiagram();
			System.out.println("Menu: criar Diagrama de Sequencia");
			System.out.println("Digite o nome do Diagrama de Sequ�ncia:");
			String name = sc.nextLine();
			System.out.println("Digite a condi��o de guarda (true or false):");
			String guard = sc.nextLine();
			
			if(guard.equals("true")) {
				 diagram.setName(name);
				 diagram.setGuardCondition(true);
				sequenceDiagramGroup.addSequenceDiagram(diagram);
				//
				//System.out.println("DIAGRAMA: " + diagram);
			}
			else {
				 diagram.setName(name);
				 diagram.setGuardCondition(false);
				sequenceDiagramGroup.addSequenceDiagram(diagram);
			}
			elementosDiagrama();
			break;
		}
		case 3: {
			handle2Diagrama();
			menuInicial();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
	
	public static void menuInicial() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("Menu: inicial");
		System.out.println("[1] Criar um grupo digrama de sequencia");
		System.out.println("[2] Validar diagramas");
		System.out.println("[3] Gerar XML");
		System.out.println("[4] Encerrar o programa");
		
		int num1 = Integer.parseInt(sc.nextLine());
		
		switch (num1) {
		case 1: {
			createSequenceDiagramGroup();
			System.out.println("[1] Adicionar elemento ao diagrama");
			System.out.println("[2] Voltar ao menu inicial");
			int num = Integer.parseInt(sc.nextLine());
			
			if(num == 1) {
				addElementToSequenceDiagram();
			}
			else {
				break;
			}
			
			break;
		}
		default:
			System.out.println("Sair");
			return;
		}
	}
	
	public static void handle2Diagrama() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("Menu: criar elementos de Diagrama de Sequencia");
		System.out.println("[1] Criar Mensagem");
		System.out.println("[2] Criar Fragmento");
		System.out.println("[3] Criar Optional");
		
		Fragment fragmento = new Fragment("default");
		
		//elementosDiagrama();
		int num1 = Integer.parseInt(sc.nextLine());
		switch (num1) {
		case 1: {
			System.out.println("Insira o nome da mensagem");
			String name1 = sc.nextLine();
			System.out.println("Insira o valor da probablilidade");
			Float prob = Float.parseFloat(sc.nextLine());
			
			System.out.println("Insira o Lifeline de Origem");
			String origem = sc.nextLine();
			Lifeline source = new Lifeline(origem);
			sequenceDiagramGroup.addLifeline(source);
			
			System.out.println("Insira o Lifeline de Destino");
			String destino = sc.nextLine();
			Lifeline target = new Lifeline(destino);
			sequenceDiagramGroup.addLifeline(target);
			
			Message message = new Message(name1, prob, source, target);
			diagram.addElement(message);
			
			//System.out.println("Mensagem"+ message);
			//System.out.println("Lista de elementos"+ diagram.getElements());
			menuInicial();
			break;
		}
		case 2: {
			System.out.println("Insira o nome do Fragmento");
			String name1 = sc.nextLine();
			fragmento.setName(name1);
			diagram.addElement(fragmento);
			
			//System.out.println("Fragmento"+ fragmento);
			//System.out.println("Lista de elementos"+ diagram.getElements());
			menuInicial();
			break;
		}
		case 3: {
			System.out.println("Menu: criar Optional");
			List<SequenceDiagram> sequenceDiagrams = sequenceDiagramGroup.getSequenceDiagrams();
			List<FragmentParent> fragmentParents = new ArrayList<>();
			for(SequenceDiagram seq : sequenceDiagrams) {
				List<ISequenceDiagramElement> elements = seq.getElements();
				for(ISequenceDiagramElement element : elements) {
					if(element instanceof Fragment) {
						fragmentParents.add(new FragmentParent((Fragment) element, seq));
					}
				}		
			}
			if(fragmentParents.isEmpty()) {
				throw new IllegalArgumentException("Nao ha nenhum fragment cadastrado.");
			}
			
			System.out.println("Os fragmentos criados se encontram na lista abaixo: ");
			
			
			for(int i = 0; i < fragmentParents.size(); i++) {
				System.out.printf("Fragment [%d] = \n%s\n\n", i, fragmentParents.get(i));
			}
			
			System.out.print("Para selecionar o fragmento desejado digite o indice do fragmento desejado: ");
			
			FragmentParent chosenFragment = fragmentParents.get(Integer.parseInt(sc.nextLine()));
			Optional optional = new Optional(chosenFragment.getFragment(), chosenFragment.getRepresentedBy());
			Fragments fragments = new Fragments();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
		
	}
	
	public static void elementosDiagrama() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("[1] Adicionar Mensagens");
		System.out.println("[2] Adicionar Fragmento");
		handle2Diagrama();
	}	

}
