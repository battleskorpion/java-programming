package minesweeper;

import minesweeper.BoardTile.TileCover;
import minesweeper.BoardTile.TileType;

public class BoardTile {
	private TileType type; 
	private TileCover cover; 
			
	public BoardTile(TileType type, TileCover cover) {
		this.type = type; 
		this.cover = cover; 
	}
	
	public int tileTypeIntValue() {
		return type.ordinal(); 
	}
	
	public TileType tileType() {
		return type; 
	}
	
	public TileCover tileCover() {
		return cover; 
	}
	
	public void setTileValue(TileType val) {
		type = val; 
	}
	
	public void setTileCover(TileCover val) {
		cover = val; 
	}
	
	public void setTile(TileType type, TileCover cover) {
		this.type = type; 
		this.cover = cover; 
	}
	
	/** 
	 * increases nearby mines counter of this tile by one
	 * @return new adjacent bombs count (this tile type) 
	 */
	public TileType increase() 
	{
		return type = type.increase(); 
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCoveredMine() {
		return ((this.tileType() == TileType.MINE_TILE) && (this.tileCover() == TileCover.COVERED_TILE)); 
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMarkedMine() {
		return ((this.tileType() == TileType.MINE_TILE) && (this.tileCover() == TileCover.MARKED_TILE)); 
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isUncoveredMine() {
		return ((this.tileType() == TileType.MINE_TILE) && (this.tileCover() == TileCover.NO_COVER)); 
	}
	
	public boolean isCovered() {
		return (cover == TileCover.COVERED_TILE); 
	}
	
	public boolean isUncovered() {
		return cover == TileCover.NO_COVER; 
	}

	public boolean isMarked() {
		return cover == TileCover.MARKED_TILE; 
	}
	
	public boolean isEmptyTile() {
		return type == TileType.EMPTY_TILE; 
	}

	public void coverTile() {
		cover = TileCover.COVERED_TILE; 
	}
	
	public void uncoverTile() {
		cover = TileCover.NO_COVER; 
	}
	
	public void markTile() {
		cover = TileCover.MARKED_TILE; 
	}
	
	public void unmarkTile() {
		cover = TileCover.COVERED_TILE; 
	}
	
	public void markWrongTile() {
		cover = TileCover.WRONG_MARKED_TILE; 
	}
	
	public int imageValue() {
		switch (cover) {
		case NO_COVER: 
			return type.ordinal(); 
		case COVERED_TILE: 
			return 10; 
		case MARKED_TILE: 
			return 11; 
		case WRONG_MARKED_TILE:
			return 12; 
		default: 
			return -1; 		//TODO: ERROR
		}
	}
	
	public enum TileType {
		EMPTY_TILE, 
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, 
		EIGHT {
			@Override
	        public TileType increase() {
	            return this; 
	        };
		}, 
		MINE_TILE {
			@Override
	        public TileType increase() {
	            return this; 
	        };
		};  
		
		public TileType increase() {
	        // No bounds checking required here, because the last instance overrides
	        return values()[ordinal() + 1];
	    }
	}
	
	public enum TileCover {
		NO_COVER, COVERED_TILE, MARKED_TILE, WRONG_MARKED_TILE
	}
}
