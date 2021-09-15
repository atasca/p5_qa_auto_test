package stepImplementation;


import org.junit.Assert;
import util.World;

public class CommonStepImpl {


    public void checkStatus(int statusCode) {
        Assert.assertEquals(statusCode, World.getResponse().statusCode());
    }
}
