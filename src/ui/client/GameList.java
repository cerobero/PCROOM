package ui.client;

public class GameList {
	
	// 인스턴스 변수 설정
	private String game_name;
	private int game_price;
	
	// 생성자1
	public GameList() {
		
		
	}
	
	// 생성자2
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
