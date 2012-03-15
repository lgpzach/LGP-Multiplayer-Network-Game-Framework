package com.lgposse.cards.views;

import java.util.ArrayList;

import com.lgposse.cards.models.Card;
import com.lgposse.cards.models.Deck;
import com.lgposse.game.views.View;

import acm.graphics.GCompound;

public class DeckView extends GCompound implements View {

	public Deck d;
	public ArrayList<CardView> cardViews;
	private boolean flipped;
	
	public DeckView(Deck d, boolean flipped) {
		this.flipped = flipped;
		this.d = d;
		this.cardViews = new ArrayList<CardView>();
		this.update();
	}
	
	public void update() {
		cardViews.clear();
		this.removeAll();
		int x = 0;
		int y = 0;
		for(Card c : d.cards) {
			if(x > -10) {
				CardView cv = new CardView(c, flipped);
				this.add(cv, x, y);
				cardViews.add(cv);
				x -= 1;
				y -= 1;
			}
		}
	}
}
