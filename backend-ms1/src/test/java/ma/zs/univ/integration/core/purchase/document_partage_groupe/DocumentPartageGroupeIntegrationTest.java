package ma.zs.univ.integration.core.purchase.document-partage-groupe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentPartageGroupeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentPartageGroupeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
