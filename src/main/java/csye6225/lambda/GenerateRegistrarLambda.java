package csye6225.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class GenerateRegistrarLambda implements RequestHandler<CourseEvent, CourseEvent> {
    @Override
    public CourseEvent handleRequest(CourseEvent courseEvent, Context context) {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("http://Assignment1-env.fkmahjfmsh.us-east-2.elasticbeanstalk.com/rest/registerOffering");
            StringEntity params =new StringEntity("{\"registrationId\":\""+courseEvent.getCourseId()+
                    "\",\"offeringId\":\""+courseEvent.getCourseId()+
                    "\",\"offeringType\":\"Course\",\"department\":\""+courseEvent.getDepartment()+
                    "\",\"perUnitPrice\":15}");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            System.out.println(response);

            //handle response here...

        }catch (Exception ex) {

            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown();
        }
        return courseEvent;
    }
}