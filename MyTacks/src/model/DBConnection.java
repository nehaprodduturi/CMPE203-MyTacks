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
	public String createTack(String url, String userName, String imageFile,String imagePath) throws IOException {
		System.out.println("in DB Tack");
		/*BasicDBObject findQuery = new BasicDBObject("userName", userName);
		BasicDBList db1=new BasicDBList();
		//db1.add(new BasicDBObject("tack",url));
		BasicDBObject update = new BasicDBObject();
		update.put("$push", db1.add(new BasicDBObject("tack",url)));
		collection.update(findQuery,update,true,true);*/

		BasicDBObject updateQuery = new BasicDBObject("userName", userName);
		//updateQuery.put( "userName", userName );
		BasicDBObject updateCommand = new BasicDBObject();
		BasicDBObject tack=new BasicDBObject();
		tack.put("tack",imageFile);
		tack.put("imageURL",imagePath);
		BasicDBObject tacks=new BasicDBObject();
		tacks.put("tacks", tack);
		// updateCommand.put( "$push", new BasicDBObject( "tack",url ));
		//updateCommand = new BasicDBObject();
		updateCommand.put("$push",tacks);

		//GridFS gfs=
		/*GridFS gfsPhoto = new GridFS(db, "photo");
		GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
		gfsFile.setFilename(userName+""+url);
		//gfsFile.save();*/
		WriteResult result = collection.update( updateQuery, updateCommand, true, true );

		/*	DBCursor doc=collection.find(findQuery);
		System.out.println("doc.getQuery()"+doc.getQuery());
		System.out.println("document"+doc);*/

		//System.out.println("db1"+db1);
		//collection.findAndModify(doc.getQuery(), db1);

		return url;


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
	public ArrayList<TackModel> getTackDetailsByUserAndBoard(String userName, String boardName) {
		TackModel tacks = null;
		ArrayList<TackModel> tacksList = new ArrayList<TackModel>();
		BasicDBObject findQuery = new BasicDBObject("userName", userName);
		//append("boardName", boardName);
		int counter=0;
		DBCursor docs = collection.find(findQuery);
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
				System.out.println("List Item"+tackListItemObject.getString("imageURL"));
				tacks.setTackURL(tackListItemObject.getString("imageURL").toString());
				tacks.setTackName(tackListItemObject.getString("tack").toString());
				//System.out.println("DB Object"+tackListItemObject.getString("imageURL").toString());
				//System.out.println("-------"+tacks.getTackURL());
				tacksList.add(tacks);
			}

			//tacks.setTackURL(doc.get("tack").toString());
			//tacks.setTackName(doc.get("tack").toString());
			//tacks.setDescription(doc.get("userName").toString());
			//System.out.println("Image URL"+doc.get("imageURL").toString());

			//System.out.println("doc.hasNext"+counter);
		}
		return tacksList;
	}

}
