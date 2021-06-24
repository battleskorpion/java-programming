
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BatikTest 
{

    public static void main(String[] args) 
    {
        // Uncomment the below lines and set proper values if you are behind a proxy server
        ///System.setProperty("http.proxyHost", "");
        ///System.setProperty("http.proxyPort", ""); 

        final Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setSize(200, 120);
        shell.setText("SWT Batik Example");
        shell.setLayout(new GridLayout());
        shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


        Composite composite = new Composite(shell, SWT.EMBEDDED);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        java.awt.Frame locationFrame = SWT_AWT.new_Frame(composite);
        locationFrame.add(createComponents(new File("F:/java-programming/batik-1.14/samples/3D.svg")));

        locationFrame.pack();
        //shell.pack();


        shell.open();
        while(!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }

    private static JComponent createComponents(File f) 
    {
        // Create a panel and add the button, status label and the SVG canvas.
        final JPanel panel = new JPanel(new BorderLayout());
        JSVGCanvas svgCanvas = new JSVGCanvas();

        panel.add("Center", svgCanvas);


        try {
            svgCanvas.setURI(f.toURI().toURL().toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }   

        return panel;
    }
}