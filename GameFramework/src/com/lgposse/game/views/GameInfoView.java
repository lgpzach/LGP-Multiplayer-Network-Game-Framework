package com.lgposse.game.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lgposse.common.Colors;
import com.lgposse.common.Fonts;
import com.lgposse.game.models.Game;

public class GameInfoView extends JPanel implements View {
	private static final long serialVersionUID = 8620274785292705112L;
	protected Game game;
	
	protected JPanel infoPanel;
	protected JPanel iconPanel;
	
	protected ImageIcon icon;
	protected JLabel iconLabel;
	
	protected JLabel lblName;
	protected JLabel lblType;
	protected JLabel lblPlayerCount;
	protected JLabel lblState;
	
	protected JButton btnJoinGame;
	
	
	public GameInfoView() {
		this.setLayout(new BorderLayout(5,0));
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		iconPanel = new JPanel();
		
		lblName = new JLabel();
		lblName.setFont(Fonts.TITLE);
		
		lblType = new JLabel();
		lblType.setFont(Fonts.SUBTITLE);
		
		lblPlayerCount = new JLabel();
		lblPlayerCount.setFont(Fonts.BODY_BOLD);
		
		lblState = new JLabel();
		lblState.setFont(Fonts.BODY_BOLD);
		
		btnJoinGame = new JButton("Join Game");
		btnJoinGame.setFont(Fonts.LOBBY_BUTTON);
		btnJoinGame.setBackground(Colors.DARK_GREEN);
		btnJoinGame.setForeground(Color.white);
		
		try {
			icon = new ImageIcon(new URL("http://j-cards-lobby.lgposse.com/images/cardgame.gif"));
		} catch (MalformedURLException e) { e.printStackTrace(); icon = null; }
		iconLabel = new JLabel(icon);
		iconLabel.setOpaque(true);
		iconLabel.setBorder(new EmptyBorder(0,5,0,5));
		
		iconPanel.add(iconLabel);
		iconPanel.setPreferredSize(new Dimension(125,118));
		
		lblName.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblType.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblState.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblPlayerCount.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		infoPanel.add(lblName);
		infoPanel.add(lblType);
		infoPanel.add(lblState);
		infoPanel.add(lblPlayerCount);
		
		this.add(iconPanel, BorderLayout.WEST);
		this.add(infoPanel, BorderLayout.CENTER);
		this.add(btnJoinGame, BorderLayout.SOUTH);
	}
	
	public GameInfoView(Game game) {
		super();
		this.setGame(game);
	}


	@Override
	public void update() {
		lblName.setText(game.name);
		
		lblType.setText(game.type);
		
		lblPlayerCount.setText("Players: " + Integer.toString(game.players.size()) + 
				" / " + Integer.toString(game.maxPlayers));
		if(game.players.size() == game.maxPlayers) {
			btnJoinGame.setEnabled(false);
			btnJoinGame.setBackground(Color.DARK_GRAY);
			btnJoinGame.setText("Full");
			lblPlayerCount.setForeground(Color.red);
		} else {
			btnJoinGame.setEnabled(true);
			btnJoinGame.setBackground(Colors.DARK_GREEN);
			btnJoinGame.setText("Join Game");
			lblPlayerCount.setForeground(Color.black);
		}
		
		lblState.setText(game.state.toString());
		switch(game.state) {
		case JOINABLE:
			lblState.setForeground(new Color(0,100,0));
			break;
		case IN_PROGRESS:
		case FINISHED:
			lblState.setForeground(Color.red);
			break;
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
		this.update();
	}
}
