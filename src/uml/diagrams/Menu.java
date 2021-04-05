package uml.diagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import uml.diagrams.utils.XMLUtils;

import uml.diagrams.activity.ActivityDiagram;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.activity.exceptions.ActivityRepresentationException;
import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class Menu {

	private ActivityDiagram activityDiagram;
	private SequenceDiagramsGroup sequenceDiagramGroup;
	private Scanner sc = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void menu() {

		System.out.println("[1] Criar/editar digrama de atividade");
		System.out.println("[2] Criar/editar digrama de sequência");
		System.out.println("[3] Validar diagramas");
		System.out.println("[4] Gerar XML");

		int num1 = sc.nextInt();

		switch (num1) {
		case 1: {
			try {
				createActivityDiagram();
			} catch (ActivityDiagramRuleException e) {
				e.printStackTrace();
			}
			break;
		}
		case 2: {
			createSequenceDiagram();
		}
		case 3: {
			try {
				validateDiagrams();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException e) {
				printLine("O seguinte problema foi encontrado::");
				printLine(e.toString());
			}
			break;
		}
		case 4: {
			try {
				validateDiagrams();
				generateXML();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException | ParserConfigurationException
					| SAXException | IOException | TransformerException e) {
				printLine("O seguinte problema foi encontrado ao tentar gerar o XML::");
				printLine(e.toString());
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + num1);
		}
	}

	private void createActivityDiagram() throws ActivityDiagramRuleException {
		if (this.activityDiagram == null) {
			printLine("Digite o nome do diagrama de atividade: ");
			this.activityDiagram = new ActivityDiagram(getInputFromConsole());
		} else
			printLine("Já existe um diagrama de atividade com o nome: " + this.activityDiagram.getName());

		addElementToActivityDiagram();

	}

	private void addElementToActivityDiagram() throws ActivityDiagramRuleException {
		printLine("[1] Adicionar Start Node: ");
		printLine("[2] Adicionar Activity Node: ");
		printLine("[3] Adicionar Decision Node: ");
		printLine("[4] Adicionar Merge Node: ");
		printLine("[5] Adicionar Final Node: ");
		printLine("[6] Adicionar Transistion: ");
		printLine("[7] Printar diagrama: ");
		printLine("[8] Retornar ao menu: ");

		int num1 = sc.nextInt();

		switch (num1) {
		case 1: {
			printLine("Digite o nome do start Node: ");
			this.activityDiagram.addNodeElement(new StartNode(getInputFromConsole()));
			break;
		}
		case 2: {
			printLine("Digite o nome do Activity Node: ");
			this.activityDiagram.addNodeElement(new ActivityNode(getInputFromConsole()));
			break;
		}
		case 3: {
			printLine("Digite o nome do Decision Node: ");
			this.activityDiagram.addNodeElement(new DecisionNode(getInputFromConsole()));
			break;
		}
		case 4: {
			printLine("Digite o nome do merge Node: ");
			this.activityDiagram.addNodeElement(new MergeNode(getInputFromConsole()));
			break;
		}
		case 5: {
			printLine("Digite o nome do final Node: ");
			this.activityDiagram.addNodeElement(new FinalNode(getInputFromConsole()));
			break;
		}
		case 6: {
			printLine("Digite o nome da transition: ");
			String transitionName = getInputFromConsole();

			printLine("Digite o valor da prob:");
			Float transitionProb = sc.nextFloat();

			printLine("Escolha o source:");
			BaseNode source = getElement();

			printLine("Escolha o target:");
			BaseNode target = getElement();

			this.activityDiagram.addTransition(transitionName, transitionProb, source, target);
			break;
		}
		case 7: {
			printLine(this.activityDiagram.toString().replaceAll("(?<!\\/)><", ">\n<").replaceAll("/><", "/>\n<"));
			break;
		}
		case 8: {
			menu();
			break;
		}
		default:
			printLine("Opção inválida");
			addElementToActivityDiagram();
		}

		addElementToActivityDiagram();
	}

	private String getInputFromConsole() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return "";
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

	private void validateDiagrams() throws ActivityDiagramRuleException, ActivityRepresentationException {
		if (this.activityDiagram == null && this.sequenceDiagramGroup == null)
			throw new Error("Nenhum diagrama adicionado ainda. Crie um diagrama para poder realizar a validação...");
		else if (this.activityDiagram != null && this.sequenceDiagramGroup == null)
			throw new ActivityRepresentationException("O diagrama de sequência não possui um de sequência associado");

		this.activityDiagram.validateActivityDiagram();
	}

	private void generateXML() throws UnsupportedEncodingException, ParserConfigurationException, SAXException,
			IOException, TransformerException {
		String activityPath = System.getProperty("user.dir") + "/activity.xml";
		String sequencePath = System.getProperty("user.dir") + "/sequence.xml";

		XMLUtils.generateXML(this.activityDiagram.toString(), activityPath);
		XMLUtils.generateXML(this.sequenceDiagramGroup.toString(), sequencePath);
		
		printLine("Diagrama de atividade salvo em:: "+ activityPath);
		printLine("Diagrama de sequência salvo em:: "+ sequencePath);
	}

	private void printLine(String text) {
		System.out.println(text);
	}
}
