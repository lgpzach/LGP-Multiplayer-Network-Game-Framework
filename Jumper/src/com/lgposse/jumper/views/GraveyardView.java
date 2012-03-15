package com.lgposse.jumper.views;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GCompound;
import acm.graphics.GRect;

import com.lgposse.game.views.View;
import com.lgposse.jumper.models.Graveyard;
import com.lgposse.jumper.models.Peg;

public class GraveyardView extends GCompound implements View, ChangeListener {
	
	protected Graveyard graveyard;
	
	private GRect outerRect;
	
	public GraveyardView(Graveyard graveyard) {
		this.graveyard = graveyard;
		update();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		update();
	}

	@Override
	public void update() {
		this.removeAll();
		int x = 0;
		int y = 0;
		int count = 16;
		outerRect = new GRect(250,40);
		this.add(outerRect, -5, -5);
		PegView empty = new PegView(new Peg(-1, Peg.types.EMPTY));
		for(Peg p : this.graveyard.getPegs()) {
			this.add(new PegView(p));
			if(count == 9) y += 15; x = 0;
			count--;
			x += 15;
		}
		if(count > 8) {
			for(int i = count; i > 8; i--) {
				this.add(empty, x, y);
				x += 15;
			}
			y += 15; x = 0;
			for(int i = 8; i > 0; i--) {
				this.add(empty, x, y);
				x += 15;
			}
		} else {
			for(int i = count; i > 0; i--) {
				this.add(empty, x, y);
				x += 15;
			}
		}
	}

}
