package Presentacion;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.model.mxICell;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import Negocio.Nodo;

public class SolucionView extends JFrame {
	private static final long serialVersionUID = 1L;
	private Nodo root;

    public SolucionView(Nodo root) {
        this.root = root;        
        initGUI();
    }
    
    public void initGUI() {
    	setLayout(new BorderLayout());
        add(buildTreePanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
		setVisible(true);
		pack();
    }

    private JPanel buildTreePanel() {
    	JPanel solucionPanel = new JPanel();
    	mxGraph grafoSolucion = new mxGraph();
        Object parent = grafoSolucion.getDefaultParent();
        
        grafoSolucion.getModel().beginUpdate();
        mxICell nodoRaiz = addNode(grafoSolucion, parent, root);
        grafoSolucion.getModel().endUpdate();
        
        mxGraphComponent componenteGrafo = new mxGraphComponent(grafoSolucion);
        mxHierarchicalLayout layoutGrafo = new mxHierarchicalLayout(grafoSolucion);
        
        layoutGrafo.setInterRankCellSpacing(100);
        layoutGrafo.execute(parent);
        solucionPanel.add(componenteGrafo);
        
        return solucionPanel;
    }
    
	private mxICell addNode(mxGraph grafo, Object parent, Nodo nodo) {
		mxICell vertice;
		 
		 if(nodo.isEsAtributo()) {
			 vertice = (mxICell) grafo.insertVertex(parent, nodo.getAtributo(), nodo.getAtributo(), 0, 0, 80, 30);
		 }
	     else if(nodo.isEsHoja()) {
	    	vertice = (mxICell) grafo.insertVertex(parent, nodo.getClase(), nodo.getClase(), 0, 0, 80, 30);
	     }	
		 else {
			 vertice = (mxICell) grafo.insertVertex(parent, nodo.getValor(), nodo.getValor(), 0, 0, 80, 30);
		 }
		
        if (!nodo.isEsHoja()) {
            mxICell[] verticesHijosList = new mxICell[nodo.getHijos().size()];
            
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                Nodo hijo = nodo.getHijos().get(i);
                
                mxICell verticeHijo = addNode(grafo, parent, hijo);
                verticesHijosList[i] = verticeHijo;
                grafo.insertEdge(parent, null, "", vertice, verticeHijo);
            }
            grafo.addCells(verticesHijosList);
            grafo.updateCellSize(vertice);
        }
        
        return vertice;
    }
}
