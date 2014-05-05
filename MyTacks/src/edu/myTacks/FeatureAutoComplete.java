package edu.myTacks;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import model.BoardModel;
import model.DBConnection;

public class FeatureAutoComplete {

	private int totalBoards;
	// private String data = "Afghanistan, Albania, Zimbabwe";
	private List<String> boards;
	DBConnection dbc;
	public String userName;
	public FeatureAutoComplete(String userName) throws UnknownHostException {
		
		this.userName=userName;
		dbc=new DBConnection("localhost");
	}

	public List<String> getData(String query) {


		String board = null;
		query = query.toLowerCase();
		ArrayList<BoardModel> allBoards=new ArrayList<BoardModel>();
		allBoards=dbc.getBoards(this.userName);
		List<String> matched = new ArrayList<String>();
		for(int i=0; i<allBoards.size(); i++) {
			board = allBoards.get(i).getBoardName().toLowerCase();
			System.out.println("ALL"+board);
			if(board.startsWith(query)) {
				matched.add(allBoards.get(i).getBoardName().toString());
				
			}
		}
		return matched;
	}
}
