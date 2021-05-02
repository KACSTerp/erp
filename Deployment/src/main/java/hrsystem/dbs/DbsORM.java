package hrsystem.dbs;


import hrsystem.Dbs;

import interfaces.IORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;
import io.ciera.runtime.summit.types.StringUtil;

import sharedtypes.AccountEvents;
import sharedtypes.Types;


public class DbsORM extends Port<Dbs> implements IORM {

    public DbsORM( Dbs context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    public void AddInstanceAttributes( final String p_ClassName,  final String p_InstanceName,  final String p_AttributeName,  final String p_AttributeValue ) throws XtumlException {
        try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "UPDATE " + p_ClassName + " set " + p_AttributeName + " = '" + p_AttributeValue  + "' where instance = '" + p_InstanceName + "';";
                PreparedStatement preparedStmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.execute();
                System.out.println("Insterting into database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
     	}catch(Exception e) {
     		 System.out.printf( "Exception, %s, in AddInstanceAttributes()\n", e ); 
     	}
    }

    public void RelateInstances( final String p_ClassNameA,  final String p_ClassNameB,  final String p_RelationshipName,  final String p_InstanceKeyA,  final String p_InstanceKeyB ) throws XtumlException {
        int count = 0;
    	try {
     		Connection conn = null;
     		Statement stmt = null;
     		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String selectSql = "SELECT id FROM " + p_ClassNameA + " where instance = '" + p_InstanceKeyA +"'";
                ResultSet rs = stmt.executeQuery(selectSql);
                rs.next();
                int id = rs.getInt(1);
                System.out.println("Selecting from table in given database...");
                count++;
                String secondSelectSql = "SELECT id FROM " + p_ClassNameB + " where instance = '" + p_InstanceKeyB +"'";
                ResultSet secrs = stmt.executeQuery(secondSelectSql);
                secrs.next();
                int secid = secrs.getInt(1);
                System.out.println("Selecting from table in given database...");
                count++;
                String sql = "CREATE TABLE Relationship"
                		+ "(id MEDIUMINT NOT NULL AUTO_INCREMENT,"
                		+ "relationship VARCHAR(255),"
                		+ "left_class VARCHAR(255),"
                		+ "right_class VARCHAR(255),"
                		+ "PRIMARY KEY (id, relationship))"; 
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
                count++;
                String insertSql = "INSERT INTO Relationship (relationship, left_class, right_class) " +
                        "VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(insertSql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setString(1, p_RelationshipName);
                preparedStmt.setString(2, p_InstanceKeyA);
                preparedStmt.setString(3, p_InstanceKeyB);
                preparedStmt.execute();
                count++;
                System.out.println("Insterting into database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
             }
     	}catch(Exception e) {
     		 System.out.printf( "Exception, %s, in RelateInstances()\n", e );
     		 if(count == 2) {
     			try {
     	     		Connection conn = null;
     	     		Statement stmt = null;
     	     		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
     	            String user = "root";// root must match you admin user name
     	            String password = "<Password>";// add you admin password 
     	            conn = DriverManager.getConnection(url1, user, password);
     	            if (conn != null) {
     	                System.out.println("Connected to the database sqlDataBase");
     	                stmt = conn.createStatement();
     	                String selectSql = "SELECT id FROM " + p_ClassNameA + " where instance = '" + p_InstanceKeyA +"'";
     	                ResultSet rs = stmt.executeQuery(selectSql);
     	                rs.next();
     	                int id = rs.getInt(1);
     	                System.out.println("Selecting from table in given database...");
     	                
     	                String secondSelectSql = "SELECT id FROM " + p_ClassNameB + " where instance = '" + p_InstanceKeyB +"'";
     	                ResultSet secrs = stmt.executeQuery(secondSelectSql);
     	                secrs.next();
     	                int secid = secrs.getInt(1);
     	                System.out.println("Selecting from table in given database...");
     	                
     	                String insertSql = "INSERT INTO Relationship (relationship, left_class, right_class) " +
     	                        "VALUES (?, ?, ?)";
     	                PreparedStatement preparedStmt = conn.prepareStatement(insertSql,
     	                        Statement.RETURN_GENERATED_KEYS);
     	                preparedStmt.setString(1, p_RelationshipName);
     	                preparedStmt.setString(2, p_InstanceKeyA);
     	                preparedStmt.setString(3, p_InstanceKeyB);
     	                preparedStmt.execute();
     	                System.out.println("Insterting into database...");
     	                if(stmt!=null)
     	                    conn.close();
     	                if(conn!=null)
     	                    conn.close();
     	             }
     	     	}catch(Exception ex) {
     	     		 System.out.printf( "Exception, %s, in RelateInstances()\n", ex );
     	     	}
     		 }else {
     			 System.out.println("There's something wrong with input values!");
     		 }
     	}
    }

    public void AddAttributes( final String p_ClassName,  final String p_AttributeName,  final Types p_ORMType ) throws XtumlException {
        try {
            Connection conn = null;
            Statement stmt = null;
            String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
           conn = DriverManager.getConnection(url1, user, password);
           if (conn != null) {
               System.out.println("Connected to the database sqlDataBase");
               stmt = conn.createStatement();
               String sql = "SELECT count(" + p_AttributeName + ") from " + p_ClassName + ""; 
               stmt.execute(sql);

               System.out.println("Created table in given database...");
               if(stmt!=null)
                   conn.close();
               if(conn!=null)
                   conn.close();
            }
        }catch(Exception e) {
             System.out.printf( "Exception, %s, in AddAttributes()\n", e );
             try {
                Connection conn = null;
                Statement stmt = null;
                String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
                String user = "root";// root must match you admin user name
                String password = "<Password>";// add you admin password 
               conn = DriverManager.getConnection(url1, user, password);
               if (conn != null) {
                   System.out.println("Connected to the database sqlDataBase");
                   stmt = conn.createStatement();
                   String sql = "ALTER TABLE " + p_ClassName + " ADD " + p_AttributeName + " varchar(2048) NOT NULL DEFAULT '';";
                   stmt.execute(sql);
   
                   System.out.print("Table ALTERED....");
                   System.out.println("Created table in given database...");
                   if(stmt!=null)
                       conn.close();
                   if(conn!=null)
                       conn.close();
               }
             }catch(Exception ex) {
                System.out.printf( "Exception, %s, in AddAttributes()\n", ex );
             }
        }
    }

    public void ReadAccountsLog() throws XtumlException {
        try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            ArrayList<String> list = new ArrayList<String>();
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "SELECT p_Code, p_Name, p_Action FROM LOGS";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Selecting from table in given database...");
                while(rs.next()){
                    //Retrieve by column name
                	String p_Code  = rs.getString("p_Code");
                    String p_Name = rs.getString("p_Name");
                    AccountEvents p_Action = (AccountEvents) rs.getObject("p_Action");
                    
                    //Display values
                    System.out.print("p_Code: " + p_Code);
                    System.out.print(", p_Name: " + p_Name);
                    System.out.print(", p_Action: " + p_Action);
                    
                    list.add(p_Code);
                    list.add(p_Name);
                    list.add(p_Action.toString());
                    
                    Dbs.Singleton().SendAccountsLog(list);
                 }
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
    	}catch(Exception e) {
    		 System.out.printf( "Exception, %s, in ReadAccountsLog()\n", e ); 
    	}
    }

