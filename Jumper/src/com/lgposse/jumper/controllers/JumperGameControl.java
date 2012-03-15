package com.lgposse.jumper.controllers;

import com.lgposse.jumper.app.JumperGameApp;
import com.lgposse.jumper.views.JumperGameView;

public class JumperGameControl extends JumperGameView {

	private static final long serialVersionUID = -1615423148645365538L;
	protected JumperGameApp app;
	
	public JumperGameControl(JumperGameApp jumperGameApp) {
		super(jumperGameApp.game);
		this.app = jumperGameApp;
		this.setName("JumperGameControl");
		this.initComponents();
	}
	
	@Override
	public void initComponents() {
		this.boardView = new BoardControl(this.app);
		//this.graveyards[0] = new Graveyard();
		//this.graveyards[1] = new Graveyard();
		//this.graveyardViews[0] = new GraveyardView(graveyards[0]);
		//this.graveyardViews[1] = new GraveyardView(graveyards[1]);
		
		//this.add(graveyardViews[0], 0, 0);
		this.add(boardView, 60, 60);
		//this.add(graveyardViews[1], 100,500);
	}

}
