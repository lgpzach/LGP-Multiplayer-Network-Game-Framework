package com.lgposse.game.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lgposse.common.Colors;
import com.lgposse.common.Fonts;
import com.lgposse.game.database.GameList;
import com.lgposse.thirdparty.JSplitPaneWithZeroSizeDivider;

public class GameListView extends JPanel implements View, ListSelectionListener {

	private static final long serialVersionUID = 3929554407457698398L;
	protected GameList gameList;
	
	protected JSplitPaneWithZeroSizeDivider splitPane;
	protected JPanel listPanel;
	protected JPanel nullInfoPanel;
	protected GameInfoView infoPanel;
	
	
	protected JList<String> games;
	protected JButton btnCreateGame;
	
	public GameListView(GameList gameList) {
		this.setLayout(new BorderLayout());
		
		this.gameList = gameList;
		
		listPanel = new JPanel();
		nullInfoPanel = new NullInfoPanel();
		
		splitPane = new  JSplitPaneWithZeroSizeDivider(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(nullInfoPanel);
		splitPane.setLeftComponent(listPanel);
		this.initializeListPanel();
		this.add(splitPane, BorderLayout.CENTER);
	}

	protected void initializeListPanel() {
		games = new JList<String>(gameList.toArray());
		games.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		games.addListSelectionListener(this);
		
		btnCreateGame = new JButton("Create Game");
		btnCreateGame.setFont(Fonts.LOBBY_BUTTON);
		btnCreateGame.setBackground(Colors.LIGHT_BLUE);
		btnCreateGame.setForeground(Color.white);
		
		listPanel.setLayout(new BorderLayout());
		listPanel.add(games, BorderLayout.CENTER);
		listPanel.add(btnCreateGame, BorderLayout.SOUTH);
	}
	
	public void update() {}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> list = (JList<?>)e.getSource();
		if(!list.isSelectionEmpty()) {
			infoPanel.setGame(gameList.games.get(list.getSelectedIndex()));
			splitPane.setRightComponent(infoPanel);
		} else {
			splitPane.setRightComponent(nullInfoPanel);
		}
	}
}

class NullInfoPanel extends JPanel {
	private static final long serialVersionUID = 6871791693995015437L;
	
	public JLabel lblSelect;
	
	public NullInfoPanel() {
		this.setLayout(new BorderLayout());
		lblSelect = new JLabel("Select a game from the left or click 'New Game'");
		lblSelect.setFont(new Font("sansserif", Font.BOLD, 22));
		this.add(lblSelect, BorderLayout.CENTER);
	}
}