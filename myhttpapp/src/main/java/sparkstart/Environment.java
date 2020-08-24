package sparkstart;


import application.MyHttpMain;
import spark.Spark;

public class Environment {

    public static String getBaseUri() {

        // return environment if want to run externally
//        if(true)
//            return "https://apichallenges.herokuapp.com";


        // if not running then start the spark
        if(!Port.inUse("localhost", 4567)) {
            //start it up
            Spark.port(4567);
            String[] args = {};

            MyHttpMain.main(args);

            // wait till running
            int maxtries = 10;
            while (!Port.inUse("localhost", 4567)) {
                maxtries--;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return "http://localhost:4567";

        // TODO: incorporate browsermob proxy and allow configuration of all
        //  requests through a proxy file to output a HAR file of all requests for later review
    }
}
