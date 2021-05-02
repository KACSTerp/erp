package hrsystem;


import hrsystem.dbs.DbsORM;
import deployment.HRGuiController;

import io.ciera.runtime.summit.application.IApplication;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.components.Component;
import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.ArrayList;


public class Dbs extends Component<Dbs> {

    private Map<String, Class<?>> classDirectory;
    private static Dbs singleton;

    public Dbs(IApplication app, IRunContext runContext, int populationId) {
        super(app, runContext, populationId);



        classDirectory = new TreeMap<>();
        singleton = this;

    }
    public static Dbs Singleton() {
    	return singleton;
    }

    // domain functions
    public void SendAccountsLog(final ArrayList<String> list)throws XtumlException {
    	    
        try {
              HRGuiController.Singleton().SendAccountsLog(list);
          }catch(Exception e) {
              
          }  
        
    }

    public void SendAssociations( final String p_RelationshipName )throws XtumlException {
    	    
        try {
              HRGuiController.Singleton().SendAssociations(p_RelationshipName);
          }catch(Exception e) {
              
          }  
        
    }


    // relates and unrelates


    // instance selections


    // relationship selections


    // ports
    private DbsORM DbsORM;
    public DbsORM ORM() {
        if ( null == DbsORM ) DbsORM = new DbsORM( this, null );
        return DbsORM;
    }


    // utilities


    // component initialization function
    @Override
    public void initialize() throws XtumlException {

    }

    @Override
    public String getVersion() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("DbsProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version", "Unknown");
    }
    @Override
    public String getVersionDate() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("DbsProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version_date", "Unknown");
    }

    @Override
    public boolean addInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot add empty instance to population." );

        return false;
    }

    @Override
    public boolean removeInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot remove empty instance from population." );

        return false;
    }

    @Override
    public Dbs context() {
        return this;
    }

    @Override
    public Class<?> getClassByKeyLetters(String keyLetters) {
        return classDirectory.get(keyLetters);
    }

}
