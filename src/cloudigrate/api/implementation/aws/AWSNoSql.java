/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.implementation.aws;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
/*import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;*/
import com.amazonaws.auth.AWSCredentials;

public class AWSNoSql {

		// We need DynamoDB to communicate with actual AWS Console 
		AmazonDynamoDBClient dynamoDB = null;
//		DynamoDB dynamoDB1 = null;
		AWSCredentials credentials = null;

		// Constructor that initializes the AWS DynamoDB component - See InitializeAWS.java for more details
		@SuppressWarnings("deprecation")
		public AWSNoSql()
		{
			credentials = InitializeAWS.getCredentials();
			dynamoDB = new AmazonDynamoDBClient(credentials);
//			dynamoDB1 =  new DynamoDB(dynamoDB);
			Region usWest2 = Region.getRegion(Regions.US_WEST_1);
			dynamoDB.setRegion(usWest2);
		}

		public String insertItem(String attributeName, String attributeValue) {
			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
			
			UUID idOne = UUID.randomUUID();
			item.put("Id", new AttributeValue().withS(idOne.toString()));
			item.put(attributeName, new AttributeValue().withS(attributeValue));
			
			@SuppressWarnings("deprecation")
			PutItemRequest putItemRequest = new PutItemRequest("CloudIgrate", item);
			@SuppressWarnings("deprecation")
			PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);	
			System.out.println("Item inserted");
			return idOne.toString();
		}

		public List<Map<String, AttributeValue>> getItem(String keyValue) {
			HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
			@SuppressWarnings("deprecation")
			Condition condition = new Condition().withComparisonOperator(
			ComparisonOperator.GT.toString()).withAttributeValueList(
					new AttributeValue().withS(keyValue));
			scanFilter.put("Id", condition);
			ScanRequest scanRequest = new ScanRequest("CloudIgrate")
					.withScanFilter(scanFilter);
			ScanResult scanResult = dynamoDB.scan(scanRequest);
			List<Map<String, AttributeValue>> attributes = scanResult.getItems();
			return attributes;
		}
		
		/*public void deleteItem(String tableName, String attributeName, String attributeValue)
		{
			 //Table table = (tableName);
			Table table = dynamoDB1.getTable(tableName);
			DeleteItemOutcome outcome = table.deleteItem(attributeName, attributeValue);
		}*/
}
