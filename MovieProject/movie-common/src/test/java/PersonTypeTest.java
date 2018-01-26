import com.mqul.mp.PersonType;
import com.mqul.mp.QueryBuilder;
import com.mqul.mp.TransferableUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonTypeTest {

    @Test
    public void typeToCaseSensitiveStringTest()
    {
        PersonType personType = PersonType.ACTOR;
        final String expectedActorValue = "Actor";

        assertEquals(expectedActorValue, personType.toString());

        personType = PersonType.DIRECTOR;
        final String expectedDirectorValue = "Director";

        assertEquals(expectedDirectorValue, personType.toString());
    }
}
