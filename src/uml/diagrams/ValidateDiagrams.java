package uml.diagrams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import uml.diagrams.activity.ActivityDiagram;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.activity.exceptions.ActivityRepresentationException;
import uml.diagrams.menu.sequencediagram.MenuSequenceDiagramsGroup;
import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.utils.XMLUtils;

public class ValidateDiagrams {

	ActivityDiagram activityDiagram;
	SequenceDiagramsGroup sequenceDiagramGroup;
	
	void createActivityDiagram(Menu menu) throws ActivityDiagramRuleException {
		if (this.activityDiagram == null) {
			printLine("Digite o nome do diagrama de atividade: ");
			this.activityDiagram = new ActivityDiagram(menu.getInputFromConsole());
		} else
			printLine("Já existe um diagrama de atividade com o nome: " + this.activityDiagram.getName());
	
		menu.addElementToActivityDiagram();
	}
	
	void createSequenceDiagramGroup()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException, MessageFormatException {
	    if (this.sequenceDiagramGroup == null) {
	        this.sequenceDiagramGroup = new MenuSequenceDiagramsGroup().createSequenceDiagramGroup();
	    }
	    else {
	        System.out.println("Ja existe um Grupo de Diagrama de Sequencia");
	    }
	}
	
	void generateXML() throws UnsupportedEncodingException, ParserConfigurationException, SAXException,
			IOException, TransformerException {
		String activityPath = System.getProperty("user.dir") + "/activity.xml";
		String sequencePath = System.getProperty("user.dir") + "/sequence.xml";
	
		XMLUtils.generateXML(this.activityDiagram.toString(), activityPath);
		XMLUtils.generateXML(this.sequenceDiagramGroup.toString(), sequencePath);
	
		printLine("Diagrama de atividade salvo em:: " + activityPath);
		printLine("Diagrama de sequência salvo em:: " + sequencePath);
	}
	void validateDiagrams() throws ActivityDiagramRuleException, ActivityRepresentationException {
		if (this.activityDiagram == null && this.sequenceDiagramGroup == null)
			throw new Error("Nenhum diagrama adicionado ainda. Crie um diagrama para poder realizar a validação...");
		else if (this.activityDiagram != null && this.sequenceDiagramGroup == null)
			throw new ActivityRepresentationException("O diagrama de atividades não possui um de sequência associado");
		else if (this.activityDiagram == null && this.sequenceDiagramGroup != null)
			throw new ActivityRepresentationException("O diagrama de sequência não possui um de atividades associado");
	
		this.activityDiagram.validateActivityDiagram();
	}
	
	void printLine(String text) {
		System.out.println(text);
	}





	
}
