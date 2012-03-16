package com.lgposse.game.app;

import com.lgposse.game.controllers.LobbyControl;
import com.lgposse.game.controllers.PlayerControl;
import com.lgposse.game.database.GameDatabase;
import com.lgposse.game.database.GameList;
import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;
import com.lgposse.game.net.NetControl;
import com.lgposse.net.common.ObjectListener;

/**
 * The top level container for all games.
 * This class isn't a frame or a panel. In fact, it doesn't have any UI at all.
 * 
 * Components:
 * 	- Player: who you are.
 * 	- Database: what you need to interact with the server.
 * 
 * 	- UserControl(this): get the user object
 * 	- LobbyControl(this): created once we have a user. Starts next phase of operation.
 * 
 * @author Zach
 *
 */
public abstract class GameApp implements Runnable, ObjectListener {
	
	public GameContainer gameContainer; // the main frame or applet in which everything shall run
	
	public Player player;
	public GameDatabase database;
	
	public PlayerControl playerControl;
	public LobbyControl lobbyControl;

	public NetControl netControl; // set to phase out database
	
	public GameApp(GameContainer gameContainer) {
		this.gameContainer = gameContainer;
		this.playerControl = new PlayerControl(this);
		this.gameContainer.setActiveComponent(this.playerControl);
		this.netControl = new NetControl(this, "localhost", 36777);
	}

	public void run() {
		playerControl = new PlayerControl(this);
	}
	
	public abstract void gotPlayer(Player player);
	public abstract void gotGame(Game game);
	
	public abstract boolean canMove();
	
	public void gotObject(Object o) {
		// set to phase out database
		if(o instanceof Game) this.gotGame((Game) o);
		else if(o instanceof GameList) {
			GameList gameList = (GameList) o;
			System.out.println(this.lobbyControl);
			this.lobbyControl.gotGameList(gameList);
		}
		else if(o instanceof Player) this.gotPlayer((Player) o);
	}
}
