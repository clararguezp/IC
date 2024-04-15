package Presentacion;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.model.mxICell;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import Negocio.Nodo;

public class SolutionView extends JFrame {
	private static final long serialVersionUID = 1L;
	private Nodo root;

    public SolutionView(Nodo root) {
        this.root = root;
        setLayout(new BorderLayout());
        this.add(buildTreePanel(), BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.pack();
    }

    private JPanel buildTreePanel() {
    	JPanel panel = new JPanel();
    	mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        mxICell rootCell = addNode(graph, parent, root);
        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);        
        layout.setInterRankCellSpacing(100);
        layout.execute(parent);
        panel.add(graphComponent);
        return panel;
    }
    
	private mxICell addNode(mxGraph graph, Object parent, Nodo node) {
		mxICell vertex;
		 
		 if(node.isEsAtributo()) {
			 vertex = (mxICell) graph.insertVertex(parent, node.getAtributo(), node.getAtributo(), 0, 0, 80, 30);
		 }
	     else if(node.isEsHoja()) {
	    	vertex = (mxICell) graph.insertVertex(parent, node.getClase(), node.getClase(), 0, 0, 80, 30);
	     }	
		 else {
			 vertex = (mxICell) graph.insertVertex(parent, node.getValor(), node.getValor(), 0, 0, 80, 30);
		 }
		
        if (!node.isEsHoja()) {
            mxICell[] childVertices = new mxICell[node.getHijos().size()];
            for (int i = 0; i < node.getHijos().size(); i++) {
                Nodo child = node.getHijos().get(i);
                mxICell childVertex = addNode(graph, parent, child);
                childVertices[i] = childVertex;
                graph.insertEdge(parent, null, "", vertex, childVertex);
            }
            graph.addCells(childVertices);
            graph.updateCellSize(vertex);
        }
        return vertex;
    }
}
