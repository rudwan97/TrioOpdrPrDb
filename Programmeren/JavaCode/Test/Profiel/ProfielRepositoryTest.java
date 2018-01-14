package Profiel;

import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

public class ProfielRepositoryTest {


    @Test
    public void testGettersFromProfileRepositoryWithValidProfileReturnsTrue (){


        //Arrange
        Profiel profile = new Profiel("2123213", "Henk", "12-11-2017");

        //Act
        String profileName = profile.getProfileName();
        boolean getProfileNameWorks = profileName.equals("Henk");

        String subscriptionNumber = profile.getSubscriptionNumber();
        boolean getSubscriptionNumberWorks = subscriptionNumber.equals("2123213");

        Assert.assertTrue(getProfileNameWorks);
        Assert.assertTrue(getSubscriptionNumberWorks);
    }

    @Test
    public void testSettersFromProfileRepositoryWithValidProfileReturnsTrue (){

        //Arrange
        Profiel profile = new Profiel("2123213", "Henk", "12-11-2017");

        //Act
        profile.setProfileName("Frans");
        String newProfileName = profile.getProfileName();
        boolean setProfileNameWorks = newProfileName.equals("Frans");

        profile.setSubscriptionNumber("12345678");
        String newSubscriptionNumber = profile.getSubscriptionNumber();
        boolean setSubscriptionnumberWorks = newSubscriptionNumber.equals("12345678");


        Assert.assertTrue(setProfileNameWorks);
        Assert.assertTrue(setSubscriptionnumberWorks);
    }

}