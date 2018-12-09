package csye6225.lambda;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import java.util.HashMap;
import java.util.List;

public class HelloWorldLambda implements RequestHandler<DynamodbEvent, String> {

    @Override
    public String handleRequest(DynamodbEvent dynamodbEvent, Context context) {
        for (DynamodbStreamRecord record : dynamodbEvent.getRecords()) {
            String courseId = record.getDynamodb().getNewImage().get("courseId").getS();
            String announcementText = record.getDynamodb().getNewImage().get("announcementText").getS();

            CourseModel cm = CourseUtil.get(courseId);

            //create a new SNS client and set endpoint
            AmazonSNSClient snsClient = new AmazonSNSClient();
            snsClient.setRegion(Region.getRegion(Regions.US_EAST_2));

            String topicArn = cm.getTopic();

            //publish to an SNS topic
            PublishRequest publishRequest = new PublishRequest(topicArn, announcementText);
            PublishResult publishResult = snsClient.publish(publishRequest);
            //print MessageId of message published to SNS topic
            System.out.println("MessageId - " + publishResult.getMessageId());
        }
        return "published";
    }
}
