package com.lgposse.jumper.controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.lgposse.jumper.app.JumperGameApp;
import com.lgposse.jumper.views.BoardView;
import com.lgposse.jumper.views.PegView;

public class BoardControl extends BoardView implements MouseListener {

	protected JumperGameApp app;
	protected PegView selectedPeg;
	
	public BoardControl(JumperGameApp app) {
		super(app.game.board);
		this.app = app;
		for(PegView[] row : this.pegViews) {
			for(PegView p : row) {
				p.addMouseListener(this);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		PegView clickedPeg = (PegView) e.getSource();
		if(app.game.isTurn(app.player)) {
			if(selectedPeg == null) {
				if(app.game.isPiece(app.player, clickedPeg.peg)) {
					selectedPeg = clickedPeg;
					selectedPeg.setFillColor(Color.GREEN);
				}
			} else {
				PegView destinationPeg = clickedPeg;
				this.app.game.board.jump(selectedPeg.peg, destinationPeg.peg);
				selectedPeg = null;
				this.update();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}