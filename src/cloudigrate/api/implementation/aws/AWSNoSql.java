/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.implementation.aws;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.ComparisonOperator;
import com.amazonaws.services.dynamodb.model.Condition;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.PutItemResult;
import com.amazonaws.services.dynamodb.model.ScanRequest;
import com.amazonaws.services.dynamodb.model.ScanResult;
import com.amazonaws.auth.AWSCredentials;


public class AWSNoSql {

		// We need DynamoDB to communicate with actual AWS Console 
		AmazonDynamoDBClient dynamoDB = null;
		AWSCredentials credentials = null;

		// Constructor that initializes the AWS DynamoDB component - See InitializeAWS.java for more details
		@SuppressWarnings("deprecation")
		public AWSNoSql()
		{
			credentials = InitializeAWS.getCredentials();
			dynamoDB = new AmazonDynamoDBClient(credentials);
			Region usWest2 = Region.getRegion(Regions.US_WEST_1);
			dynamoDB.setRegion(usWest2);
		}

		@SuppressWarnings("deprecation")
		public void insertItem(String item1, String tableName) {
			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
			item.put("CustomerId", new AttributeValue().withS("8965"));
			@SuppressWarnings("deprecation")
			PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
			@SuppressWarnings("deprecation")
			PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);	
		}

		public void getItem(String tableName, String attributeName,
				String attributeValue) {
			HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
			@SuppressWarnings("deprecation")
			Condition condition = new Condition().withComparisonOperator(
			ComparisonOperator.GT.toString()).withAttributeValueList(
					new AttributeValue().withN(attributeValue));
			scanFilter.put(attributeName, condition);
			ScanRequest scanRequest = new ScanRequest(tableName)
					.withScanFilter(scanFilter);
			ScanResult scanResult = dynamoDB.scan(scanRequest);
			
		}
}
