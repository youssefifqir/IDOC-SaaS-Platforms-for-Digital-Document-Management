package ma.zs.univ.integration.core.doc.document-tag;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentTagIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentTagHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
