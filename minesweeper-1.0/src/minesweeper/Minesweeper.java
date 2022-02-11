package minesweeper;

import abstractProgram.abstractProgram.AbstractProgramWindow;
import abstractProgram.abstractProgram.RunProgram;
import minesweeper.BoardTile.TileCover;				// enum
import minesweeper.BoardTile.TileType;				// enum

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.CLabel;

public class Minesweeper extends AbstractProgramWindow {
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	@SuppressWarnings("unused")
	private RunProgram program; 
	private static Minesweeper window; 				// INSTANCE OF THIS WINDOW (CLASS) 
	private ArrayList<Image> img;
	private Canvas canvas; 
	private CLabel lblMinesLeft; 
	
	private final int NUM_TILE_IMAGES = 13;
	private final int CELL_SIZE = 15;
	private final int NUM_MINES = 40;
	private final int NUM_ROWS = 16;
	private final int NUM_COLS = 16;
	private final int NUM_CELLS = NUM_ROWS * NUM_COLS; 
	
	// dont like?  
	private BoardTile[][] field;
	private int minesLeft;
	private final int COVER_FOR_CELL = 10;
    private final int MARK_FOR_CELL = 10;
    //private final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
   // private final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;
    private boolean inGame; 
    private final int DRAW_MINE = 9;
    private final int DRAW_COVER = 10;
    private final int DRAW_MARK = 11;
    private final int DRAW_WRONG_MARK = 12;
    private CLabel lblNewLabel_1;
	
	public Minesweeper(RunProgram program)
	{
		this.program = program; 
		initBoard(); 
	}
	
