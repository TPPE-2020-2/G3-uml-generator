package uml.diagrams;

import java.util.Scanner;

import uml.diagrams.activity.ActivityDiagram;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class Menu {
	
	//private ActivityDiagram activityDiagram;
	public  static SequenceDiagramsGroup sequenceDiagramGroup;
	public static Scanner sc = new Scanner(System.in);	
	
	public static void main(String[] args) throws SequenceDiagramRuleException  {	
	
		System.out.println("[1] Criar digrama de sequÃªncia");
		System.out.println("[2] Validar diagramas");
		System.out.println("[3] Gerar XML");
		
		int num1 = Integer.parseInt(sc.nextLine());
		
		switch (num1) {
		case 1: {
			createSequenceDiagram();
			addElementToSequenceDiagram();
			break;
		}
		case 2: {
			
			break;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + num1);
			System.out.println("fim de criação1");
		}
	}
	
	private static void createSequenceDiagram() {
		if (sequenceDiagramGroup == null) 
			sequenceDiagramGroup = new SequenceDiagramsGroup();
		else 
			System.out.println("JÃ¡ existe um diagrama de sequÃªncia");

	}
	
		public static void addElementToSequenceDiagram() throws SequenceDiagramRuleException {
		System.out.println("[1] Adicionar Lifelines: ");
		System.out.println("[2] Adicionar Fragmentos: ");
		System.out.println("[3] Adicionar Diagrama de sequência: ");
		
		int num1 = Integer.parseInt(sc.nextLine());

		switch (num1) {
		case 1: {
			System.out.println("Digite o nome do LifeLine: ");
			String name = sc.nextLine();
			//System.out.println("Name: " + name);			
			Lifeline life = new Lifeline(name);
			sequenceDiagramGroup.addLifeline(life);
			break;
		}
		case 2: {
			System.out.println("Digite o nome do Fragmentos: ");
			sequenceDiagramGroup.addLifeline(new Lifeline(sc.nextLine()));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
}
