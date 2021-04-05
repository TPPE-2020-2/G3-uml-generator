package uml.diagrams;

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
import uml.diagrams.sequence.fragments.Fragments;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.sequencediagrams.messages.*;

public class Menu {
	
	//private ActivityDiagram activityDiagram;
	public  static SequenceDiagramsGroup sequenceDiagramGroup;
	public static Scanner sc = new Scanner(System.in);	
	public static Fragments fragmentos = new Fragments();
	
	public static void main(String[] args) throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException  {	
	
		menuInicial();
		
		

	}
	
	private static void createSequenceDiagram() {
		if (sequenceDiagramGroup == null) 
			sequenceDiagramGroup = new SequenceDiagramsGroup();
		else 
			System.out.println("JÃ¡ existe um diagrama de sequÃªncia");

	}
	
	public static void addElementToSequenceDiagram() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("[1] Adicionar Lifelines: ");
		System.out.println("[2] Adicionar Diagrama de sequência: ");
		System.out.println("[3] Adicionar Fragments: ");
		
		int num1 = Integer.parseInt(sc.nextLine());

		switch (num1) {
		case 1: {
			System.out.println("Digite o nome do LifeLine: ");
			String name = sc.nextLine();
			//System.out.println("Name: " + name);			
			Lifeline lifeline = new Lifeline(name);
			sequenceDiagramGroup.addLifeline(lifeline);
			break;
		}
		case 2: {
			handle2Diagrama();
			break;
		}
		case 3: {

		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
	
	public static void menuInicial() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("[1] Criar um grupo digrama de sequencia");
		System.out.println("[2] Validar diagramas");
		System.out.println("[3] Gerar XML");
		System.out.println("[4] Encerrar o programa");
		
		int num1 = Integer.parseInt(sc.nextLine());
		
		switch (num1) {
		case 1: {
			createSequenceDiagram();
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
			System.out.println("fim de programa");
			return;
		}
	}
	
	public static void handle2Diagrama() throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		SequenceDiagram diagram = new SequenceDiagram("default", true);
		
		System.out.println("Digite o nome do Diagrama de Sequência:");
		String name = sc.nextLine();
		System.out.println("Digite a condição de guarda (true or false):");
		String guard = sc.nextLine();
		
		if(guard.equals("true")) {
			 diagram.setName(name);
			 diagram.setGuardCondition(true);
			sequenceDiagramGroup.addSequenceDiagram(diagram);
			elementosDiagrama();
			//
			//System.out.println("DIAGRAMA: " + diagram);
		}
		else {
			 diagram.setName(name);
			 diagram.setGuardCondition(false);
			sequenceDiagramGroup.addSequenceDiagram(diagram);
			elementosDiagrama();
		}
		
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
			Fragment fragmento = new Fragment(name1);
			diagram.addElement(fragmento);
			
			System.out.println("Fragmento"+ fragmento);
			System.out.println("Lista de elementos"+ diagram.getElements());
			menuInicial();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
		
	}
	
	public static void elementosDiagrama() {
		System.out.println("[1] Adicionar Mensagens");
		System.out.println("[2] Adicionar Fragmento");
	}	

}
