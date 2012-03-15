package com.lgposse.cards.views;

import java.util.ArrayList;

import com.lgposse.cards.models.Card;
import com.lgposse.cards.models.Hand;
import com.lgposse.game.views.View;

import acm.graphics.GCompound;

public class HandView extends GCompound implements View {
	public Hand h;
	public boolean horizontal;
	public ArrayList<CardView> cardViews;
	protected boolean flipped;
	private int x = 0;
	
	public HandView(Hand h, boolean horizontal, boolean flipped) {
		this.h = h;
		this.horizontal = horizontal;
		this.flipped = flipped;
		this.cardViews = new ArrayList<CardView>();
		this.update();
	}
	
	public HandView(Hand h, boolean flipped) {
		// dummy constructor for subclass use
		this.h = h;
		this.cardViews = new ArrayList<CardView>();
	}
	
	public void update() {
		this.removeAll();
		this.cardViews.clear();
		for(Card c : h.cards) {
			CardView cv = new CardView(c, this.flipped);
			if(horizontal) {
				this.add(cv, x, 0);
			} else {
				this.add(cv, 0, x);
			}
			this.cardViews.add(cv);
			x += 15;
		}
	}
}