    public void ReadAssociations( final String p_ClassName ) throws XtumlException {
    	try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            ArrayList<String> list = new ArrayList<String>();
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String countSql = "SELECT COUNT(*) AS NUMBEROFCOLUMNS FROM INFORMATION_SCHEMA.COLUMNS " + 
                		"WHERE table_schema = 'HR' AND table_name = 'Relationship';";
                ResultSet countQuery = stmt.executeQuery(countSql);
                System.out.println("Counting from table in given database...");
                countQuery.next();
                int count = countQuery.getInt(1);
                System.out.println("Starting while...");
                String sql = "SELECT relationship FROM Relationship where left_class = '" + p_ClassName + "' OR right_class ='" + p_ClassName +"'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	System.out.println("ReadAssociations ===> " + rs.getString(1));
                	list.add(rs.getString(1));
                	Dbs.Singleton().SendAccountsLog(list);
                 }
                
                System.out.println("Selecting from table in given database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
    	}catch(Exception e) {
    		 System.out.printf( "Exception, %s, in ReadAssociations()\n", e ); 
    	}
    }

    public void LogAccount( final String p_Code,  final String p_Name,  final AccountEvents p_Action ) throws XtumlException {
        try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "INSERT INTO LOGS (p_Code, p_Name, p_Action) " +
                        "VALUES (?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setString(1, p_Code);
                preparedStmt.setString(2, p_Name);
                preparedStmt.setString(3, p_Action.toString());
                preparedStmt.execute();
                System.out.println("Insterting into database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
    	}catch(Exception e) {
    		 System.out.printf( "Exception, %s, in LogAccount()\n", e ); 
    	}
    }

    public void RelateClasses( final String p_ClassNameA,  final String p_ClassNameB,  final String p_RelationshipName ) throws XtumlException {
    	int count = 0;
    	try {
     		Connection conn = null;
     		Statement stmt = null;
     		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "CREATE TABLE Relationship"
                		+ "(id MEDIUMINT NOT NULL AUTO_INCREMENT,"
                		+ "relationship VARCHAR(255),"
                		+ "left_class VARCHAR(255),"
                		+ "right_class VARCHAR(255),"
                		+ "PRIMARY KEY (id, relationship))"; 
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
                count++;
                String insertSql = "INSERT INTO Relationship (relationship, left_class, right_class) " +
                        "VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(insertSql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setString(1, p_RelationshipName);
                preparedStmt.setString(2, p_ClassNameA);
                preparedStmt.setString(3, p_ClassNameB);
                preparedStmt.execute();
                count++;
                System.out.println("Insterting into database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
             }
     	}catch(Exception e) {
     		 System.out.printf( "Exception, %s, in RelateInstances()\n", e );
     		 if(count == 0) {
     			try {
     	     		Connection conn = null;
     	     		Statement stmt = null;
     	     		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
     	            String user = "root";// root must match you admin user name
     	            String password = "<Password>";// add you admin password 
     	            conn = DriverManager.getConnection(url1, user, password);
     	            if (conn != null) {
     	                System.out.println("Connected to the database sqlDataBase");
     	                stmt = conn.createStatement();
     	                
     	                String insertSql = "INSERT INTO Relationship (relationship, left_class, right_class) " +
     	                        "VALUES (?, ?, ?)";
     	                PreparedStatement preparedStmt = conn.prepareStatement(insertSql,
     	                        Statement.RETURN_GENERATED_KEYS);
     	                preparedStmt.setString(1, p_RelationshipName);
     	                preparedStmt.setString(2, p_ClassNameA);
     	                preparedStmt.setString(3, p_ClassNameB);
     	                preparedStmt.execute();
     	                System.out.println("Insterting into database...");
     	                if(stmt!=null)
     	                    conn.close();
     	                if(conn!=null)
     	                    conn.close();
     	             }
     	     	}catch(Exception ex) {
     	     		 System.out.printf( "Exception, %s, in RelateInstances()\n", ex );
     	     	}
     		 }else {
     			 System.out.println("There's something wrong with input values!");
     		 }
     	}
    }

    public void ReadInstances( final String p_ClassName ) throws XtumlException {
        try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            ArrayList<String> list = new ArrayList<String>();
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String countSql = "SELECT COUNT(*) AS NUMBEROFCOLUMNS FROM INFORMATION_SCHEMA.COLUMNS " + 
                		"WHERE table_schema = 'HR' AND table_name = '" + p_ClassName + "';";
                ResultSet countQuery = stmt.executeQuery(countSql);
                System.out.println("Counting from table in given database...");
                countQuery.next();
                int count = countQuery.getInt(1);
                System.out.println("Starting while...");
                String sql = "SELECT * FROM " + p_ClassName;
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	for(int i = 1; i <= count; i++) {
                	System.out.println("ReadInstances ===> " + rs.getString(i));
                		list.add(rs.getString(i));
                	Dbs.Singleton().SendAccountsLog(list);
                	}
                 }
                
                System.out.println("Selecting from table in given database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
    	}catch(Exception e) {
    		 System.out.printf( "Exception, %s, in ReadInstances()\n", e ); 
    	}
    }

    public void CreateInstance( final String p_ClassName,  final String p_InstanceName ) throws XtumlException {
        try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "INSERT INTO " + p_ClassName + " (instance) " +
                        "VALUES (?)";
                PreparedStatement preparedStmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setString(1, p_InstanceName);
                preparedStmt.execute();
                System.out.println("Insterting into database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
     	}catch(Exception e) {
     		 System.out.printf( "Exception, %s, in CreateInstance()\n", e ); 
     	}
    }

    public void UnrelateInstances( final String p_ClassNameA,  final String p_ClassNameB,  final String p_RelationshipName,  final String p_InstanceKeyA,  final String p_InstanceKeyB ) throws XtumlException {
    	try {
    		Connection conn = null;
    		Statement stmt = null;
    		String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
            ArrayList<String> list = new ArrayList<String>();
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database sqlDataBase");
                stmt = conn.createStatement();
                String sql = "DELETE FROM Relationship where left_class = '" + p_InstanceKeyA +"' AND right_class = '" + p_InstanceKeyB + "'";
                PreparedStatement preparedStmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStmt.execute();
                System.out.println("DELETE from table in given database...");
                if(stmt!=null)
                    conn.close();
                if(conn!=null)
                    conn.close();
            }
    	}catch(Exception e) {
    		 System.out.printf( "Exception, %s, in UnrelateInstances()\n", e ); 
    	}
    }

    public void CreateClass( final String p_Name ) throws XtumlException {
        try {
            Connection conn = null;
            Statement stmt = null;
            String url1 = "jdbc:mysql://localhost:<DatabasePort>/<DatabaseName>";// you should replace <DatabasePort> and <DatabaseName> to match your existing database note: mySQL default port is 3306
            String user = "root";// root must match you admin user name
            String password = "<Password>";// add you admin password 
           conn = DriverManager.getConnection(url1, user, password);
           if (conn != null) {
               System.out.println("Connected to the database sqlDataBase");
               stmt = conn.createStatement();
               String sql = "CREATE TABLE " + p_Name
                       + "(id MEDIUMINT NOT NULL AUTO_INCREMENT,"
                       + "instance VARCHAR(255),"
                       + "PRIMARY KEY (id, instance))"; 
               stmt.executeUpdate(sql);
               System.out.println("Created table in given database...");
               if(stmt!=null)
                   conn.close();
               if(conn!=null)
                   conn.close();
            }
        }catch(Exception e) {
             System.out.printf( "Exception, %s, in CreateClass()\n", e ); 
        }
    }



    // outbound messages
    public void SendInstances( final String p_ClassName,  final String p_AttributeName,  final String p_AttributeValue ) throws XtumlException {
        if ( satisfied() ) send(new IORM.SendInstances(p_ClassName, p_AttributeName, p_AttributeValue));
        else {
        }
    }
    public void SendAssociations( final String p_RelationshipName ) throws XtumlException {
        if ( satisfied() ) send(new IORM.SendAssociations(p_RelationshipName));
        else {
        }
    }
    public void SendAccountsLog() throws XtumlException {
        if ( satisfied() ) send(new IORM.SendAccountsLog());
        else {
        }
    }


    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case IORM.SIGNAL_NO_ADDINSTANCEATTRIBUTES:
                AddInstanceAttributes(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), StringUtil.deserialize(message.get(2)), StringUtil.deserialize(message.get(3)));
                break;
            case IORM.SIGNAL_NO_RELATEINSTANCES:
                RelateInstances(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), StringUtil.deserialize(message.get(2)), StringUtil.deserialize(message.get(3)), StringUtil.deserialize(message.get(4)));
                break;
            case IORM.SIGNAL_NO_ADDATTRIBUTES:
                AddAttributes(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), Types.deserialize(message.get(2)));
                break;
            case IORM.SIGNAL_NO_READACCOUNTSLOG:
                ReadAccountsLog();
                break;
            case IORM.SIGNAL_NO_READASSOCIATIONS:
                ReadAssociations(StringUtil.deserialize(message.get(0)));
                break;
            case IORM.SIGNAL_NO_LOGACCOUNT:
                LogAccount(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), AccountEvents.deserialize(message.get(2)));
                break;
            case IORM.SIGNAL_NO_RELATECLASSES:
                RelateClasses(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), StringUtil.deserialize(message.get(2)));
                break;
            case IORM.SIGNAL_NO_READINSTANCES:
                ReadInstances(StringUtil.deserialize(message.get(0)));
                break;
            case IORM.SIGNAL_NO_CREATEINSTANCE:
                CreateInstance(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)));
                break;
            case IORM.SIGNAL_NO_UNRELATEINSTANCES:
                UnrelateInstances(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), StringUtil.deserialize(message.get(2)), StringUtil.deserialize(message.get(3)), StringUtil.deserialize(message.get(4)));
                break;
            case IORM.SIGNAL_NO_CREATECLASS:
                CreateClass(StringUtil.deserialize(message.get(0)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "ORM";
    }

}
