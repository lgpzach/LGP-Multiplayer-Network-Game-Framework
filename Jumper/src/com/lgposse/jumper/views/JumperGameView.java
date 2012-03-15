package com.lgposse.jumper.views;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GCanvas;

import com.lgposse.game.views.View;
import com.lgposse.jumper.models.Graveyard;
import com.lgposse.jumper.models.JumperGame;

public class JumperGameView extends GCanvas implements View, ChangeListener {

	private static final long serialVersionUID = 9026620026334510507L;
	protected BoardView boardView;
	protected Graveyard[] graveyards = new Graveyard[2];
	protected GraveyardView[] graveyardViews = new GraveyardView[2];
	protected JumperGame game;
	
	public JumperGameView(JumperGame game) {
		this.game = game;
		//this.initComponents();
	}

	public void initComponents() {
		this.boardView = new BoardView(game.board);
		this.graveyards[0] = new Graveyard();
		this.graveyards[1] = new Graveyard();
		this.graveyardViews[0] = new GraveyardView(graveyards[0]);
		this.graveyardViews[1] = new GraveyardView(graveyards[1]);
		
		this.add(graveyardViews[0]);
		this.add(boardView);
		this.add(graveyardViews[1]);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
