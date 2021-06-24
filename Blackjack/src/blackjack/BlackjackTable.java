package blackjack;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;	

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.BorderLayout;
import javax.swing.JRootPane;

public class BlackjackTable 
{
	protected Shell shlBlackjack;
	protected Composite tableComposite; 
	protected Composite dealerComposite;
	protected Composite playerComposite; 
	protected ArrayList<Composite> composites; 
	private Label lblT;

	private Frame dealerCompositeFrame;
	private Panel dealerCompositePanel;
	private JRootPane dealerCompositeRootPane;
	private Frame playerCompositeFrame;
	private Panel playerCompositePanel;
	private JRootPane playerCompositeRootPane;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BlackjackTable window = new BlackjackTable();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBlackjack.open();
		shlBlackjack.layout();
		while (!shlBlackjack.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() 
	{
		shlBlackjack = new Shell();
		shlBlackjack.setSize(1280, 720);
		shlBlackjack.setText("Blackjack");
		
		tableComposite = new Composite(shlBlackjack, SWT.NONE);
		tableComposite.setBounds(10, 55, 1244, 616);

		SashForm sashForm = new SashForm(tableComposite, SWT.VERTICAL);
		sashForm.setBounds(10, 10, 1224, 596);
		
		/* dealer */ 
		dealerComposite = new Composite(sashForm, SWT.EMBEDDED); 
		
		dealerCompositeFrame = SWT_AWT.new_Frame(dealerComposite);
		
		dealerCompositePanel = new Panel();
		dealerCompositeFrame.add(dealerCompositePanel);
		dealerCompositePanel.setLayout(new BorderLayout(0, 0));
		
		dealerCompositeRootPane = new JRootPane();
		dealerCompositePanel.add(dealerCompositeRootPane);
		
		/* player */
		playerComposite = new Composite(sashForm, SWT.EMBEDDED); 
		
		playerCompositeFrame = SWT_AWT.new_Frame(playerComposite);
		
		playerCompositePanel = new Panel();
		playerCompositeFrame.add(playerCompositePanel);
		playerCompositePanel.setLayout(new BorderLayout(0, 0));
		
		playerCompositeRootPane = new JRootPane();
		playerCompositePanel.add(playerCompositeRootPane);
		
		/* composites array */ 
		composites = new ArrayList<Composite>();
		composites.add(playerComposite); 
		composites.add(dealerComposite);		// dealer always last 
	
	}
	
	/**
	 *  precondition: contents created
	 */
	protected void addCard(Card card, int composite)
	{
		//Label cardLabel = new Label(composites.get(composite), SWT.NONE); 
		/**
		 * @wbp.nonvisual location=60,419
		 */
		JSVGCanvas svgCanvas = new JSVGCanvas();
		JPanel panel = new JPanel();
		Image cardImage = new Image(Display.getDefault(), card.getImageAddress());	
		
		panel.add(svgCanvas);
		
		//cardLabel.setBounds(10, 10, 55, 15);
		//cardLabel.setImage(cardImage);
	}

}
