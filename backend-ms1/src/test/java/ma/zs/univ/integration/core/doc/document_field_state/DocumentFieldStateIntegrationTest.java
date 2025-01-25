package ma.zs.univ.integration.core.doc.document-field-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentFieldStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentFieldStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
