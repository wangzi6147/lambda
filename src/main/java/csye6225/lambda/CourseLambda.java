package csye6225.lambda;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClient;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;

public class CourseLambda implements RequestHandler<DynamodbEvent, CourseEvent> {
    @Override
    public CourseEvent handleRequest(DynamodbEvent dynamodbEvent, Context context) {
        for (DynamodbEvent.DynamodbStreamRecord record : dynamodbEvent.getRecords()) {
            if (record.getEventName().equals("INSERT")) {
                String courseId = record.getDynamodb().getNewImage().get("courseId").getS();
                String department = record.getDynamodb().getNewImage().get("department").getS();
                CourseEvent ce = new CourseEvent(courseId, department);
                if (record.getDynamodb().getNewImage().containsKey("boardId")) {
                    ce.setBoardId(record.getDynamodb().getNewImage().get("boardId").getS());
                } else {
                    ce.setBoardId("");
                }
                System.out.println(ce);

                AWSStepFunctionsClient client = new AWSStepFunctionsClient();
                client.setRegion(Region.getRegion(Regions.US_EAST_2));
                StartExecutionRequest request = new StartExecutionRequest();
                request.setInput(ce.toString());
                request.setStateMachineArn("arn:aws:states:us-east-2:874029527438:stateMachine:CourseWorkflow");
                client.startExecution(request);
            }
        }
        return null;
    }
}
