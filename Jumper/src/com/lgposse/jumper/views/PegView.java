package com.lgposse.jumper.views;

import java.awt.Color;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GOval;

import com.lgposse.common.Colors;
import com.lgposse.game.views.View;
import com.lgposse.jumper.models.Peg;

public class PegView extends GOval implements View, ChangeListener {
	public Peg peg;
	
	public PegView(Peg peg) {
		super(10,10);
		this.peg = peg;
		peg.addChangeListener(this);
	}

	@Override
	public void update() {
		switch(peg.type) {
		case EMPTY:
			this.setColor(Colors.LIGHT_GRAY);
			this.setFillColor(Colors.LIGHT_GRAY);
			break;
		case PLAYER_ONE:
			this.setColor(Color.ORANGE);
			this.setFillColor(Color.ORANGE);
			break;
		case PLAYER_TWO:
			this.setColor(Color.BLUE);
			this.setFillColor(Color.BLUE);
			break;
		}
		
		this.setFilled(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		update();
	}
}
