package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Solution
{
    public static void main(String[] args)
    {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Sid927\\Desktop\\logs"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date before = null;
        Date after = null;
//        try
//        {
//            before = sdf.parse("05.01.2021 10:22:55");
//            after  = sdf.parse("05.01.2001 10:22:55");
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        }

//        System.out.println(logParser.getNumberOfUniqueIPs(before, after));
//        System.out.println(logParser.getUniqueIPs(null, new Date()));
//        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, new Date()));
//        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, new Date()));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, new Date()));
//        System.out.println(logParser.getNumberOfUsers(after, before));
//        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", after, before));
//        System.out.println(logParser.getUsersForIP("146.34.15.5", after, before));
//        System.out.println(logParser.getLoggedUsers(after, before));
//        System.out.println(logParser.getDownloadedPluginUsers( after,  before));
//        System.out.println(logParser.getWroteMessageUsers( after,  before));
//        System.out.println(logParser.getSolvedTaskUsers( after,  before));
//        System.out.println(logParser.getSolvedTaskUsers( after,  before, 48));
//        System.out.println(logParser.getDoneTaskUsers( after,  before));
//        System.out.println(logParser.getDoneTaskUsers( after,  before, 48));

//       System.out.println(logParser.getDatesForUserAndEvent( "Eduard Petrovich Morozko",  Event.LOGIN,  after,  before));
//       System.out.println(logParser.getDatesWhenSomethingFailed( after,  before));
//       System.out.println(logParser.getDatesWhenErrorHappened( after,  before));
//       System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", after, before));
//       System.out.println(logParser.getDateWhenUserSolvedTask( "Eduard Petrovich Morozko",  48,  after,  before));
//       System.out.println(logParser.getDateWhenUserDoneTask( "Eduard Petrovich Morozko",  48,  after,  before));
//       System.out.println(logParser.getDatesWhenUserWroteMessage( "Eduard Petrovich Morozko",  after,  before));
//       System.out.println(logParser.getDatesWhenUserDownloadedPlugin( "Eduard Petrovich Morozko",  after,  before));

//        System.out.println(logParser.getNumberOfAllEvents(after, before));
//        System.out.println(logParser.getAllEvents(after, before));
//        System.out.println(logParser.getEventsForIP("146.34.15.5", after, before));
//        System.out.println(logParser.getEventsForUser("Eduard Petrovich Morozko", after, before));
//        System.out.println(logParser.getFailedEvents(after, before));
//        System.out.println(logParser.getErrorEvents(after, before));
//        System.out.println(logParser.getNumberOfAttemptToSolveTask(48, after, before));
//        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(48, after, before));
//        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(after, before));
//        System.out.println(logParser.getAllDoneTasksAndTheirNumber(after, before));

        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for ip = \"146.34.15.5\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get ip for date = \"05.01.2021 02:42:58\""));
        System.out.println(logParser.execute("get ip for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get ip for status = \"FAILED\" and date between \"05.01.2001 02:42:58\" and \"05.01.2045 02:42:58\""));


    }
}
