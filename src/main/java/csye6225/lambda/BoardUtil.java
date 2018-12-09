package csye6225.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class BoardUtil {

    public static BoardModel add(BoardModel bm) {
        AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(bm);
        return bm;
    }
}
