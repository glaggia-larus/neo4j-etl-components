package org.neo4j.etl.provisioning.platforms;

import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;

import org.neo4j.etl.provisioning.Server;

class StackHandle implements Server
{
    private final String ipAddress;
    private final AmazonCloudFormation cloudFormation;
    private final String stackName;
    private TestType testType;

    StackHandle( String ipAddress, AmazonCloudFormation cloudFormation, String stackName, TestType testType )
    {
        this.ipAddress = ipAddress;
        this.cloudFormation = cloudFormation;
        this.stackName = stackName;
        this.testType = testType;
    }

    @Override
    public String ipAddress()
    {
        return ipAddress;
    }

    @Override
    public void close() throws Exception
    {
        cloudFormation.deleteStack( new DeleteStackRequest().withStackName( stackName ) );
    }
}
