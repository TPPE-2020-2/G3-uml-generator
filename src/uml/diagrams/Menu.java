package uml.diagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Fragments;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.ISequenceDiagramElement;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.fragments.FragmentParent;
import uml.diagrams.sequence.sequencediagrams.messages.Message;

public class Menu {

	private ActivityDiagram activityDiagram;
	private SequenceDiagramsGroup sequenceDiagramGroup;
	private Scanner sc = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private SequenceDiagram diagram;

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
				printLine("Ocorreu um erro inesperado::" + e.toString());
				printLine("Retornando ao menu inicial...");
			}
			break;
		}
		case 2: {
			try {
				createSequenceDiagramGroup();
			} catch (SequenceDiagramRuleException | EmptyOptionalFragmentException | MessageFormatException e) {
				printLine("Ocorreu um erro inesperado::" + e.toString());
				printLine("Retornando ao menu inicial...");
			}
		}
		case 3: {
			try {
				validateDiagrams();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException e) {
				printLine("O seguinte problema foi encontrado::" + e.toString());
				printLine("Retornando ao menu inicial...");
			}
			break;
		}
		case 4: {
			try {
				validateDiagrams();
				generateXML();
			} catch (ActivityDiagramRuleException | ActivityRepresentationException | ParserConfigurationException
					| SAXException | IOException | TransformerException e) {
				printLine("O seguinte problema foi encontrado ao tentar gerar o XML::" + e.toString());
				printLine("Retornando ao menu inicial...");
			}
		}
		default:
			printLine("Opção inválida:: " + num1);
		}
		menu();
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

	private BaseNode getElement() {
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

	private void createSequenceDiagramGroup()
			throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		if (this.sequenceDiagramGroup == null)
			this.sequenceDiagramGroup = new SequenceDiagramsGroup();
		else
			printLine("Já existe um diagrama de sequência");

		addElementToSequenceDiagram();

	}

	private void validateDiagrams() throws ActivityDiagramRuleException, ActivityRepresentationException {
		if (this.activityDiagram == null && this.sequenceDiagramGroup == null)
			throw new Error("Nenhum diagrama adicionado ainda. Crie um diagrama para poder realizar a validação...");
		else if (this.activityDiagram != null && this.sequenceDiagramGroup == null)
			throw new ActivityRepresentationException("O diagrama de atividades não possui um de sequência associado");
		else if (this.activityDiagram == null && this.sequenceDiagramGroup != null)
			throw new ActivityRepresentationException("O diagrama de sequência não possui um de atividades associado");

		this.activityDiagram.validateActivityDiagram();
	}

	private void generateXML() throws UnsupportedEncodingException, ParserConfigurationException, SAXException,
			IOException, TransformerException {
		String activityPath = System.getProperty("user.dir") + "/activity.xml";
		String sequencePath = System.getProperty("user.dir") + "/sequence.xml";

		XMLUtils.generateXML(this.activityDiagram.toString(), activityPath);
		XMLUtils.generateXML(this.sequenceDiagramGroup.toString(), sequencePath);

		printLine("Diagrama de atividade salvo em:: " + activityPath);
		printLine("Diagrama de sequência salvo em:: " + sequencePath);
	}

	private void addElementToSequenceDiagram()
			throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		printLine("[1] Adicionar Lifelines: ");
		printLine("[2] Adicionar/Editar Diagrama de sequência: ");
		printLine("[3] Adicionar Fragments: ");
		printLine("[4] Visualizar diagrama");
		printLine("[5] Retornar ao menu: ");

		int num1 = sc.nextInt();

		switch (num1) {
			case 1: {
				System.out.println("Menu: criar LifeLine");
				System.out.println("Digite o nome do LifeLine: ");
				Lifeline lifeline = new Lifeline(getInputFromConsole());
				sequenceDiagramGroup.addLifeline(lifeline);
				break;
			}
			case 2: {
				printLine("[1] Adicionar Diagrama de sequência: ");
				printLine("[2] Editar Diagrama de sequência: ");
				this.diagram = null;
				
				int num2 = sc.nextInt();
				
				if (num2 == 1) {
					printLine("Menu: criar Diagrama de Sequencia");
					printLine("Digite o nome do Diagrama de Sequência:");
					
					String name = getInputFromConsole();
					
					printLine("Digite a condição de guarda (true or false):");
					
					String guard = getInputFromConsole();
					
					boolean choice = guard.equals("true");
					
					SequenceDiagram tempDiagram = new SequenceDiagram(name, choice);
					
					this.diagram = tempDiagram;
					
					sequenceDiagramGroup.addSequenceDiagram(tempDiagram);
				}
				else if (num2 == 2) {
					printLine("Escolha o diagrama de sequência (por índice): ");
					int i = 0;
					for (SequenceDiagram diag: sequenceDiagramGroup.getSequenceDiagrams()) {
						printLine("[" + i++ + "] " + diag.getName());
					}
					
					this.diagram = sequenceDiagramGroup.getSequenceDiagrams().get(sc.nextInt());
				}
				else {
					printLine("Opção inválida. Retornando ao menu...");
					menu();
				}
				
				handle2Diagrama();
				break;
			}
			case 3: {
				printLine("Insira o nome do fragment");
				String name = getInputFromConsole();
				
				printLine("Escolha o diagrama de sequência (por índice): ");
				int i = 0;
				for (SequenceDiagram diag: sequenceDiagramGroup.getSequenceDiagrams()) {
					printLine("[" + i++ + "] " + diag.getName());
				}
				
				SequenceDiagram tempDiagram = sequenceDiagramGroup.getSequenceDiagrams().get(sc.nextInt());
				
				sequenceDiagramGroup.getOptionals().addOptional(new Optional(new Fragment(name), tempDiagram));
				
				break;
			}
			case 4: {
				printLine(this.sequenceDiagramGroup.toString().replaceAll("(?<!\\/)><", ">\n<").replaceAll("/><", "/>\n<"));
			}
			case 5: {
				menu();
			}
			default: {
				printLine("Opção inválida:: " + num1);
				menu();
			}
		}
		addElementToSequenceDiagram();
	}

	private void handle2Diagrama()
			throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
		System.out.println("Menu: criar elementos de Diagrama de Sequencia (diagrama atual ::" + this.diagram.getName() + ")::");
		System.out.println("[1] Criar Mensagem");
		System.out.println("[2] Criar Fragmento");
		System.out.println("[3] Criar Optional");
		System.out.println("[4] Voltar ao menu anterior");

		Fragment fragmento = new Fragment("default");

		int num1 = sc.nextInt();
		switch (num1) {
			case 1: {
				System.out.println("Insira o nome da mensagem");
				String name1 = getInputFromConsole();
				System.out.println("Insira o valor da probablilidade");
				Float prob = sc.nextFloat();

				System.out.println("Insira o Lifeline de Origem");

				Lifeline source = getLifeline();

				System.out.println("Insira o Lifeline de Destino");

				Lifeline target = getLifeline();

				Message message = new Message(name1, prob, source, target);
				diagram.addElement(message);
				break;
			}
			case 2: {
				System.out.println("Insira o nome do Fragmento");
				String name1 = getInputFromConsole();
				fragmento.setName(name1);
				diagram.addElement(fragmento);
				break;
			}
			case 3: {
				System.out.println("Menu: criar Optional");
				List<SequenceDiagram> sequenceDiagrams = sequenceDiagramGroup.getSequenceDiagrams();
				List<FragmentParent> fragmentParents = new ArrayList<>();
				for (SequenceDiagram seq : sequenceDiagrams) {
					List<ISequenceDiagramElement> elements = seq.getElements();
					for (ISequenceDiagramElement element : elements) {
						if (element instanceof Fragment) {
							fragmentParents.add(new FragmentParent((Fragment) element, seq));
						}
					}
				}
				if (fragmentParents.isEmpty()) {
					throw new IllegalArgumentException("Nao ha nenhum fragment cadastrado.");
				}
	
				System.out.println("Os fragmentos criados se encontram na lista abaixo: ");
	
				for (int i = 0; i < fragmentParents.size(); i++) {
					System.out.printf("Fragment [%d] = \n%s\n\n", i, fragmentParents.get(i));
				}
	
				System.out.print("Para selecionar o fragmento desejado digite o indice do fragmento desejado: ");
	
				FragmentParent chosenFragment = fragmentParents.get(sc.nextInt());
				Optional optional = new Optional(chosenFragment.getFragment(), chosenFragment.getRepresentedBy());
				Fragments fragments = new Fragments();
				break;
			}
			case 4: {
				addElementToSequenceDiagram();
			}
			default: {
				printLine("Opção inválida:: " + num1);
				menu();			
			}
		}
		
		handle2Diagrama();
	}
	
	private Lifeline getLifeline() {
		printLine("Escola a lifeline (por índice):");
		int i = 0;
		for (Lifeline lifeline: sequenceDiagramGroup.getLifelines().getLifelines()) {
			printLine("[" + i++ + "] " + lifeline.getName());
		}
		return sequenceDiagramGroup.getLifelines().getLifelines().get(sc.nextInt());
	}

	private void printLine(String text) {
		System.out.println(text);
	}
}
