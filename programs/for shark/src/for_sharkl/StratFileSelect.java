package for_sharkl;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import java.awt.*; 

//the WindowBuilder / SWTDesigner tooling
// uses the @PostConstruct method to figure out
// that the class is am Eclipse 4 part

// one method must be annotated with @PostConstruct and
// must receive a least a SWT Composiste

public class StratFileSelect {

	@PostConstruct
	public void createControls(Composite parent) {
	System.out.println(this.getClass().getSimpleName()
		+ " @PostConstruct method called.");
	}
}
