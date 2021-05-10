package uml.diagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.activity.exceptions.ActivityRepresentationException;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	ValidateDiagrams diagram = new ValidateDiagrams();

	public void menu() {

		System.out.println("[1] Criar/editar digrama de atividade");
		System.out.println("[2] Criar/editar digrama de sequência");
		System.out.println("[3] Validar diagramas");
		System.out.println("[4] Gerar XML");

		int num1 = sc.nextInt();

		switch (num1) {
		case 1: {
			try {
				diagram.createActivityDiagram(this);
			} catch (ActivityDiagramRuleException e) {
				diagram.printLine("Ocorreu um erro inesperado::" + e.toString());
				diagram.printLine("Retornando ao menu inicial...");
			}
			break;
		}
		case 2: {
			try {
				diagram.createSequenceDiagramGroup();
			} catch (SequenceDiagramRuleException | EmptyOptionalFragmentException | MessageFormatException e) {
				diagram.printLine("Ocorreu um erro inesperado::" + e.toString());
				diagram.printLine("Retornando ao menu inicial...");
			}
			break;
		}
		case 3: {
			try {
				diagram.validateDiagrams();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException e) {
				diagram.printLine("O seguinte problema foi encontrado::" + e.toString());
				diagram.printLine("Retornando ao menu inicial...");
			}
			break;
		}
		case 4: {
			try {
				diagram.validateDiagrams();
				diagram.generateXML();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException | ParserConfigurationException
					| SAXException | IOException | TransformerException e) {
				diagram.printLine("O seguinte problema foi encontrado ao tentar gerar o XML::" + e.toString());
				diagram.printLine("Retornando ao menu inicial...");
			}
		}
		default:
			diagram.printLine("Opção inválida:: " + num1);
		}
		menu();
	}

	void addElementToActivityDiagram() throws ActivityDiagramRuleException {
		diagram.printLine("[1] Adicionar Start Node: ");
		diagram.printLine("[2] Adicionar Activity Node: ");
		diagram.printLine("[3] Adicionar Decision Node: ");
		diagram.printLine("[4] Adicionar Merge Node: ");
		diagram.printLine("[5] Adicionar Final Node: ");
		diagram.printLine("[6] Adicionar Transistion: ");
		diagram.printLine("[7] Printar diagrama: ");
		diagram.printLine("[8] Retornar ao menu: ");

		int num1 = sc.nextInt();

		switch (num1) {
		case 1: {
			diagram.printLine("Digite o nome do start Node: ");
			diagram.activityDiagram.addNodeElement(new StartNode(getInputFromConsole()));
			break;
		}
		case 2: {
			diagram.printLine("Digite o nome do Activity Node: ");
			diagram.activityDiagram.addNodeElement(new ActivityNode(getInputFromConsole()));
			break;
		}
		case 3: {
			diagram.printLine("Digite o nome do Decision Node: ");
			diagram.activityDiagram.addNodeElement(new DecisionNode(getInputFromConsole()));
			break;
		}
		case 4: {
			diagram.printLine("Digite o nome do merge Node: ");
			diagram.activityDiagram.addNodeElement(new MergeNode(getInputFromConsole()));
			break;
		}
		case 5: {
			diagram.printLine("Digite o nome do final Node: ");
			diagram.activityDiagram.addNodeElement(new FinalNode(getInputFromConsole()));
			break;
		}
		case 6: {
			diagram.printLine("Digite o nome da transition: ");
			String transitionName = getInputFromConsole();

			diagram.printLine("Digite o valor da prob:");
			Float transitionProb = sc.nextFloat();

			diagram.printLine("Escolha o source:");
			BaseNode source = getElement();

			diagram.printLine("Escolha o target:");
			BaseNode target = getElement();

			diagram.activityDiagram.addTransition(transitionName, transitionProb, source, target);
			break;
		}
		case 7: {
			diagram.printLine(diagram.activityDiagram.toString().replaceAll("(?<!\\/)><", ">\n<").replaceAll("/><", "/>\n<"));
			break;
		}
		case 8: {
			menu();
			break;
		}
		default:
			diagram.printLine("Opção inválida");
			addElementToActivityDiagram();
		}

		addElementToActivityDiagram();
	}

	String getInputFromConsole() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return "";
		}
	}

	private BaseNode getElement() {
		diagram.printLine("Escolha o tipo do elemento: ");
		diagram.printLine("[1] Start node");
		diagram.printLine("[2] Activity Node: ");
		diagram.printLine("[3] Decision Node: ");
		diagram.printLine("[4] Merge node: ");
		diagram.printLine("[5] Final node: ");

		int num1 = sc.nextInt();

		switch (num1) {
			case 1: {
				return diagram.activityDiagram.getActivityDiagramElements().getStartNode();
			}
			case 2: {
				diagram.printLine("Digite o índice do activity node:");
				int i = 0;
				for (ActivityNode node : diagram.activityDiagram.getActivityDiagramElements().getActivityNodes()) {
					diagram.printLine("[" + i++ + "]" + node.getName());
				}
				return diagram.activityDiagram.getActivityDiagramElements().getActivityNodes().get(sc.nextInt());
			}
			case 3: {
				diagram.printLine("Digite o índice do Decision node:");
				int i = 0;
				for (DecisionNode node : diagram.activityDiagram.getActivityDiagramElements().getDecisionNodes()) {
					diagram.printLine("[" + i++ + "]" + node.getName());
				}
				return diagram.activityDiagram.getActivityDiagramElements().getDecisionNodes().get(sc.nextInt());
			}
			case 4: {
				diagram.printLine("Digite o índice do Merge node:");
				int i = 0;
				for (DecisionNode node : diagram.activityDiagram.getActivityDiagramElements().getDecisionNodes()) {
					diagram.printLine("[" + i++ + "]" + node.getName());
				}
				return diagram.activityDiagram.getActivityDiagramElements().getDecisionNodes().get(sc.nextInt());
			}
			case 5: {
				diagram.printLine("Digite o índice do Final node:");
				int i = 0;
				for (FinalNode node : diagram.activityDiagram.getActivityDiagramElements().getFinalNodes()) {
					diagram.printLine("[" + i++ + "]" + node.getName());
				}
				return diagram.activityDiagram.getActivityDiagramElements().getFinalNodes().get(sc.nextInt());
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}
}
