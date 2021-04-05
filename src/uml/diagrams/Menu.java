package uml.diagrams;

import java.util.List;
import java.util.Scanner;


import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.fragments.Fragments;
import uml.diagrams.sequence.fragments.Optional;

public class Menu {
	
	//private ActivityDiagram activityDiagram;
	public  static SequenceDiagramsGroup sequenceDiagramGroup;
	public static Scanner sc = new Scanner(System.in);	
	public static Fragments fragmentos = new Fragments();
	
	public static void main(String[] args) throws SequenceDiagramRuleException, EmptyOptionalFragmentException  {	
	
		menuInicial();
		
		

	}
	
	private static void createSequenceDiagram() {
		if (sequenceDiagramGroup == null) 
			sequenceDiagramGroup = new SequenceDiagramsGroup();
		else 
			System.out.println("JÃ¡ existe um diagrama de sequÃªncia");

	}
	
	public static void addElementToSequenceDiagram() throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
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
			System.out.println("Digite o nome do Diagrama de Sequência:");
			String name = sc.nextLine();
			//System.out.println("Name: " + name);
			System.out.println("Digite a condição de guarda (true or false):");
			String guard = sc.nextLine();
			
			if(guard.equals("true")) {
				SequenceDiagram diagram = new SequenceDiagram(name, true);
				sequenceDiagramGroup.addSequenceDiagram(diagram);
				menuInicial();
				//
				//System.out.println("DIAGRAMA: " + diagram);
			}
			else {
				SequenceDiagram diagram = new SequenceDiagram(name, false);
				sequenceDiagramGroup.addSequenceDiagram(diagram);
			}
			 break;
		}
		case 3: {
			System.out.println("Digite o nome do Fragmento:");
			String name = sc.nextLine();
			//System.out.println("Name: " + name);
			Fragment fragmento = new Fragment(name);
			System.out.println("Criando Optional para o fragmento criado anteriormente");
			System.out.println("Digite o nome do diagrama de sequência que representa o Optional");
			String nameDiagram = sc.nextLine();
			
			Optional optional = new Optional(fragmento, sequenceDiagramGroup.getSequenceDiagram(nameDiagram)); 
			fragmentos.addOptional(optional);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
	
	public static void menuInicial() throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
		System.out.println("[1] Criar digrama de sequÃªncia");
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
}
