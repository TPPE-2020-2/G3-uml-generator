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
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class Menu {
	
	private ActivityDiagram activityDiagram;
	private SequenceDiagramsGroup sequenceDiagramGroup;
	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)  {	
		
		System.out.println("[1] Criar digrama de atividade");
		System.out.println("[2] Criar digrama de sequência");
		System.out.println("[3] Validar diagramas");
		System.out.println("[4] Gerar XML");
	}
	
	private void createActivityDiagram() throws ActivityDiagramRuleException {
		if (this.activityDiagram == null) {
			printLine("Digite o nome do diagrama de atividade: ");
			this.activityDiagram = new ActivityDiagram(sc.nextLine());
		}
		else 
			printLine("Já existe um diagrama de atividade com o nome: " + this.activityDiagram.getName());

	}
	
	private void addElementToActivityDiagram() throws ActivityDiagramRuleException {
		printLine("[1] Adicionar Start Node: ");
		printLine("[2] Adicionar Activity Node: ");
		printLine("[3] Adicionar Decision Node: ");
		printLine("[4] Adicionar Merge Node: ");
		printLine("[5] Adicionar Final Node: ");
		printLine("[6] Adicionar Transistion: ");
		
		int num1 = sc.nextInt();

		switch (num1) {
		case 1: {
			printLine("Digite o nome do start Node: ");
			this.activityDiagram.addNodeElement(new StartNode(sc.nextLine()));
		}
		case 2: {
			printLine("Digite o nome do Activity Node: ");
			this.activityDiagram.addNodeElement(new ActivityNode(sc.nextLine()));
		}
		case 3: {
			printLine("Digite o nome do Decision Node: ");
			this.activityDiagram.addNodeElement(new DecisionNode(sc.nextLine()));
		}
		case 4: {
			printLine("Digite o nome do merge Node: ");
			this.activityDiagram.addNodeElement(new MergeNode(sc.nextLine()));
		}
		case 5: {
			printLine("Digite o nome do final Node: ");
			this.activityDiagram.addNodeElement(new FinalNode(sc.nextLine()));
		}
		case 6: {
			printLine("Digite o nome da transition: ");
			String transitionName = sc.nextLine();
						
			printLine("Digite o nome da prob:");
			Float transitionProb = sc.nextFloat();
			
			printLine("Escolha o source:");
			BaseNode source = getElement();
			
			printLine("Escolha o target:");
			BaseNode target = getElement();
			
			this.activityDiagram.addTransition(transitionName, transitionProb, source, target);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
	
	public BaseNode getElement() {
		printLine("Escolha o tipo do elemento: ");
		printLine("[1] Start node");
		printLine("[2] Activity Node: ");
		printLine("[3] Decision Node: ");
		printLine("[4] Merge node: ");
		printLine("[5] Final node: ");
		
		int num1 = sc.nextInt();
		
		switch (num1) {
		case 1: {
			return this.activityDiagram.getActivityDiagramElements().getStartNode();
		}
		case 2: {
			printLine("Digite o índice do activity node:");
			int i = 0;
			for (ActivityNode node : this.activityDiagram.getActivityDiagramElements().getActivityNodes()) {
				printLine("[" + i++ + "]" + node.getName());
			}
			return this.activityDiagram.getActivityDiagramElements().getActivityNodes().get(sc.nextInt());
		}
		case 3: {
			printLine("Digite o índice do Decision node:");
			int i = 0;
			for (DecisionNode node : this.activityDiagram.getActivityDiagramElements().getDecisionNodes()) {
				printLine("[" + i++ + "]" + node.getName());
			}
			return this.activityDiagram.getActivityDiagramElements().getDecisionNodes().get(sc.nextInt());
		}
		case 4: {
			printLine("Digite o índice do Merge node:");
			int i = 0;
			for (DecisionNode node : this.activityDiagram.getActivityDiagramElements().getDecisionNodes()) {
				printLine("[" + i++ + "]" + node.getName());
			}
			return this.activityDiagram.getActivityDiagramElements().getDecisionNodes().get(sc.nextInt());
		}
		case 5: {
			printLine("Digite o índice do Final node:");
			int i = 0;
			for (FinalNode node : this.activityDiagram.getActivityDiagramElements().getFinalNodes()) {
				printLine("[" + i++ + "]" + node.getName());
			}
			return this.activityDiagram.getActivityDiagramElements().getFinalNodes().get(sc.nextInt());
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
	
	private void createSequenceDiagram() {
		if (this.sequenceDiagramGroup == null) 
			this.sequenceDiagramGroup = new SequenceDiagramsGroup();
		else 
			printLine("Já existe um diagrama de sequência");

	}
	
	private void printLine(String text) {
		System.out.println(text);
	}
}
