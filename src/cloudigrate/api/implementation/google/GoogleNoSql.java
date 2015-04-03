/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.implementation.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import cloudigrate.api.facade.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.google.api.services.datastore.DatastoreV1.BeginTransactionRequest;
import com.google.api.services.datastore.DatastoreV1.BeginTransactionResponse;
import com.google.api.services.datastore.DatastoreV1.CommitRequest;
import com.google.api.services.datastore.DatastoreV1.Entity;
import com.google.api.services.datastore.DatastoreV1.Key;
import com.google.api.services.datastore.DatastoreV1.LookupRequest;
import com.google.api.services.datastore.DatastoreV1.LookupResponse;
import com.google.api.services.datastore.DatastoreV1.Property;
import com.google.api.services.datastore.DatastoreV1.Value;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreOptions;
import com.google.protobuf.ByteString;

public class GoogleNoSql {

	private static final String APPLICATION_DATASTORE_DATASET = "cloudigratenosql";
	private static Datastore datastore = null;
	
	public GoogleNoSql() throws GeneralSecurityException, IOException
	{
		DatastoreOptions.Builder options = new DatastoreOptions.Builder();
		options.dataset(APPLICATION_DATASTORE_DATASET);

		com.google.api.services.datastore.client.DatastoreOptions.Builder bd = options
				.credential(InitializeGoogle.getCredentials());
		// Get datastore object
		datastore = DatastoreFactory.get().create(bd.build());
	}
	
	public void insertItem(String item, String tableName) throws DatastoreException {
		// Google write code
				BeginTransactionRequest.Builder treq = BeginTransactionRequest.newBuilder();
				// Execute the RPC synchronously.
				BeginTransactionResponse tres = datastore.beginTransaction(treq.build());
				// Get the transaction handle from the response.
				ByteString tx = tres.getTransaction();
				// Create an RPC request to get entities by key.
				LookupRequest.Builder lreq = LookupRequest.newBuilder();
				// Set the entity key with only one `path_element`: no parent.
				Key.Builder key = null;
				// entity at the time the transaction started.
						lreq.getReadOptionsBuilder().setTransaction(tx);
						// Execute the RPC and get the response.
						LookupResponse lresp = datastore.lookup(lreq.build());
						// Create an RPC request to commit the transaction.
						CommitRequest.Builder creq = CommitRequest.newBuilder();
						// Set the transaction to commit.
						creq.setTransaction(tx);
						com.google.api.services.datastore.DatastoreV1.Entity entity;
						// If no entity was found, create a new one.
						Entity.Builder entityBuilder = Entity.newBuilder();
						
				//for (Map<String, AttributeValue> attributeList : scanResult.getItems()) {
		            //System.out.println("");
		            //Map.Entry<String, AttributeValue> keyEntry = attributeList.get("CustomerID");
		            //AttributeValue keyValue = keyEntry.getValue();
		            //System.out.println(attributeList.get("CustomerID").getS());
		            key = Key.newBuilder().addPathElement(Key.PathElement.newBuilder().setKind("Trivia").setName("CustomerID"));
		       	 	entityBuilder.setKey(key);
		            
		           //for (Map.Entry<String, AttributeValue> item : attributeList.entrySet())
		            {
		                String attributeName = item;
		                String value = item;
		                
		                if(!attributeName.equals("CustomerID")){
		            	 // Do Nothing
		             }
		             else
		            	 entityBuilder.addProperty(Property.newBuilder().setName(attributeName).setValue(Value.newBuilder().setStringValue(value)));
		            }
		    		// Build the entity.
		    		entity = entityBuilder.build();
		    		// Insert the entity in the commit request mutation.
		    		creq.getMutationBuilder().addInsert(entity);
	}

	public void getItem(String tableName, String attributeName,
			String attributeValue) throws DatastoreException {
		// Google Read Code
		// Create an RPC request to begin a new transaction.
		BeginTransactionRequest.Builder treq = BeginTransactionRequest.newBuilder();

		// Execute the RPC synchronously.
		BeginTransactionResponse tres = datastore
				.beginTransaction(treq.build());
		// Get the transaction handle from the response.
		ByteString tx = tres.getTransaction();

		// Create an RPC request to get entities by key.
		LookupRequest.Builder lreq = LookupRequest.newBuilder();
		// Set the entity key with only one `path_element`: no parent.
		
		Key.Builder key = Key.newBuilder().addPathElement(
				Key.PathElement.newBuilder().setKind("Trivia").setName("jyllt"));
		
		//Key.Builder k = Key.newBuilder().addPathElement(Key.PathElement.newBuilder().setKind(value))
		// Add one key to the lookup request.
		lreq.addKey(key);
		// Set the transaction, so we get a consistent snapshot of the
		// entity at the time the transaction started.
		lreq.getReadOptionsBuilder().setTransaction(tx);
		// Execute the RPC and get the response.
		LookupResponse lresp = datastore.lookup(lreq.build());
		// Create an RPC request to commit the transaction.
		CommitRequest.Builder creq = CommitRequest.newBuilder();
		// Set the transaction to commit.
		creq.setTransaction(tx);
		com.google.api.services.datastore.DatastoreV1.Entity entity;
		//Query q = new Query(Entities.NAMESPACE_METADATA_KIND);
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		
		if (lresp.getFoundCount() > 0) {
			entity = lresp.getFound(0).getEntity();
			
			item.put("CustomerId", new AttributeValue().withS("8965"));
			for(int i=0; i<entity.getPropertyCount();i++)
			{
					item.put(entity.getProperty(i).getName(), new AttributeValue().withS(entity.getProperty(i).getValue().getStringValue()));
			}
		} else {
			System.out.println("Item not found in Google cloud store");
		}
	}

}
