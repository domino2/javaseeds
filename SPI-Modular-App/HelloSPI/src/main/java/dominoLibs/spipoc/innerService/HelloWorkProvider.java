package dominoLibs.spipoc.innerService;

import dominoLibs.spipoc.Work;
import dominoLibs.spipoc.WorkProvider;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 02-12-2015.
 */
public class HelloWorkProvider extends WorkProvider{

    Map<URI, HelloWork> works = new HashMap<>();

    @Override
    public String getScheme() {
        return "InnerWorkProvides";
    }

    @Override
    public Work newWork(URI uri, Map<String, ?> env) {
        HelloWork work = works.get(uri);
        if (work==null) {
            work = new HelloWork();
            works.put(uri,work);
        }

        return work;

    }

    @Override
    public Work getWork(URI uri) {
        Work work = works.get(uri);
        if (work!=null) {
            return work;
        } else {
            return newWork(uri,null);
        }
    }
}
