package com.lgposse.cards.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;

import com.lgposse.cards.models.Card;
import com.lgposse.game.views.View;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;

public class CardView extends GCompound implements View {
	private String suit;
	private String rank;
	
	private final int SUIT_SIZE = 22;
	private final int RANK_SIZE = 16;
	
	public Card card;
	
	public CardView(Card c, boolean flipped) {
		this.card = c;
		this.suit = Card.decode_suit_symbol(c.suit);
		this.rank = Card.decode_rank_symbol(c.rank);
		
		Color cardColor = CardView.decodeColor(c.suit);
		
		GRoundRect outerRect = new GRoundRect(80,140);
		GRect innerRect = new GRect(50,110);
		
		CenterDesign centerDesign = new CenterDesign(c);
		
		DoubleLabel upperLabel = new DoubleLabel(this.rank, this.suit, true, RANK_SIZE, SUIT_SIZE, cardColor);
		DoubleLabel lowerLabel = new DoubleLabel(this.suit, this.rank, false, SUIT_SIZE, RANK_SIZE, cardColor);

		if(flipped) {
			outerRect.setFillColor(Color.pink);
			outerRect.setFilled(true);
			innerRect.setFillColor(Color.red);
			innerRect.setColor(Color.red);
			innerRect.setFilled(true);
		} else {
			outerRect.setFillColor(Color.white);
			innerRect.setColor(Color.white);
			outerRect.setFilled(true);
		}
		
		this.add(outerRect, 0,0);
		this.add(innerRect, 15,15);
		
		if(!flipped) {
			this.add(upperLabel, 1,12);
			this.add(centerDesign, 16,40);
			this.add(lowerLabel, 66, 118);
		}
	}

	public static Color decodeColor(int suit) {
		Color c;
		if (suit == 0 || suit == 1) {
			c = Color.black;
		} else {
			c = Color.red;
		}
		return c;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

class DoubleLabel extends GCompound {
	public DoubleLabel(String tupper, String tlower, boolean upperLabel, int uSize, int dSize, Color c) {
		GBigLabel upper = new GBigLabel(tupper, uSize, c);
		GBigLabel lower = new GBigLabel(tlower, dSize, c);
		if(upperLabel) {
			this.add(upper, 2, 3);
			this.add(lower, 0, 20);
		} else {
			this.add(upper, 0, 3);
			this.add(lower, 2, 20);
		}
	}
}

class GBigLabel extends GLabel {
	public GBigLabel(String caption, int size, Color c) {
		super(caption);
		this.setColor(c);
		Font f;
		if(caption.equals("10")) {
			f = new Font("Arial", Font.PLAIN, size - 4);
			AffineTransform trans = new AffineTransform();
			trans.scale(0.8, 1.4);
			f = f.deriveFont(trans);
		} else f = new Font("Arial", Font.PLAIN, size);
		this.setFont(f);
	}
}

class CenterDesign extends GCompound {
	public GLabelStack side;
	public GLabelStack side2;
	public GLabelStack center;
	
	public CenterDesign(Card c) {
		int suit = c.suit;
		int rank = c.rank;
		boolean[] p21 = {true, false};
		boolean[] p22 = {true, true};
		
		boolean[] p31 = {false, true, false};
		boolean[] p32 = {true, false, true};
		boolean[] p33 = {true, true, true};
		
		boolean[] p44 = {true, true, true, true};
		
		switch(rank) {
		case 1:
		case 14:
			center = new ThreeStack(suit, p31);
			break;
		case 2:
			center = new ThreeStack(suit, p32);
			break;
		case 3:
			center = new ThreeStack(suit, p33);
			break;
		case 4:
			side   = new ThreeStack(suit, p32);
			side2  = new ThreeStack(suit, p32);
			break;
		case 5:
			center = new ThreeStack(suit, p31);
			side   = new ThreeStack(suit, p32);
			side2  = new ThreeStack(suit, p32);
			break;
		case 6:
			side   = new ThreeStack(suit, p33);
			side2  = new ThreeStack(suit, p33);
			break;
		case 7:
			center = new TwoStack(suit, p21);
			side   = new ThreeStack(suit, p33);
			side2  = new ThreeStack(suit, p33);
			break;
		case 8:
			center = new TwoStack(suit, p22);
			side   = new ThreeStack(suit, p33);
			side2  = new ThreeStack(suit, p33);
			break;
		case 9:
			center = new ThreeStack(suit, p31);
			side   = new FourStack(suit, p44);
			side2  = new FourStack(suit, p44);
			break;
		case 10:
			center = new TwoStack(suit, p22);
			side   = new FourStack(suit, p44);
			side2  = new FourStack(suit, p44);
			break;
		}
		try { this.add(center, 16, 0); } catch(NullPointerException e) {}
		try {
			this.add(side, 0, 0);
			this.add(side2, 32, 0);
		} catch(NullPointerException e) {}
	}
}

abstract class GLabelStack extends GCompound {
	private final int SIZE = 28;
	public GLabel[] pos = new GLabel[4];
	
	public GLabelStack(int suit, boolean[] positions, int plusY, int padY) {
		String suitSymbol = Card.decode_suit_symbol(suit);
		Color c = CardView.decodeColor(suit);
		int i = 0;
		int y = padY;
		Font f  = new Font("Arial", Font.PLAIN, SIZE);
		for(boolean p : positions) {
			if(p) {
				pos[i] = new GLabel(suitSymbol);
				pos[i].setFont(f);
				pos[i].setColor(c);
				this.add(pos[i], 0, y);
				i++;
			}
			y += plusY;
		}
	}
	
	public GLabelStack() {}
}

class EmptyStack extends GLabelStack {}

class TwoStack extends GLabelStack {
	public TwoStack(int suit, boolean[] positions) {
		super(suit, positions, 30, 20);
	}
}

class ThreeStack extends GLabelStack {
	public ThreeStack(int suit, boolean[] positions) {
		super(suit, positions, 40, 0);
	}
}

class FourStack extends GLabelStack {
	public FourStack(int suit, boolean[] positions) {
		super(suit, positions, 26, 0);
	}
}