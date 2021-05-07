package uml.diagrams.menu.sequencediagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.SequenceDiagramsGroupController;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;
import uml.diagrams.sequence.sequencediagrams.messages.MessageAsync;
import uml.diagrams.sequence.sequencediagrams.messages.MessageSync;
import uml.diagrams.sequence.sequencediagrams.messages.Reply;
import uml.diagrams.sequence.fragments.Optional;

public class MenuSequenceDiagramsGroup {

    private SequenceDiagramsGroupController sequenceDiagramsGroupController = new SequenceDiagramsGroupController();
    private Scanner sc = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public SequenceDiagramsGroup createSequenceDiagramGroup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
        menuAddComponentToSequenceDiagramGroup();

        return sequenceDiagramsGroupController.getGeneratedDiagram();
    }

    private void menuAddComponentToSequenceDiagramGroup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
        printLine("[1] Adicionar Lifeline ");
        printLine("[2] Adicionar/Editar Diagrama de sequência ");
        printLine("[3] Adicionar Optional ");
        printLine("[4] Visualizar diagrama");
        printLine("[5] Retornar ao menu principal ");

        int num1 = sc.nextInt();

        switch (num1) {
        case 1: {
            addLifeline();
            break;
        }
        case 2: {
            addOrReplaceSequenceDiagram();
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

    private void addLifeline() throws SequenceDiagramRuleException {
        System.out.println("Menu: criar LifeLine");
        System.out.println("Digite o nome do LifeLine: ");
        String name = getInputFromConsole();

        sequenceDiagramsGroupController.addLifeline(name);
    }

    private void addOrReplaceSequenceDiagram()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
        SequenceDiagram diagram;
        int option;
        printLine("[1] Adicionar Diagrama de sequência: ");
        printLine("[2] Editar Diagrama de sequência: ");

        option = sc.nextInt();
        if (option == 1) {
            diagram = addSequenceDiagram();
        } else if (option == 2) {
            diagram = getSequenceDiagramByOption();
        } else {
            printLine("Opção inválida. Retornando ao menu...");
            return;
        }

        if (diagram != null) {
            new MenuAddItemsSequenceDiagram(sequenceDiagramsGroupController).addSequenceDiagramElements(diagram);
        } else {
            printLine("Diagrama de sequência não encontrado. Retornando ao menu...");
            return;
        }
    }

    private SequenceDiagram addSequenceDiagram() throws SequenceDiagramRuleException {
        System.out.println("Menu: criar Diagrama de Sequencia");
        System.out.println("Digite o nome do Diagrama de Sequencia:");
        String name = getInputFromConsole();
        System.out.println("Digite a condicao de guarda (true or false):");
        Boolean guard = sc.nextBoolean();

        return sequenceDiagramsGroupController.addSequenceDiagram(name, guard);
    }

    private void addOptional()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
        String optionalName = null;
        SequenceDiagram chosenSeqDiagram = null;

        System.out.println("Menu: Criar Optional");
        System.out.println("Digite o nome do Diagrama de Sequencia: ");
        optionalName = getInputFromConsole();

        chosenSeqDiagram = getSequenceDiagramByOption();
        if (chosenSeqDiagram != null) {
            sequenceDiagramsGroupController.addOptional(optionalName, chosenSeqDiagram);
        } else {
            System.out.println("Diagrama Invalido");
            return;
        }
    }

    private void showDiagram() {
        printLine(sequenceDiagramsGroupController.getGeneratedDiagramFormatted());
    }

    private SequenceDiagram getSequenceDiagramByOption() {
        SequenceDiagram sequenceDiagram = null;
        printLine("Escolha o diagrama de sequência (por índice): ");

        if (showSequenceDiagrams()) {
            sequenceDiagram = sequenceDiagramsGroupController.getSequenceDiagramByIndex(sc.nextInt());
        }
        return sequenceDiagram;
    }

    private boolean showSequenceDiagrams() {
        List<SequenceDiagram> sequenceDiagrams = sequenceDiagramsGroupController.getSequenceDiagrams();
        if (sequenceDiagrams != null && !sequenceDiagrams.isEmpty()) {
            for (int i = 0; i < sequenceDiagrams.size(); i++) {
                printLine("[" + i + "] " + sequenceDiagrams.get(i).getName());
            }
            return true;
        }
        printLine("Nao ha diagramas disponiveis");
        return false;
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