	/****************************
	 * @wbp.parser.entryPoint	*
	 ****************************/
	public void open()
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents();

		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shell.isDisposed()) 
		{
			/*******************************************/
			/* SLEEP DISPLAY IF THERE IS NOTHING TO DO */
			/*******************************************/
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	/**
	 * Creates the GUI and opens the display. 
	 * @param shell {@link Shell} (window) to display until closed/disposed. 
	 */
	public void open(Shell shell) {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents();

		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shell.isDisposed()) {
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
		shell = new Shell();
		shell.setSize(700, 700);
		shell.setText("program"); 
		
		Composite board_composite = new Composite(shell, SWT.NONE);
		board_composite.setBounds(10, 37, 664, 614);
		
		canvas = new Canvas(board_composite, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				drawBoard(canvas, e); 
			}
		});
		
		canvas.setBounds(0, 0, 664, 605);
		
		lblMinesLeft = new CLabel(shell, SWT.NONE);
		lblMinesLeft.setBounds(10, 10, 300, 21);
		lblMinesLeft.setText("Mines Left: " + minesLeft);
		
		lblNewLabel_1 = new CLabel(shell, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		lblNewLabel_1.setBounds(374, 10, 300, 21);
		window = this; 
		
		canvas.addMouseListener(new MinesMouseAdapter());
	} 
	
	private void initBoard() {
		img = new ArrayList<Image>(); 
		
		File path = new File("src//resources//tiles"); 
		for (int i = 0; i < NUM_TILE_IMAGES; i++) {
			img.add(new Image(Display.getDefault(), path.getPath() + "\\" + i + ".png")); 
		}
		
		newBoard(); 
	}
	
	private void newBoard() {
		int cell;

        Random random = new Random();
        inGame = true;
        minesLeft = NUM_MINES;

        field = new BoardTile[NUM_ROWS][NUM_COLS];

        for (int row = 0; row < NUM_ROWS; row++) {
        	for (int col = 0; col < NUM_COLS; col++) {
        		field[row][col] = new BoardTile(TileType.EMPTY_TILE, TileCover.COVERED_TILE);	
        	}
        }

        if (shell != null) {
        	lblMinesLeft.setText("Mines left: " + minesLeft);
        } 

        int i = 0;

        while (i < NUM_MINES) {

            int position = (int) (NUM_CELLS * random.nextDouble());
            int row = position / NUM_ROWS; 
            int col = position % NUM_COLS; 

            if ((position < NUM_CELLS) 
            		&& (!field[row][col].isCoveredMine())) {
            	
                int current_col = position % NUM_COLS;
                field[row][col].setTile(TileType.MINE_TILE, TileCover.COVERED_TILE); 
                i++;

                if (current_col > 0) {
                    cell = position - 1 - NUM_COLS;
                    if (cell >= 0) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }

                    cell = position + NUM_COLS - 1;
                    if (cell < NUM_CELLS) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }
                }

                cell = position - NUM_COLS;
                if (cell >= 0) {
                    if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                        field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                    }
                }

                cell = position + NUM_COLS;
                if (cell < NUM_CELLS) {
                    if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                        field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                    }
                }

                if (current_col < (NUM_COLS - 1)) {
                    cell = position - NUM_COLS + 1;
                    if (cell >= 0) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }
                    cell = position + NUM_COLS + 1;
                    if (cell < NUM_CELLS) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }
                    cell = position + 1;
                    if (cell < NUM_CELLS) {
                        if (!field[cell / NUM_ROWS][cell % NUM_COLS].isCoveredMine()) {
                            field[cell / NUM_ROWS][cell % NUM_COLS].increase();
                        }
                    }
                }
            }
        }
	}
	
	private void drawBoard(Canvas canvas, PaintEvent event)
	{
		int uncover = 0;
	                
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				BoardTile cell = field[row][col];

				if (inGame && cell.isUncoveredMine()) {
					inGame = false;
				}

				if (!inGame) {
	                if (cell.isCoveredMine()) {
	                    cell.uncoverTile();
	                } 
	                else if (cell.isMarkedMine()) {
	                    cell.setTileCover(TileCover.MARKED_TILE); 
	                } 
	                else if (cell.isUncoveredMine()) {
	                	cell.setTileCover(TileCover.WRONG_MARKED_TILE); 
	                } 
	                //else if (cell.isCovered()) {
	                //    cell.cover = DRAW_COVER;
	                //}

				} 
				else {
	                if (cell.isCoveredMine()) {
	                    //cell.markTile();
	                } 
	                else if (cell.isCovered()) {
//	                    cell.coverTile();
	                    uncover++;
	                }
				} 
	                    
				event.gc.drawImage(img.get(cell.imageValue()), col * CELL_SIZE, row * CELL_SIZE); 
			}
		}
		
		if (uncover == 0 && inGame) { 
			inGame = false;
			lblMinesLeft.setText("Game won.");
		} 
		else if (!inGame) {	
			lblMinesLeft.setText("Game lost.");
	    } 
	}
	
	private void find_empty_cells(int j) {

        int current_col = j % NUM_COLS;
        int cell;

        if (current_col > 0) {
            cell = j - NUM_COLS - 1;
            if (cell >= 0) {
                if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].isEmptyTile()) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + NUM_COLS - 1;
            if (cell < NUM_CELLS) {
                if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - NUM_COLS;
        if (cell >= 0) {
            if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + NUM_COLS;
        if (cell < NUM_CELLS) {
        	if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (NUM_COLS - 1)) {
            cell = j - NUM_COLS + 1;
            if (cell >= 0) {
            	if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + NUM_COLS + 1;
            if (cell < NUM_CELLS) {
            	if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell < NUM_CELLS) {
            	if (field[cell / NUM_ROWS][cell % NUM_COLS].isCovered()) {
                    field[cell / NUM_ROWS][cell % NUM_COLS].uncoverTile();
                    if (field[cell / NUM_ROWS][cell % NUM_COLS].tileType() == TileType.EMPTY_TILE) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

    }
	
	private class MinesMouseAdapter extends MouseAdapter {

		public void mouseUp(MouseEvent e) {

			int x = e.x;
			int y = e.y;

			int cCol = x / CELL_SIZE;
            int cRow = y / CELL_SIZE;

            boolean doRepaint = false;

            if (!inGame) {

                newBoard();
                canvas.redraw();
            }

            if ((x < NUM_COLS * CELL_SIZE) && (y < NUM_COLS * CELL_SIZE)) {

                if (e.button == 3) {

                    if (!field[cRow][cCol].isUncovered()) {

                        doRepaint = true;

                        if (!field[cRow][cCol].isMarked()) {

                            if (minesLeft > 0) {
                                field[cRow][cCol].markTile();
                                minesLeft--;
                                lblMinesLeft.setText("Mines left: " + minesLeft); 
                            } 
                            else {
                            	lblMinesLeft.setText("No marks left.");
                            }
                        } 
                        else {

                            field[cRow][cCol].unmarkTile();
                            minesLeft++;
                            lblMinesLeft.setText("Mines left: " + minesLeft);
                        }
                    }

                } 
                else {

                    if (field[cRow][cCol].isMarked()) {
                        return;
                    }

                    else if (field[cRow][cCol].isCovered()) {

                        field[cRow][cCol].uncoverTile();
                        doRepaint = true;

                        if (field[cRow][cCol].isUncoveredMine()) {
                            inGame = false;
                        }

                        if (field[cRow][cCol].isEmptyTile()) {
                            find_empty_cells((cRow * NUM_COLS) + cCol);
                        }
                    }
                }

                if (doRepaint) {
                    canvas.redraw();
                }
            }
		}
        
    }
}
