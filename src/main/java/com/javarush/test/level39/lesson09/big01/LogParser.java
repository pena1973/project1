package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private final Path logDir;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    // IPQuery
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            set.add(record.ip);
        }
        return set.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            set.add(record.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.user.equals(user))
                set.add(record.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.event == event)
                set.add(record.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.status == status)
                set.add(record.ip);
        }

        return set;
    }

    //UserQuery
    @Override
    public Set<String> getAllUsers()
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(null, null);
        for (Record record : list)
        {

            set.add(record.user);
        }

        return set;

    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(null, null);
        for (Record record : list)
        {

            set.add(record.user);
        }

        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        int count = 0;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.user.equals(user))
                count++;
        }
        return count;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.ip.equals(ip))
                set.add(record.user);
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.event == Event.LOGIN)
                set.add(record.user);
        }
        return set;

    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.event == Event.DOWNLOAD_PLUGIN)
                set.add(record.user);
        }
        return set;


    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.event == Event.WRITE_MESSAGE)
                set.add(record.user);
        }
        return set;


    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.event == Event.SOLVE_TASK)
                set.add(record.user);
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if ((record.event == Event.SOLVE_TASK) && (record.nomTask == task))
                set.add(record.user);
        }
        return set;

    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.event == Event.DONE_TASK)
                set.add(record.user);
        }
        return set;

    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> set = new HashSet<String>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if ((record.event == Event.DONE_TASK) && (record.nomTask == task))
                set.add(record.user);
        }
        return set;

    }

    //DateQuery
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        Set<Date> set = new HashSet<Date>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if ((record.event == event) && (record.user.equals(user)))
                set.add(record.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        Set<Date> set = new HashSet<Date>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.status == Status.FAILED)
                set.add(record.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        Set<Date> set = new HashSet<Date>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if (record.status == Status.ERROR)
                set.add(record.date);
        }
        return set;

    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        Date dateFirst = new Date(Long.MAX_VALUE);
        boolean find = false;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if ((record.event == Event.LOGIN) && (record.user.equals(user)))
            {
                dateFirst = dateFirst.before(record.date) ? dateFirst : record.date;
                find = true;
            }
        }
        return find ? dateFirst : null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        Date dateFirst = new Date(Long.MAX_VALUE);
        boolean find = false;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if ((record.event == Event.SOLVE_TASK) && (record.nomTask == task) && (record.user.equals(user)))
            {
                dateFirst = dateFirst.before(record.date) ? dateFirst : record.date;
                find = true;
            }
        }
        return find ? dateFirst : null;

    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {

        Date dateFirst = new Date(Long.MAX_VALUE);
        boolean find = false;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if ((record.event == Event.DONE_TASK) && (record.nomTask == task) && (record.user.equals(user)))
            {
                dateFirst = dateFirst.before(record.date) ? dateFirst : record.date;
                find = true;
            }
        }
        return find ? dateFirst : null;

    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        Set<Date> set = new HashSet<Date>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if ((record.event == Event.WRITE_MESSAGE) && (record.user.equals(user)))
                set.add(record.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        Set<Date> set = new HashSet<Date>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {

            if ((record.event == Event.DOWNLOAD_PLUGIN) && (record.user.equals(user)))
                set.add(record.date);
        }
        return set;

    }

    //EventQuery
    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            set.add(record.event);
        }
        return set.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            set.add(record.event);
        }
        return set;

    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.ip.equals(ip))
                set.add(record.event);
        }
        return set;

    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.user.equals(user))
                set.add(record.event);
        }
        return set;


    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.status == Status.FAILED)
                set.add(record.event);
        }
        return set;


    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<Event>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.status == Status.ERROR)
                set.add(record.event);
        }
        return set;

    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if ((record.event == Event.SOLVE_TASK) && (record.nomTask == task))
                count++;
        }
        return count;

    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if ((record.event == Event.DONE_TASK) && (record.nomTask == task))
                count++;
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.event == Event.SOLVE_TASK)
                map.put(record.nomTask, map.containsKey(record.nomTask) ? map.get(record.nomTask) + 1 : 1);
        }
        return map;

    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Record> list = getRecords(after, before);
        for (Record record : list)
        {
            if (record.event == Event.DONE_TASK)
                map.put(record.nomTask, map.containsKey(record.nomTask) ? map.get(record.nomTask) + 1 : 1);
        }
        return map;
    }

    @Override
    public Set<Object> execute(String query)
    {
        Set<Object> set = new HashSet<>();
        List<Record> list = getRecords(null, null);
        // get field1 for field2 = "value1" and date between "value2" and "value3"
        Date after = null;
        Date before = null;
        String query1 = "";
        String query2 = "";

        if (query.contains("between")) // отрежу от квери даты
        {
            int startDateBetween = query.indexOf("and date between");
            query1 = query.substring(0, startDateBetween - 1);
            query2 = query.substring(startDateBetween, query.length());
            String[] d = query2.split(" ");

            String afterStr = d.length > 7 ? (d[3] + " " + d[4]).replaceAll("\"", "") : "";
            String beforeStr = d.length > 7 ? (d[6] + " " + d[7]).replaceAll("\"", "") : "";

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            try
            {
                after = sdf.parse(afterStr);
                before = sdf.parse(beforeStr);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

        } else query1 = query;

        String[] s = query1.split(" ");
        String field1 = s[1];
        String field2 = s.length > 2 ? s[3] : "";
        String valueStr = "";

        if (field2.equals("user")) valueStr = s.length > 7 ? (s[5] + " " + s[6] + " " + s[7]).replaceAll("\"", "") : "";
        else if (field2.equals("date")) valueStr = s.length > 6 ? (s[5] + " " + s[6]).replaceAll("\"", "") : "";
        else valueStr = s.length > 4 ? s[5].replaceAll("\"", "") : "";

        Object value = "";
        switch (field2)
        {
            case "ip":
                value = valueStr;
                break;
            case "user":
                value = valueStr;
                break;
            case "date":
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                try
                {
                    value = sdf.parse(valueStr);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                break;
            case "event":
                value = Event.valueOf(valueStr);
                break;
            case "status":
                value = Status.valueOf(valueStr);//
                break;
        }
        return getResult(field1, field2, value, after, before);
    }

    private Set<Object> getResult(String field1, String field2, Object value, Date after, Date before)
    {
        Set<Object> set = new HashSet<>();
        List<Record> list = getRecords(after, before);

        switch (field1)
        {
            case "ip":
                for (Record record : list)
                {
                    if (!field2.isEmpty())
                    {
                        if ((field2.equals("user") && (record.user.equals(value)))
                                || (field2.equals("date") && (record.date.equals(value)))
                                || (field2.equals("event") && (record.event == value))
                                || (field2.equals("status") && (record.status == value)))
                        {
                            set.add(record.ip);
                        }
                    } else set.add(record.ip);
                }
                return set;

            case "user":

                for (Record record : list)
                {
                    if (!field2.isEmpty())
                    {
                        if ((field2.equals("ip") && (record.ip.equals(value)))
                                || (field2.equals("date") && (record.date.equals(value)))
                                || (field2.equals("event") && (record.event == value))
                                || (field2.equals("status") && (record.status == value)))
                        {
                            set.add(record.user);
                        }
                    } else set.add(record.user);
                }
                return set;
            case "date":

                for (Record record : list)
                {
                    if (!field2.isEmpty())
                    {
                        if ((field2.equals("ip") && (record.ip.equals(value)))
                                || (field2.equals("user") && (record.user.equals(value)))
                                || (field2.equals("event") && (record.event == value))
                                || (field2.equals("status") && (record.status == value)))
                        {
                            set.add(record.date);
                        }
                    } else set.add(record.date);
                }
                return set;
            case "event":
                for (Record record : list)
                {
                    if (!field2.isEmpty())
                    {
                        if ((field2.equals("ip") && (record.ip.equals(value)))
                                || (field2.equals("user") && (record.user.equals(value)))
                                || (field2.equals("date") && (record.date.equals(value)))
                                || (field2.equals("status") && (record.status == value)))
                        {
                            set.add(record.event);
                        }
                    } else set.add(record.event);
                }
                return set;
            case "status":
                for (Record record : list)
                {
                    if (!field2.isEmpty())
                    {
                        if ((field2.equals("ip") && (record.ip.equals(value)))
                                || (field2.equals("user") && (record.user.equals(value)))
                                || (field2.equals("date") && (record.date.equals(value)))
                                || (field2.equals("event") && (record.event == value)))
                        {
                            set.add(record.status);
                        }
                    } else set.add(record.status);
                }
                return set;
            default:
                return null;
        }
    }

    // мои методы и классы
    private class Record
    {
        private String ip = "";    // ip адрес с которого пользователь произвел событие.
        private String user = ""; //- имя пользователя (одно или несколько слов разделенные пробелами).
        private Date date;       // - дата события в формате day.month.year hour:minute:second
        private Event event;
        private int nomTask;
        private Status status;   //
    }

    private List<Record> getRecords(Date after, Date before)
    {
        final File dir = new File(logDir.toString());
        FilenameFilter filter = new FilenameFilter()
        {
            public boolean accept(File directory, String fileName)
            {
                return fileName.endsWith(".log");
            }
        };

        List<Record> list = new ArrayList<Record>();

        if (dir.listFiles(filter) == null) return list;

        for (File file : dir.listFiles(filter))
        {
            if (file.isFile())
            {
                String strLine;
                try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath())))
                {
                    while ((strLine = reader.readLine()) != null)
                    {
                        Date recordDate = null;
                        String[] s = strLine.split("\\t");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        try
                        {
                            recordDate = sdf.parse(s[2]);
                        }
                        catch (ParseException e)
                        {
                            e.printStackTrace();
                        }

                        if ((after != null) && !(recordDate.after(after) || recordDate.equals(after)))
                        {
                            continue;

                        } else if ((before != null) && !(recordDate.before(before) || recordDate.equals(before)))
                        {
                            continue;
                        }
                        Record record = new Record();
                        record.ip = s[0];
                        record.user = s[1];
                        record.date = recordDate;
                        String[] events = s[3].split(" ");
                        record.event = Event.valueOf(events[0]);
                        record.nomTask = events.length > 1 ? Integer.parseInt(events[1]) : 0;
                        record.status = Status.valueOf(s[4]);
                        list.add(record);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
