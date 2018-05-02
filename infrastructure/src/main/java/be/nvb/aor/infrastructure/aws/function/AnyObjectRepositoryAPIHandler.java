package be.nvb.aor.infrastructure.aws.function;

import be.nvb.aor.infrastructure.aws.port.rest.SparkResources;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import spark.Spark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AnyObjectRepositoryAPIHandler implements RequestStreamHandler {
    private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    private static Context context;

    static {
        try {
            handler = SparkLambdaContainerHandler.getAwsProxyHandler();
            SparkResources.defineResources(
                    () -> {
                        return s -> {
                            if (context != null) {
                                context.getLogger().log(s);
                            }
                        };
                    });
            Spark.awaitInitialization();
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spark container", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {

        this.context = context;
        handler.proxyStream(inputStream, outputStream, context);

        // just in case it wasn't closed by the mapper
        outputStream.close();
    }
}
