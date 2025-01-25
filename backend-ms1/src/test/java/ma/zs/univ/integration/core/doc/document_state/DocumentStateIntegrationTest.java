package ma.zs.univ.integration.core.doc.document-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
