package ui.client;

public class GameList {
	
	// �ν��Ͻ� ���� ����
	private String game_name;
	private int game_price;
	
	// ������1
	public GameList() {
		
		
	}
	
	// ������2
	public GameList(String game_name, int game_price){
		this.game_name = game_name;
		this.game_price = game_price;
	}

	// getter, setter
	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public int getGame_price() {
		return game_price;
	}

	public void setGame_price(int game_price) {
		this.game_price = game_price;
	}
	
	
	
}
