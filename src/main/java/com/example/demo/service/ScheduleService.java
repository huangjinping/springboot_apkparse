package com.example.demo.service;

import com.example.demo.utils.DateUtils;
import com.example.demo.utils.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ScheduleService {

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateDeleteTemp() {
        File savePos = new File("tempApp");
        try {
            for (File childFile : savePos.listFiles()) {
                String nameTime = childFile.getName();
                if (nameTime.length() > 10) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.strDateFormat);
                    Date parse = simpleDateFormat.parse(nameTime);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse);
                    calendar.add(Calendar.MINUTE, 5);
                    Calendar currentTime = Calendar.getInstance();
                    if (currentTime.compareTo(calendar) == 1) {
                        FileUtils.deleteDirWithPath(childFile.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateDoc() {
//        System.out.println("=====11==updateDoc===start==" + System.currentTimeMillis());
//        List<String> commands = new ArrayList<>();
//        commands.add("./json/docUpdate.sh");
//        List<String> strings = CommandLineTool.executeNewFlow(commands);
//        LogUtils.logJson(strings);
//        System.out.println("=======updateDoc===end==" + System.currentTimeMillis());
    }
}
