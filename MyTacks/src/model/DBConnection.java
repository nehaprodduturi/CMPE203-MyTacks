package model;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class DBConnection {
	private String host=null;
	public DBCollection collection;
	DB db;
	public DBConnection(String host) throws UnknownHostException
	{
		this.host=host;
		MongoClient mc=new MongoClient(this.host,27017);
		db= mc.getDB("test");
		collection=db.getCollection("user");
	}
	public boolean insertRecord(UserModel um) {
		boolean flag=false;

		DBCursor cursor=collection.find(new BasicDBObject("userName",um.getUserName()));
		if(cursor.count()<1)
		{
			System.out.println("LastName db"+um.getLastName() );
			BasicDBObject basicdb=new BasicDBObject("userName",um.getUserName());
			basicdb.append("firstName", um.getFirstName());
			basicdb.append("lastName", um.getLastName());
			basicdb.append("email", um.getEmail());
			basicdb.append("password", um.getPassword());

			try
			{
				collection.insert(basicdb);
				flag=true;
			}
			catch(Exception e)
			{
				flag=false;
			}
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	public boolean validate(String userName, String password) {

		DBCursor cursor;
		boolean flag = false;
		BasicDBObject query = new BasicDBObject("userName", userName);
		query.append("password",password);
		cursor=collection.find(query);
		try {
			while(cursor.hasNext()) {
				System.out.println("In validate");
				System.out.println(cursor.next());
				flag=true;
			}
		}
		catch(Exception e)
		{
			flag=false;
		}
		finally {
			cursor.close();
		}
		return flag;
	}
	public void createTack(String userName, String imageFile,String imagePath,TackModel tm) throws IOException {
		System.out.println("in DB Tack");
		DBCursor cursor;
		String boardName= tm.getBoardName();
		BasicDBObject updateQuery = new BasicDBObject("userName", userName).append("boards.boardName",boardName);
		cursor=collection.find(updateQuery);
		System.out.println("cursor"+cursor);

		try {
			if(cursor.hasNext()) {
				DBObject doc = cursor.next();
				BasicDBObject updateCommand = new BasicDBObject();
				BasicDBObject tack=new BasicDBObject();
				tack.put("tack",imageFile);
				tack.put("imageURL",imagePath);
				tack.put("url",tm.getTackURL());
				tack.put("tackDescription", tm.getTackDescription());
				tack.put("boardName", boardName);
				BasicDBObject tacks=new BasicDBObject();
				tacks.put("tacks", tack);
				updateCommand.put("$push",tacks);
				collection.findAndModify(updateQuery, updateCommand);
				
			}
			else
			{
				System.out.println("No such user and board");
			}
		}
		finally
		{
			cursor.close();
		}
		/*	DBCursor doc=collection.find(findQuery);
		System.out.println("doc.getQuery()"+doc.getQuery());
		System.out.println("document"+doc);*/

		//System.out.println("db1"+db1);
		//collection.findAndModify(doc.getQuery(), db1);

		return;


	}
	public UserModel getUserDetails(String userName)
	{

		//String user="";
		UserModel usr = null;
		ArrayList<UserModel> userList = new ArrayList<UserModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userName);

		DBCursor docs = collection.find(findQuery);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			usr= new UserModel();
			//usr.setId((Integer) doc.get("id"));
			usr.setUserName(doc.get("userName").toString());
			usr.setFirstName(doc.get("firstName").toString());
			usr.setLastName(doc.get("lastName").toString());
			usr.setEmail(doc.get("email").toString());
			userList.add(usr);

		}
		return usr;

	}
	public boolean updateUser(UserModel um) {
		BasicDBObject newDocument = new BasicDBObject();
		BasicDBObject updateValues=new BasicDBObject();
		boolean flag=false;
		try
		{
			updateValues.append("firstName", um.getFirstName());
			updateValues.append("lastName", um.getLastName());
			updateValues.append("email", um.getEmail());
			newDocument.append("$set",updateValues);
			BasicDBObject searchQuery = new BasicDBObject().append("userName", um.getUserName());
			WriteResult ws=collection.update(searchQuery, newDocument);
			System.out.println("Write"+ws);
			flag=true;
		}
		catch(Exception e)
		{
			return false;
		}
		return flag;
	}
	public void createBoard(BoardModel bm, String userNameSession) {

		BasicDBObject updateQuery = new BasicDBObject("userName", userNameSession);
		BasicDBObject updateCommand = new BasicDBObject();
		BasicDBObject board=new BasicDBObject();
		DBCursor docs = collection.find(updateQuery);
		System.out.println("docs count"+(docs.count()));
		if(docs.hasNext()) 
		{
			DBObject doc = docs.next();
			board.put("boardCategory", bm.getBoardCategory());
			board.put("boardName", bm.getBoardName());
			board.put("boardDescription", bm.getBoardDescription());
			board.put("boardType", bm.getBoardType());
			// board.put("board",board);
			//board.put("imageURL",imagePath);
			BasicDBObject boards=new BasicDBObject();
			boards.put("boards", board);
			updateCommand.put("$push",boards);   
			WriteResult result = collection.update( updateQuery, updateCommand, true, true );
			//return url;
		}
	}
	public ArrayList<TackModel> getTackDetailsByUserAndBoard(String userName, String boardName) {
		TackModel tacks = null;
		ArrayList<TackModel> tacksList = new ArrayList<TackModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userName).append("tacks.boardName",boardName);
		/*BasicDBObject findQuery=new BasicDBObject();
		findQuery.put("userName", userName);
		findQuery.put("boardName",boardName);*/
		//BasicDBObject findQuery = new BasicDBObject("tacks.boardName",boardName);
		BasicDBObject andQuery = new BasicDBObject();
		/*ArrayList<BasicDBObject> findQuery = new ArrayList<BasicDBObject>();
		findQuery.add(new BasicDBObject("userName", userName));
		findQuery.add(new BasicDBObject("tacks.boardName",boardName));
		andQuery.put("$and", andQuery);*/ 

		//
		//BasicDBObject findQuery =new BasicDBObject("userName", userName).append("boards",new BasicDBObject("boards", new BasicDBObject("$elemMatch", new BasicDBObject("boardName", boardName)))); 
		//new BasicDBObject("userName", userName).append("boards", new BasicDBObject("$elemMatch", new BasicDBObject("boardName", boardName)));
		//.append("boards.boardName", boardName);
		System.out.println("findQuery"+findQuery);
		int counter=0;
		DBCursor docs = collection.find(findQuery);
		System.out.println("docs"+docs);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();

			System.out.println("doc"+doc);

			BasicDBList tackListGet = (BasicDBList)doc.get("tacks");

			//usr.setId((Integer) doc.get("id"));
			System.out.println("tackCount"+tackListGet.size());
			for(Object tackListItem: tackListGet)
			{

				tacks= new TackModel();
				BasicDBObject tackListItemObject = (BasicDBObject) tackListItem;
				if(tackListItemObject.getString("boardName").toString().equalsIgnoreCase(boardName))
				{
					tacks.setTackURL(tackListItemObject.getString("url").toString());
					tacks.setTackDescription(tackListItemObject.getString("tackDescription"));
					tacks.setTackName(tackListItemObject.getString("tack").toString());
					tacksList.add(tacks);
				}
			}

			//tacks.setTackURL(doc.get("tack").toString());
			//tacks.setTackName(doc.get("tack").toString());
			//tacks.setDescription(doc.get("userName").toString());
			//System.out.println("Image URL"+doc.get("imageURL").toString());

			//System.out.println("doc.hasNext"+counter);
		}
		return tacksList;
	}
	public ArrayList<BoardModel> getBoardsByUser(String userNameSession) {
		ArrayList<BoardModel> boardsList = new ArrayList<BoardModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userNameSession);
		int counter=0;
		BoardModel board;
		DBCursor docs = collection.find(findQuery);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println("doc"+doc);

			BasicDBList boardListGet = (BasicDBList)doc.get("boards");
			System.out.println("boardCount"+boardListGet.size());
			for(Object boardListItem: boardListGet)
			{
				board= new BoardModel();
				BasicDBObject boardListItemObject = (BasicDBObject) boardListItem;
				System.out.println("List Item"+boardListItemObject.getString("boardName"));
				board.setBoardName(boardListItemObject.getString("boardName").toString());
				board.setBoardDescription(boardListItemObject.getString("boardDescription").toString());
				board.setBoardCategory(boardListItemObject.getString("boardCategory").toString());
				board.setBoardType(boardListItemObject.getString("boardType"));
				boardsList.add(board);
			}

		}
		return boardsList;
	}
	public ArrayList<BoardModel> getBoards(String userName) {
		ArrayList<BoardModel> boardsList = new ArrayList<BoardModel>();
		BoardModel board;
		DBCursor docs = collection.find(new BasicDBObject("userName",userName));
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println("doc"+doc);

			BasicDBList boardListGet = (BasicDBList)doc.get("boards");
			System.out.println("boardCount"+boardListGet.size());
			for(Object boardListItem: boardListGet)
			{
				board= new BoardModel();
				BasicDBObject boardListItemObject = (BasicDBObject) boardListItem;
				System.out.println("List Item"+boardListItemObject.getString("boardName"));
				board.setBoardName(boardListItemObject.getString("boardName").toString());
				boardsList.add(board);
			}

		}
		return boardsList;
	}
	public ArrayList<BoardModel> getPublicBoards() {

		ArrayList<BoardModel> boardsList = new ArrayList<BoardModel>();
		BoardModel board;
		DBCursor docs = collection.find(new BasicDBObject("boards.boardType","public"));
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println("doc"+doc);

			BasicDBList boardListGet = (BasicDBList)doc.get("boards");
			System.out.println("boardCount"+boardListGet.size());
			for(Object boardListItem: boardListGet)
			{
				board= new BoardModel();
				BasicDBObject boardListItemObject = (BasicDBObject) boardListItem;
				if(boardListItemObject.getString("boardType").toString().equalsIgnoreCase("public"))
				{
				System.out.println("List Item"+boardListItemObject.getString("boardName"));
				board.setBoardName(boardListItemObject.getString("boardName").toString());
				board.setBoardDescription(boardListItemObject.getString("boardDescription").toString());
				board.setBoardCategory(boardListItemObject.getString("boardCategory").toString());
				board.setBoardType(boardListItemObject.getString("boardType"));
				boardsList.add(board);
				}
			}

		}
		return boardsList;
	}
	public ArrayList<TackModel> getTackDetailsByBoard(String boardName) {
		TackModel tacks = null;
		ArrayList<TackModel> tacksList = new ArrayList<TackModel>();
		BasicDBObject findQuery = new BasicDBObject("tacks.boardName", boardName);
		System.out.println("findQuery"+findQuery);
		DBCursor docs = collection.find(findQuery);
		System.out.println("docs"+docs);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();

			System.out.println("doc"+doc);

			BasicDBList tackListGet = (BasicDBList)doc.get("tacks");
			//System.out.println("tackCount"+tackListGet.size());
			for(Object tackListItem: tackListGet)
			{ 
				tacks= new TackModel();
				BasicDBObject tackListItemObject = (BasicDBObject) tackListItem;
				if(tackListItemObject.getString("boardName").toString().equalsIgnoreCase(boardName))
				{
					tacks.setTackURL(tackListItemObject.getString("imageURL").toString());
					tacks.setTackDescription(tackListItemObject.getString("tackDescription"));
					tacks.setTackName(tackListItemObject.getString("tack").toString());
					tacksList.add(tacks);
				}
			}

		}
		return tacksList;
	}
	public ArrayList<BoardModel> getPrivateBoardsByUser(String userNameSession) {
		ArrayList<BoardModel> boardsList = new ArrayList<BoardModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userNameSession);
		int counter=0;
		BoardModel board;
		DBCursor docs = collection.find(findQuery);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println("doc"+doc);

			BasicDBList boardListGet = (BasicDBList)doc.get("boards");
			System.out.println("boardCount"+boardListGet.size());
			for(Object boardListItem: boardListGet)
			{
				board= new BoardModel();
				BasicDBObject boardListItemObject = (BasicDBObject) boardListItem;
				if(boardListItemObject.getString("boardType").toString().equalsIgnoreCase("private"))
				{
				System.out.println("List Item"+boardListItemObject.getString("boardName"));
				board.setBoardName(boardListItemObject.getString("boardName").toString());
				board.setBoardDescription(boardListItemObject.getString("boardDescription").toString());
				board.setBoardCategory(boardListItemObject.getString("boardCategory").toString());
				board.setBoardType(boardListItemObject.getString("boardType"));
				boardsList.add(board);
				}
			}

		}
		return boardsList;
	}
	public ArrayList<BoardModel> getFavoriteBoardsByUser(String userNameSession) {
		ArrayList<BoardModel> boardsList = new ArrayList<BoardModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userNameSession);
		int counter=0;
		BoardModel board;
		DBCursor docs = collection.find(findQuery);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println("doc"+doc);

			BasicDBList boardListGet = (BasicDBList)doc.get("boards");
			System.out.println("boardCount"+boardListGet.size());
			for(Object boardListItem: boardListGet)
			{
				board= new BoardModel();
				BasicDBObject boardListItemObject = (BasicDBObject) boardListItem;
				if(boardListItemObject.getString("boardType").toString().equalsIgnoreCase("favorite"))
				{
				System.out.println("List Item"+boardListItemObject.getString("boardName"));
				board.setBoardName(boardListItemObject.getString("boardName").toString());
				board.setBoardDescription(boardListItemObject.getString("boardDescription").toString());
				board.setBoardCategory(boardListItemObject.getString("boardCategory").toString());
				board.setBoardType(boardListItemObject.getString("boardType"));
				boardsList.add(board);
				}
			}

		}
		return boardsList;
	}
	
}
