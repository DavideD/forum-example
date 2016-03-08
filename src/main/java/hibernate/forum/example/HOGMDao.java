package hibernate.forum.example;

import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.ogm.datastore.impl.AvailableDatastoreProvider;
import org.hibernate.search.cfg.Environment;

public class HOGMDao {

	public OgmConfiguration hogmdao(String db) {

		OgmConfiguration cfgogm = new OgmConfiguration();

		try {
			cfgogm.setProperty( OgmProperties.DATASTORE_PROVIDER, AvailableDatastoreProvider.CASSANDRA_EXPERIMENTAL.name() );
			cfgogm.setProperty( OgmProperties.DATABASE, db );
			cfgogm.setProperty( OgmProperties.HOST, "127.0.0.1:9042" );
			cfgogm.setProperty( "hibernate.search.default.directory_provider ", "ram" );
			return cfgogm;
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}
	}
}
