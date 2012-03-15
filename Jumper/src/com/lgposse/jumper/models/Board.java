package com.lgposse.jumper.models;

import java.io.Serializable;

import com.lgposse.game.models.Model;

public class Board extends Model implements Serializable {
	/**
	 * TODO: notes on how far I've gotten:
	 *  ..OOOOOO
		.OOOOOOO
		O..O....
		O.......
		........
		+.......
		++++++++
	 * 
	 * I've gotten to jumping so far, and it works.
	 * 
	 */
	private static final long serialVersionUID = 6276185649839912163L;
	public Peg[][] pegs;

	public Board() {
		int id = 0;
		pegs = new Peg[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch(i) {
				case 0:
				case 1:
					pegs[i][j] = new Peg(id, Peg.types.PLAYER_TWO);
					break;
				case 6:
				case 7:
					pegs[i][j] = new Peg(id, Peg.types.PLAYER_ONE);
					break;
				default:
					pegs[i][j] = new Peg(id, Peg.types.EMPTY);
				}
				id++;
			}
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				s += pegs[i][j];
			}
			s += '\n';
		}
		return s;
	}
	
	/**
	 * Swaps the locations of two pegs.
	 * @param jumpingPeg
	 * @param landingLocation
	 * @return
	 */
	public boolean jump(Peg jPeg, Peg lPeg) {
		boolean success = false;
		if(!jPeg.type.equals(Peg.types.EMPTY) && lPeg.type.equals(Peg.types.EMPTY)) {
			int jx = getX(indexOf(jPeg));
			int jy = getY(indexOf(jPeg));
			int lx = getX(indexOf(lPeg));
			int ly = getY(indexOf(lPeg));
			
			if( // diagonal check
				(jx + 2 == lx  || jx - 2 == lx) &&
			    (jy + 2 == ly  || jy - 2 == ly)
			) {
				// checks have passed, swap the coordinates
				swap(jPeg, lPeg, jx, jy, lx, ly);
				success = true;
			}
			
			else if(
				((jx + 2 == lx || jx - 2 == lx) && jy == ly) ||
				(jx == lx && (jy + 2 == ly || jy - 2 == ly))
			) {
				swap(jPeg, lPeg, jx, jy, lx, ly);
				success = true;
			}
			
			Peg mPeg = inBetween(jx,jy,lx,ly);
			if(mPeg != null) {
				boolean remove = false;
				
				switch(mPeg.type) {
				case PLAYER_ONE:
					if(jPeg.type.equals(Peg.types.PLAYER_TWO)) {
						remove = true;
					}
					break;
				case PLAYER_TWO:
					if(jPeg.type.equals(Peg.types.PLAYER_ONE)) {
						remove = true;
					}
					break;
				}
				
				if(remove) {
					mPeg.type = Peg.types.EMPTY;
				}
			}
		} 
		return success;
	}
	
	public Peg pegById(int id) {
		for(Peg[] row : pegs) {
			for(Peg p : row) {
				if(p.id == id) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Peg pegByCoordinates(int x, int y) {
		try {
			return pegs[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public int getX(Peg p) {
		return getX(indexOf(p));
	}
	
	public int getY(Peg p) {
		return getY(indexOf(p));
	}
	
	private void swap(Peg jPeg, Peg lPeg, int jx, int jy, int lx, int ly) {
		pegs[jx][jy] = lPeg;
		pegs[lx][ly] = jPeg;
	}
	
	private String indexOf(Peg peg) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pegs[i][j].equals(peg)) {
					return Integer.toString(i) + " " + Integer.toString(j);
				}
			}
		}
		return "";
	}
	
	private int getX(String indexOf) {
		String[] x = indexOf.split(" ");
		return Integer.parseInt(x[0]);
	}
	
	private int getY(String indexOf) {
		String[] y = indexOf.split(" ");
		return Integer.parseInt(y[1]);
	}
	
	private Peg inBetween(int jx, int jy, int lx, int ly) {
		if(jx + 1 == lx - 1) {
			if(jy + 1 == ly - 1) {
				return pegs[jx+1][jy+1];
			}
			if(jy - 1 == ly + 1) {
				return pegs[jx+1][jy-1];
			}
			if(jy == ly) {
				return pegs[jx+1][jy];
			}
		}
		if(jx - 1 == lx + 1) {
			if(jy + 1 == ly - 1) {
				return pegs[jx-1][jy+1];
			}
			if(jy - 1 == ly + 1) {
				return pegs[jx-1][jy-1];
			}
			if(jy == ly) {
				return pegs[jx-1][jy];
			}
		}
		if(jx == lx) {
			if(jy + 1 == ly - 1) {
				return pegs[jx][jy+1];
			}
			if(jy - 1 == ly + 1) {
				return pegs[jx][jy-1];
			}
			if(jy == ly) {
				return pegs[jx][jy];
			}
		}
		return null;
	}
}
