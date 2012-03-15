package com.lgposse.jumper.views;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lgposse.game.views.View;
import com.lgposse.jumper.models.Board;
import com.lgposse.jumper.models.Peg;

import acm.graphics.GCompound;

public class BoardView extends GCompound implements View, ChangeListener {
	protected Board board;
	protected PegView[][] pegViews;
	
	public BoardView(Board board) {
		this.board = board;
		pegViews = new PegView[8][8];
		int y = 0;
		for(Peg[] row : board.pegs) {
			int x = 0;
			for(Peg p : row) {
				PegView pv = new PegView(p);
				this.add(pv, x, y);
				this.pegViews[board.getX(pv.peg)][board.getY(pv.peg)] = pv;
				x += 15;
			}
			y += 15;
		}
		board.addChangeListener(this);
		this.scale(2);
		update();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.update();
	}

	@Override
	public void update() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				pegViews[row][col].peg = board.pegByCoordinates(row, col);
				pegViews[row][col].update();
			}
		}
	}
}
