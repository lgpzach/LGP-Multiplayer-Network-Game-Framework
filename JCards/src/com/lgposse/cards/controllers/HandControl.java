package com.lgposse.cards.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.lgposse.cards.models.Hand;
import com.lgposse.cards.views.CardView;
import com.lgposse.cards.views.HandView;

public class HandControl extends HandView {

	private boolean owned;

	public HandControl(Hand h, boolean horizontal, boolean owned) {
		super(h, horizontal, !owned);
		this.owned = owned;
		this.update();
	}
	
	@Override
	public void update() {
		super.update();
		if(owned) {
			addListeners();
		}
	}
	
	public void addListeners() {
		for(CardView cv : this.cardViews) {
			cv.addMouseListener(new CardListener(cv));
		}
	}

}

class CardListener implements MouseListener {

	private CardView cv;
	boolean staged;
	
	public CardListener(CardView cv) {
		this.cv = cv;
	}
	
	public void stage() {
		if(staged) {
			cv.setLocation(cv.getX(), cv.getY() + 15);
			staged = false;
		} else {
			cv.setLocation(cv.getX(), cv.getY() - 15);
			staged = true;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getClickCount());
		this.stage();
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