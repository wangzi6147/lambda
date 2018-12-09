package csye6225.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;

public class CourseUtil {

    public static CourseModel get(String id) {

        AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);

        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",  new AttributeValue().withS(id));

        DynamoDBQueryExpression<CourseModel> queryExpression = new DynamoDBQueryExpression<CourseModel>()
                .withIndexName("courseId-index")
                .withKeyConditionExpression("courseId = :v1")
                .withConsistentRead(false)
                .withExpressionAttributeValues(eav);

        List<CourseModel> list =  mapper.query(CourseModel.class, queryExpression);
        if (list.size() == 0) return null;
        return list.get(0);
    }

    public static CourseModel delete(String id) {

        AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        CourseModel cm = get(id);
        mapper.delete(cm, new DynamoDBDeleteExpression());
        return cm;
    }



    public static CourseModel update(CourseModel cm) {
        delete(cm.getCourseId());

        AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(cm);
        return cm;
    }
}
