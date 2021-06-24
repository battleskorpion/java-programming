package blackjack;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Panel;
import java.io.File;
import java.net.MalformedURLException;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JRootPane;
import org.eclipse.swt.layout.FillLayout;

public class BlackjackTable 
{
	// variables
	public static final String title = Blackjack.title;
	
	private Display display; 
	protected Shell shlBlackjack;
	private Composite tableComposite; 
	private Composite dealerComposite;
	private Composite playerComposite; 
	private ArrayList<Composite> composites; 

	private java.awt.Frame dealerCompositeFrame;
	private Panel dealerCompositePanel;
	private JRootPane dealerCompositeRootPane;
	JSVGCanvas dealerSVGCanvas;
	private java.awt.Frame playerCompositeFrame;
	private Panel playerCompositePanel;
	JSVGCanvas playerSVGCanvas; 
	private JRootPane playerCompositeRootPane;
	private ArrayList<JSVGCanvas> SVGCanvases; 
	
	public BlackjackTable()
	{

	}
	
	public void blackjack()
	{
		Deck deck = new Deck();
		
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		ArrayList<Hand> hands = new ArrayList<Hand>(); 			// should be in the dealing order (dealer last)
		hands.add(playerHand);
		hands.add(dealerHand); 
		
		System.out.println("test 1"); 
		// starting deal
		//initialDeal(deck, hands);			//TODO:
		System.out.println("test 2"); 
		//displayHands(hands);
		System.out.println("test 3"); 
		// let player hit or stay
		//dealPlayer(deck, hands, 0);
		System.out.println("test 4"); 
		//displayWinner(calculateWinner(hands));
	}

	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint	
	 */
	public void open() 
	{
		display = Display.getDefault();
		createContents();
		shlBlackjack.open();
		shlBlackjack.layout();
		blackjack(); 
		
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

		shlBlackjack = new Shell(display);
		shlBlackjack.setSize(1280, 720);
		shlBlackjack.setText("Blackjack");
		
		//TODO: Test
		shlBlackjack.setLayout(new GridLayout());
		shlBlackjack.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
				//tableComposite = new Composite(shlBlackjack, SWT.EMBEDDED);
		
		//TODO: this works
		tableComposite = new Composite(shlBlackjack, SWT.EMBEDDED);
		tableComposite.setLayout(new GridLayout(1, false));
		GridData gd_tableComposite = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 2);
		gd_tableComposite.widthHint = 1254;
		gd_tableComposite.heightHint = 668;
		tableComposite.setLayoutData(gd_tableComposite);
		tableComposite.setBounds(10, 55, 1244, 616);
		
				//tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Composite dealerComposite = new Composite(tableComposite, SWT.EMBEDDED);
		GridData gd_dealerComposite = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_dealerComposite.widthHint = 1244;
		gd_dealerComposite.heightHint = gd_tableComposite.heightHint / 2;
		dealerComposite.setLayoutData(gd_dealerComposite);
		java.awt.Frame tableDealerFrame = SWT_AWT.new_Frame(dealerComposite);
		
		tableDealerFrame.add(createSVGPanel());		// TODO: breaks windowbuilder :(
		
		Composite playerComposite = new Composite(tableComposite, SWT.EMBEDDED);
		GridData gd_playerComposite = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_playerComposite.widthHint = 1244;
		gd_playerComposite.heightHint = gd_tableComposite.heightHint / 2;
		playerComposite.setLayoutData(gd_playerComposite);
		java.awt.Frame tablePlayerFrame = SWT_AWT.new_Frame(playerComposite);
				//tablePlayerFrame.setBounds(playerComposite.getBounds().x, playerComposite.getBounds().y, playerComposite.getBounds().width, playerComposite.getBounds().height); 
		
		JPanel panel = createSVGPanel(); 

				//panel.setSize(1244, gd_tableComposite.heightHint / 2);
				//panel.setBounds(playerComposite.getBounds().x, playerComposite.getBounds().y, playerComposite.getBounds().width, playerComposite.getBounds().height); 
				//panel.getComponent(0).setBounds(playerComposite.getBounds().x, playerComposite.getBounds().y, playerComposite.getBounds().width, playerComposite.getBounds().height);
		tablePlayerFrame.add(panel);		// TODO: breaks windowbuilder :(
				//tablePlayerFrame.setBounds(playerComposite.getBounds().x, playerComposite.getBounds().y, playerComposite.getBounds().width, playerComposite.getBounds().height);

		tablePlayerFrame.pack();
				//TODO: progreks?
		
		
				//SashForm sashForm = new SashForm(tableComposite, SWT.VERTICAL | SWT.EMBEDDED); //TODO: Test
				//sashForm.setBounds(10, 10, 1224, 596);
				//dealerComposite = new Composite(tableComposite, SWT.EMBEDDED); 
				//dealerComposite.setLayout(new GridLayout());
				//dealerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				//java.awt.Frame tableFrame = SWT_AWT.new_Frame(dealerComposite);
				//tableFrame.add(createSVGPanel());
				//tableFrame.pack();
				
				/* dealer */ 
				//dealerComposite = new Composite(sashForm, SWT.EMBEDDED); 
				
				//dealerCompositeFrame = SWT_AWT.new_Frame(dealerComposite);
				
