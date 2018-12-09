package csye6225.lambda;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClient;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;

import java.util.HashMap;
import java.util.List;

public class GenerateBoardLambda implements RequestHandler<CourseEvent, CourseEvent> {
    @Override
    public CourseEvent handleRequest(CourseEvent courseEvent, Context context) {
        CourseModel cm = CourseUtil.get(courseEvent.getCourseId());
        cm.setBoardId(courseEvent.getCourseId()); // use courseId as boardId
        CourseUtil.update(cm);

        BoardModel bm = new BoardModel();
        bm.setBoardId(courseEvent.getCourseId());
        bm.setCourseId(courseEvent.getCourseId());
        BoardUtil.add(bm);
        return courseEvent;
    }
}