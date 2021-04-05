package uml.diagrams.sequence.sequencediagrams.fragments;

import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class FragmentParent {
	private Fragment fragment;
	private SequenceDiagram representedBy;
	
	public FragmentParent(Fragment fragment, SequenceDiagram representedBy) {
		super();
		this.fragment = fragment;
		this.representedBy = representedBy;
	}
	
	public Fragment getFragment() {
		return fragment;
	}
	public SequenceDiagram getRepresentedBy() {
		return representedBy;
	}
	
	@Override
	public String toString() {
		return String.format("Fragment: %s\n represetedBy: %s", this.fragment.toString(), representedBy.toString());
	}

}