				//dealerCompositePanel = new Panel();
				//dealerCompositeFrame.add(dealerCompositePanel);
				//dealerCompositePanel.setLayout(new BorderLayout(0, 0));
				//
				//dealerCompositePanel.setVisible(true);
				//dealerCompositeFrame.setVisible(true);
				//
				//dealerCompositeRootPane = new JRootPane();
				//dealerCompositePanel.add(dealerCompositeRootPane);
				//
				//dealerSVGCanvas = new JSVGCanvas();
				//dealerCompositeFrame.add(createSVGPanel());
				//dealerCompositeFrame.pack(); 
				//dealerCompositeFrame.setVisible(true);
				/*
				 * 
				// */
				////dealerCompositeRootPane.add(dealerSVGCanvas);
				//dealerCompositeRootPane.getContentPane().add(createSVGPanel(dealerSVGCanvas)); 
				//dealerCompositeRootPane.setVisible(true);
				//
				/* player */
				//playerComposite = new Composite(sashForm, SWT.EMBEDDED); 
				
				//playerCompositeFrame = SWT_AWT.new_Frame(playerComposite);
				
				//playerCompositePanel = new Panel();
				//playerCompositeFrame.add(playerCompositePanel);
				//playerCompositePanel.setLayout(new BorderLayout(0, 0));
				//
				//playerCompositeRootPane = new JRootPane();
				//playerCompositePanel.add(playerCompositeRootPane);
				
				//playerSVGCanvas = new JSVGCanvas();
				////try {
				//	playerSVGCanvas.setURI(new File("F:/java-programming/batik-1.14/samples/3D.svg").toURI().toURL().toString());
				//} catch (MalformedURLException e) {
				//	// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				//playerCompositePanel.add(playerSVGCanvas);
		
		/* composites array */ 
		composites = new ArrayList<Composite>();
		composites.add(playerComposite); 
		composites.add(dealerComposite);
		
				/* canvases array */
				//SVGCanvases = new ArrayList<JSVGCanvas>(); 
				//SVGCanvases.add(playerSVGCanvas); 
				//SVGCanvases.add(dealerSVGCanvas);		// dealer always last 

	}
	
	private JPanel createSVGPanel()
	{
		JSVGCanvas svgCanvas = new JSVGCanvas();
		JPanel panel = new JPanel();
		panel.setLayout(null);		//TODO: yessss
		panel.setSize(100, 400);
		svgCanvas.setBounds(panel.getBounds());
		//////svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
		panel.add(svgCanvas); 
		
		//TODO: Temp
		File f = new File("card_deck.svg"); 	//"F:/java-programming/batik-1.14/samples/3D.svg"
		try {
			svgCanvas.setURI(f.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return panel; 
	}
	
	/**
	 *  precondition: contents created
	 *  index: index of composite/canvas
	 */
	private void addCard(Card card, int index)	
	{
		//Label cardLabel = new Label(composites.get(composite), SWT.NONE); 
		/**
		 * @wbp.nonvisual location=60,419
		 */
		
		//Image cardImage = new Image(Display.getDefault(), card.getImageAddress());
		File cardDeckFile = new File("card_deck.svg"); 
		
		System.out.println(cardDeckFile.toURI().toString()); 
		try 
		{
			//SVGCanvases.get(index).loadSVGDocument(cardDeckFile.toURI().toString());
			//dealerSVGCanvas.setURI(cardDeckFile.toURI().toURL().toString());	//TODO: breaking here 
			
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		//System.out.println(SVGCanvases.get(index).getURI()); 
		//cardLabel.setBounds(10, 10, 55, 15);
		//cardLabel.setImage(cardImage);
	}

	private void initialDeal(Deck deck, ArrayList<Hand> hands) 
	{
		for (int i = 0; i < Blackjack.INITIAL_CARDS; i++) 
		{
			for (int handIndex = 0; handIndex < hands.size(); handIndex++) 
			{
				Card card = deck.deal(); 
				hands.get(handIndex).add(card);
				// testing
				//System.out.println(j + " Hand length: " + hands[0].length());
				//System.out.println(deck.deal());
				if (i == 0 && handIndex == 0) {
					addCard(card, handIndex); 
				}
			}
		}
	}
	
	private void displayHands(ArrayList<Hand> hands) 
	{
		JOptionPane.showMessageDialog(null, "Hand: \n" + hands.get(0).toString() + "\n"
				+ "Dealer's Hand: \n" + hands.get(hands.size() - 1).toString(),
				title, JOptionPane.PLAIN_MESSAGE);
	}
	
	private void dealPlayer(Deck deck, ArrayList<Hand> hands, int handIndex) 
	{
		String[] options = {"Hit", "Stay"};
		int choice;

		do 
		{
			choice = JOptionPane.showOptionDialog(null, "Hit or Stay?", title, 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null, options, options[1]);
			if (options[choice].equals("Hit")) {
				hands.get(handIndex).add(deck.deal());
				displayHands(hands);
			}
		}
		while (options[choice].equals("Hit"));

	}
	
	private String calculateWinner(ArrayList<Hand> hands) 
	{
		int player = 0;
		int dealer = hands.size() - 1; 		// dealer is last because they deal themselves last 
		
		if (hands.get(dealer).handValue() > Blackjack.VALUE_LIMIT) {
			return "dealer";
			// player busted
		}
		else if (hands.get(dealer).handValue() == Blackjack.VALUE_LIMIT) {
			return "dealer";
			// dealer got 21
		}
		else if (hands.get(dealer).handValue() > Blackjack.VALUE_LIMIT) {
			return "player";
			// dealer busted, player did not
		}
		else if (hands.get(dealer).handValue() > hands.get(player).handValue()) {
			return "dealer";
			// dealer exceeded player
		}
		else if (hands.get(dealer).handValue() == hands.get(player).handValue()) {
			return "push";
			// push
		}
		
		else if (hands.get(dealer).handValue() < hands.get(player).handValue()) {
			return "player";
			// player exceeded dealer
		}

		return "idk";
		// idk

  	}

	private void displayWinner(String winner) 
  	{
		JOptionPane.showMessageDialog(null, winner, title, JOptionPane.PLAIN_MESSAGE); 
	}
}
